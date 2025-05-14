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

public class MathematicsScreen extends AppCompatActivity {

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
        setContentView(R.layout.activity_mathematics_screen);

        questionContainer = findViewById(R.id.MathematicsQuestionContainer);

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
                "What is the value of π (pi) approximately?",
                new String[]{"2.12", "3.14", "3.41", "4.13"},
                "3.14",
                1
        ));
        allQuestions.add(new Question(
                "What is the derivative of x²?",
                new String[]{"2x", "x", "x²", "2"},
                "2x",
                1
        ));
        allQuestions.add(new Question(
                "Solve: 5 + 3 × 2 = ?",
                new String[]{"11", "16", "10", "13"},
                "11",
                1
        ));
        allQuestions.add(new Question(
                "What is the square root of 144?",
                new String[]{"10", "12", "14", "16"},
                "12",
                1
        ));
        allQuestions.add(new Question(
                "What type of number is √2?",
                new String[]{"Rational Number", "Irrational Number", "Whole Number", "Prime Number"},
                "Irrational Number",
                1
        ));
        allQuestions.add(new Question(
                "What is the sum of angles in a triangle?",
                new String[]{"90°", "180°", "270°", "360°"},
                "180°",
                1
        ));
        allQuestions.add(new Question(
                "What is 2³ equal to?",
                new String[]{"6", "8", "12", "16"},
                "8",
                1
        ));
        allQuestions.add(new Question(
                "What is the value of 5! (5 factorial)?",
                new String[]{"20", "60", "120", "150"},
                "120",
                1
        ));
        allQuestions.add(new Question(
                "What is the slope of a horizontal line?",
                new String[]{"0", "1", "Undefined", "Infinite"},
                "0",
                1
        ));
        allQuestions.add(new Question(
                "What is the formula for the area of a circle?",
                new String[]{"πr", "2πr", "πr²", "πr³"},
                "πr²",
                1
        ));
        allQuestions.add(new Question(
                "What is the value of sin(0°)?",
                new String[]{"0", "1", "-1", "√2/2"},
                "0",
                1
        ));
        allQuestions.add(new Question(
                "What is the sum of the first 10 positive integers?",
                new String[]{"45", "50", "55", "60"},
                "55",
                1
        ));
        allQuestions.add(new Question(
                "What is the solution to x + 5 = 10?",
                new String[]{"3", "5", "10", "15"},
                "5",
                1
        ));
        allQuestions.add(new Question(
                "What is the perimeter of a square with side length 4?",
                new String[]{"12", "16", "20", "24"},
                "16",
                1
        ));
        allQuestions.add(new Question(
                "What is the value of log₁₀(100)?",
                new String[]{"1", "2", "3", "10"},
                "2",
                1
        ));
        allQuestions.add(new Question(
                "What is the least common multiple of 6 and 8?",
                new String[]{"12", "16", "24", "48"},
                "24",
                1
        ));
        allQuestions.add(new Question(
                "What is the value of cos(90°)?",
                new String[]{"0", "1", "-1", "√3/2"},
                "0",
                1
        ));
        allQuestions.add(new Question(
                "What is the solution to 2x = 8?",
                new String[]{"2", "4", "6", "8"},
                "4",
                1
        ));
        allQuestions.add(new Question(
                "What is the area of a rectangle with length 5 and width 3?",
                new String[]{"8", "15", "18", "20"},
                "15",
                1
        ));
        allQuestions.add(new Question(
                "What is the value of 3² + 4²?",
                new String[]{"20", "25", "30", "36"},
                "25",
                1
        ));

        // Medium Questions (20)
        allQuestions.add(new Question(
                "What is the derivative of sin(x)?",
                new String[]{"cos(x)", "-sin(x)", "-cos(x)", "sin(x)"},
                "cos(x)",
                2
        ));
        allQuestions.add(new Question(
                "What is the solution to the quadratic equation x² - 4 = 0?",
                new String[]{"x = ±1", "x = ±2", "x = ±3", "x = ±4"},
                "x = ±2",
                2
        ));
        allQuestions.add(new Question(
                "What is the value of tan(45°)?",
                new String[]{"0", "1", "√2", "√3"},
                "1",
                2
        ));
        allQuestions.add(new Question(
                "What is the sum of an arithmetic series with first term 2, last term 20, and 10 terms?",
                new String[]{"110", "120", "130", "140"},
                "110",
                2
        ));
        allQuestions.add(new Question(
                "What is the integral of 2x dx?",
                new String[]{"x² + C", "2x² + C", "x + C", "2x + C"},
                "x² + C",
                2
        ));
        allQuestions.add(new Question(
                "What is the probability of rolling a 6 on a fair die?",
                new String[]{"1/4", "1/6", "1/3", "1/2"},
                "1/6",
                2
        ));
        allQuestions.add(new Question(
                "What is the value of e⁰?",
                new String[]{"0", "1", "e", "∞"},
                "1",
                2
        ));
        allQuestions.add(new Question(
                "What is the determinant of a 2x2 matrix [[1, 2], [3, 4]]?",
                new String[]{"-2", "2", "-10", "10"},
                "-2",
                2
        ));
        allQuestions.add(new Question(
                "What is the slope of the line y = 2x + 3?",
                new String[]{"1", "2", "3", "4"},
                "2",
                2
        ));
        allQuestions.add(new Question(
                "What is the sum of the interior angles of a pentagon?",
                new String[]{"360°", "540°", "720°", "900°"},
                "540°",
                2
        ));
        allQuestions.add(new Question(
                "What is the value of log₂(8)?",
                new String[]{"2", "3", "4", "8"},
                "3",
                2
        ));
        allQuestions.add(new Question(
                "What is the solution to the system of equations: x + y = 5, x - y = 1?",
                new String[]{"(2, 3)", "(3, 2)", "(4, 1)", "(1, 4)"},
                "(3, 2)",
                2
        ));
        allQuestions.add(new Question(
                "What is the volume of a cube with side length 3?",
                new String[]{"9", "18", "27", "36"},
                "27",
                2
        ));
        allQuestions.add(new Question(
                "What is the derivative of e^x?",
                new String[]{"e^x", "xe^(x-1)", "e^(x+1)", "x e^x"},
                "e^x",
                2
        ));
        allQuestions.add(new Question(
                "What is the value of sin(30°) + cos(60°)?",
                new String[]{"0", "1", "2", "√3"},
                "1",
                2
        ));
        allQuestions.add(new Question(
                "What is the number of ways to arrange 3 distinct objects?",
                new String[]{"3", "6", "9", "12"},
                "6",
                2
        ));
        allQuestions.add(new Question(
                "What is the inverse of the function f(x) = 2x + 1?",
                new String[]{"f⁻¹(x) = x - 1", "f⁻¹(x) = (x - 1)/2", "f⁻¹(x) = x/2", "f⁻¹(x) = 2x - 1"},
                "f⁻¹(x) = (x - 1)/2",
                2
        ));
        allQuestions.add(new Question(
                "What is the value of the series 1 + 1/2 + 1/4 + 1/8 to infinity?",
                new String[]{"1", "2", "3", "4"},
                "2",
                2
        ));
        allQuestions.add(new Question(
                "What is the area of a triangle with base 6 and height 4?",
                new String[]{"12", "18", "24", "30"},
                "12",
                2
        ));
        allQuestions.add(new Question(
                "What is the value of cos²(30°) + sin²(30°)?",
                new String[]{"0", "1", "√3/2", "2"},
                "1",
                2
        ));

        // Difficult Questions (10)
        allQuestions.add(new Question(
                "What is the limit of (1 - 1/n)^n as n approaches infinity?",
                new String[]{"0", "1", "1/e", "e"},
                "1/e",
                3
        ));
        allQuestions.add(new Question(
                "What is the derivative of ln(x)?",
                new String[]{"1/x", "ln(x)", "x", "e^x"},
                "1/x",
                3
        ));
        allQuestions.add(new Question(
                "What is the solution to the differential equation dy/dx = y?",
                new String[]{"y = e^x + C", "y = Ce^x", "y = x + C", "y = ln(x) + C"},
                "y = Ce^x",
                3
        ));
        allQuestions.add(new Question(
                "What is the value of the integral ∫(0 to π) sin(x) dx?",
                new String[]{"0", "1", "2", "π"},
                "2",
                3
        ));
        allQuestions.add(new Question(
                "What is the probability of getting exactly 2 heads in 3 coin tosses?",
                new String[]{"1/8", "3/8", "1/2", "5/8"},
                "3/8",
                3
        ));
        allQuestions.add(new Question(
                "What is the eigenvalue of the matrix [[2, 0], [0, 3]] corresponding to the eigenvector [1, 0]?",
                new String[]{"2", "3", "4", "5"},
                "2",
                3
        ));
        allQuestions.add(new Question(
                "What is the value of the sum of the first n terms of the geometric series 1 + 2 + 4 + ... with n=3?",
                new String[]{"6", "7", "8", "9"},
                "7",
                3
        ));
        allQuestions.add(new Question(
                "What is the Taylor series expansion of e^x around x=0 up to the second term?",
                new String[]{"1 + x", "1 + x + x²/2", "x + x²/2", "1 + x²"},
                "1 + x",
                3
        ));
        allQuestions.add(new Question(
                "What is the value of the double integral ∫(0 to 1) ∫(0 to 1) xy dx dy?",
                new String[]{"1/4", "1/2", "1", "2"},
                "1/4",
                3
        ));
        allQuestions.add(new Question(
                "What is the rank of a 3x3 matrix with linearly dependent rows?",
                new String[]{"0", "1", "2", "3"},
                "2",
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
            addQuestion(q, i + 1); // Pass question number
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
        resultIntent.putExtra("math_score", scoreResult);
        setResult(RESULT_OK, resultIntent);
        finish(); // End activity
    }
}