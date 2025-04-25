package com.example.careermitra;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class OperatingSystemsScreen extends AppCompatActivity {

    private LinearLayout questionContainer;
    private ArrayList<RadioGroup> radioGroups = new ArrayList<>();

    private String[] questions = {
            "1. Which of the following is an Operating System?",
            "2. Which one is NOT a function of Operating System?",
            "3. Which of the following is a mobile Operating System?"
    };

    private String[][] options = {
            {"Oracle", "Linux", "Google", "Intel"},
            {"Memory Management", "Processor Management", "Providing Internet", "Device Management"},
            {"Android", "Windows 7", "MacOS", "Ubuntu"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_operating_systems_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        questionContainer = findViewById(R.id.questionContainer);

        // Add all questions
        createQuiz();

        // Add submit button
        addSubmitButton();

    }

    private void createQuiz() {
        for (int i = 0; i < questions.length; i++) {
            addQuestion(questions[i], options[i]);
        }
    }

    private void addQuestion(String questionText, String[] optionsArray) {
        // Create Question TextView
        TextView textView = new TextView(this);
        textView.setText(questionText);
        textView.setTextSize(18);
        textView.setPadding(0, 24, 0, 8);
        questionContainer.addView(textView);

        // Create Options RadioGroup
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);

        for (String option : optionsArray) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioGroup.addView(radioButton);
        }

        radioGroups.add(radioGroup);
        questionContainer.addView(radioGroup);
    }

    private void addSubmitButton() {
        Button submitButton = new Button(this);
        submitButton.setText("Submit");
        submitButton.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        submitButton.setPadding(0, 32, 0, 32);

        submitButton.setOnClickListener(view -> collectAnswers());

        questionContainer.addView(submitButton);
    }

    private void collectAnswers() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < radioGroups.size(); i++) {
            RadioGroup group = radioGroups.get(i);
            int selectedId = group.getCheckedRadioButtonId();

            if (selectedId != -1) {
                RadioButton selected = findViewById(selectedId);
                result.append("Q").append(i + 1).append(": ")
                        .append(selected.getText().toString())
                        .append("\n");
            } else {
                result.append("Q").append(i + 1).append(": No Answer\n");
            }
        }

        // Show results as a Toast (you can also show in new screen if you want)
        Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show();
    }
}