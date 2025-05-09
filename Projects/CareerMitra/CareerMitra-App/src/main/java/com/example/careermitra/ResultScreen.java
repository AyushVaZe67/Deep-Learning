package com.example.careermitra;

import android.annotation.SuppressLint;
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


    // Declare scores at the class level
    float os_score, algorithms_score, programming_score, se_score,
            cn_score, electronics_score, ca_score, math_score, comm_score;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        btnPredict = findViewById(R.id.btnPredict);
        finalOutput = findViewById(R.id.finalOutput);
        progressBar = findViewById(R.id.progressBar);


        // Get and convert scores
        os_score = convertScoreToRange(parseScore(getIntent().getStringExtra("os_score")));
        algorithms_score = convertScoreToRange(parseScore(getIntent().getStringExtra("algorithms_score")));
        programming_score = convertScoreToRange(parseScore(getIntent().getStringExtra("programming_score")));
        se_score = convertScoreToRange(parseScore(getIntent().getStringExtra("se_score")));
        cn_score = convertScoreToRange(parseScore(getIntent().getStringExtra("cn_score")));
        electronics_score = convertScoreToRange(parseScore(getIntent().getStringExtra("electronics_score")));
        ca_score = convertScoreToRange(parseScore(getIntent().getStringExtra("ca_score")));
        math_score = convertScoreToRange(parseScore(getIntent().getStringExtra("math_score")));
        comm_score = convertScoreToRange(parseScore(getIntent().getStringExtra("comm_score")));

        // Display scores
        setScore(R.id.osResult, os_score);
        setScore(R.id.algorithmsResult, algorithms_score);
        setScore(R.id.programmingResult, programming_score);
        setScore(R.id.seResult, se_score);
        setScore(R.id.cnResult, cn_score);
        setScore(R.id.electronicsResult, electronics_score);
        setScore(R.id.caResult, ca_score);
        setScore(R.id.mathResult, math_score);
        setScore(R.id.commResult, comm_score);

        btnPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
    }

    private void setScore(int viewId, float score) {
        TextView resultView = findViewById(viewId);
        resultView.setText("Score: " + score);
    }

    private float parseScore(String score) {
        try {
            return score != null ? Float.parseFloat(score) : 0.0f;
        } catch (NumberFormatException e) {
            return 0.0f;
        }
    }

    private float convertScoreToRange(float score) {
        return 20 + ((score - 1) / (5 - 1)) * 80; // 80 = (100 - 20)
    }

    private void sendRequestToAPI(JSONObject data) {
        String url = "https://careermitra.onrender.com/predict";

        progressBar.setVisibility(View.VISIBLE);
        finalOutput.setText("");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            String predictedJobRole = response.getString("predicted_job_role");
                            finalOutput.setText("Predicted Job Role: " + predictedJobRole);
                        } catch (JSONException e) {
                            finalOutput.setText("Parsing error!");
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        finalOutput.setText("API Error: " + error.getMessage());
                        Toast.makeText(ResultScreen.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}
