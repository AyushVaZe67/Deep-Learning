package com.example.careermitra;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ResultScreen extends AppCompatActivity {

    AppCompatButton btnPredict;
    TextView finalOutput;
    ProgressBar progressBar;

    // All scores as floats
    float os_score, algorithms_score, programming_score, se_score,
            cn_score, electronics_score, ca_score, math_score, comm_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        btnPredict = findViewById(R.id.btnPredict);
        finalOutput = findViewById(R.id.finalOutput);
        progressBar = findViewById(R.id.progressBar);

        // Get all scores from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            os_score = extras.getFloat("os_score", 0f);
            algorithms_score = extras.getFloat("algo_score", 0f);
            programming_score = extras.getFloat("programming_score", 0f);
            se_score = extras.getFloat("se_score", 0f);
            cn_score = extras.getFloat("cn_score", 0f);
            electronics_score = extras.getFloat("electronics_score", 0f);
            ca_score = extras.getFloat("ca_score", 0f);
            math_score = extras.getFloat("math_score", 0f);
            comm_score = extras.getFloat("comm_score", 0f);

            // Display all scores
            setScore(R.id.osResult, os_score);
            setScore(R.id.algorithmsResult, algorithms_score);
            setScore(R.id.programmingResult, programming_score);
            setScore(R.id.seResult, se_score);
            setScore(R.id.cnResult, cn_score);
            setScore(R.id.electronicsResult, electronics_score);
            setScore(R.id.caResult, ca_score);
            setScore(R.id.mathResult, math_score);
            setScore(R.id.commResult, comm_score);
        } else {
            Toast.makeText(this, "No scores received", Toast.LENGTH_SHORT).show();
        }

        btnPredict.setOnClickListener(v -> {
            JSONObject data = new JSONObject();
            try {
                data.put("Acedamic percentage in Operating Systems", os_score);
                data.put("percentage in Algorithms", algorithms_score);
                data.put("Percentage in Programming Concepts", programming_score);
                data.put("Percentage in Software Engineering", se_score);
                data.put("Percentage in Computer Networks", cn_score);
                data.put("Percentage in Electronics Subjects", electronics_score);
                data.put("Percentage in Computer Architecture", ca_score);
                data.put("Percentage in Mathematics", math_score);
                data.put("Percentage in Communication skills", comm_score);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            sendRequestToAPI(data);
        });
    }

    private void setScore(int viewId, float score) {
        TextView resultView = findViewById(viewId);
        if (resultView != null) {
            resultView.setText(String.format("Score: %.1f/100", score));
        }
    }

    private void sendRequestToAPI(JSONObject data) {
        String url = "https://careermitra.onrender.com/predict";

        progressBar.setVisibility(View.VISIBLE);
        finalOutput.setText("");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, url, data,
                response -> {
                    progressBar.setVisibility(View.GONE);
                    try {
                        String predictedJobRole = response.getString("predicted_job_role");
                        finalOutput.setText("Predicted Job Role: " + predictedJobRole);
                    } catch (JSONException e) {
                        finalOutput.setText("Error parsing response");
                        e.printStackTrace();
                    }
                },
                error -> {
                    progressBar.setVisibility(View.GONE);
                    finalOutput.setText("API Error: " + error.getMessage());
                    Toast.makeText(ResultScreen.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                });

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}