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

    // All scores as integers
    int os_score, algorithms_score, programming_score, se_score,
            cn_score, electronics_score, ca_score, math_score, comm_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        btnPredict = findViewById(R.id.btnPredict);
        finalOutput = findViewById(R.id.finalOutput);
        progressBar = findViewById(R.id.progressBar);

        // Get integer scores directly
        os_score = getIntent().getIntExtra("os_score", 0);
        algorithms_score = getIntent().getIntExtra("algorithms_score", 0);
        programming_score = getIntent().getIntExtra("programming_score", 0);
        se_score = getIntent().getIntExtra("se_score", 0);
        cn_score = getIntent().getIntExtra("cn_score", 0);
        electronics_score = getIntent().getIntExtra("electronics_score", 0);
        ca_score = getIntent().getIntExtra("ca_score", 0);
        math_score = getIntent().getIntExtra("math_score", 0);
        comm_score = getIntent().getIntExtra("comm_score", 0);

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

        // Predict button click
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

    private void setScore(int viewId, int score) {
        TextView resultView = findViewById(viewId);
        resultView.setText("Score: " + score + "/100");
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
