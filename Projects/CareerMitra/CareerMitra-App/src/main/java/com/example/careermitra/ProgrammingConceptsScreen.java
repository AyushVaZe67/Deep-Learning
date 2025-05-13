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

public class ProgrammingConceptsScreen extends AppCompatActivity {

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
        setContentView(R.layout.activity_programming_concepts_screen);

        questionContainer = findViewById(R.id.ProgrammingConceptsQuestionContainer);

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
                "What does JVM stand for?",
                new String[]{"Java Virtual Machine", "Java Variable Model", "Joint Virtual Mechanism", "Java Vendor Model"},
                "Java Virtual Machine",
                1
        ));
        allQuestions.add(new Question(
                "Which language is primarily used for developing Android apps?",
                new String[]{"Python", "Java", "C#", "Swift"},
                "Java",
                1
        ));
        allQuestions.add(new Question(
                "What is the purpose of a compiler?",
                new String[]{"Execute the program", "Translate code to machine code", "Design the UI", "Manage memory"},
                "Translate code to machine code",
                1
        ));
        allQuestions.add(new Question(
                "What is the correct way to start a loop in Java?",
                new String[]{"for (int i=0; i<10; i++)", "loop (i=0; i<10; i++)", "repeat until (i=10)", "start loop i=0 to 10"},
                "for (int i=0; i<10; i++)",
                1
        ));
        allQuestions.add(new Question(
                "What is the value of boolean variable after 'boolean a = true && false;'?",
                new String[]{"true", "false", "null", "error"},
                "false",
                1
        ));
        allQuestions.add(new Question(
                "What does OOP stand for?",
                new String[]{"Object-Oriented Programming", "Object-Optimized Process", "Operational Object Protocol", "Object Organization Principle"},
                "Object-Oriented Programming",
                1
        ));
        allQuestions.add(new Question(
                "Which keyword is used to inherit a class in Java?",
                new String[]{"extends", "implements", "inherits", "import"},
                "extends",
                1
        ));
        allQuestions.add(new Question(
                "What is the default value of an int variable in Java?",
                new String[]{"0", "1", "null", "-1"},
                "0",
                1
        ));
        allQuestions.add(new Question(
                "Which of these is NOT a primitive data type in Java?",
                new String[]{"int", "float", "String", "boolean"},
                "String",
                1
        ));
        allQuestions.add(new Question(
                "What is the correct syntax to create an object in Java?",
                new String[]{"ClassName obj = new ClassName();", "new ClassName obj;", "ClassName obj = ClassName();", "obj = new ClassName;"},
                "ClassName obj = new ClassName();",
                1
        ));
        allQuestions.add(new Question(
                "Which method must be implemented by all Java threads?",
                new String[]{"start()", "run()", "execute()", "init()"},
                "run()",
                1
        ));
        allQuestions.add(new Question(
                "What is the size of int in Java?",
                new String[]{"16-bit", "32-bit", "64-bit", "Depends on system"},
                "32-bit",
                1
        ));
        allQuestions.add(new Question(
                "Which collection implements FIFO principle?",
                new String[]{"ArrayList", "LinkedList", "Stack", "Queue"},
                "Queue",
                1
        ));
        allQuestions.add(new Question(
                "What is the superclass of all classes in Java?",
                new String[]{"Object", "Class", "Main", "Super"},
                "Object",
                1
        ));
        allQuestions.add(new Question(
                "Which keyword is used to prevent method overriding?",
                new String[]{"static", "final", "private", "sealed"},
                "final",
                1
        ));
        allQuestions.add(new Question(
                "What is the correct way to handle exceptions in Java?",
                new String[]{"try-catch", "error-handle", "exception-block", "catch-throw"},
                "try-catch",
                1
        ));
        allQuestions.add(new Question(
                "Which of these is a marker interface?",
                new String[]{"Runnable", "Serializable", "Comparable", "Cloneable"},
                "Serializable",
                1
        ));
        allQuestions.add(new Question(
                "What does JDK stand for?",
                new String[]{"Java Development Kit", "Java Deployment Kit", "Java Design Kit", "Java Debugging Kit"},
                "Java Development Kit",
                1
        ));
        allQuestions.add(new Question(
                "Which method is called when an object is created?",
                new String[]{"main()", "init()", "constructor()", "new()"},
                "constructor()",
                1
        ));
        allQuestions.add(new Question(
                "What is the default access modifier in Java?",
                new String[]{"public", "private", "protected", "package-private"},
                "package-private",
                1
        ));

        // Medium Questions (20)
        allQuestions.add(new Question(
                "What is method overloading?",
                new String[]{"Same method name with different parameters", "Changing method return type", "Making method private", "Using same method in subclass"},
                "Same method name with different parameters",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'finally' block?",
                new String[]{"To handle exceptions", "To ensure code execution", "To terminate program", "To skip code execution"},
                "To ensure code execution",
                2
        ));
        allQuestions.add(new Question(
                "Which design pattern ensures only one instance of a class?",
                new String[]{"Factory", "Singleton", "Observer", "Decorator"},
                "Singleton",
                2
        ));
        allQuestions.add(new Question(
                "What is polymorphism in OOP?",
                new String[]{"Many forms of a single entity", "Hiding implementation details", "Bundling data with methods", "Creating hierarchical relationships"},
                "Many forms of a single entity",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'volatile' keyword?",
                new String[]{"To make variable constant", "To improve performance", "To ensure thread visibility", "To prevent garbage collection"},
                "To ensure thread visibility",
                2
        ));
        allQuestions.add(new Question(
                "Which is NOT a SOLID principle?",
                new String[]{"Single Responsibility", "Open-Closed", "Liskov Substitution", "Inheritance Principle"},
                "Inheritance Principle",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'transient' keyword?",
                new String[]{"To make variable serializable", "To prevent serialization", "To make variable thread-safe", "To mark deprecated variables"},
                "To prevent serialization",
                2
        ));
        allQuestions.add(new Question(
                "What is autoboxing in Java?",
                new String[]{"Automatic conversion between primitives and wrappers", "Automatic memory management", "Automatic exception handling", "Automatic thread synchronization"},
                "Automatic conversion between primitives and wrappers",
                2
        ));
        allQuestions.add(new Question(
                "Which is NOT a Java memory area?",
                new String[]{"Heap", "Stack", "Method Area", "Cache Area"},
                "Cache Area",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'assert' keyword?",
                new String[]{"To throw exceptions", "To test assumptions", "To debug code", "To log messages"},
                "To test assumptions",
                2
        ));
        allQuestions.add(new Question(
                "Which collection maintains insertion order?",
                new String[]{"HashSet", "TreeSet", "LinkedHashSet", "HashMap"},
                "LinkedHashSet",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'strictfp' keyword?",
                new String[]{"To enforce strict floating-point calculations", "To improve performance", "To make methods final", "To prevent inheritance"},
                "To enforce strict floating-point calculations",
                2
        ));
        allQuestions.add(new Question(
                "Which is NOT a type of inner class?",
                new String[]{"Static nested", "Member", "Local", "Anonymous"},
                "Member",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'native' keyword?",
                new String[]{"To indicate platform-specific implementation", "To create new objects", "To mark deprecated methods", "To improve performance"},
                "To indicate platform-specific implementation",
                2
        ));
        allQuestions.add(new Question(
                "Which is NOT a Java 8 feature?",
                new String[]{"Lambda expressions", "Stream API", "Modules", "Default methods"},
                "Modules",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'var' in Java 10+?",
                new String[]{"To create variables", "For type inference", "To mark variables as volatile", "To create variant types"},
                "For type inference",
                2
        ));
        allQuestions.add(new Question(
                "Which is NOT a functional interface?",
                new String[]{"Runnable", "Comparator", "Serializable", "Consumer"},
                "Serializable",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'Optional' class?",
                new String[]{"To handle null values", "To create optional parameters", "To mark optional code", "To provide alternative implementations"},
                "To handle null values",
                2
        ));
        allQuestions.add(new Question(
                "Which is NOT a JVM language?",
                new String[]{"Scala", "Kotlin", "Groovy", "TypeScript"},
                "TypeScript",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'try-with-resources'?",
                new String[]{"To handle multiple exceptions", "To automatically close resources", "To improve performance", "To simplify try-catch blocks"},
                "To automatically close resources",
                2
        ));

        // Difficult Questions (10)
        allQuestions.add(new Question(
                "What is double brace initialization?",
                new String[]{"Initialization using two braces", "Anonymous inner class with instance initializer", "Two-step constructor call", "Static block initialization"},
                "Anonymous inner class with instance initializer",
                3
        ));
        allQuestions.add(new Question(
                "What is the diamond problem in multiple inheritance?",
                new String[]{"Ambiguity when same method exists in multiple superclasses", "Memory consumption issue", "Performance degradation", "Constructor chaining problem"},
                "Ambiguity when same method exists in multiple superclasses",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'sun.misc.Unsafe' class?",
                new String[]{"To perform unsafe operations", "For low-level operations", "To bypass security checks", "All of the above"},
                "All of the above",
                3
        ));
        allQuestions.add(new Question(
                "What is the difference between '>>' and '>>>' operators?",
                new String[]{"No difference", ">>> is unsigned right shift", ">> is logical shift", ">>> is arithmetic shift"},
                ">>> is unsigned right shift",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'invokedynamic' bytecode?",
                new String[]{"To support dynamic languages", "To improve performance", "To enable reflection", "To support generics"},
                "To support dynamic languages",
                3
        ));
        allQuestions.add(new Question(
                "What is the difference between 'Comparable' and 'Comparator'?",
                new String[]{"No difference", "Comparable is for natural ordering, Comparator for custom", "Comparator is interface, Comparable is class", "Comparable is thread-safe"},
                "Comparable is for natural ordering, Comparator for custom",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'ClassValue' class?",
                new String[]{"To store class-specific values", "To create class instances", "To compare classes", "To inspect class metadata"},
                "To store class-specific values",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'VarHandle' in Java 9+?",
                new String[]{"To handle variables", "For safe variable access", "To replace reflection", "For low-level variable operations"},
                "For low-level variable operations",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'StackWalker' API?",
                new String[]{"To walk through stack frames", "To analyze stack traces", "To inspect call hierarchy", "All of the above"},
                "All of the above",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'MethodHandles'?",
                new String[]{"To replace reflection", "For low-level method operations", "To improve performance", "All of the above"},
                "All of the above",
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
        resultIntent.putExtra("programming_score", scoreResult);
        setResult(RESULT_OK, resultIntent);
        finish(); // End activity
    }
}