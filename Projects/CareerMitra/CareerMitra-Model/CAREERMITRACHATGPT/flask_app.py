from flask import Flask, request, render_template_string
import pandas as pd
import pickle

# Initialize Flask app
app = Flask(__name__)

# Load model and pre-processing tools
with open("mlp_model.pkl", "rb") as f:
    model = pickle.load(f)
with open("scaler.pkl", "rb") as f:
    scaler = pickle.load(f)
with open("label_encoder.pkl", "rb") as f:
    label_encoder = pickle.load(f)

# Define the 9 input fields
input_columns = [
    'Acedamic percentage in Operating Systems',
    'percentage in Algorithms',
    'Percentage in Programming Concepts',
    'Percentage in Software Engineering',
    'Percentage in Computer Networks',
    'Percentage in Electronics Subjects',
    'Percentage in Computer Architecture',
    'Percentage in Mathematics',
    'Percentage in Communication skills'
]

# HTML template for the web UI
HTML_TEMPLATE = """
<!DOCTYPE html>
<html>
<head>
    <title>CareerMitra - Career Prediction</title>
    <style>
        body { font-family: Arial; margin: 40px; background: #f4f4f4; }
        h1 { color: #2c3e50; }
        label { font-weight: bold; }
        input[type=number] { width: 300px; padding: 8px; margin-bottom: 10px; }
        input[type=submit] { padding: 10px 20px; font-weight: bold; }
        .result { background: #dff0d8; padding: 15px; border-left: 5px solid #3c763d; margin-top: 20px; }
    </style>
</head>
<body>
    <h1>CareerMitra - Career Path Prediction</h1>
    <form method="post">
        {% for col in columns %}
            <label>{{ col }}</label><br/>
            <input type="number" name="{{ col }}" step="0.01" required><br/>
        {% endfor %}
        <input type="submit" value="Predict Job Role">
    </form>

    {% if prediction %}
        <div class="result">
            <h2>🎯 Suggested Job Role: <strong>{{ prediction }}</strong></h2>
        </div>
    {% endif %}
</body>
</html>
"""

@app.route("/", methods=["GET", "POST"])
def index():
    prediction = None
    if request.method == "POST":
        try:
            # Collect and process user inputs
            user_inputs = [float(request.form[col]) for col in input_columns]
            input_df = pd.DataFrame([user_inputs], columns=input_columns)

            # Preprocess and predict
            scaled_input = scaler.transform(input_df)
            pred = model.predict(scaled_input)
            prediction = label_encoder.inverse_transform(pred)[0]

        except Exception as e:
            prediction = f"Error: {e}"

    return render_template_string(HTML_TEMPLATE, columns=input_columns, prediction=prediction)

if __name__ == "__main__":
    app.run(debug=True)
