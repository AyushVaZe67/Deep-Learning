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
    private List<Question> selectedQuestions = new ArrayList<>();
    private TextView scoreTextView;

    private static class Question {
        String question;
        String[] options;
        String correctAnswer;
        int difficulty; // 1=easy, 2=medium, 3=difficult

        Question(String q, String[] o, String a, int d) {
            question = q;
            options = o;
            correctAnswer = a;
            difficulty = d;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithms_screen);

        questionContainer = findViewById(R.id.AlgorithmsQuestionContainer);

        loadAllQuestions();  // Load all 50 questions
        selectRandomQuestions(10); // Select 10 random questions
        createQuiz(selectedQuestions); // Create quiz with selected questions
        addSubmitButton();
        addScoreView();
    }

    private void loadAllQuestions() {
        allQuestions.clear();

        // Easy Questions (20)
        allQuestions.add(new Question(
                "What is the time complexity of binary search?",
                new String[]{"O(n)", "O(log n)", "O(n log n)", "O(1)"},
                "O(log n)",
                1
        ));
        allQuestions.add(new Question(
                "Which data structure uses FIFO?",
                new String[]{"Stack", "Queue", "Tree", "Graph"},
                "Queue",
                1
        ));
        allQuestions.add(new Question(
                "Which algorithm finds shortest path in weighted graphs?",
                new String[]{"DFS", "Kruskal", "Dijkstra's Algorithm", "Prim"},
                "Dijkstra's Algorithm",
                1
        ));
        allQuestions.add(new Question(
                "Which sorting algorithm is best when array is nearly sorted?",
                new String[]{"Quick Sort", "Heap Sort", "Bubble Sort", "Insertion Sort"},
                "Insertion Sort",
                1
        ));
        allQuestions.add(new Question(
                "Which algorithm is used for cycle detection in graphs?",
                new String[]{"Dijkstra", "DFS", "BFS", "Prim"},
                "DFS",
                1
        ));
        allQuestions.add(new Question(
                "What is the worst-case time complexity of quicksort?",
                new String[]{"O(n)", "O(log n)", "O(n log n)", "O(n^2)"},
                "O(n^2)",
                1
        ));
        allQuestions.add(new Question(
                "Which algorithm is used to find the maximum flow in a network?",
                new String[]{"Bellman-Ford", "Dijkstra", "Ford-Fulkerson", "Kruskal"},
                "Ford-Fulkerson",
                1
        ));
        allQuestions.add(new Question(
                "Which sorting algorithm has the best average time complexity?",
                new String[]{"Bubble Sort", "Quick Sort", "Merge Sort", "Insertion Sort"},
                "Quick Sort",
                1
        ));
        allQuestions.add(new Question(
                "Which data structure is used in the implementation of recursion?",
                new String[]{"Queue", "Stack", "Array", "Linked List"},
                "Stack",
                1
        ));
        allQuestions.add(new Question(
                "Which algorithm is used for finding the minimum spanning tree of a graph?",
                new String[]{"Prim's Algorithm", "Kruskal's Algorithm", "DFS", "Dijkstra's Algorithm"},
                "Prim's Algorithm",
                1
        ));
        allQuestions.add(new Question(
                "What is the time complexity of linear search?",
                new String[]{"O(n)", "O(log n)", "O(1)", "O(n^2)"},
                "O(n)",
                1
        ));
        allQuestions.add(new Question(
                "Which data structure uses LIFO?",
                new String[]{"Queue", "Stack", "Heap", "Tree"},
                "Stack",
                1
        ));
        allQuestions.add(new Question(
                "Which sorting algorithm has O(n^2) worst-case time complexity?",
                new String[]{"Merge Sort", "Quick Sort", "Bubble Sort", "Heap Sort"},
                "Bubble Sort",
                1
        ));
        allQuestions.add(new Question(
                "What is the main operation in a depth-first search?",
                new String[]{"Enqueue", "Dequeue", "Push/Pop", "Insert/Delete"},
                "Push/Pop",
                1
        ));
        allQuestions.add(new Question(
                "Which algorithm uses divide and conquer approach?",
                new String[]{"Bubble Sort", "Merge Sort", "Insertion Sort", "Selection Sort"},
                "Merge Sort",
                1
        ));
        allQuestions.add(new Question(
                "What is the space complexity of recursive bubble sort?",
                new String[]{"O(1)", "O(log n)", "O(n)", "O(n^2)"},
                "O(n)",
                1
        ));
        allQuestions.add(new Question(
                "Which algorithm is not stable?",
                new String[]{"Bubble Sort", "Merge Sort", "Quick Sort", "Insertion Sort"},
                "Quick Sort",
                1
        ));
        allQuestions.add(new Question(
                "What is the best case time complexity of bubble sort?",
                new String[]{"O(n)", "O(n log n)", "O(n^2)", "O(log n)"},
                "O(n)",
                1
        ));
        allQuestions.add(new Question(
                "Which algorithm is not comparison based?",
                new String[]{"Quick Sort", "Merge Sort", "Counting Sort", "Heap Sort"},
                "Counting Sort",
                1
        ));
        allQuestions.add(new Question(
                "What is the time complexity to access an element in an array?",
                new String[]{"O(1)", "O(n)", "O(log n)", "O(n log n)"},
                "O(1)",
                1
        ));

        // Medium Questions (20)
        allQuestions.add(new Question(
                "What is the time complexity of Floyd-Warshall algorithm?",
                new String[]{"O(n)", "O(n^2)", "O(n^3)", "O(n log n)"},
                "O(n^3)",
                2
        ));
        allQuestions.add(new Question(
                "Which algorithm is used to detect negative cycles?",
                new String[]{"Dijkstra", "Bellman-Ford", "Kruskal", "Prim"},
                "Bellman-Ford",
                2
        ));
        allQuestions.add(new Question(
                "What is the space complexity of merge sort?",
                new String[]{"O(1)", "O(log n)", "O(n)", "O(n log n)"},
                "O(n)",
                2
        ));
        allQuestions.add(new Question(
                "Which algorithm is used for topological sorting?",
                new String[]{"BFS", "DFS", "Dijkstra", "Prim"},
                "DFS",
                2
        ));
        allQuestions.add(new Question(
                "What is the time complexity of heap sort?",
                new String[]{"O(n)", "O(n log n)", "O(n^2)", "O(log n)"},
                "O(n log n)",
                2
        ));
        allQuestions.add(new Question(
                "Which algorithm is not suitable for large datasets?",
                new String[]{"Quick Sort", "Merge Sort", "Bubble Sort", "Heap Sort"},
                "Bubble Sort",
                2
        ));
        allQuestions.add(new Question(
                "What is the time complexity of BFS?",
                new String[]{"O(V+E)", "O(V)", "O(E)", "O(V log E)"},
                "O(V+E)",
                2
        ));
        allQuestions.add(new Question(
                "Which algorithm uses greedy approach?",
                new String[]{"Dijkstra", "DFS", "BFS", "Merge Sort"},
                "Dijkstra",
                2
        ));
        allQuestions.add(new Question(
                "What is the time complexity of selection sort?",
                new String[]{"O(n)", "O(n log n)", "O(n^2)", "O(log n)"},
                "O(n^2)",
                2
        ));
        allQuestions.add(new Question(
                "Which algorithm is used for pattern searching?",
                new String[]{"KMP", "Dijkstra", "Prim", "Kruskal"},
                "KMP",
                2
        ));
        allQuestions.add(new Question(
                "What is the time complexity of counting sort?",
                new String[]{"O(n)", "O(n+k)", "O(n log n)", "O(n^2)"},
                "O(n+k)",
                2
        ));
        allQuestions.add(new Question(
                "Which algorithm is used for matrix multiplication?",
                new String[]{"Strassen", "Dijkstra", "Prim", "Kruskal"},
                "Strassen",
                2
        ));
        allQuestions.add(new Question(
                "What is the time complexity of radix sort?",
                new String[]{"O(n)", "O(nk)", "O(n log n)", "O(n^2)"},
                "O(nk)",
                2
        ));
        allQuestions.add(new Question(
                "Which algorithm is used for solving N-Queens problem?",
                new String[]{"Backtracking", "Greedy", "Dynamic Programming", "Divide and Conquer"},
                "Backtracking",
                2
        ));
        allQuestions.add(new Question(
                "What is the time complexity of shell sort?",
                new String[]{"O(n)", "O(n log n)", "O(n^2)", "Depends on gap sequence"},
                "Depends on gap sequence",
                2
        ));
        allQuestions.add(new Question(
                "Which algorithm is used for finding strongly connected components?",
                new String[]{"Kosaraju", "Dijkstra", "Prim", "Kruskal"},
                "Kosaraju",
                2
        ));
        allQuestions.add(new Question(
                "What is the time complexity of interpolation search?",
                new String[]{"O(n)", "O(log log n)", "O(log n)", "O(n log n)"},
                "O(log log n)",
                2
        ));
        allQuestions.add(new Question(
                "Which algorithm is used for finding bridges in a graph?",
                new String[]{"Tarjan", "Dijkstra", "Prim", "Kruskal"},
                "Tarjan",
                2
        ));
        allQuestions.add(new Question(
                "What is the time complexity of bucket sort?",
                new String[]{"O(n)", "O(n+k)", "O(n^2)", "O(n log n)"},
                "O(n+k)",
                2
        ));
        allQuestions.add(new Question(
                "Which algorithm is used for finding articulation points?",
                new String[]{"Tarjan", "Dijkstra", "Prim", "Kruskal"},
                "Tarjan",
                2
        ));

        // Difficult Questions (10)
        allQuestions.add(new Question(
                "What is the time complexity of the fastest known matrix multiplication algorithm?",
                new String[]{"O(n^2)", "O(n^2.37)", "O(n^3)", "O(n log n)"},
                "O(n^2.37)",
                3
        ));
        allQuestions.add(new Question(
                "Which algorithm solves the all-pairs shortest path problem?",
                new String[]{"Dijkstra", "Bellman-Ford", "Floyd-Warshall", "Kruskal"},
                "Floyd-Warshall",
                3
        ));
        allQuestions.add(new Question(
                "What is the time complexity of the fastest known comparison sort?",
                new String[]{"O(n)", "O(n log n)", "O(n^2)", "O(log n)"},
                "O(n log n)",
                3
        ));
        allQuestions.add(new Question(
                "Which algorithm is used for solving the traveling salesman problem exactly?",
                new String[]{"Branch and Bound", "Greedy", "Dynamic Programming", "Divide and Conquer"},
                "Branch and Bound",
                3
        ));
        allQuestions.add(new Question(
                "What is the time complexity of the fastest known integer multiplication algorithm?",
                new String[]{"O(n log n)", "O(n^2)", "O(n log n 2^(log* n))", "O(n)"},
                "O(n log n 2^(log* n))",
                3
        ));
        allQuestions.add(new Question(
                "Which algorithm is used for solving the knapsack problem exactly?",
                new String[]{"Greedy", "Dynamic Programming", "Divide and Conquer", "Backtracking"},
                "Dynamic Programming",
                3
        ));
        allQuestions.add(new Question(
                "What is the time complexity of the fastest known convex hull algorithm?",
                new String[]{"O(n)", "O(n log n)", "O(n^2)", "O(log n)"},
                "O(n log n)",
                3
        ));
        allQuestions.add(new Question(
                "Which algorithm is used for solving the maximum subarray problem in O(n)?",
                new String[]{"Kadane", "Divide and Conquer", "Dynamic Programming", "Greedy"},
                "Kadane",
                3
        ));
        allQuestions.add(new Question(
                "What is the time complexity of the fastest known sorting algorithm for integers?",
                new String[]{"O(n)", "O(n log n)", "O(n^2)", "O(n log log n)"},
                "O(n log log n)",
                3
        ));
        allQuestions.add(new Question(
                "Which algorithm is used for solving the maximum flow problem in O(VE^2)?",
                new String[]{"Edmonds-Karp", "Dinic", "Ford-Fulkerson", "Push-Relabel"},
                "Edmonds-Karp",
                3
        ));
    }


    private void selectRandomQuestions(int count) {
        // Shuffle all questions
        Collections.shuffle(allQuestions);

        // Select the first 'count' questions
        selectedQuestions = new ArrayList<>(allQuestions.subList(0, count));

        // Sort selected questions by difficulty for better user experience
        Collections.sort(selectedQuestions, (q1, q2) -> Integer.compare(q1.difficulty, q2.difficulty));
    }

    private void createQuiz(List<Question> questions) {
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            addQuestion(q, i+1); // Pass question number
        }
    }

    private void addQuestion(Question q, int questionNumber) {
        TextView textView = new TextView(this);
        textView.setText(questionNumber + ". " + q.question);
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
            String correctAnswer = selectedQuestions.get(i).correctAnswer;

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
        int percentageScore = (score * 100) / selectedQuestions.size();
        String scoreText = "You scored " + percentageScore + "/100";
        scoreTextView.setText(scoreText);
        Toast.makeText(this, "Score: " + percentageScore + "/100", Toast.LENGTH_SHORT).show();

        // Send score back to MainScreen as a String
        String scoreResult = percentageScore + "/100";
        Intent resultIntent = new Intent();
        resultIntent.putExtra("algo_score", scoreResult);
        setResult(RESULT_OK, resultIntent);
        finish(); // End activity
    }
}