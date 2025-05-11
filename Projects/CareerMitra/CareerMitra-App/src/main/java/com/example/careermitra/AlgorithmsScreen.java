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
        createQuiz(allQuestions.subList(0, 10)); // Show 10 random questions
        addSubmitButton();
    }

    private void loadAllQuestions() {
        allQuestions.clear();

        // Easy
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

        // Medium
        allQuestions.add(new Question(
                "Which of the following is a greedy algorithm?",
                new String[]{"Bubble Sort", "Quick Sort", "Dijkstra’s Algorithm", "Selection Sort"},
                "Dijkstra’s Algorithm"
        ));
        allQuestions.add(new Question(
                "What does Big O notation describe?",
                new String[]{"The actual time", "Worst case complexity", "Best case", "Average case"},
                "Worst case complexity"
        ));
        allQuestions.add(new Question(
                "Which sorting algorithm has worst-case O(n^2)?",
                new String[]{"Merge Sort", "Quick Sort", "Heap Sort", "Bubble Sort"},
                "Bubble Sort"
        ));
        allQuestions.add(new Question(
                "How many edges are in a complete graph with n vertices?",
                new String[]{"n", "n(n-1)/2", "n^2", "n-1"},
                "n(n-1)/2"
        ));
        allQuestions.add(new Question(
                "Which algorithm uses divide and conquer?",
                new String[]{"BFS", "Merge Sort", "Dijkstra", "Kruskal"},
                "Merge Sort"
        ));

        // Hard
        allQuestions.add(new Question(
                "What is the time complexity of the best case in QuickSort?",
                new String[]{"O(n^2)", "O(log n)", "O(n log n)", "O(n)"},
                "O(n log n)"
        ));
        allQuestions.add(new Question(
                "Which algorithm is used to find Minimum Spanning Tree?",
                new String[]{"BFS", "DFS", "Dijkstra", "Prim"},
                "Prim"
        ));
        allQuestions.add(new Question(
                "Which of these problems is NP-complete?",
                new String[]{"Sorting", "Shortest Path", "Subset Sum", "Matrix Multiplication"},
                "Subset Sum"
        ));
        allQuestions.add(new Question(
                "What is backtracking used for?",
                new String[]{"Greedy problems", "Divide and Conquer", "Dynamic Programming", "Constraint satisfaction"},
                "Constraint satisfaction"
        ));
        allQuestions.add(new Question(
                "Which is not an example of Dynamic Programming?",
                new String[]{"Fibonacci", "Knapsack", "Binary Search", "Longest Common Subsequence"},
                "Binary Search"
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
        List<String> options = new ArrayList<>();
        Collections.addAll(options, q.options);
        Collections.shuffle(options);

        for (int i = 0; i < options.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(options.get(i));
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

        int finalScore = Math.round(score / 2.0f); // Convert 10-question score to score out of 5

        String scoreText = "You scored " + finalScore + " out of 5";
        resultTextView.setText(scoreText);
        Toast.makeText(this, "Score: " + finalScore + "/5", Toast.LENGTH_SHORT).show();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("algorithms_score", finalScore);
        setResult(RESULT_OK, resultIntent);
        finish(); // Return to main screen
    }
}
