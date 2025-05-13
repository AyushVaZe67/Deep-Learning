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

public class ElectronicsSubjectsScreen extends AppCompatActivity {

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
        setContentView(R.layout.activity_electronics_subjects_screen);

        questionContainer = findViewById(R.id.ElectronicsQuestionContainer);

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
                "What does LED stand for?",
                new String[]{"Light Emitting Diode", "Low Energy Device", "Linear Electrical Diode", "Light Energy Diode"},
                "Light Emitting Diode",
                1
        ));
        allQuestions.add(new Question(
                "Which component stores electrical energy?",
                new String[]{"Resistor", "Capacitor", "Transistor", "Diode"},
                "Capacitor",
                1
        ));
        allQuestions.add(new Question(
                "What is the function of a diode?",
                new String[]{"Amplifies signals", "Allows current in one direction", "Stores energy", "Blocks voltage completely"},
                "Allows current in one direction",
                1
        ));
        allQuestions.add(new Question(
                "What unit is used to measure resistance?",
                new String[]{"Ohm", "Volt", "Ampere", "Watt"},
                "Ohm",
                1
        ));
        allQuestions.add(new Question(
                "Which device amplifies electronic signals?",
                new String[]{"Resistor", "Capacitor", "Transistor", "Inductor"},
                "Transistor",
                1
        ));
        allQuestions.add(new Question(
                "What is the unit of capacitance?",
                new String[]{"Farad", "Henry", "Ohm", "Volt"},
                "Farad",
                1
        ));
        allQuestions.add(new Question(
                "What does BJT stand for?",
                new String[]{"Base Junction Transistor", "Bipolar Junction Transistor", "Binary Junction Transistor", "Buffered Junction Transistor"},
                "Bipolar Junction Transistor",
                1
        ));
        allQuestions.add(new Question(
                "Which component opposes changes in current?",
                new String[]{"Capacitor", "Resistor", "Inductor", "Diode"},
                "Inductor",
                1
        ));
        allQuestions.add(new Question(
                "What is the primary function of a rectifier?",
                new String[]{"Amplify signals", "Convert AC to DC", "Store energy", "Regulate voltage"},
                "Convert AC to DC",
                1
        ));
        allQuestions.add(new Question(
                "What is the unit of power in electronics?",
                new String[]{"Watt", "Ohm", "Volt", "Ampere"},
                "Watt",
                1
        ));
        allQuestions.add(new Question(
                "Which law relates voltage, current, and resistance?",
                new String[]{"Kirchhoff’s Law", "Ohm’s Law", "Faraday’s Law", "Coulomb’s Law"},
                "Ohm’s Law",
                1
        ));
        allQuestions.add(new Question(
                "What does IC stand for in electronics?",
                new String[]{"Integrated Circuit", "Internal Capacitor", "Insulated Conductor", "Inductive Coil"},
                "Integrated Circuit",
                1
        ));
        allQuestions.add(new Question(
                "What is the purpose of a fuse in a circuit?",
                new String[]{"Amplify signals", "Protect from overcurrent", "Store energy", "Regulate voltage"},
                "Protect from overcurrent",
                1
        ));
        allQuestions.add(new Question(
                "Which component is used to smooth voltage in power supplies?",
                new String[]{"Resistor", "Capacitor", "Diode", "Transistor"},
                "Capacitor",
                1
        ));
        allQuestions.add(new Question(
                "What is the unit of inductance?",
                new String[]{"Farad", "Henry", "Ohm", "Volt"},
                "Henry",
                1
        ));
        allQuestions.add(new Question(
                "What does MOSFET stand for?",
                new String[]{"Metal Oxide Semiconductor Field Effect Transistor", "Modulated Signal Field Effect Transistor", "Metal Oxide Signal Frequency Transistor", "Multi-Oxide Semiconductor Transistor"},
                "Metal Oxide Semiconductor Field Effect Transistor",
                1
        ));
        allQuestions.add(new Question(
                "Which device converts electrical energy to mechanical energy?",
                new String[]{"Motor", "Generator", "Transformer", "Rectifier"},
                "Motor",
                1
        ));
        allQuestions.add(new Question(
                "What is the purpose of a voltage regulator?",
                new String[]{"Amplify signals", "Maintain constant voltage", "Convert AC to DC", "Store energy"},
                "Maintain constant voltage",
                1
        ));
        allQuestions.add(new Question(
                "Which component is used in a filter circuit?",
                new String[]{"Resistor", "Capacitor", "Transistor", "Diode"},
                "Capacitor",
                1
        ));
        allQuestions.add(new Question(
                "What is the function of a transformer?",
                new String[]{"Convert DC to AC", "Step up or step down voltage", "Store energy", "Amplify signals"},
                "Step up or step down voltage",
                1
        ));

        // Medium Questions (20)
        allQuestions.add(new Question(
                "What is the gain of an inverting operational amplifier determined by?",
                new String[]{"Input voltage", "Ratio of resistors", "Capacitance", "Inductance"},
                "Ratio of resistors",
                2
        ));
        allQuestions.add(new Question(
                "Which type of diode is used for voltage regulation?",
                new String[]{"Schottky diode", "Zener diode", "Light-emitting diode", "Tunnel diode"},
                "Zener diode",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of a Schmitt trigger?",
                new String[]{"Amplify signals", "Convert analog to digital", "Noise filtering", "Voltage regulation"},
                "Noise filtering",
                2
        ));
        allQuestions.add(new Question(
                "Which circuit law is used to analyze complex circuits?",
                new String[]{"Ohm’s Law", "Kirchhoff’s Law", "Faraday’s Law", "Coulomb’s Law"},
                "Kirchhoff’s Law",
                2
        ));
        allQuestions.add(new Question(
                "What is the function of an oscillator circuit?",
                new String[]{"Generate periodic signals", "Amplify signals", "Store energy", "Regulate voltage"},
                "Generate periodic signals",
                2
        ));
        allQuestions.add(new Question(
                "Which transistor configuration provides high current gain?",
                new String[]{"Common Base", "Common Emitter", "Common Collector", "Common Gate"},
                "Common Emitter",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of a band-pass filter?",
                new String[]{"Allow all frequencies", "Allow specific frequency range", "Block low frequencies", "Block high frequencies"},
                "Allow specific frequency range",
                2
        ));
        allQuestions.add(new Question(
                "Which component is critical in a switching power supply?",
                new String[]{"Resistor", "Capacitor", "Transistor", "Diode"},
                "Transistor",
                2
        ));
        allQuestions.add(new Question(
                "What is the significance of the Q factor in a resonant circuit?",
                new String[]{"Power loss", "Bandwidth quality", "Voltage gain", "Current amplification"},
                "Bandwidth quality",
                2
        ));
        allQuestions.add(new Question(
                "Which type of amplifier has the highest efficiency?",
                new String[]{"Class A", "Class B", "Class C", "Class AB"},
                "Class C",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of a Darlington pair?",
                new String[]{"Voltage regulation", "High current gain", "Noise filtering", "Frequency modulation"},
                "High current gain",
                2
        ));
        allQuestions.add(new Question(
                "Which diode is used for high-speed switching?",
                new String[]{"Zener diode", "Schottky diode", "Light-emitting diode", "Varactor diode"},
                "Schottky diode",
                2
        ));
        allQuestions.add(new Question(
                "What is the role of a crystal oscillator?",
                new String[]{"Amplify signals", "Provide stable frequency", "Store energy", "Regulate voltage"},
                "Provide stable frequency",
                2
        ));
        allQuestions.add(new Question(
                "Which circuit is used to convert digital signals to analog?",
                new String[]{"ADC", "DAC", "Op-Amp", "PLL"},
                "DAC",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of a choke in a circuit?",
                new String[]{"Amplify signals", "Block AC signals", "Store energy", "Regulate voltage"},
                "Block AC signals",
                2
        ));
        allQuestions.add(new Question(
                "Which parameter affects the cutoff frequency of a filter?",
                new String[]{"Resistance", "Capacitance", "Voltage", "Current"},
                "Capacitance",
                2
        ));
        allQuestions.add(new Question(
                "What is the function of a clipper circuit?",
                new String[]{"Limit signal amplitude", "Amplify signals", "Generate pulses", "Regulate voltage"},
                "Limit signal amplitude",
                2
        ));
        allQuestions.add(new Question(
                "Which component is used in a voltage divider circuit?",
                new String[]{"Capacitor", "Resistor", "Inductor", "Diode"},
                "Resistor",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of a phase-locked loop (PLL)?",
                new String[]{"Amplify signals", "Synchronize signals", "Store energy", "Convert AC to DC"},
                "Synchronize signals",
                2
        ));
        allQuestions.add(new Question(
                "Which type of capacitor is used for high-frequency applications?",
                new String[]{"Electrolytic", "Ceramic", "Tantalum", "Paper"},
                "Ceramic",
                2
        ));

        // Difficult Questions (10)
        allQuestions.add(new Question(
                "What is the significance of the Miller effect in amplifiers?",
                new String[]{"Reduces gain", "Increases input capacitance", "Improves efficiency", "Stabilizes voltage"},
                "Increases input capacitance",
                3
        ));
        allQuestions.add(new Question(
                "Which theorem is used to simplify complex circuits?",
                new String[]{"Ohm’s Theorem", "Norton’s Theorem", "Kirchhoff’s Theorem", "Coulomb’s Theorem"},
                "Norton’s Theorem",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of a gyrator circuit?",
                new String[]{"Amplify signals", "Simulate inductance", "Regulate voltage", "Generate pulses"},
                "Simulate inductance",
                3
        ));
        allQuestions.add(new Question(
                "Which phenomenon causes signal distortion in amplifiers?",
                new String[]{"Thermal noise", "Harmonic distortion", "Phase shift", "Capacitive coupling"},
                "Harmonic distortion",
                3
        ));
        allQuestions.add(new Question(
                "What is the role of a varactor diode in circuits?",
                new String[]{"Voltage regulation", "Frequency tuning", "High-speed switching", "Light emission"},
                "Frequency tuning",
                3
        ));
        allQuestions.add(new Question(
                "Which amplifier configuration is used for impedance matching?",
                new String[]{"Common Base", "Common Emitter", "Common Collector", "Common Gate"},
                "Common Collector",
                3
        ));
        allQuestions.add(new Question(
                "What is the significance of the slew rate in an op-amp?",
                new String[]{"Maximum frequency", "Rate of output voltage change", "Input impedance", "Power consumption"},
                "Rate of output voltage change",
                3
        ));
        allQuestions.add(new Question(
                "Which circuit is used to generate a stable reference voltage?",
                new String[]{"Voltage divider", "Bandgap reference", "Clipper circuit", "Schmitt trigger"},
                "Bandgap reference",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of a tunnel diode in high-frequency circuits?",
                new String[]{"Voltage regulation", "Negative resistance", "High-speed switching", "Light emission"},
                "Negative resistance",
                3
        ));
        allQuestions.add(new Question(
                "Which parameter is critical in designing a low 4. What is the significance of the common mode rejection ratio (CMRR) in an op-amp?",
                new String[]{"Noise reduction", "Gain stability", "Input impedance", "Rejection of common signals"},
                "Rejection of common signals",
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
        resultIntent.putExtra("electronics_score", scoreResult);
        setResult(RESULT_OK, resultIntent);
        finish(); // End activity
    }
}