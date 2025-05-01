package com.example.careermitra;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MathematicsScreen extends AppCompatActivity {

    private LinearLayout questionContainer;
    private ArrayList<RadioGroup> radioGroups = new ArrayList<>();
    private TextView resultTextView;

    private String[] questions = {
            "1. What is the value of π (pi) approximately?",
            "2. What is the derivative of x²?",
            "3. Solve: 5 + 3 × 2 = ?",
            "4. What is the square root of 144?",
            "5. What type of number is √2?"
    };

    private String[][] options = {
            {"2.12", "3.14", "3.41", "4.13"},
            {"2x", "x", "x²", "2"},
            {"11", "16", "10", "13"},
            {"10", "12", "14", "16"},
            {"Rational Number", "Irrational Number", "Whole Number", "Prime Number"}
    };

    private String[] correctAnswers = {
            "3.14",
            "2x",
            "11",
            "12",
            "Irrational Number"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematics_screen);

        questionContainer = findViewById(R.id.MathematicsQuestionContainer);

        createQuiz();
        addSubmitButton();
    }

    private void createQuiz() {
        for (int i = 0; i < questions.length; i++) {
            addQuestion(questions[i], options[i]);
        }
    }

    private void addQuestion(String questionText, String[] optionsArray) {
        TextView textView = new TextView(this);
        textView.setText(questionText);
        textView.setTextSize(18);
        textView.setTextColor(getResources().getColor(R.color.text_dark_blue));
        textView.setPadding(20, 32, 20, 12);
        questionContainer.addView(textView);

        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        radioGroup.setPadding(20, 12, 20, 24);

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

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.gravity = Gravity.CENTER_HORIZONTAL;
        submitButton.setLayoutParams(params);
        submitButton.setPadding(40, 20, 40, 20);

        submitButton.setOnClickListener(view -> evaluateQuiz());

        questionContainer.addView(submitButton);

        resultTextView = new TextView(this);
        resultTextView.setTextSize(16);
        resultTextView.setPadding(20, 40, 20, 40);
        resultTextView.setTextColor(getResources().getColor(R.color.primary_dark_blue));
        questionContainer.addView(resultTextView);
    }

    private void evaluateQuiz() {
        int score = 0;

        // Loop through all the questions and calculate the score
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

        // Send the score back to MainScreen
        Intent resultIntent = new Intent();
        resultIntent.putExtra("math_score", score + "/" + questions.length);  // Pass score as a string
        setResult(RESULT_OK, resultIntent);  // Return the result to the calling activity
        finish();  // Close the current activity (MathematicsScreen)
    }
}
