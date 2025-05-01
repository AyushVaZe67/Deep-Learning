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

public class MainScreen extends AppCompatActivity {

    AppCompatButton gotoOperatingSystemScreen, goToAlgorithmsScreen, goToProgrammingConcepts,
            goToSoftwareEngineeringScreen, goToComputerNetworksScreen, goToElectronicsSubjectsScreen,
            goToComputerArchitectureScreen, goToMathematicsScreen, goToCommunicationSkills;

    TextView osScore, algorithmsScore, programmingConceptsScore,
            softwareEngineeringScore, computerNetworksScore,
            electronicsSubjectsScore, computerArchitectureScore,
            mathematicsScore, communicationSkillsScore;

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

        osScore = findViewById(R.id.osScoreMainScreen);
        algorithmsScore = findViewById(R.id.algorithmsScoreMainScreen);
        programmingConceptsScore = findViewById(R.id.programmingConceptsScoreMainScreen);
        softwareEngineeringScore = findViewById(R.id.softwareEngineeringScoreMainScreen);
        computerNetworksScore = findViewById(R.id.computerNetworksScoreMainScreen);
        electronicsSubjectsScore = findViewById(R.id.electronicsSubjectsScoreMainScreen);
        computerArchitectureScore = findViewById(R.id.computerArchitectureScoreMainScreen);
        mathematicsScore = findViewById(R.id.mathematicsScoreMainScreen);
        communicationSkillsScore = findViewById(R.id.communicationSkillsScoreMainScreen);


        gotoOperatingSystemScreen = findViewById(R.id.gotoOperatingSystemScreen);
        goToAlgorithmsScreen = findViewById(R.id.goToAlgorithmsScreen);
        goToProgrammingConcepts = findViewById(R.id.goToProgrammingConcepts);
        goToSoftwareEngineeringScreen = findViewById(R.id.goToSoftwareEngineeringScreen);
        goToComputerNetworksScreen = findViewById(R.id.goToComputerNetworksScreen);
        goToElectronicsSubjectsScreen = findViewById(R.id.goToElectronicsSubjectsScreen);
        goToComputerArchitectureScreen = findViewById(R.id.goToComputerArchitectureScreen);
        goToMathematicsScreen = findViewById(R.id.goToMathematicsScreen);
        goToCommunicationSkills = findViewById(R.id.goToCommunicationSkills);

        gotoOperatingSystemScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, OperatingSystemsScreen.class);
                startActivityForResult(intent, 1); // Request code 1
            }
        });

        goToAlgorithmsScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, AlgorithmsScreen.class);
                startActivityForResult(intent, 2); // Request code 2 for algorithms
            }
        });

        goToProgrammingConcepts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, ProgrammingConceptsScreen.class);
                startActivityForResult(intent, 3); // Request code 2 for algorithms
            }
        });

        goToSoftwareEngineeringScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, SoftwareEngineeringScreen.class);
                startActivityForResult(intent, 4); // Request code 4
            }
        });

        goToComputerNetworksScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, ComputerNetworksScreen.class);
                startActivityForResult(intent, 5); // Request code 5
            }
        });

        goToElectronicsSubjectsScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, ElectronicsSubjectsScreen.class);
                startActivityForResult(intent, 6); // Request code 6
            }
        });

        goToComputerArchitectureScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, ComputerArchitectureScreen.class);
                startActivityForResult(intent, 7); // Request code 7
            }
        });

        goToMathematicsScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, MathematicsScreen.class);
                startActivityForResult(intent, 8); // Request code 8
            }
        });

        goToCommunicationSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, CommunicationSkillsScreen.class);
                startActivityForResult(intent, 9); // Request code 9
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case 1:
                    String osScoreText = data.getStringExtra("os_score");
                    if (osScoreText != null && osScore != null) {
                        osScore.setText("Operating System Score: " + osScoreText);
                    }
                    break;

                case 2:
                    String algorithmsScoreText = data.getStringExtra("algorithms_score");
                    if (algorithmsScoreText != null && algorithmsScore != null) {
                        algorithmsScore.setText("Algorithms Score: " + algorithmsScoreText);
                    }
                    break;

                case 3:
                    String programmingConceptsScoreText = data.getStringExtra("programming_score");
                    if (programmingConceptsScoreText != null && programmingConceptsScore != null) {
                        programmingConceptsScore.setText("Programming Concepts Score: " + programmingConceptsScoreText);
                    }
                    break;

                case 4:
                    String seScoreText = data.getStringExtra("se_score");
                    if (seScoreText != null && softwareEngineeringScore != null) {
                        softwareEngineeringScore.setText("Software Engineering Score: " + seScoreText);
                    }
                    break;

                case 5:
                    String cnScoreText = data.getStringExtra("cn_score");
                    if (cnScoreText != null && computerNetworksScore != null) {
                        computerNetworksScore.setText("Computer Networks Score: " + cnScoreText);
                    }
                    break;

                case 6:
                    String electronicsScoreText = data.getStringExtra("electronics_score");
                    if (electronicsScoreText != null && electronicsSubjectsScore != null) {
                        electronicsSubjectsScore.setText("Electronics Score: " + electronicsScoreText);
                    }
                    break;

                case 7:
                    String caScoreText = data.getStringExtra("ca_score");
                    if (caScoreText != null && computerArchitectureScore != null) {
                        computerArchitectureScore.setText("Computer Architecture Score: " + caScoreText);
                    }
                    break;

                case 8:
                    String mathScoreText = data.getStringExtra("math_score");
                    if (mathScoreText != null && mathematicsScore != null) {
                        mathematicsScore.setText("Mathematics Score: " + mathScoreText);
                    }
                    break;

                case 9:
                    String commScoreText = data.getStringExtra("comm_score");
                    if (commScoreText != null && communicationSkillsScore != null) {
                        communicationSkillsScore.setText("Communication Skills Score: " + commScoreText);
                    }
                    break;
            }
        }
    }

}
