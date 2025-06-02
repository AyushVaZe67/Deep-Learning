from fastapi import FastAPI, UploadFile, File
from fastapi.middleware.cors import CORSMiddleware
from ultralytics import YOLO
import shutil
import os

app = FastAPI()

# Allow frontend to access backend API (useful for dashboard later)
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # Or replace with frontend domain
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Load your trained model
model = YOLO("best.pt")  # Make sure best.pt is in the same folder

@app.post("/predict/")
async def predict(file: UploadFile = File(...)):
    # Save uploaded image temporarily
    temp_path = f"temp_{file.filename}"
    with open(temp_path, "wb") as buffer:
        shutil.copyfileobj(file.file, buffer)

    # Run model prediction
    results = model(temp_path)

    # Clean up temp image
    os.remove(temp_path)

    # Get prediction results
    predictions = results[0].names
    classes = results[0].boxes.cls.tolist()

    return {
        "predicted_classes": [predictions[int(cls)] for cls in classes]
    }
