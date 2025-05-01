package com.example.careermitra;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ComputerNetworksScreen extends AppCompatActivity {

    private LinearLayout questionContainer;
    private ArrayList<RadioGroup> radioGroups = new ArrayList<>();

    private String[] questions = {
            "1. What does TCP stand for?",
            "2. Which device operates at Layer 2 of the OSI model?",
            "3. What is the purpose of an IP address?",
            "4. What protocol is used to securely transfer files?",
            "5. What is the function of the MAC address?"
    };

    private String[][] options = {
            {"Transmission Control Protocol", "Transfer Control Protocol", "Transport Communication Protocol", "Tunnel Control Protocol"},
            {"Router", "Switch", "Modem", "Firewall"},
            {"To identify devices on a network", "To encrypt data", "To block malicious IPs", "To route email traffic"},
            {"HTTP", "FTP", "SFTP", "SMTP"},
            {"Locates a website", "Routes emails", "Uniquely identifies a network device", "Encrypts messages"}
    };

    private String[] correctAnswers = {
            "Transmission Control Protocol",
            "Switch",
            "To identify devices on a network",
            "SFTP",
            "Uniquely identifies a network device"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_networks_screen);

        questionContainer = findViewById(R.id.ComputerNetworksQuestionContainer);

        createQuiz();
        addSubmitButton();
    }

    private void createQuiz() {
        for (int i = 0; i < questions.length; i++) {
            addQuestion(questions[i], options[i]);
        }
    }

    private void addQuestion(String questionText, String[] optionsArray) {
        TextView textView = new TextView(this);
        textView.setText(questionText);
        textView.setTextSize(18);
        textView.setTextColor(getResources().getColor(R.color.text_dark_blue));
        textView.setPadding(20, 24, 20, 8);
        questionContainer.addView(textView);

        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        radioGroup.setPadding(20, 8, 20, 8);

        for (String option : optionsArray) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioButton.setTextSize(16);
            radioButton.setTextColor(getResources().getColor(R.color.text_dark_gray));
            radioGroup.addView(radioButton);
        }

        radioGroups.add(radioGroup);
        questionContainer.addView(radioGroup);
    }

    private void addSubmitButton() {
        Button submitButton = new Button(this);
        submitButton.setText("Submit Answers");
        submitButton.setBackgroundColor(getResources().getColor(R.color.button_blue));
        submitButton.setTextColor(getResources().getColor(R.color.white));
        submitButton.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        submitButton.setPadding(40, 20, 40, 20);

        submitButton.setOnClickListener(view -> evaluateQuiz());

        questionContainer.addView(submitButton);
    }

    private void evaluateQuiz() {
        int score = 0;

        for (int i = 0; i < radioGroups.size(); i++) {
            RadioGroup group = radioGroups.get(i);
            int selectedId = group.getCheckedRadioButtonId();

            if (selectedId != -1) {
                RadioButton selected = findViewById(selectedId);
                String selectedAnswer = selected.getText().toString();

                if (selectedAnswer.equals(correctAnswers[i])) {
                    score++;
                }
            }
        }

        // Send score back to MainScreen
        Intent resultIntent = new Intent();
        resultIntent.putExtra("cn_score", score + "/" + questions.length);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
