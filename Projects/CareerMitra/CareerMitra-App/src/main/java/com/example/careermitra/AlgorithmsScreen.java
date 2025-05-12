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
    private final List<Question> fixedQuestions = new ArrayList<>();
    private TextView scoreTextView;

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

        loadFixedQuestions();  // Load 10 questions now
        createQuiz(fixedQuestions); // Create quiz with 10 questions
        addSubmitButton();
        addScoreView();
    }

    private void loadFixedQuestions() {
        fixedQuestions.clear();

        fixedQuestions.add(new Question(
                "1. What is the time complexity of binary search?",
                new String[]{"O(n)", "O(log n)", "O(n log n)", "O(1)"},
                "O(log n)"
        ));
        fixedQuestions.add(new Question(
                "2. Which data structure uses FIFO?",
                new String[]{"Stack", "Queue", "Tree", "Graph"},
                "Queue"
        ));
        fixedQuestions.add(new Question(
                "3. Which algorithm finds shortest path in weighted graphs?",
                new String[]{"DFS", "Kruskal", "Dijkstra's Algorithm", "Prim"},
                "Dijkstra's Algorithm"
        ));
        fixedQuestions.add(new Question(
                "4. Which sorting algorithm is best when array is nearly sorted?",
                new String[]{"Quick Sort", "Heap Sort", "Bubble Sort", "Insertion Sort"},
                "Insertion Sort"
        ));
        fixedQuestions.add(new Question(
                "5. Which algorithm is used for cycle detection in graphs?",
                new String[]{"Dijkstra", "DFS", "BFS", "Prim"},
                "DFS"
        ));
        // Add 5 more questions
        fixedQuestions.add(new Question(
                "6. What is the worst-case time complexity of quicksort?",
                new String[]{"O(n)", "O(log n)", "O(n log n)", "O(n^2)"},
                "O(n^2)"
        ));
        fixedQuestions.add(new Question(
                "7. Which algorithm is used to find the maximum flow in a network?",
                new String[]{"Bellman-Ford", "Dijkstra", "Ford-Fulkerson", "Kruskal"},
                "Ford-Fulkerson"
        ));
        fixedQuestions.add(new Question(
                "8. Which sorting algorithm has the best average time complexity?",
                new String[]{"Bubble Sort", "Quick Sort", "Merge Sort", "Insertion Sort"},
                "Quick Sort"
        ));
        fixedQuestions.add(new Question(
                "9. Which data structure is used in the implementation of recursion?",
                new String[]{"Queue", "Stack", "Array", "Linked List"},
                "Stack"
        ));
        fixedQuestions.add(new Question(
                "10. Which algorithm is used for finding the minimum spanning tree of a graph?",
                new String[]{"Prim's Algorithm", "Kruskal's Algorithm", "DFS", "Dijkstra's Algorithm"},
                "Prim's Algorithm"
        ));
    }

    private void createQuiz(List<Question> questions) {
        for (Question q : questions) {
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
        params.topMargin = 40;
        submitButton.setLayoutParams(params);
        submitButton.setPadding(40, 20, 40, 20);

        submitButton.setOnClickListener(view -> evaluateQuiz());

        questionContainer.addView(submitButton);
    }

    private void addScoreView() {
        scoreTextView = new TextView(this);
        scoreTextView.setTextSize(18);
        scoreTextView.setPadding(0, 32, 0, 32);
        scoreTextView.setTextColor(Color.BLACK);
        scoreTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        questionContainer.addView(scoreTextView);
    }

    private void evaluateQuiz() {
        int score = 0;

        for (int i = 0; i < radioGroups.size(); i++) {
            RadioGroup group = radioGroups.get(i);
            int selectedId = group.getCheckedRadioButtonId();
            RadioButton[] options = radioButtonsList.get(i);
            String correctAnswer = fixedQuestions.get(i).correctAnswer;

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

        // Calculate the score as percentage out of 100
        int percentageScore = (score * 100) / 10;  // 10 questions, so multiply by 100 and divide by 10
        String scoreText = "You scored " + percentageScore + "/100";  // Keeping the format as String
        scoreTextView.setText(scoreText);
        Toast.makeText(this, "Score: " + percentageScore + "/100", Toast.LENGTH_SHORT).show();

        // Send score back to MainScreen as a String
        String scoreResult = percentageScore + "/100";
        Intent resultIntent = new Intent();
        resultIntent.putExtra("algo_score", scoreResult);  // Passing as String
        setResult(RESULT_OK, resultIntent);
        finish(); // End activity
    }
}
