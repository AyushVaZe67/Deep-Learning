import streamlit as st
import joblib
import numpy as np

# Load the saved model and label encoder
svm_model = joblib.load('svm_model.pkl')
le = joblib.load('label_encoder.pkl')

# Full list of unique symptoms (from your dataset)
symptom_list_full = [
    'Aggression', 'Anxiety', 'Bruising', 'Cough', 'Dehydration', 'Diarrhea', 'Discharge',
    'Ear infection', 'Excessive drooling', 'Fever', 'Hair loss', 'Itching', 'Lethargy', 'Limping',
    'Loss of appetite', 'Nasal discharge', 'Pain', 'Rash', 'Red eyes', 'Shivering', 'Sneezing',
    'Swelling', 'Vomiting', 'Watery eyes', 'Weight loss'
]

st.title("Pet Disease Prediction")
st.write("Select 3 symptoms to predict the disease:")

# User selects 3 symptoms
symptom1 = st.selectbox("Symptom 1", symptom_list_full)
symptom2 = st.selectbox("Symptom 2", symptom_list_full)
symptom3 = st.selectbox("Symptom 3", symptom_list_full)

# Predict button
if st.button("Predict Disease"):
    # Initialize input vector with 75 zeros
    input_vector = np.zeros(len(symptom_list_full) * 3)

    # Map selected symptoms to one-hot positions
    if symptom1 in symptom_list_full:
        index1 = symptom_list_full.index(symptom1)
        input_vector[index1] = 1  # Symptom1_

    if symptom2 in symptom_list_full:
        index2 = symptom_list_full.index(symptom2) + len(symptom_list_full)
        input_vector[index2] = 1  # Symptom2_

    if symptom3 in symptom_list_full:
        index3 = symptom_list_full.index(symptom3) + 2 * len(symptom_list_full)
        input_vector[index3] = 1  # Symptom3_

    # Reshape for model
    input_vector = input_vector.reshape(1, -1)

    # Predict
    prediction = svm_model.predict(input_vector)
    predicted_disease = le.inverse_transform(prediction)[0]

    st.success(f"Predicted Disease: {predicted_disease}")
