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

# Load training column structure (create this from training phase if needed)
full_feature_df = pd.read_csv("careermitra_dataset.csv")
X_raw = full_feature_df.drop(columns=['Suggested Job Role'])
all_columns = X_raw.columns

# Step 1: UI Inputs
st.title("CareerMitra - Career Path Prediction")
st.markdown("Fill all inputs below for a more accurate prediction.")

user_inputs = {}
for col in all_columns:
    if full_feature_df[col].dtype == 'object':
        unique_vals = full_feature_df[col].dropna().unique()
        user_inputs[col] = st.selectbox(f"{col}", options=list(unique_vals))
    else:
        min_val = int(full_feature_df[col].min())
        max_val = int(full_feature_df[col].max())
        default_val = int(full_feature_df[col].mean())
        user_inputs[col] = st.slider(f"{col}", min_val, max_val, default_val)

# Step 2: Create DataFrame with all expected columns
input_df = pd.DataFrame([user_inputs])

# Step 3: Encode categorical and scale
categorical_cols = input_df.select_dtypes(include='object').columns
numerical_cols = input_df.select_dtypes(exclude='object').columns

cat_encoded = encoder.transform(input_df[categorical_cols])
combined_input = sparse.hstack([input_df[numerical_cols], cat_encoded])
scaled_input = scaler.transform(combined_input)

# Step 4: Predict
if st.button("Predict Job Role"):
    pred = model.predict(scaled_input)
    result = label_encoder.inverse_transform(pred)[0]
    st.success(f"🎯 Suggested Job Role: **{result}**")
