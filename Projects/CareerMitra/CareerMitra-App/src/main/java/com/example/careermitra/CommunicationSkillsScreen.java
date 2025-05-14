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

public class CommunicationSkillsScreen extends AppCompatActivity {

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
        setContentView(R.layout.activity_communication_skills_screen);

        questionContainer = findViewById(R.id.CommunicationSkillsQuestionContainer);

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
                "What is active listening?",
                new String[]{"Hearing passively", "Interrupting to give advice", "Fully concentrating on the speaker", "Only responding with yes or no"},
                "Fully concentrating on the speaker",
                1
        ));
        allQuestions.add(new Question(
                "Which is an example of non-verbal communication?",
                new String[]{"Speaking loudly", "Body language", "Storytelling", "Speaking clearly"},
                "Body language",
                1
        ));
        allQuestions.add(new Question(
                "What is important for effective written communication?",
                new String[]{"Using difficult words", "Being brief and clear", "Writing long paragraphs", "Ignoring grammar"},
                "Being brief and clear",
                1
        ));
        allQuestions.add(new Question(
                "What does 'feedback' mean in communication?",
                new String[]{"The message content", "The emotional reaction", "The response from the receiver", "The sender's emotion"},
                "The response from the receiver",
                1
        ));
        allQuestions.add(new Question(
                "What is the 7% rule related to communication?",
                new String[]{"7% of communication is words", "7% is body language", "7% is voice tone", "7% is eye contact"},
                "7% of communication is words",
                1
        ));
        allQuestions.add(new Question(
                "What is the primary goal of communication?",
                new String[]{"To entertain", "To share information", "To confuse", "To argue"},
                "To share information",
                1
        ));
        allQuestions.add(new Question(
                "Which is a barrier to effective communication?",
                new String[]{"Clarity", "Noise", "Listening", "Feedback"},
                "Noise",
                1
        ));
        allQuestions.add(new Question(
                "What does 'empathy' contribute to communication?",
                new String[]{"Confusion", "Understanding others' feelings", "Authority", "Distraction"},
                "Understanding others' feelings",
                1
        ));
        allQuestions.add(new Question(
                "Which is a key component of verbal communication?",
                new String[]{"Gestures", "Tone of voice", "Facial expressions", "Posture"},
                "Tone of voice",
                1
        ));
        allQuestions.add(new Question(
                "What is the purpose of paraphrasing in a conversation?",
                new String[]{"To interrupt", "To clarify understanding", "To change the topic", "To end the discussion"},
                "To clarify understanding",
                1
        ));
        allQuestions.add(new Question(
                "What is an example of a positive communication trait?",
                new String[]{"Interrupting", "Respectful tone", "Ignoring questions", "Being vague"},
                "Respectful tone",
                1
        ));
        allQuestions.add(new Question(
                "What does 'clarity' mean in communication?",
                new String[]{"Using complex words", "Being easily understood", "Speaking quickly", "Avoiding details"},
                "Being easily understood",
                1
        ));
        allQuestions.add(new Question(
                "Which is a form of written communication?",
                new String[]{"Email", "Eye contact", "Gestures", "Tone"},
                "Email",
                1
        ));
        allQuestions.add(new Question(
                "What is the role of eye contact in communication?",
                new String[]{"Shows distraction", "Builds trust", "Interrupts the speaker", "Reduces clarity"},
                "Builds trust",
                1
        ));
        allQuestions.add(new Question(
                "What does 'conciseness' mean in communication?",
                new String[]{"Using long sentences", "Being brief and to the point", "Adding unnecessary details", "Speaking loudly"},
                "Being brief and to the point",
                1
        ));
        allQuestions.add(new Question(
                "What is a benefit of good communication in a team?",
                new String[]{"Increased conflict", "Improved collaboration", "More misunderstandings", "Reduced trust"},
                "Improved collaboration",
                1
        ));
        allQuestions.add(new Question(
                "What is the purpose of asking questions in a conversation?",
                new String[]{"To dominate", "To clarify and engage", "To confuse", "To end quickly"},
                "To clarify and engage",
                1
        ));
        allQuestions.add(new Question(
                "What does 'tone' refer to in communication?",
                new String[]{"The volume of speech", "The emotional quality of voice", "The speed of speech", "The choice of words"},
                "The emotional quality of voice",
                1
        ));
        allQuestions.add(new Question(
                "What is a sign of poor listening skills?",
                new String[]{"Nodding", "Interrupting frequently", "Making eye contact", "Summarizing"},
                "Interrupting frequently",
                1
        ));
        allQuestions.add(new Question(
                "What is the purpose of a meeting agenda?",
                new String[]{"To confuse participants", "To guide discussion", "To extend the meeting", "To avoid decisions"},
                "To guide discussion",
                1
        ));

        // Medium Questions (20)
        allQuestions.add(new Question(
                "What is the purpose of the 'SBI' model in feedback?",
                new String[]{"To criticize", "To describe situation, behavior, impact", "To confuse", "To avoid feedback"},
                "To describe situation, behavior, impact",
                2
        ));
        allQuestions.add(new Question(
                "Which type of question encourages detailed responses?",
                new String[]{"Yes/No question", "Closed question", "Open-ended question", "Rhetorical question"},
                "Open-ended question",
                2
        ));
        allQuestions.add(new Question(
                "What is 'assertive communication'?",
                new String[]{"Being aggressive", "Expressing needs respectfully", "Avoiding conflict", "Ignoring others"},
                "Expressing needs respectfully",
                2
        ));
        allQuestions.add(new Question(
                "What is the impact of cultural differences on communication?",
                new String[]{"No impact", "Can cause misunderstandings", "Simplifies communication", "Reduces feedback"},
                "Can cause misunderstandings",
                2
        ));
        allQuestions.add(new Question(
                "What is the role of 'mirroring' in communication?",
                new String[]{"Copying body language to build rapport", "Interrupting the speaker", "Changing the topic", "Ignoring emotions"},
                "Copying body language to build rapport",
                2
        ));
        allQuestions.add(new Question(
                "What is a key feature of persuasive communication?",
                new String[]{"Vagueness", "Strong arguments", "Avoiding facts", "Being passive"},
                "Strong arguments",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of a 'call to action' in a presentation?",
                new String[]{"Confuse the audience", "Encourage specific behavior", "Extend the talk", "Avoid conclusions"},
                "Encourage specific behavior",
                2
        ));
        allQuestions.add(new Question(
                "What is 'jargon' in communication?",
                new String[]{"Clear language", "Specialized terminology", "Simple words", "Emotional tone"},
                "Specialized terminology",
                2
        ));
        allQuestions.add(new Question(
                "What is the benefit of using visual aids in a presentation?",
                new String[]{"Distracts audience", "Enhances understanding", "Increases complexity", "Reduces engagement"},
                "Enhances understanding",
                2
        ));
        allQuestions.add(new Question(
                "What is the role of 'silence' in communication?",
                new String[]{"Creates confusion", "Allows reflection", "Shows disinterest", "Speeds up conversation"},
                "Allows reflection",
                2
        ));
        allQuestions.add(new Question(
                "What is a characteristic of constructive feedback?",
                new String[]{"Vague and general", "Specific and actionable", "Harsh and critical", "Avoids details"},
                "Specific and actionable",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'summarizing' in a discussion?",
                new String[]{"To interrupt", "To clarify key points", "To change the topic", "To confuse"},
                "To clarify key points",
                2
        ));
        allQuestions.add(new Question(
                "What is a common mistake in email communication?",
                new String[]{"Clear subject line", "Poor grammar", "Concise message", "Polite tone"},
                "Poor grammar",
                2
        ));
        allQuestions.add(new Question(
                "What is the impact of a monotone voice in a presentation?",
                new String[]{"Engages audience", "Reduces interest", "Increases clarity", "Builds trust"},
                "Reduces interest",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'rapport' in communication?",
                new String[]{"Create conflict", "Build trust and connection", "Avoid discussion", "Increase formality"},
                "Build trust and connection",
                2
        ));
        allQuestions.add(new Question(
                "What is a key element of negotiation communication?",
                new String[]{"Ignoring others", "Active listening", "Speaking loudly", "Avoiding compromise"},
                "Active listening",
                2
        ));
        allQuestions.add(new Question(
                "What is the role of 'context' in communication?",
                new String[]{"Irrelevant", "Shapes message interpretation", "Confuses audience", "Reduces clarity"},
                "Shapes message interpretation",
                2
        ));
        allQuestions.add(new Question(
                "What is a benefit of storytelling in communication?",
                new String[]{"Increases confusion", "Engages and persuades", "Reduces clarity", "Avoids facts"},
                "Engages and persuades",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of a 'thesis statement' in a speech?",
                new String[]{"Confuse the audience", "State the main idea", "Extend the speech", "Avoid focus"},
                "State the main idea",
                2
        ));
        allQuestions.add(new Question(
                "What is a sign of effective group communication?",
                new String[]{"Dominating the conversation", "Equal participation", "Ignoring feedback", "Vague goals"},
                "Equal participation",
                2
        ));

        // Difficult Questions (10)
        allQuestions.add(new Question(
                "What is the 'Johari Window' used for in communication?",
                new String[]{"Increase conflict", "Understand self and others", "Avoid feedback", "Simplify messages"},
                "Understand self and others",
                3
        ));
        allQuestions.add(new Question(
                "What is the impact of cognitive bias on communication?",
                new String[]{"Improves clarity", "Distorts perceptions", "Enhances trust", "Simplifies messages"},
                "Distorts perceptions",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of the 'Aristotle’s Appeals' in persuasion?",
                new String[]{"Confuse the audience", "Use ethos, pathos, logos", "Avoid arguments", "Reduce engagement"},
                "Use ethos, pathos, logos",
                3
        ));
        allQuestions.add(new Question(
                "What is the role of 'proxemics' in non-verbal communication?",
                new String[]{"Use of time", "Use of space", "Use of tone", "Use of words"},
                "Use of space",
                3
        ));
        allQuestions.add(new Question(
                "What is the significance of 'emotional intelligence' in communication?",
                new String[]{"Increases conflict", "Enhances understanding of emotions", "Reduces clarity", "Avoids feedback"},
                "Enhances understanding of emotions",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of the 'GROW' model in coaching conversations?",
                new String[]{"Avoid goals", "Structure goal-oriented talks", "Increase confusion", "Reduce feedback"},
                "Structure goal-oriented talks",
                3
        ));
        allQuestions.add(new Question(
                "What is the impact of 'groupthink' on communication?",
                new String[]{"Encourages diverse ideas", "Suppresses critical thinking", "Increases clarity", "Builds trust"},
                "Suppresses critical thinking",
                3
        ));
        allQuestions.add(new Question(
                "What is the role of 'haptics' in communication?",
                new String[]{"Use of touch", "Use of eye contact", "Use of tone", "Use of silence"},
                "Use of touch",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of 'transactional analysis' in communication?",
                new String[]{"Increase conflict", "Analyze interpersonal interactions", "Simplify messages", "Avoid feedback"},
                "Analyze interpersonal interactions",
                3
        ));
        allQuestions.add(new Question(
                "What is the significance of 'chronemics' in communication?",
                new String[]{"Use of space", "Use of time", "Use of tone", "Use of gestures"},
                "Use of time",
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
        resultIntent.putExtra("comm_score", scoreResult);
        setResult(RESULT_OK, resultIntent);
        finish(); // End activity
    }
}