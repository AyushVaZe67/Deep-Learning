import streamlit as st
import numpy as np
import pickle

# Load model, scaler, and label encoder
with open('xgb_model.pkl', 'rb') as f:
    model = pickle.load(f)

with open('scaler.pkl', 'rb') as f:
    scaler = pickle.load(f)

with open('label_encoder.pkl', 'rb') as f:
    le = pickle.load(f)

# App Title
st.title("🎯 Career Prediction Based on Academic Performance")

st.write("Enter your subject percentages below:")

# Input fields for the 9 features
os_percent = st.number_input("Academic percentage in Operating Systems", 0.0, 100.0, format="%.2f")
algo_percent = st.number_input("Percentage in Algorithms", 0.0, 100.0, format="%.2f")
prog_percent = st.number_input("Percentage in Programming Concepts", 0.0, 100.0, format="%.2f")
se_percent = st.number_input("Percentage in Software Engineering", 0.0, 100.0, format="%.2f")
cn_percent = st.number_input("Percentage in Computer Networks", 0.0, 100.0, format="%.2f")
electronics_percent = st.number_input("Percentage in Electronics Subjects", 0.0, 100.0, format="%.2f")
ca_percent = st.number_input("Percentage in Computer Architecture", 0.0, 100.0, format="%.2f")
maths_percent = st.number_input("Percentage in Mathematics", 0.0, 100.0, format="%.2f")
comm_percent = st.number_input("Percentage in Communication Skills", 0.0, 100.0, format="%.2f")

# Collect all inputs
input_data = np.array([[os_percent, algo_percent, prog_percent, se_percent,
                        cn_percent, electronics_percent, ca_percent,
                        maths_percent, comm_percent]])

# Predict button
if st.button("Predict Career"):
    # Scale the input
    input_scaled = scaler.transform(input_data)

    # Predict
    prediction = model.predict(input_scaled)
    probabilities = model.predict_proba(input_scaled)

    # Decode label
    predicted_label = le.inverse_transform(prediction)[0]

    st.success(f"🏆 Predicted Career: {predicted_label}")

    # Show class probabilities nicely
    st.subheader("Prediction Probabilities:")
    prob_df = {}
    for idx, class_name in enumerate(le.classes_):
        prob_df[class_name] = probabilities[0][idx]

    st.write(prob_df)