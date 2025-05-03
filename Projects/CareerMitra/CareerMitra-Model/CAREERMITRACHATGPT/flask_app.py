from flask import Flask, request, render_template_string, jsonify
import pandas as pd
import pickle

app = Flask(__name__)

# Load model and preprocessing tools
with open("mlp_model.pkl", "rb") as f:
    model = pickle.load(f)
with open("scaler.pkl", "rb") as f:
    scaler = pickle.load(f)
with open("label_encoder.pkl", "rb") as f:
    label_encoder = pickle.load(f)

# Define input columns
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

# Load dataset for mean, min, max values (for the web form)
dataset = pd.read_csv("careermitra_dataset.csv")

HTML_TEMPLATE = """
<!DOCTYPE html>
<html>
<head>
    <title>CareerMitra - Career Path Prediction</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        h1 { color: #2c3e50; }
        label { font-weight: bold; display: block; margin-top: 10px; }
        input[type='number'] {
            width: 300px; padding: 6px; margin-bottom: 10px;
        }
        input[type='submit'] {
            padding: 8px 16px; font-weight: bold;
            background-color: #2c3e50; color: white; border: none;
        }
        .result { margin-top: 20px; font-size: 1.2em; color: green; }
    </style>
</head>
<body>
    <h1>CareerMitra - Career Path Prediction</h1>
    <form method="post">
        {% for col in columns %}
            <label for="{{ col }}">{{ col }}</label>
            <input type="number" step="0.01" name="{{ col }}" required
                   min="{{ stats[col]['min'] }}" max="{{ stats[col]['max'] }}"
                   value="{{ stats[col]['mean']|round(2) }}">
        {% endfor %}
        <br/>
        <input type="submit" value="Predict Job Role">
    </form>

    {% if prediction %}
        <div class="result">🎯 Suggested Job Role: <strong>{{ prediction }}</strong></div>
    {% endif %}
</body>
</html>
"""

@app.route("/", methods=["GET", "POST"])
def index():
    prediction = None

    # Get basic stats for HTML form
    stats = {
        col: {
            "min": float(dataset[col].min()),
            "max": float(dataset[col].max()),
            "mean": float(dataset[col].mean())
        }
        for col in input_columns
    }

    if request.method == "POST":
        user_inputs = [float(request.form[col]) for col in input_columns]
        input_df = pd.DataFrame([user_inputs], columns=input_columns)
        scaled_input = scaler.transform(input_df)
        pred = model.predict(scaled_input)
        prediction = label_encoder.inverse_transform(pred)[0]

    return render_template_string(HTML_TEMPLATE, columns=input_columns, stats=stats, prediction=prediction)


@app.route("/predict", methods=["POST"])
def predict():
    try:
        data = request.get_json()

        # Validate input
        missing = [col for col in input_columns if col not in data]
        if missing:
            return jsonify({"error": f"Missing fields: {missing}"}), 400

        input_data = [data[col] for col in input_columns]
        input_df = pd.DataFrame([input_data], columns=input_columns)
        scaled_input = scaler.transform(input_df)
        pred = model.predict(scaled_input)
        prediction = label_encoder.inverse_transform(pred)[0]

        return jsonify({"predicted_job_role": prediction})

    except Exception as e:
        return jsonify({"error": str(e)}), 500


if __name__ == "__main__":
    app.run(debug=True)
