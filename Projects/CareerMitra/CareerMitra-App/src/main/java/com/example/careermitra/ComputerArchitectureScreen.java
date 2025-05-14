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

public class ComputerArchitectureScreen extends AppCompatActivity {

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
        setContentView(R.layout.activity_computer_architecture_screen);

        questionContainer = findViewById(R.id.ComputerArchitectureQuestionContainer);

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
                "What does CPU stand for?",
                new String[]{"Central Processing Unit", "Control Processing Unit", "Central Programming Unit", "Compute Processing Unit"},
                "Central Processing Unit",
                1
        ));
        allQuestions.add(new Question(
                "Which memory is volatile?",
                new String[]{"ROM", "Hard Disk", "RAM", "SSD"},
                "RAM",
                1
        ));
        allQuestions.add(new Question(
                "What is the full form of ALU?",
                new String[]{"Arithmetic Logic Unit", "Array Logic Unit", "Advanced Logic Unit", "Automatic Logic Unit"},
                "Arithmetic Logic Unit",
                1
        ));
        allQuestions.add(new Question(
                "Which component connects the CPU and main memory?",
                new String[]{"Control Unit", "Bus", "Cache", "Register"},
                "Bus",
                1
        ));
        allQuestions.add(new Question(
                "What type of memory is ROM?",
                new String[]{"Volatile Memory", "Non-Volatile Memory", "Temporary Memory", "None of the above"},
                "Non-Volatile Memory",
                1
        ));
        allQuestions.add(new Question(
                "What is the primary function of the control unit?",
                new String[]{"Perform arithmetic", "Coordinate CPU operations", "Store data", "Manage I/O"},
                "Coordinate CPU operations",
                1
        ));
        allQuestions.add(new Question(
                "Which memory is closest to the CPU?",
                new String[]{"RAM", "Cache", "ROM", "Hard Disk"},
                "Cache",
                1
        ));
        allQuestions.add(new Question(
                "What does RAM stand for?",
                new String[]{"Read Access Memory", "Random Access Memory", "Rapid Access Memory", "Read-Only Memory"},
                "Random Access Memory",
                1
        ));
        allQuestions.add(new Question(
                "What is the purpose of registers in a CPU?",
                new String[]{"Long-term storage", "Temporary data storage", "Execute instructions", "Manage I/O"},
                "Temporary data storage",
                1
        ));
        allQuestions.add(new Question(
                "Which architecture uses a single bus for data and instructions?",
                new String[]{"Harvard", "Von Neumann", "RISC", "CISC"},
                "Von Neumann",
                1
        ));
        allQuestions.add(new Question(
                "What does GPU stand for?",
                new String[]{"General Processing Unit", "Graphics Processing Unit", "Global Processing Unit", "Gateway Processing Unit"},
                "Graphics Processing Unit",
                1
        ));
        allQuestions.add(new Question(
                "Which component fetches instructions from memory?",
                new String[]{"ALU", "Control Unit", "Program Counter", "Cache"},
                "Program Counter",
                1
        ));
        allQuestions.add(new Question(
                "What is the role of the instruction register?",
                new String[]{"Store data", "Hold current instruction", "Perform arithmetic", "Manage memory"},
                "Hold current instruction",
                1
        ));
        allQuestions.add(new Question(
                "What does BIOS stand for?",
                new String[]{"Basic Input/Output System", "Binary Input/Output System", "Basic Instruction Operating System", "Buffered Input/Output System"},
                "Basic Input/Output System",
                1
        ));
        allQuestions.add(new Question(
                "Which memory type is used for permanent storage?",
                new String[]{"RAM", "Cache", "ROM", "Register"},
                "ROM",
                1
        ));
        allQuestions.add(new Question(
                "What is the function of the data bus?",
                new String[]{"Execute instructions", "Transfer data", "Store instructions", "Control CPU"},
                "Transfer data",
                1
        ));
        allQuestions.add(new Question(
                "What does RISC stand for?",
                new String[]{"Reduced Instruction Set Computer", "Rapid Instruction Set Computer", "Restricted Instruction Set Computer", "Random Instruction Set Computer"},
                "Reduced Instruction Set Computer",
                1
        ));
        allQuestions.add(new Question(
                "Which component performs arithmetic operations?",
                new String[]{"Control Unit", "ALU", "Register", "Cache"},
                "ALU",
                1
        ));
        allQuestions.add(new Question(
                "What is the purpose of the address bus?",
                new String[]{"Transfer data", "Specify memory locations", "Execute instructions", "Store data"},
                "Specify memory locations",
                1
        ));
        allQuestions.add(new Question(
                "What does SRAM stand for?",
                new String[]{"Static Random Access Memory", "Sequential Random Access Memory", "Systematic Read Access Memory", "Standard Random Access Memory"},
                "Static Random Access Memory",
                1
        ));

        // Medium Questions (20)
        allQuestions.add(new Question(
                "What is the purpose of cache memory?",
                new String[]{"Permanent storage", "Reduce CPU-memory access time", "Execute instructions", "Manage I/O"},
                "Reduce CPU-memory access time",
                2
        ));
        allQuestions.add(new Question(
                "Which architecture separates data and instruction memory?",
                new String[]{"Von Neumann", "Harvard", "CISC", "Pipelined"},
                "Harvard",
                2
        ));
        allQuestions.add(new Question(
                "What is the role of pipelining in a CPU?",
                new String[]{"Increase clock speed", "Parallel instruction execution", "Reduce power consumption", "Manage memory"},
                "Parallel instruction execution",
                2
        ));
        allQuestions.add(new Question(
                "What does CISC stand for?",
                new String[]{"Complex Instruction Set Computer", "Compact Instruction Set Computer", "Combined Instruction Set Computer", "Centralized Instruction Set Computer"},
                "Complex Instruction Set Computer",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of virtual memory?",
                new String[]{"Increase CPU speed", "Extend RAM using storage", "Manage registers", "Cache instructions"},
                "Extend RAM using storage",
                2
        ));
        allQuestions.add(new Question(
                "Which component handles interrupt requests?",
                new String[]{"ALU", "Control Unit", "Cache", "Register"},
                "Control Unit",
                2
        ));
        allQuestions.add(new Question(
                "What is the advantage of a multi-core processor?",
                new String[]{"Lower power consumption", "Parallel task execution", "Smaller size", "Single-thread performance"},
                "Parallel task execution",
                2
        ));
        allQuestions.add(new Question(
                "What does DMA stand for?",
                new String[]{"Direct Memory Access", "Dynamic Memory Allocation", "Data Memory Access", "Direct Machine Access"},
                "Direct Memory Access",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of the memory management unit (MMU)?",
                new String[]{"Execute instructions", "Translate virtual to physical addresses", "Store data", "Manage I/O"},
                "Translate virtual to physical addresses",
                2
        ));
        allQuestions.add(new Question(
                "Which type of memory is faster than DRAM?",
                new String[]{"ROM", "SRAM", "Flash", "Hard Disk"},
                "SRAM",
                2
        ));
        allQuestions.add(new Question(
                "What is the role of the instruction cycle?",
                new String[]{"Fetch and execute instructions", "Store data", "Manage memory", "Handle interrupts"},
                "Fetch and execute instructions",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of a branch predictor?",
                new String[]{"Reduce power consumption", "Improve pipeline efficiency", "Manage memory", "Store data"},
                "Improve pipeline efficiency",
                2
        ));
        allQuestions.add(new Question(
                "Which architecture is designed for low power consumption?",
                new String[]{"CISC", "RISC", "Von Neumann", "Harvard"},
                "RISC",
                2
        ));
        allQuestions.add(new Question(
                "What is the function of the stack pointer?",
                new String[]{"Store instructions", "Track stack memory", "Execute arithmetic", "Manage I/O"},
                "Track stack memory",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of cache coherence?",
                new String[]{"Reduce power consumption", "Ensure consistent data in multi-core systems", "Increase clock speed", "Manage interrupts"},
                "Ensure consistent data in multi-core systems",
                2
        ));
        allQuestions.add(new Question(
                "What does EPROM stand for?",
                new String[]{"Erasable Programmable Read-Only Memory", "Enhanced Program Read-Only Memory", "External Programmable Read-Only Memory", "Embedded Program Read-Only Memory"},
                "Erasable Programmable Read-Only Memory",
                2
        ));
        allQuestions.add(new Question(
                "What is the role of the accumulator in a CPU?",
                new String[]{"Store intermediate results", "Manage memory", "Execute instructions", "Handle interrupts"},
                "Store intermediate results",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of the bus interface unit?",
                new String[]{"Execute arithmetic", "Manage data transfer", "Store instructions", "Handle interrupts"},
                "Manage data transfer",
                2
        ));
        allQuestions.add(new Question(
                "Which memory type is used in USB flash drives?",
                new String[]{"SRAM", "DRAM", "Flash", "ROM"},
                "Flash",
                2
        ));
        allQuestions.add(new Question(
                "What is the advantage of out-of-order execution?",
                new String[]{"Lower power consumption", "Improved performance", "Reduced memory usage", "Simplified design"},
                "Improved performance",
                2
        ));

        // Difficult Questions (10)
        allQuestions.add(new Question(
                "What is the purpose of speculative execution in CPUs?",
                new String[]{"Reduce power consumption", "Execute instructions ahead of time", "Manage memory", "Store data"},
                "Execute instructions ahead of time",
                3
        ));
        allQuestions.add(new Question(
                "Which technique mitigates pipeline stalls?",
                new String[]{"Cache coherence", "Branch prediction", "Virtual memory", "Direct memory access"},
                "Branch prediction",
                3
        ));
        allQuestions.add(new Question(
                "What is the significance of the memory hierarchy?",
                new String[]{"Increase power consumption", "Optimize speed-cost trade-off", "Simplify design", "Reduce instruction set"},
                "Optimize speed-cost trade-off",
                3
        ));
        allQuestions.add(new Question(
                "Which architecture is used in ARM processors?",
                new String[]{"CISC", "RISC", "Von Neumann", "Harvard"},
                "RISC",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of the TLB?",
                new String[]{"Store instructions", "Cache virtual-to-physical mappings", "Execute arithmetic", "Manage I/O"},
                "Cache virtual-to-physical mappings",
                3
        ));
        allQuestions.add(new Question(
                "Which issue affects multi-core processor performance?",
                new String[]{"Cache coherence", "Instruction set size", "Clock speed", "Register size"},
                "Cache coherence",
                3
        ));
        allQuestions.add(new Question(
                "What is the role of superscalar architecture?",
                new String[]{"Execute multiple instructions per cycle", "Reduce power consumption", "Manage memory", "Store data"},
                "Execute multiple instructions per cycle",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of the write-back cache policy?",
                new String[]{"Increase power consumption", "Improve write performance", "Simplify design", "Reduce memory usage"},
                "Improve write performance",
                3
        ));
        allQuestions.add(new Question(
                "Which technique is used to reduce power in modern CPUs?",
                new String[]{"Dynamic Voltage and Frequency Scaling", "Cache coherence", "Branch prediction", "Virtual memory"},
                "Dynamic Voltage and Frequency Scaling",
                3
        ));
        allQuestions.add(new Question(
                "What is the significance of Amdahl’s Law in parallel processing?",
                new String[]{"Limits speedup from parallelization", "Increases clock speed", "Reduces memory usage", "Simplifies instruction set"},
                "Limits speedup from parallelization",
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
        resultIntent.putExtra("ca_score", scoreResult);
        setResult(RESULT_OK, resultIntent);
        finish(); // End activity
    }
}