package com.example.careermitra;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CareerMitraAPI {

    @POST("/predict")
    Call<PredictionResponse> getPrediction(@Body PredictionRequest request);
}
