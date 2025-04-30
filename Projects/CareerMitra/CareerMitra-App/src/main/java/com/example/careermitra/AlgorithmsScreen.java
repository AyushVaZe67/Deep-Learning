package com.example.careermitra;

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

public class AlgorithmsScreen extends AppCompatActivity {

    private LinearLayout questionContainer;
    private final ArrayList<RadioGroup> radioGroups = new ArrayList<>();
    private final ArrayList<RadioButton[]> radioButtonsList = new ArrayList<>();

    private final String[] questions = {
            "1. What is the time complexity of binary search?",
            "2. Which sorting algorithm is the fastest in average case?",
            "3. What does 'Big O' notation describe?",
            "4. Which algorithm technique does merge sort use?",
            "5. What is the worst-case time complexity of bubble sort?"
    };

    private final String[][] options = {
            {"O(n)", "O(log n)", "O(n log n)", "O(1)"},
            {"Bubble Sort", "Insertion Sort", "Merge Sort", "Quick Sort"},
            {"Memory usage", "Execution time", "Growth rate", "Code size"},
            {"Divide and Conquer", "Backtracking", "Greedy", "Dynamic Programming"},
            {"O(n)", "O(log n)", "O(n^2)", "O(n log n)"}
    };

    private final String[] correctAnswers = {
            "O(log n)",
            "Quick Sort",
            "Growth rate",
            "Divide and Conquer",
            "O(n^2)"
    };

    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_algorithms_screen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        questionContainer = findViewById(R.id.AlgorithmsQuestionContainer);

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
        TextView textView = new TextView(this);
        textView.setText(questionText);
        textView.setTextSize(18);
        textView.setTextColor(getColor(android.R.color.black));
        textView.setPadding(0, 24, 0, 8);
        questionContainer.addView(textView);

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
                rb.setEnabled(false); // Disable all after submit
                String answer = rb.getText().toString();

                if (answer.equals(correctAnswer)) {
                    rb.setTextColor(Color.parseColor("#388E3C")); // Green
                }

                if (selectedId == rb.getId() && !answer.equals(correctAnswer)) {
                    rb.setTextColor(Color.parseColor("#D32F2F")); // Red
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
        resultIntent.putExtra("algorithms_score", score + "/" + questions.length);
        setResult(RESULT_OK, resultIntent);
        finish(); // Finish and return to MainScreen
    }

}
