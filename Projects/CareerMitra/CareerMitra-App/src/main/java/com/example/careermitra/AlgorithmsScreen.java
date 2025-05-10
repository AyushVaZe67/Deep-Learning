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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmsScreen extends AppCompatActivity {

    private LinearLayout questionContainer;
    private final ArrayList<RadioGroup> radioGroups = new ArrayList<>();
    private final ArrayList<RadioButton[]> radioButtonsList = new ArrayList<>();
    private final List<Question> allQuestions = new ArrayList<>();
    private TextView resultTextView;

    private static class Question {
        String question;
        String[] options;
        String correctAnswer;

        Question(String q, String[] o, String a) {
            question = q;
            options = o;
            correctAnswer = a;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithms_screen);

        questionContainer = findViewById(R.id.AlgorithmsQuestionContainer);

        loadAllQuestions();
        Collections.shuffle(allQuestions);
        createQuiz(allQuestions.subList(0, 5)); // Select 5 questions
        addSubmitButton();
    }

    private void loadAllQuestions() {
        allQuestions.clear();

        // Sample 5 questions only for brevity. Add more as needed.
        allQuestions.add(new Question(
                "What is the time complexity of binary search?",
                new String[]{"O(n)", "O(log n)", "O(n log n)", "O(1)"},
                "O(log n)"
        ));

        allQuestions.add(new Question(
                "Which data structure uses FIFO?",
                new String[]{"Stack", "Queue", "Tree", "Graph"},
                "Queue"
        ));

        allQuestions.add(new Question(
                "Which algorithm finds shortest path in weighted graphs?",
                new String[]{"DFS", "Kruskal", "Dijkstra's Algorithm", "Prim"},
                "Dijkstra's Algorithm"
        ));

        allQuestions.add(new Question(
                "Which sorting algorithm is best when array is nearly sorted?",
                new String[]{"Quick Sort", "Heap Sort", "Bubble Sort", "Insertion Sort"},
                "Insertion Sort"
        ));

        allQuestions.add(new Question(
                "Which algorithm is used for cycle detection in graphs?",
                new String[]{"Dijkstra", "DFS", "BFS", "Prim"},
                "DFS"
        ));
    }

    private void createQuiz(List<Question> selectedQuestions) {
        for (Question q : selectedQuestions) {
            addQuestion(q);
        }
    }

    private void addQuestion(Question q) {
        TextView textView = new TextView(this);
        textView.setText(q.question);
        textView.setTextSize(18);
        textView.setTextColor(getResources().getColor(R.color.text_dark_blue));
        textView.setPadding(20, 32, 20, 12);
        questionContainer.addView(textView);

        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        radioGroup.setPadding(20, 12, 20, 24);

        RadioButton[] radioButtons = new RadioButton[q.options.length];
        for (int i = 0; i < q.options.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(q.options[i]);
            radioButton.setTextSize(16);
            radioButton.setTextColor(getResources().getColor(R.color.text_dark_gray));
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

        for (int i = 0; i < radioGroups.size(); i++) {
            RadioGroup group = radioGroups.get(i);
            int selectedId = group.getCheckedRadioButtonId();
            RadioButton[] options = radioButtonsList.get(i);
            String correctAnswer = allQuestions.get(i).correctAnswer;

            for (RadioButton rb : options) {
                rb.setEnabled(false);

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

        String scoreText = "You scored " + score + " out of " + radioGroups.size();
        resultTextView.setText(scoreText);
        Toast.makeText(this, "Score: " + score + "/" + radioGroups.size(), Toast.LENGTH_SHORT).show();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("algorithms_score", score + "/" + radioGroups.size());
        setResult(RESULT_OK, resultIntent);
        finish(); // Return to main screen
    }
}
