package com.example.careermitra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainScreen extends AppCompatActivity {

    AppCompatButton gotoOperatingSystemScreen,goToAlgorithmsScreen,goToProgrammingConcepts;

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

        gotoOperatingSystemScreen = findViewById(R.id.gotoOperatingSystemScreen);
        goToAlgorithmsScreen = findViewById(R.id.goToAlgorithmsScreen);
        goToProgrammingConcepts = findViewById(R.id.goToProgrammingConcepts);

        gotoOperatingSystemScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainScreen.this,OperatingSystemsScreen.class));
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
    }
}