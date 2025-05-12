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

            // Pass the individual scores as extra data to the ResultScreen
            for (Map.Entry<Integer, TextView> entry : scoreViews.entrySet()) {
                int requestCode = entry.getKey();
                String key = getScoreKey(requestCode);
                String scoreText = entry.getValue().getText().toString();

                // Convert score to float and scale it to 100 (i.e., score out of 100)
                float scoreOutOf5 = extractScoreFromText(scoreText);
                float scoreOutOf100 = scoreOutOf5 * 20f;

                intent.putExtra(key, scoreOutOf100);
            }

            startActivity(intent);
        });
    }

    private void setupNavigation(int buttonId, int requestCode, Class<?> targetClass, int scoreTextViewId) {
        AppCompatButton button = findViewById(buttonId);
        TextView scoreView = findViewById(scoreTextViewId);
        scoreViews.put(requestCode, scoreView);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                getActivityResultCallback(requestCode)
        );

        launchers.put(requestCode, launcher);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainScreen.this, targetClass);
            launcher.launch(intent);
        });
    }

    private ActivityResultCallback<ActivityResult> getActivityResultCallback(int requestCode) {
        return result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                TextView scoreView = scoreViews.get(requestCode);
                String key = getScoreKey(requestCode);

                if (scoreView != null && key != null) {
                    String score = result.getData().getStringExtra(key);
                    if (score != null) {
                        scoreView.setText("Score: " + score);
                    }
                }
            }
        };
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
            String[] parts = text.split(":");
            String raw = parts[1].replaceAll("/.*", "").trim(); // number before "/"
            return Float.parseFloat(raw);
        } catch (Exception e) {
            return 0f;
        }
    }
}
