package com.example.careermitra;

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

public class CommunicationSkillsScreen extends AppCompatActivity {

    private LinearLayout questionContainer;
    private ArrayList<RadioGroup> radioGroups = new ArrayList<>();
    private TextView resultTextView;

    private String[] questions = {
            "1. What is active listening?",
            "2. Which is an example of non-verbal communication?",
            "3. What is important for effective written communication?",
            "4. What does 'feedback' mean in communication?",
            "5. What is the 7% rule related to communication?"
    };

    private String[][] options = {
            {"Hearing passively", "Interrupting to give advice", "Fully concentrating on the speaker", "Only responding with yes or no"},
            {"Speaking loudly", "Body language", "Storytelling", "Speaking clearly"},
            {"Using difficult words", "Being brief and clear", "Writing long paragraphs", "Ignoring grammar"},
            {"The message content", "The emotional reaction", "The response from the receiver", "The sender's emotion"},
            {"7% of communication is words", "7% is body language", "7% is voice tone", "7% is eye contact"}
    };

    private String[] correctAnswers = {
            "Fully concentrating on the speaker",
            "Body language",
            "Being brief and clear",
            "The response from the receiver",
            "7% of communication is words"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication_skills_screen);

        questionContainer = findViewById(R.id.CommunicationSkillsQuestionContainer);

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
        textView.setPadding(20, 32, 20, 12);
        questionContainer.addView(textView);

        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        radioGroup.setPadding(20, 12, 20, 24);

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
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < radioGroups.size(); i++) {
            RadioGroup group = radioGroups.get(i);
            int selectedId = group.getCheckedRadioButtonId();

            if (selectedId != -1) {
                RadioButton selected = findViewById(selectedId);
                String selectedAnswer = selected.getText().toString();

                if (selectedAnswer.equals(correctAnswers[i])) {
                    score++;
                }

                result.append("Q").append(i + 1).append(": Your Answer: ").append(selectedAnswer)
                        .append("\nCorrect Answer: ").append(correctAnswers[i]).append("\n\n");

            } else {
                result.append("Q").append(i + 1).append(": No Answer\nCorrect Answer: ")
                        .append(correctAnswers[i]).append("\n\n");
            }
        }

        result.append("Final Score: ").append(score).append("/").append(questions.length);

        resultTextView.setText(result.toString());

        Toast.makeText(this, "Quiz Submitted!", Toast.LENGTH_SHORT).show();
    }
}
