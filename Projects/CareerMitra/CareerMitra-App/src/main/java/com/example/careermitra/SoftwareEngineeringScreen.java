package com.example.careermitra;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SoftwareEngineeringScreen extends AppCompatActivity {

    private LinearLayout questionContainer;
    private ArrayList<RadioGroup> radioGroups = new ArrayList<>();

    private String[] questions = {
            "1. What is the first phase of the Software Development Life Cycle (SDLC)?",
            "2. What does UML stand for?",
            "3. What is Agile methodology mainly focused on?",
            "4. Which model is also called the 'linear sequential model'?",
            "5. In software testing, what does 'black box testing' mean?"
    };

    private String[][] options = {
            {"Design", "Requirement Analysis", "Testing", "Implementation"},
            {"Unified Modeling Language", "User Machine Learning", "Universal Machine Logic", "Unique Modeling Language"},
            {"Strict documentation", "Customer collaboration and flexibility", "Lengthy planning", "Waterfall execution"},
            {"Agile Model", "Waterfall Model", "Spiral Model", "V-Model"},
            {"Testing internal logic", "Testing without looking at internal code", "Unit testing", "Performance testing"}
    };

    private String[] correctAnswers = {
            "Requirement Analysis",
            "Unified Modeling Language",
            "Customer collaboration and flexibility",
            "Waterfall Model",
            "Testing without looking at internal code"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software_engineering_screen);

        questionContainer = findViewById(R.id.SoftwareEngineeringQuestionContainer);

        createQuiz();
        addSubmitButton();
    }

    private void createQuiz() {
        for (int i = 0; i < questions.length; i++) {
            addQuestion(questions[i], options[i]);
        }
    }

    private void addQuestion(String questionText, String[] optionsArray) {
        // Question Text
        TextView textView = new TextView(this);
        textView.setText(questionText);
        textView.setTextSize(18);
        textView.setTextColor(getResources().getColor(R.color.text_dark_blue));
        textView.setPadding(20, 24, 20, 8);
        questionContainer.addView(textView);

        // Options
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        radioGroup.setPadding(20, 8, 20, 8);

        for (String option : optionsArray) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioButton.setTextSize(16);
            radioButton.setTextColor(getResources().getColor(R.color.text_dark_gray));
            radioGroup.addView(radioButton);
        }

        radioGroups.add(radioGroup);
        questionContainer.addView(radioGroup);
    }

    private void addSubmitButton() {
        Button submitButton = new Button(this);
        submitButton.setText("Submit Answers");
        submitButton.setBackgroundColor(getResources().getColor(R.color.button_blue));
        submitButton.setTextColor(getResources().getColor(R.color.white));
        submitButton.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        submitButton.setPadding(40, 20, 40, 20);

        submitButton.setOnClickListener(view -> evaluateQuiz());

        questionContainer.addView(submitButton);
    }

    private void evaluateQuiz() {
        int score = 0;

        for (int i = 0; i < radioGroups.size(); i++) {
            RadioGroup group = radioGroups.get(i);
            int selectedId = group.getCheckedRadioButtonId();

            if (selectedId != -1) {
                RadioButton selected = findViewById(selectedId);
                String selectedAnswer = selected.getText().toString();

                if (selectedAnswer.equals(correctAnswers[i])) {
                    score++;
                }
            }
        }

        // Send score back to MainScreen
        Intent resultIntent = new Intent();
        resultIntent.putExtra("se_score", score + "/" + questions.length);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
