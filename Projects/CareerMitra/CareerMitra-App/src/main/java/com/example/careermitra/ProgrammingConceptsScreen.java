package com.example.careermitra;

import android.os.Bundle;
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
        // Question Text
        TextView textView = new TextView(this);
        textView.setText(questionText);
        textView.setTextSize(18);
        textView.setPadding(0, 24, 0, 8);
        questionContainer.addView(textView);

        // Options
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);

        for (String option : optionsArray) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioButton.setTextSize(16);
            radioGroup.addView(radioButton);
        }

        radioGroups.add(radioGroup);
        questionContainer.addView(radioGroup);
    }

    private void addSubmitButton() {
        Button submitButton = new Button(this);
        submitButton.setText("Submit Answers");
        submitButton.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
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
                }

                result.append("Q").append(i + 1).append(": Your Answer: ").append(selectedAnswer)
                        .append("\nCorrect Answer: ").append(correctAnswers[i]).append("\n\n");

            } else {
                result.append("Q").append(i + 1).append(": No Answer\nCorrect Answer: ")
                        .append(correctAnswers[i]).append("\n\n");
            }
        }

        result.append("Final Score: ").append(score).append("/").append(questions.length);

        // Show the result below submit button
        TextView resultTextView = new TextView(this);
        resultTextView.setText(result.toString());
        resultTextView.setTextSize(16);
        resultTextView.setPadding(0, 40, 0, 40);

        questionContainer.addView(resultTextView);
    }
}
