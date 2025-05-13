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

public class SoftwareEngineeringScreen extends AppCompatActivity {

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
        setContentView(R.layout.activity_software_engineering_screen);

        questionContainer = findViewById(R.id.SoftwareEngineeringQuestionContainer);

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
                "What is the first phase of the Software Development Life Cycle (SDLC)?",
                new String[]{"Design", "Requirement Analysis", "Testing", "Implementation"},
                "Requirement Analysis",
                1
        ));
        allQuestions.add(new Question(
                "What does UML stand for?",
                new String[]{"Unified Modeling Language", "User Machine Learning", "Universal Machine Logic", "Unique Modeling Language"},
                "Unified Modeling Language",
                1
        ));
        allQuestions.add(new Question(
                "What is Agile methodology mainly focused on?",
                new String[]{"Strict documentation", "Customer collaboration and flexibility", "Lengthy planning", "Waterfall execution"},
                "Customer collaboration and flexibility",
                1
        ));
        allQuestions.add(new Question(
                "Which model is also called the 'linear sequential model'?",
                new String[]{"Agile Model", "Waterfall Model", "Spiral Model", "V-Model"},
                "Waterfall Model",
                1
        ));
        allQuestions.add(new Question(
                "In software testing, what does 'black box testing' mean?",
                new String[]{"Testing internal logic", "Testing without looking at internal code", "Unit testing", "Performance testing"},
                "Testing without looking at internal code",
                1
        ));
        allQuestions.add(new Question(
                "What is the purpose of a use case diagram?",
                new String[]{"Code structure", "System behavior", "Data flow", "Class hierarchy"},
                "System behavior",
                1
        ));
        allQuestions.add(new Question(
                "Which SDLC model incorporates risk analysis?",
                new String[]{"Waterfall", "Agile", "Spiral", "V-Model"},
                "Spiral",
                1
        ));
        allQuestions.add(new Question(
                "What does RAD stand for in software development?",
                new String[]{"Rapid Application Development", "Real-time Analysis Design", "Robust Application Delivery", "Reliable Agile Development"},
                "Rapid Application Development",
                1
        ));
        allQuestions.add(new Question(
                "What is the primary goal of unit testing?",
                new String[]{"Test system integration", "Test individual components", "Test performance", "Test user interface"},
                "Test individual components",
                1
        ));
        allQuestions.add(new Question(
                "What is a characteristic of the Waterfall model?",
                new String[]{"Iterative development", "Sequential phases", "Flexible requirements", "Continuous integration"},
                "Sequential phases",
                1
        ));
        allQuestions.add(new Question(
                "Which document outlines project objectives and scope?",
                new String[]{"SRS", "Design Document", "Test Plan", "User Manual"},
                "SRS",
                1
        ));
        allQuestions.add(new Question(
                "What does the term 'refactoring' refer to?",
                new String[]{"Rewriting code", "Improving code structure", "Debugging", "Adding features"},
                "Improving code structure",
                1
        ));
        allQuestions.add(new Question(
                "Which testing is performed by end-users?",
                new String[]{"Unit testing", "Integration testing", "Acceptance testing", "System testing"},
                "Acceptance testing",
                1
        ));
        allQuestions.add(new Question(
                "What is a sprint in Agile methodology?",
                new String[]{"A testing phase", "A short development cycle", "A documentation phase", "A deployment phase"},
                "A short development cycle",
                1
        ));
        allQuestions.add(new Question(
                "What is the purpose of a class diagram in UML?",
                new String[]{"Show system behavior", "Show system structure", "Show data flow", "Show user interactions"},
                "Show system structure",
                1
        ));
        allQuestions.add(new Question(
                "Which methodology emphasizes early delivery of working software?",
                new String[]{"Waterfall", "Agile", "V-Model", "Spiral"},
                "Agile",
                1
        ));
        allQuestions.add(new Question(
                "What does 'SRS' stand for?",
                new String[]{"Software Requirement Specification", "System Resource Standard", "Software Review System", "System Requirement Solution"},
                "Software Requirement Specification",
                1
        ));
        allQuestions.add(new Question(
                "What is the purpose of integration testing?",
                new String[]{"Test individual units", "Test system as a whole", "Test module interactions", "Test user acceptance"},
                "Test module interactions",
                1
        ));
        allQuestions.add(new Question(
                "What is a key feature of Scrum?",
                new String[]{"Fixed requirements", "Daily stand-up meetings", "Sequential phases", "Extensive documentation"},
                "Daily stand-up meetings",
                1
        ));
        allQuestions.add(new Question(
                "What does the V-Model emphasize?",
                new String[]{"Iterative development", "Testing at each phase", "Rapid prototyping", "Continuous deployment"},
                "Testing at each phase",
                1
        ));

        // Medium Questions (20)
        allQuestions.add(new Question(
                "What is the main advantage of the Spiral model?",
                new String[]{"Low cost", "Risk management", "Fixed requirements", "Fast delivery"},
                "Risk management",
                2
        ));
        allQuestions.add(new Question(
                "Which design pattern separates object construction from its representation?",
                new String[]{"Singleton", "Factory", "Builder", "Adapter"},
                "Builder",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of continuous integration?",
                new String[]{"Reduce bugs", "Automate deployment", "Frequent code integration", "Improve documentation"},
                "Frequent code integration",
                2
        ));
        allQuestions.add(new Question(
                "Which UML diagram shows the sequence of operations?",
                new String[]{"Class Diagram", "Use Case Diagram", "Sequence Diagram", "Activity Diagram"},
                "Sequence Diagram",
                2
        ));
        allQuestions.add(new Question(
                "What is a key principle of the Agile Manifesto?",
                new String[]{"Comprehensive documentation", "Customer collaboration over contract negotiation", "Following a plan", "Process over people"},
                "Customer collaboration over contract negotiation",
                2
        ));
        allQuestions.add(new Question(
                "Which testing focuses on system performance under load?",
                new String[]{"Unit testing", "Integration testing", "Stress testing", "Acceptance testing"},
                "Stress testing",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of a design pattern?",
                new String[]{"Write new code", "Solve common design problems", "Debug code", "Test software"},
                "Solve common design problems",
                2
        ));
        allQuestions.add(new Question(
                "Which SDLC model is best for large, complex projects?",
                new String[]{"Waterfall", "Agile", "Spiral", "RAD"},
                "Spiral",
                2
        ));
        allQuestions.add(new Question(
                "What does the Singleton pattern ensure?",
                new String[]{"Multiple instances", "Single instance of a class", "Thread safety", "Data encapsulation"},
                "Single instance of a class",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of regression testing?",
                new String[]{"Test new features", "Ensure existing functionality works", "Test performance", "Test user interface"},
                "Ensure existing functionality works",
                2
        ));
        allQuestions.add(new Question(
                "Which diagram shows the flow of data in a system?",
                new String[]{"Class Diagram", "Data Flow Diagram", "Sequence Diagram", "Use Case Diagram"},
                "Data Flow Diagram",
                2
        ));
        allQuestions.add(new Question(
                "What is a key characteristic of DevOps?",
                new String[]{"Manual testing", "Collaboration between development and operations", "Fixed requirements", "Sequential phases"},
                "Collaboration between development and operations",
                2
        ));
        allQuestions.add(new Question(
                "Which pattern is used to adapt incompatible interfaces?",
                new String[]{"Factory", "Adapter", "Observer", "Decorator"},
                "Adapter",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of a prototype in software development?",
                new String[]{"Final product", "Test concepts and gather feedback", "Write documentation", "Deploy software"},
                "Test concepts and gather feedback",
                2
        ));
        allQuestions.add(new Question(
                "Which methodology uses iterative development with fixed time-boxes?",
                new String[]{"Waterfall", "Scrum", "V-Model", "RAD"},
                "Scrum",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of a test case?",
                new String[]{"Write code", "Define test conditions and expected results", "Debug code", "Design system"},
                "Define test conditions and expected results",
                2
        ));
        allQuestions.add(new Question(
                "Which UML diagram is used to model workflows?",
                new String[]{"Class Diagram", "Activity Diagram", "Sequence Diagram", "Component Diagram"},
                "Activity Diagram",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of code reviews?",
                new String[]{"Write new code", "Improve code quality", "Test software", "Deploy software"},
                "Improve code quality",
                2
        ));
        allQuestions.add(new Question(
                "Which testing is done to verify system security?",
                new String[]{"Unit testing", "Security testing", "Integration testing", "Performance testing"},
                "Security testing",
                2
        ));
        allQuestions.add(new Question(
                "What is a key feature of Kanban?",
                new String[]{"Fixed sprints", "Visual workflow management", "Sequential phases", "Extensive documentation"},
                "Visual workflow management",
                2
        ));

        // Difficult Questions (10)
        allQuestions.add(new Question(
                "What is the main challenge of applying the Waterfall model to large projects?",
                new String[]{"Lack of flexibility", "High cost", "Slow testing", "Poor documentation"},
                "Lack of flexibility",
                3
        ));
        allQuestions.add(new Question(
                "Which design pattern allows adding responsibilities to objects dynamically?",
                new String[]{"Singleton", "Factory", "Decorator", "Adapter"},
                "Decorator",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of the Observer pattern?",
                new String[]{"Manage object creation", "Notify objects of state changes", "Adapt interfaces", "Encapsulate algorithms"},
                "Notify objects of state changes",
                3
        ));
        allQuestions.add(new Question(
                "Which SDLC model is best for projects with unclear requirements?",
                new String[]{"Waterfall", "Agile", "V-Model", "Spiral"},
                "Agile",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of the Strategy pattern?",
                new String[]{"Manage object creation", "Encapsulate interchangeable algorithms", "Adapt interfaces", "Add responsibilities"},
                "Encapsulate interchangeable algorithms",
                3
        ));
        allQuestions.add(new Question(
                "Which testing verifies system behavior under extreme conditions?",
                new String[]{"Unit testing", "Integration testing", "Stress testing", "Regression testing"},
                "Stress testing",
                3
        ));
        allQuestions.add(new Question(
                "What is the main benefit of using microservices architecture?",
                new String[]{"Monolithic codebase", "Independent deployment of services", "Centralized control", "Fixed requirements"},
                "Independent deployment of services",
                3
        ));
        allQuestions.add(new Question(
                "Which UML diagram is used to model physical components?",
                new String[]{"Class Diagram", "Component Diagram", "Sequence Diagram", "Activity Diagram"},
                "Component Diagram",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of the Facade pattern?",
                new String[]{"Simplify complex subsystem interfaces", "Create objects", "Notify state changes", "Encapsulate algorithms"},
                "Simplify complex subsystem interfaces",
                3
        ));
        allQuestions.add(new Question(
                "Which methodology is best for projects requiring frequent risk assessment?",
                new String[]{"Waterfall", "Agile", "Spiral", "V-Model"},
                "Spiral",
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
        resultIntent.putExtra("se_score", scoreResult);
        setResult(RESULT_OK, resultIntent);
        finish(); // End activity
    }
}