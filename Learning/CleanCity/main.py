from flask import Flask, request, render_template_string
import cv2
import numpy as np
from ultralytics import YOLO
from collections import Counter
import base64

app = Flask(__name__)
model = YOLO('best.pt')  # Load once

THRESHOLD = 10  # detection count threshold

def img_to_base64(img):
    _, buffer = cv2.imencode('.jpg', img)
    return base64.b64encode(buffer).decode('utf-8')

@app.route('/', methods=['GET', 'POST'])
def detect():
    if request.method == 'POST':
        file = request.files['image']
        if not file:
            return "No file uploaded", 400

        img_bytes = file.read()
        np_arr = np.frombuffer(img_bytes, np.uint8)
        img = cv2.imdecode(np_arr, cv2.IMREAD_COLOR)  # BGR

        # Run YOLO inference
        results = model(img)
        boxes = results[0].boxes

        labels = []
        for box in boxes:
            class_id = int(box.cls[0])
            labels.append(model.names[class_id])

        label_counts = Counter(labels)
        total_count = sum(label_counts.values())

        # Draw boxes on img
        for box in boxes:
            x1, y1, x2, y2 = map(int, box.xyxy[0])
            class_id = int(box.cls[0])
            conf = float(box.conf[0])
            label = model.names[class_id]

            cv2.rectangle(img, (x1, y1), (x2, y2), (0, 255, 0), 1)
            cv2.putText(img, f'{label} {conf:.2f}', (x1, y1 - 10),
                        cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0, 255, 0), 1)

        img_rgb = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        img_base64 = img_to_base64(img_rgb)

        # Determine status and colors
        if total_count > THRESHOLD:
            status_message = "Kachara gaadi ko bulao"
            page_bg_color = "#ff4c4c"  # Red background
            status_bg_color = "#b22222"  # Darker red for status box
            text_color = "white"
        else:
            status_message = "No need"
            page_bg_color = "#4caf50"  # Green background
            status_bg_color = "#2e7d32"  # Darker green for status box
            text_color = "white"

        # Render results page with dynamic background color
        return render_template_string('''
            <html>
            <head>
              <title>YOLOv8 Detection Result</title>
              <style>
                body {
                  font-family: Arial, sans-serif;
                  margin: 0;
                  padding: 40px;
                  background-color: {{ page_bg_color }};
                  color: {{ text_color }};
                }
                .container {
                  max-width: 900px;
                  margin: auto;
                  background: rgba(255, 255, 255, 0.15);
                  border-radius: 12px;
                  padding: 30px;
                  box-shadow: 0 0 20px rgba(0,0,0,0.3);
                }
                h2 {
                  text-align: center;
                  margin-bottom: 20px;
                }
                .status {
                  background-color: {{ status_bg_color }};
                  color: {{ text_color }};
                  padding: 15px 25px;
                  font-weight: bold;
                  border-radius: 8px;
                  width: fit-content;
                  margin: 15px auto 30px auto;
                  font-size: 1.4em;
                  text-align: center;
                  box-shadow: 0 0 10px rgba(0,0,0,0.2);
                }
                .counts p {
                  margin: 5px 0;
                  font-size: 1.1em;
                }
                img {
                  display: block;
                  max-width: 100%;
                  height: auto;
                  border: 3px solid white;
                  border-radius: 10px;
                  margin: 0 auto;
                  box-shadow: 0 0 15px rgba(0,0,0,0.4);
                }
                a {
                  display: block;
                  margin-top: 30px;
                  text-align: center;
                  font-weight: bold;
                  color: {{ text_color }};
                  text-decoration: underline;
                  font-size: 1.1em;
                }
                a:hover {
                  opacity: 0.85;
                }
                /* Upload form styles */
                form {
                  text-align: center;
                }
                input[type="file"] {
                  margin: 20px 0 10px 0;
                  padding: 10px;
                  border-radius: 8px;
                  border: none;
                  font-size: 1em;
                }
                input[type="submit"] {
                  background-color: {{ status_bg_color }};
                  border: none;
                  color: {{ text_color }};
                  padding: 12px 25px;
                  font-size: 1.1em;
                  border-radius: 8px;
                  cursor: pointer;
                  transition: background-color 0.3s ease;
                }
                input[type="submit"]:hover {
                  background-color: {{ page_bg_color }};
                  color: {{ status_bg_color }};
                  border: 2px solid {{ status_bg_color }};
                }
              </style>
            </head>
            <body>
              <div class="container">
                <h2>Detected Items</h2>
                <div class="counts">
                  {% for label, count in label_counts.items() %}
                    <p>{{label}}: {{count}}</p>
                  {% endfor %}
                  <p><strong>Total:</strong> {{total_count}}</p>
                </div>
                <div class="status">{{ status_message }}</div>
                <img src="data:image/jpg;base64,{{img_base64}}">
                <a href="/">Upload another image</a>
              </div>
            </body>
            </html>
        ''', label_counts=label_counts, total_count=total_count,
             img_base64=img_base64, status_message=status_message,
             page_bg_color=page_bg_color, status_bg_color=status_bg_color,
             text_color=text_color)

    # GET - show upload form with neutral white background
    return '''
    <html>
    <head>
      <title>YOLOv8 Object Detection</title>
      <style>
        body {
          font-family: Arial, sans-serif;
          margin: 0;
          padding: 100px 40px;
          background-color: #eeeeee;
          color: #333;
          text-align: center;
        }
        h1 {
          margin-bottom: 30px;
        }
        form {
          background: white;
          display: inline-block;
          padding: 30px 50px;
          border-radius: 12px;
          box-shadow: 0 0 20px rgba(0,0,0,0.1);
        }
        input[type="file"] {
          margin-bottom: 20px;
          font-size: 1em;
        }
        input[type="submit"] {
          background-color: #4caf50;
          border: none;
          color: white;
          padding: 12px 25px;
          font-size: 1.1em;
          border-radius: 8px;
          cursor: pointer;
          transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
          background-color: #388e3c;
        }
      </style>
    </head>
    <body>
      <h1>Upload Image for Detection</h1>
      <form method="post" enctype="multipart/form-data">
        <input type="file" name="image" required>
        <br>
        <input type="submit" value="Detect">
      </form>
    </body>
    </html>
    '''

if __name__ == "__main__":
    app.run(debug=True)
