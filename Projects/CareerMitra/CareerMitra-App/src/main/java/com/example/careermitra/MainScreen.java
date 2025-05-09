package com.example.careermitra;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class MainScreen extends AppCompatActivity {

    Map<Integer, TextView> scoreViews = new HashMap<>();
    AppCompatButton goToResultScreen;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_screen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        goToResultScreen = findViewById(R.id.goToResultScreen);

        setupNavigation(R.id.gotoOperatingSystemScreen, 1, OperatingSystemsScreen.class, R.id.osScoreMainScreen);
        setupNavigation(R.id.goToAlgorithmsScreen, 2, AlgorithmsScreen.class, R.id.algorithmsScoreMainScreen);
        setupNavigation(R.id.goToProgrammingConcepts, 3, ProgrammingConceptsScreen.class, R.id.programmingConceptsScoreMainScreen);
        setupNavigation(R.id.goToSoftwareEngineeringScreen, 4, SoftwareEngineeringScreen.class, R.id.softwareEngineeringScoreMainScreen);
        setupNavigation(R.id.goToComputerNetworksScreen, 5, ComputerNetworksScreen.class, R.id.computerNetworksScoreMainScreen);
        setupNavigation(R.id.goToElectronicsSubjectsScreen, 6, ElectronicsSubjectsScreen.class, R.id.electronicsSubjectsScoreMainScreen);
        setupNavigation(R.id.goToComputerArchitectureScreen, 7, ComputerArchitectureScreen.class, R.id.computerArchitectureScoreMainScreen);
        setupNavigation(R.id.goToMathematicsScreen, 8, MathematicsScreen.class, R.id.mathematicsScoreMainScreen);
        setupNavigation(R.id.goToCommunicationSkills, 9, CommunicationSkillsScreen.class, R.id.communicationSkillsScoreMainScreen);

        goToResultScreen.setOnClickListener(v -> {
            Intent intent = new Intent(MainScreen.this, ResultScreen.class);

            // Send all scores to the ResultScreen
            for (Map.Entry<Integer, TextView> entry : scoreViews.entrySet()) {
                String label = getScoreKey(entry.getKey());
                String scoreText = entry.getValue().getText().toString();

                // Extract only the score digit before "/5"
                String score = scoreText.replaceAll(".*?(\\d)/5.*", "$1");

                intent.putExtra(label, score);
            }

            startActivity(intent);
        });


    }

    private void setupNavigation(int buttonId, int requestCode, Class<?> targetClass, int scoreTextViewId) {
        AppCompatButton button = findViewById(buttonId);
        TextView scoreView = findViewById(scoreTextViewId);
        scoreViews.put(requestCode, scoreView);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainScreen.this, targetClass);
            startActivityForResult(intent, requestCode);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            TextView scoreView = scoreViews.get(requestCode);
            String key = getScoreKey(requestCode);

            if (scoreView != null && key != null) {
                String score = data.getStringExtra(key);
                if (score != null) {
                    scoreView.setText(getSubjectLabel(requestCode) + " Score: " + score);
                }
            }
        }
    }

    private String getScoreKey(int requestCode) {
        switch (requestCode) {
            case 1: return "os_score";
            case 2: return "algorithms_score";
            case 3: return "programming_score";
            case 4: return "se_score";
            case 5: return "cn_score";
            case 6: return "electronics_score";
            case 7: return "ca_score";
            case 8: return "math_score";
            case 9: return "comm_score";
            default: return null;
        }
    }

    private String getSubjectLabel(int requestCode) {
        switch (requestCode) {
            case 1: return "Operating System";
            case 2: return "Algorithms";
            case 3: return "Programming Concepts";
            case 4: return "Software Engineering";
            case 5: return "Computer Networks";
            case 6: return "Electronics";
            case 7: return "Computer Architecture";
            case 8: return "Mathematics";
            case 9: return "Communication Skills";
            default: return "Unknown";
        }
    }
}
