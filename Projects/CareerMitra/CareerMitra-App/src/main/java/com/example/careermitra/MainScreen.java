package com.example.careermitra;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaCommunicationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainScreen extends AppCompatActivity {

    AppCompatButton gotoOperatingSystemScreen,goToAlgorithmsScreen,goToProgrammingConcepts,
            goToSoftwareEngineeringScreen,goToComputerNetworksScreen,goToElectronicsSubjectsScreen,
            goToComputerArchitectureScreen,goToMathematicsScreen,goToCommunicationSkills;

    TextView osScore;


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
                startActivity(new Intent(MainScreen.this,AlgorithmsScreen.class));
            }
        });

        goToProgrammingConcepts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this,ProgrammingConceptsScreen.class));
            }
        });

        goToSoftwareEngineeringScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this,SoftwareEngineeringScreen.class));
            }
        });

        goToComputerNetworksScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this, ComputerNetworksScreen.class));
            }
        });

        goToElectronicsSubjectsScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this, ElectronicsSubjectsScreen.class));
            }
        });

        goToComputerArchitectureScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this, ComputerArchitectureScreen.class));
            }
        });

        goToMathematicsScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this, MathematicsScreen.class));
            }
        });

        goToCommunicationSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this, CommunicationSkillsScreen.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            String score = data.getStringExtra("os_score");
            if (score != null && osScore != null) {
                osScore.setText("Score: " + score);
            }
        }
    }

}