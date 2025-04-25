package com.example.careermitra;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProgrammingConceptsScreen extends AppCompatActivity {

    private LinearLayout questionContainer;
    private ArrayList<RadioGroup> radioGroups = new ArrayList<>();
    private Button submitButton;
    private TextView resultTextView;

    private String[] questions = {
            "1. What does JVM stand for?",
            "2. Which language is primarily used for developing Android apps?",
            "3. What is the purpose of a compiler?",
            "4. What is the correct way to start a loop in Java?",
            "5. What is the value of boolean variable after 'boolean a = true && false;'?"
    };

    private String[][] options = {
            {"Java Virtual Machine", "Java Variable Model", "Joint Virtual Mechanism", "Java Vendor Model"},
            {"Python", "Java", "C#", "Swift"},
            {"Execute the program", "Translate code to machine code", "Design the UI", "Manage memory"},
            {"for (int i=0; i<10; i++)", "loop (i=0; i<10; i++)", "repeat until (i=10)", "start loop i=0 to 10"},
            {"true", "false", "null", "error"}
    };

    private String[] correctAnswers = {
            "Java Virtual Machine",
            "Java",
            "Translate code to machine code",
            "for (int i=0; i<10; i++)",
            "false"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programming_concepts_screen);

        questionContainer = findViewById(R.id.ProgrammingConceptsQuestionContainer);

        createQuiz();
        addSubmitButton();
    }

    private void createQuiz() {
        for (int i = 0; i < questions.length; i++) {
            addQuestion(questions[i], options[i]);
        }
    }

    private void addQuestion(String questionText, String[] optionsArray) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(30, 30, 30, 30);
        card.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        card.setBackground(createCardBackground());

        // Question Text
        TextView textView = new TextView(this);
        textView.setText(questionText);
        textView.setTextSize(18);
        textView.setTextColor(Color.parseColor("#0D47A1")); // dark blue
        textView.setPadding(0, 0, 0, 20);
        card.addView(textView);

        // Options
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);

        for (String option : optionsArray) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioButton.setTextSize(16);
            radioButton.setTextColor(Color.parseColor("#37474F")); // dark gray
            radioGroup.addView(radioButton);
        }

        radioGroups.add(radioGroup);
        card.addView(radioGroup);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, 20, 0, 20);

        questionContainer.addView(card, layoutParams);
    }

    private GradientDrawable createCardBackground() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(30);
        drawable.setColor(Color.parseColor("#FFFFFF"));
        drawable.setStroke(3, Color.parseColor("#90CAF9")); // Light blue stroke
        return drawable;
    }

    private void addSubmitButton() {
        submitButton = new Button(this);
        submitButton.setText("Submit Answers");
        submitButton.setTextSize(18);
        submitButton.setTextColor(Color.WHITE);
        submitButton.setBackgroundColor(Color.parseColor("#0D47A1")); // Dark blue
        submitButton.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        submitButton.setPadding(0, 32, 0, 32);

        submitButton.setOnClickListener(view -> evaluateQuiz());

        questionContainer.addView(submitButton);
    }

    private void evaluateQuiz() {
        int score = 0;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < radioGroups.size(); i++) {
            RadioGroup group = radioGroups.get(i);
            int selectedId = group.getCheckedRadioButtonId();

            if (selectedId != -1) {
                RadioButton selected = findViewById(selectedId);
                String selectedAnswer = selected.getText().toString();

                if (selectedAnswer.equals(correctAnswers[i])) {
                    score++;
                    selected.setTextColor(Color.parseColor("#2E7D32")); // Green for correct
                } else {
                    selected.setTextColor(Color.parseColor("#D32F2F")); // Red for wrong
                }

                result.append("Q").append(i + 1).append(": Your Answer: ").append(selectedAnswer)
                        .append("\nCorrect Answer: ").append(correctAnswers[i]).append("\n\n");

            } else {
                result.append("Q").append(i + 1).append(": No Answer\nCorrect Answer: ")
                        .append(correctAnswers[i]).append("\n\n");
            }

            // Disable RadioGroup after submission
            for (int j = 0; j < group.getChildCount(); j++) {
                group.getChildAt(j).setEnabled(false);
            }
        }

        result.append("\nFinal Score: ").append(score).append("/").append(questions.length);

        // Remove old result if already there
        if (resultTextView != null) {
            questionContainer.removeView(resultTextView);
        }

        // Show result
        resultTextView = new TextView(this);
        resultTextView.setText(result.toString());
        resultTextView.setTextSize(18);
        resultTextView.setTextColor(Color.parseColor("#0D47A1"));
        resultTextView.setGravity(Gravity.CENTER);
        resultTextView.setPadding(20, 40, 20, 40);

        questionContainer.addView(resultTextView);
    }
}
