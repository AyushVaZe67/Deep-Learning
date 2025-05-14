package com.example.careermitra;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ResultScreen extends AppCompatActivity {

    AppCompatButton btnPredict;
    TextView finalOutput;
    ProgressBar progressBar;

    float os_score, algorithms_score, programming_score, se_score,
            cn_score, electronics_score, ca_score, math_score, comm_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        btnPredict = findViewById(R.id.btnPredict);
        finalOutput = findViewById(R.id.finalOutput);
        progressBar = findViewById(R.id.progressBar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            os_score = extras.getFloat("os_score", -1f);
            algorithms_score = extras.getFloat("algo_score", -1f);
            programming_score = extras.getFloat("programming_score", -1f);
            se_score = extras.getFloat("se_score", -1f);
            cn_score = extras.getFloat("cn_score", -1f);
            electronics_score = extras.getFloat("electronics_score", -1f);
            ca_score = extras.getFloat("ca_score", -1f);
            math_score = extras.getFloat("math_score", -1f);
            comm_score = extras.getFloat("comm_score", -1f);

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
            return;
        }

        btnPredict.setOnClickListener(v -> {
            if (hasMissingOrZeroScores()) {
                Toast.makeText(this, "Please make sure all scores are filled and non-zero.", Toast.LENGTH_LONG).show();
                return;
            }

            JSONObject data = new JSONObject();
            try {
                data.put("Acedamic percentage in Operating Systems", (int) os_score);
                data.put("percentage in Algorithms", (int) algorithms_score);
                data.put("Percentage in Programming Concepts", (int) programming_score);
                data.put("Percentage in Software Engineering", (int) se_score);
                data.put("Percentage in Computer Networks", (int) cn_score);
                data.put("Percentage in Electronics Subjects", (int) electronics_score);
                data.put("Percentage in Computer Architecture", (int) ca_score);
                data.put("Percentage in Mathematics", (int) math_score);
                data.put("Percentage in Communication skills", (int) comm_score);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error preparing data", Toast.LENGTH_SHORT).show();
                return;
            }

            sendRequestToAPI(data);
        });
    }

    private void setScore(int viewId, float score) {
        TextView resultView = findViewById(viewId);
        if (resultView != null) {
            if (score <= 0f) {
                resultView.setText("Score: Not Entered");
            } else {
                resultView.setText(String.format("Score: %.1f/100", score));
            }
        }
    }

    private boolean hasMissingOrZeroScores() {
        return os_score <= 0 || algorithms_score <= 0 || programming_score <= 0 ||
                se_score <= 0 || cn_score <= 0 || electronics_score <= 0 ||
                ca_score <= 0 || math_score <= 0 || comm_score <= 0;
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
