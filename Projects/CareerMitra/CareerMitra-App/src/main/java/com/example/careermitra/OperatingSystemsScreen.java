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

public class OperatingSystemsScreen extends AppCompatActivity {

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
        setContentView(R.layout.activity_operating_systems_screen);

        questionContainer = findViewById(R.id.questionContainer1);

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
                "Which of the following is an Operating System?",
                new String[]{"Oracle", "Linux", "Google", "Intel"},
                "Linux",
                1
        ));
        allQuestions.add(new Question(
                "Which one is NOT a function of Operating System?",
                new String[]{"Memory Management", "Processor Management", "Providing Internet", "Device Management"},
                "Providing Internet",
                1
        ));
        allQuestions.add(new Question(
                "Which of the following is a mobile Operating System?",
                new String[]{"Android", "Windows 7", "MacOS", "Ubuntu"},
                "Android",
                1
        ));
        allQuestions.add(new Question(
                "What is the core of the Operating System called?",
                new String[]{"Shell", "Command Prompt", "Kernel", "Compiler"},
                "Kernel",
                1
        ));
        allQuestions.add(new Question(
                "Which of the following is a multi-user OS?",
                new String[]{"MS-DOS", "Windows XP", "UNIX", "None of these"},
                "UNIX",
                1
        ));
        allQuestions.add(new Question(
                "What is the main purpose of an operating system?",
                new String[]{"To make the most efficient use of computer hardware", "To allow people to use the computer", "To keep systems programmers employed", "To make computers easier to use"},
                "To make the most efficient use of computer hardware",
                1
        ));
        allQuestions.add(new Question(
                "Which of these is a real-time operating system?",
                new String[]{"Windows 10", "macOS", "VxWorks", "Ubuntu"},
                "VxWorks",
                1
        ));
        allQuestions.add(new Question(
                "What manages file systems in an OS?",
                new String[]{"File Manager", "Memory Manager", "Process Manager", "Device Manager"},
                "File Manager",
                1
        ));
        allQuestions.add(new Question(
                "Which component loads first when OS boots up?",
                new String[]{"Shell", "Kernel", "Applications", "Drivers"},
                "Kernel",
                1
        ));
        allQuestions.add(new Question(
                "What is virtual memory?",
                new String[]{"Memory on the hard disk used as RAM", "Extra fast memory", "Memory used by virtual machines", "Non-existent memory"},
                "Memory on the hard disk used as RAM",
                1
        ));
        allQuestions.add(new Question(
                "Which scheduling algorithm allocates CPU to the process with highest priority?",
                new String[]{"FCFS", "Round Robin", "Priority Scheduling", "Shortest Job First"},
                "Priority Scheduling",
                1
        ));
        allQuestions.add(new Question(
                "What is thrashing?",
                new String[]{"Excessive page swapping", "High CPU usage", "Disk fragmentation", "Memory leak"},
                "Excessive page swapping",
                1
        ));
        allQuestions.add(new Question(
                "Which of these is NOT an OS?",
                new String[]{"Windows", "Linux", "Oracle", "macOS"},
                "Oracle",
                1
        ));
        allQuestions.add(new Question(
                "What is the function of a device driver?",
                new String[]{"To allow OS to interact with hardware", "To speed up the computer", "To protect against viruses", "To manage files"},
                "To allow OS to interact with hardware",
                1
        ));
        allQuestions.add(new Question(
                "Which OS is open source?",
                new String[]{"Windows", "macOS", "Linux", "iOS"},
                "Linux",
                1
        ));
        allQuestions.add(new Question(
                "What is a deadlock?",
                new String[]{"When two processes are waiting for each other", "When a process crashes", "When memory is full", "When CPU overheats"},
                "When two processes are waiting for each other",
                1
        ));
        allQuestions.add(new Question(
                "Which of these is a Windows OS?",
                new String[]{"Mojave", "Big Sur", "Vista", "Bionic"},
                "Vista",
                1
        ));
        allQuestions.add(new Question(
                "What is a process in OS terms?",
                new String[]{"A program in execution", "A file on disk", "A user account", "A hardware component"},
                "A program in execution",
                1
        ));
        allQuestions.add(new Question(
                "Which OS uses the Darwin kernel?",
                new String[]{"Windows", "Linux", "macOS", "Android"},
                "macOS",
                1
        ));
        allQuestions.add(new Question(
                "What is the main advantage of multiprogramming?",
                new String[]{"Increased CPU utilization", "Faster boot time", "Smaller OS size", "Better graphics"},
                "Increased CPU utilization",
                1
        ));

        // Medium Questions (20)
        allQuestions.add(new Question(
                "Which scheduling algorithm is preemptive?",
                new String[]{"FCFS", "Shortest Job First", "Round Robin", "None of these"},
                "Round Robin",
                2
        ));
        allQuestions.add(new Question(
                "What is RAID used for?",
                new String[]{"Memory management", "Disk redundancy", "Process scheduling", "User authentication"},
                "Disk redundancy",
                2
        ));
        allQuestions.add(new Question(
                "Which page replacement algorithm suffers from Belady's anomaly?",
                new String[]{"FIFO", "LRU", "Optimal", "MRU"},
                "FIFO",
                2
        ));
        allQuestions.add(new Question(
                "What is the main purpose of semaphores?",
                new String[]{"Process synchronization", "Memory allocation", "File management", "Device control"},
                "Process synchronization",
                2
        ));
        allQuestions.add(new Question(
                "Which of these is a microkernel?",
                new String[]{"Windows NT", "Linux", "macOS", "QNX"},
                "QNX",
                2
        ));
        allQuestions.add(new Question(
                "What is the main advantage of paging?",
                new String[]{"Eliminates external fragmentation", "Faster process execution", "Smaller OS size", "Better security"},
                "Eliminates external fragmentation",
                2
        ));
        allQuestions.add(new Question(
                "Which of these is NOT a process state?",
                new String[]{"New", "Ready", "Waiting", "Idle"},
                "Idle",
                2
        ));
        allQuestions.add(new Question(
                "What is the main function of the OS scheduler?",
                new String[]{"Allocate CPU to processes", "Manage memory", "Handle files", "Control devices"},
                "Allocate CPU to processes",
                2
        ));
        allQuestions.add(new Question(
                "Which of these is a distributed OS?",
                new String[]{"Windows", "Linux", "Amoeba", "macOS"},
                "Amoeba",
                2
        ));
        allQuestions.add(new Question(
                "What is the main purpose of system calls?",
                new String[]{"Interface between processes and OS", "Speed up the computer", "Manage hardware directly", "Provide user interface"},
                "Interface between processes and OS",
                2
        ));
        allQuestions.add(new Question(
                "Which of these is a solution to the dining philosophers problem?",
                new String[]{"Allow at most 4 philosophers", "Use semaphores", "Resource hierarchy", "All of these"},
                "All of these",
                2
        ));
        allQuestions.add(new Question(
                "What is the main advantage of multithreading?",
                new String[]{"Better resource utilization", "Smaller program size", "Faster CPU", "Simpler programming"},
                "Better resource utilization",
                2
        ));
        allQuestions.add(new Question(
                "Which of these is NOT a memory allocation method?",
                new String[]{"First-fit", "Best-fit", "Worst-fit", "Quick-fit"},
                "Quick-fit",
                2
        ));
        allQuestions.add(new Question(
                "What is the main purpose of the TLB?",
                new String[]{"Speed up address translation", "Manage files", "Schedule processes", "Handle interrupts"},
                "Speed up address translation",
                2
        ));
        allQuestions.add(new Question(
                "Which of these is NOT a UNIX command?",
                new String[]{"ls", "dir", "grep", "chmod"},
                "dir",
                2
        ));
        allQuestions.add(new Question(
                "What is the main purpose of interrupts?",
                new String[]{"Handle asynchronous events", "Speed up the CPU", "Manage memory", "Provide security"},
                "Handle asynchronous events",
                2
        ));
        allQuestions.add(new Question(
                "Which of these is a message passing IPC mechanism?",
                new String[]{"Shared memory", "Pipes", "Semaphores", "Mutex"},
                "Pipes",
                2
        ));
        allQuestions.add(new Question(
                "What is the main purpose of the fork() system call?",
                new String[]{"Create new process", "Allocate memory", "Open file", "Send signal"},
                "Create new process",
                2
        ));
        allQuestions.add(new Question(
                "Which of these is NOT a Windows subsystem?",
                new String[]{"Win32", "POSIX", "OS/2", "DOS"},
                "DOS",
                2
        ));
        allQuestions.add(new Question(
                "What is the main purpose of the bootloader?",
                new String[]{"Load the OS kernel", "Manage files", "Handle interrupts", "Provide user interface"},
                "Load the OS kernel",
                2
        ));

        // Difficult Questions (10)
        allQuestions.add(new Question(
                "Which of these is NOT a real-time scheduling algorithm?",
                new String[]{"Rate-monotonic", "Earliest-deadline-first", "Least-slack-time", "Round-robin"},
                "Round-robin",
                3
        ));
        allQuestions.add(new Question(
                "What is the main challenge in implementing microkernels?",
                new String[]{"Performance overhead", "Memory requirements", "Security issues", "Hardware compatibility"},
                "Performance overhead",
                3
        ));
        allQuestions.add(new Question(
                "Which of these is NOT a solution to the critical section problem?",
                new String[]{"Peterson's solution", "Test-and-set", "Counting semaphore", "Monitor"},
                "Counting semaphore",
                3
        ));
        allQuestions.add(new Question(
                "What is the main advantage of capability-based security?",
                new String[]{"Fine-grained access control", "Better performance", "Simpler implementation", "Backward compatibility"},
                "Fine-grained access control",
                3
        ));
        allQuestions.add(new Question(
                "Which of these is NOT a virtualization technique?",
                new String[]{"Full virtualization", "Paravirtualization", "Hardware-assisted", "Microvirtualization"},
                "Microvirtualization",
                3
        ));
        allQuestions.add(new Question(
                "What is the main purpose of the buddy system?",
                new String[]{"Memory allocation", "Process scheduling", "File management", "Device control"},
                "Memory allocation",
                3
        ));
        allQuestions.add(new Question(
                "Which of these is NOT a type of RAID?",
                new String[]{"RAID 0", "RAID 1", "RAID 5", "RAID X"},
                "RAID X",
                3
        ));
        allQuestions.add(new Question(
                "What is the main advantage of copy-on-write?",
                new String[]{"Reduces memory usage", "Speeds up process creation", "Improves security", "Simplifies programming"},
                "Reduces memory usage",
                3
        ));
        allQuestions.add(new Question(
                "Which of these is NOT a component of a microkernel?",
                new String[]{"Process management", "Memory management", "File system", "Device drivers"},
                "File system",
                3
        ));
        allQuestions.add(new Question(
                "What is the main purpose of the Banker's algorithm?",
                new String[]{"Deadlock avoidance", "Memory allocation", "Process scheduling", "File management"},
                "Deadlock avoidance",
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
        resultIntent.putExtra("os_score", scoreResult);
        setResult(RESULT_OK, resultIntent);
        finish(); // End activity
    }
}