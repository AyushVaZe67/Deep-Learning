package com.example.careermitra;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        setScore(R.id.osResult, "os_score");
        setScore(R.id.algorithmsResult, "algorithms_score");
        setScore(R.id.programmingResult, "programming_score");
        setScore(R.id.seResult, "se_score");
        setScore(R.id.cnResult, "cn_score");
        setScore(R.id.electronicsResult, "electronics_score");
        setScore(R.id.caResult, "ca_score");
        setScore(R.id.mathResult, "math_score");
        setScore(R.id.commResult, "comm_score");
    }

    private void setScore(int viewId, String key) {
        String score = getIntent().getStringExtra(key);
        TextView resultView = findViewById(viewId);
        resultView.setText(score != null ? key.replace("_", " ").toUpperCase() + ": " + score + "" : key + ": Not Available");

    }
}
