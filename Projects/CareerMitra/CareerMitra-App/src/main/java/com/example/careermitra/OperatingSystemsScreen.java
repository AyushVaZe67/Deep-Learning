package com.example.careermitra;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
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
    private final ArrayList<RadioGroup> radioGroups = new ArrayList<>();
    private final ArrayList<String[]> optionsList = new ArrayList<>();
    private final ArrayList<RadioButton[]> radioButtonsList = new ArrayList<>();

    private final String[] questions = {
            "1. Which of the following is an Operating System?",
            "2. Which one is NOT a function of Operating System?",
            "3. Which of the following is a mobile Operating System?",
            "4. What is the core of the Operating System called?",
            "5. Which of the following is a multi-user OS?"
    };

    private final String[][] options = {
            {"Oracle", "Linux", "Google", "Intel"},
            {"Memory Management", "Processor Management", "Providing Internet", "Device Management"},
            {"Android", "Windows 7", "MacOS", "Ubuntu"},
            {"Shell", "Command Prompt", "Kernel", "Compiler"},
            {"MS-DOS", "Windows XP", "UNIX", "None of these"}
    };

    private final String[] correctAnswers = {
            "Linux",
            "Providing Internet",
            "Android",
            "Kernel",
            "UNIX"
    };

    private TextView scoreTextView;

    @SuppressLint("MissingInflatedId")
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

        questionContainer = findViewById(R.id.questionContainer1);

        createQuiz();
        addSubmitButton();
        addScoreView();
    }

    private void createQuiz() {
        for (int i = 0; i < questions.length; i++) {
            addQuestion(questions[i], options[i], correctAnswers[i]);
        }
    }

    private void addQuestion(String questionText, String[] optionsArray, String correctAnswer) {
        // Add Question TextView
        TextView textView = new TextView(this);
        textView.setText(questionText);
        textView.setTextSize(18);
        textView.setTextColor(getColor(android.R.color.black));
        textView.setPadding(0, 24, 0, 8);
        questionContainer.addView(textView);

        // Create RadioGroup
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        radioGroup.setPadding(16, 0, 0, 8);

        RadioButton[] radioButtons = new RadioButton[optionsArray.length];

        for (int i = 0; i < optionsArray.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(optionsArray[i]);
            radioButton.setTextSize(16);
            radioButton.setTextColor(getColor(android.R.color.darker_gray));
            radioGroup.addView(radioButton);
            radioButtons[i] = radioButton;
        }

        radioGroups.add(radioGroup);
        radioButtonsList.add(radioButtons);
        optionsList.add(optionsArray);
        questionContainer.addView(radioGroup);
    }

    private void addSubmitButton() {
        Button submitButton = new Button(this);
        submitButton.setText("Submit Answers");
        submitButton.setTextSize(16);
        submitButton.setBackgroundColor(getColor(android.R.color.holo_blue_light));
        submitButton.setTextColor(getColor(android.R.color.white));
        submitButton.setPadding(24, 16, 24, 16);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.topMargin = 40;
        params.gravity = Gravity.CENTER_HORIZONTAL;
        submitButton.setLayoutParams(params);

        submitButton.setOnClickListener(view -> evaluateQuiz());

        questionContainer.addView(submitButton);
    }

    private void addScoreView() {
        scoreTextView = new TextView(this);
        scoreTextView.setTextSize(18);
        scoreTextView.setTextColor(Color.BLACK);
        scoreTextView.setPadding(0, 32, 0, 32);
        scoreTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        questionContainer.addView(scoreTextView);
    }

    private void evaluateQuiz() {
        int score = 0;

        for (int i = 0; i < radioGroups.size(); i++) {
            RadioGroup group = radioGroups.get(i);
            int selectedId = group.getCheckedRadioButtonId();
            RadioButton[] options = radioButtonsList.get(i);
            String correctAnswer = correctAnswers[i];

            for (RadioButton rb : options) {
                rb.setEnabled(false); // disable all after submit
                String answer = rb.getText().toString();

                if (answer.equals(correctAnswer)) {
                    rb.setTextColor(Color.parseColor("#388E3C")); // green
                }

                if (selectedId == rb.getId() && !answer.equals(correctAnswer)) {
                    rb.setTextColor(Color.parseColor("#D32F2F")); // red
                }

                if (selectedId == rb.getId() && answer.equals(correctAnswer)) {
                    score++;
                }
            }
        }

        String scoreText = "You scored " + score + " out of " + questions.length;
        scoreTextView.setText(scoreText);
        Toast.makeText(this, "Score: " + score + "/" + questions.length, Toast.LENGTH_SHORT).show();

        // Send score back to MainScreen
        Intent resultIntent = new Intent();
        resultIntent.putExtra("os_score", score + "/" + questions.length);
        setResult(RESULT_OK, resultIntent);
        finish(); // Finish and return
    }
}


