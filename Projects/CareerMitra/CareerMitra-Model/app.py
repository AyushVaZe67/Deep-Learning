# app.py

import streamlit as st
import pandas as pd
import numpy as np
import pickle
from scipy import sparse

# Load exported files
with open("mlp_model.pkl", "rb") as f:
    model = pickle.load(f)
with open("encoder.pkl", "rb") as f:
    encoder = pickle.load(f)
with open("scaler.pkl", "rb") as f:
    scaler = pickle.load(f)
with open("label_encoder.pkl", "rb") as f:
    label_encoder = pickle.load(f)

# Get input from user
st.title("CareerMitra - Career Path Prediction")
st.markdown("Fill out the form below:")

numerical_inputs = {
    "Acedamic percentage in Operating Systems": st.number_input("Operating Systems (%)", 0, 100),
    "percentage in Algorithms": st.number_input("Algorithms (%)", 0, 100),
    "Percentage in Programming Concepts": st.number_input("Programming Concepts (%)", 0, 100),
    "Percentage in Software Engineering": st.number_input("Software Engineering (%)", 0, 100),
    "Percentage in Computer Networks": st.number_input("Computer Networks (%)", 0, 100),
    "Percentage in Electronics Subjects": st.number_input("Electronics Subjects (%)", 0, 100),
    "Percentage in Computer Architecture": st.number_input("Computer Architecture (%)", 0, 100),
    "Percentage in Mathematics": st.number_input("Mathematics (%)", 0, 100),
    "Percentage in Communication skills": st.number_input("Communication Skills (%)", 0, 100),
    "Hours working per day": st.slider("Hours working per day", 1, 24, 8),
    "Reading and Writing Skills": st.slider("Reading and Writing Skills", 1, 10, 5),
    "Memory Capability Score": st.slider("Memory Capability Score", 1, 10, 5),
    "Logical quotient rating": st.slider("Logical quotient rating", 1, 10, 5)
}

categorical_inputs = {
    "Interested subjects": st.selectbox("Interested Subjects", ["Computer Architecture", "Data Science", "Machine Learning", "Security", "Networking", "Software Engineering"]),
    "Interested Type of Books": st.selectbox("Type of Books", ["Prayer books", "Childrens", "Travel", "Romance", "Cookbooks"]),
    "Salary Range Expected": st.selectbox("Expected Salary Range", ["salary", "Work"]),
    "In a Realtionship?": st.selectbox("In a Relationship?", ["yes", "no"]),
    "Gentle or Tuff behaviour?": st.selectbox("Behaviour", ["gentle", "stubborn"]),
    "Management or Technical": st.selectbox("Career Track", ["Management", "Technical"]),
    "Salary/work": st.selectbox("Salary or Work Preference", ["salary", "work"]),
    "hard/smart worker": st.selectbox("Working Style", ["hard worker", "smart worker"]),
    "worked in teams ever?": st.selectbox("Worked in Teams?", ["yes", "no"]),
    "Introvert": st.selectbox("Introvert", ["yes", "no"])
}

if st.button("Predict Job Role"):
    # Combine inputs into dataframe
    input_df = pd.DataFrame([numerical_inputs | categorical_inputs])

    # Encode and scale
    cat_encoded = encoder.transform(input_df[categorical_inputs.keys()])
    full_input = sparse.hstack([input_df[numerical_inputs.keys()], cat_encoded])
    scaled_input = scaler.transform(full_input)

    # Predict
    pred = model.predict(scaled_input)
    job_role = label_encoder.inverse_transform(pred)[0]

    st.success(f"✅ Suggested Job Role: **{job_role}**")
