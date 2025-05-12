package com.example.careermitra;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class MainScreen extends AppCompatActivity {

    Map<Integer, TextView> scoreViews = new HashMap<>();
    Map<Integer, ActivityResultLauncher<Intent>> launchers = new HashMap<>();
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

        // Initialize all score views first
        scoreViews.put(1, findViewById(R.id.osScoreMainScreen));
        scoreViews.put(2, findViewById(R.id.algorithmsScoreMainScreen));
        scoreViews.put(3, findViewById(R.id.programmingConceptsScoreMainScreen));
        scoreViews.put(4, findViewById(R.id.softwareEngineeringScoreMainScreen));
        scoreViews.put(5, findViewById(R.id.computerNetworksScoreMainScreen));
        scoreViews.put(6, findViewById(R.id.electronicsSubjectsScoreMainScreen));
        scoreViews.put(7, findViewById(R.id.computerArchitectureScoreMainScreen));
        scoreViews.put(8, findViewById(R.id.mathematicsScoreMainScreen));
        scoreViews.put(9, findViewById(R.id.communicationSkillsScoreMainScreen));

        // Setup navigation for each subject screen
        setupNavigation(R.id.gotoOperatingSystemScreen, 1, OperatingSystemsScreen.class);
        setupNavigation(R.id.goToAlgorithmsScreen, 2, AlgorithmsScreen.class);
        setupNavigation(R.id.goToProgrammingConcepts, 3, ProgrammingConceptsScreen.class);
        setupNavigation(R.id.goToSoftwareEngineeringScreen, 4, SoftwareEngineeringScreen.class);
        setupNavigation(R.id.goToComputerNetworksScreen, 5, ComputerNetworksScreen.class);
        setupNavigation(R.id.goToElectronicsSubjectsScreen, 6, ElectronicsSubjectsScreen.class);
        setupNavigation(R.id.goToComputerArchitectureScreen, 7, ComputerArchitectureScreen.class);
        setupNavigation(R.id.goToMathematicsScreen, 8, MathematicsScreen.class);
        setupNavigation(R.id.goToCommunicationSkills, 9, CommunicationSkillsScreen.class);

        // Navigate to ResultScreen
        goToResultScreen.setOnClickListener(v -> {
            Intent intent = new Intent(MainScreen.this, ResultScreen.class);

            // Pass all scores as extras
            for (Map.Entry<Integer, TextView> entry : scoreViews.entrySet()) {
                int subjectId = entry.getKey();
                TextView scoreView = entry.getValue();
                String scoreText = scoreView.getText().toString();

                // Extract the numeric score from the text (e.g., "Score: 3/5" -> 3)
                float score = extractScoreFromText(scoreText);

                // Convert to out of 100 if needed (except for Algorithms which is already out of 100)
                float scoreOutOf100 = (subjectId == 2) ? score : score * 20f;

                // Put the score in intent with appropriate key
                intent.putExtra(getScoreKey(subjectId), scoreOutOf100);
            }

            startActivity(intent);
        });
    }

    private void setupNavigation(int buttonId, int requestCode, Class<?> targetClass) {
        AppCompatButton button = findViewById(buttonId);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        TextView scoreView = scoreViews.get(requestCode);
                        String key = getScoreKey(requestCode);
                        String score = result.getData().getStringExtra(key);
                        if (score != null && scoreView != null) {
                            scoreView.setText("Score: " + score);
                        }
                    }
                }
        );

        launchers.put(requestCode, launcher);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainScreen.this, targetClass);
            launcher.launch(intent);
        });
    }

    private String getScoreKey(int requestCode) {
        switch (requestCode) {
            case 1: return "os_score";
            case 2: return "algo_score";
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

    private float extractScoreFromText(String text) {
        try {
            // Remove "Score: " prefix if present
            if (text.startsWith("Score: ")) {
                text = text.substring("Score: ".length());
            }

            // Split by "/" and take the first part
            String[] parts = text.split("/");
            return Float.parseFloat(parts[0].trim());
        } catch (Exception e) {
            return 0f;
        }
    }
}