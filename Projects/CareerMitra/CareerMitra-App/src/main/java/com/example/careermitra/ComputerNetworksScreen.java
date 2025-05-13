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

public class ComputerNetworksScreen extends AppCompatActivity {

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
        setContentView(R.layout.activity_computer_networks_screen);

        questionContainer = findViewById(R.id.ComputerNetworksQuestionContainer);

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
                "What does TCP stand for?",
                new String[]{"Transmission Control Protocol", "Transfer Control Protocol", "Transport Communication Protocol", "Tunnel Control Protocol"},
                "Transmission Control Protocol",
                1
        ));
        allQuestions.add(new Question(
                "Which device operates at Layer 2 of the OSI model?",
                new String[]{"Router", "Switch", "Modem", "Firewall"},
                "Switch",
                1
        ));
        allQuestions.add(new Question(
                "What is the purpose of an IP address?",
                new String[]{"To identify devices on a network", "To encrypt data", "To block malicious IPs", "To route email traffic"},
                "To identify devices on a network",
                1
        ));
        allQuestions.add(new Question(
                "What protocol is used to securely transfer files?",
                new String[]{"HTTP", "FTP", "SFTP", "SMTP"},
                "SFTP",
                1
        ));
        allQuestions.add(new Question(
                "What is the function of the MAC address?",
                new String[]{"Locates a website", "Routes emails", "Uniquely identifies a network device", "Encrypts messages"},
                "Uniquely identifies a network device",
                1
        ));
        allQuestions.add(new Question(
                "What does OSI stand for?",
                new String[]{"Open System Interconnection", "Operational System Interface", "Open Source Integration", "Optical System Interconnect"},
                "Open System Interconnection",
                1
        ));
        allQuestions.add(new Question(
                "Which protocol is used for sending emails?",
                new String[]{"HTTP", "SMTP", "FTP", "DNS"},
                "SMTP",
                1
        ));
        allQuestions.add(new Question(
                "What is the default port for HTTP?",
                new String[]{"21", "80", "443", "25"},
                "80",
                1
        ));
        allQuestions.add(new Question(
                "Which layer of the OSI model is responsible for routing?",
                new String[]{"Data Link", "Network", "Transport", "Application"},
                "Network",
                1
        ));
        allQuestions.add(new Question(
                "What does DNS stand for?",
                new String[]{"Domain Name System", "Dynamic Network Service", "Distributed Name Server", "Data Network System"},
                "Domain Name System",
                1
        ));
        allQuestions.add(new Question(
                "What is the purpose of a subnet mask?",
                new String[]{"Encrypt data", "Identify network portion of IP", "Assign MAC address", "Route packets"},
                "Identify network portion of IP",
                1
        ));
        allQuestions.add(new Question(
                "Which protocol is connectionless?",
                new String[]{"TCP", "UDP", "HTTP", "FTP"},
                "UDP",
                1
        ));
        allQuestions.add(new Question(
                "What does LAN stand for?",
                new String[]{"Local Area Network", "Large Access Network", "Logical Application Network", "Long Area Network"},
                "Local Area Network",
                1
        ));
        allQuestions.add(new Question(
                "Which device connects multiple networks?",
                new String[]{"Switch", "Router", "Hub", "Bridge"},
                "Router",
                1
        ));
        allQuestions.add(new Question(
                "What is the default port for HTTPS?",
                new String[]{"80", "443", "21", "25"},
                "443",
                1
        ));
        allQuestions.add(new Question(
                "What does DHCP stand for?",
                new String[]{"Dynamic Host Configuration Protocol", "Distributed Host Control Protocol", "Domain Host Configuration Protocol", "Data Host Control Protocol"},
                "Dynamic Host Configuration Protocol",
                1
        ));
        allQuestions.add(new Question(
                "Which protocol resolves IP addresses to MAC addresses?",
                new String[]{"DNS", "ARP", "DHCP", "ICMP"},
                "ARP",
                1
        ));
        allQuestions.add(new Question(
                "What is the purpose of a firewall?",
                new String[]{"Route packets", "Assign IP addresses", "Filter network traffic", "Resolve domain names"},
                "Filter network traffic",
                1
        ));
        allQuestions.add(new Question(
                "Which topology connects all devices to a central point?",
                new String[]{"Bus", "Ring", "Star", "Mesh"},
                "Star",
                1
        ));
        allQuestions.add(new Question(
                "What does FTP stand for?",
                new String[]{"File Transfer Protocol", "Fast Transmission Protocol", "File Transport Protocol", "Flexible Transfer Protocol"},
                "File Transfer Protocol",
                1
        ));

        // Medium Questions (20)
        allQuestions.add(new Question(
                "What is the purpose of the Transport layer in the OSI model?",
                new String[]{"Physical addressing", "End-to-end communication", "Routing packets", "Data encryption"},
                "End-to-end communication",
                2
        ));
        allQuestions.add(new Question(
                "Which protocol is used for network management?",
                new String[]{"SNMP", "HTTP", "FTP", "SMTP"},
                "SNMP",
                2
        ));
        allQuestions.add(new Question(
                "What is the difference between TCP and UDP?",
                new String[]{"TCP is faster", "UDP is connection-oriented", "TCP is reliable", "UDP guarantees delivery"},
                "TCP is reliable",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of NAT?",
                new String[]{"Resolve domain names", "Map private to public IPs", "Encrypt data", "Route packets"},
                "Map private to public IPs",
                2
        ));
        allQuestions.add(new Question(
                "Which protocol is used for secure remote access?",
                new String[]{"Telnet", "SSH", "FTP", "HTTP"},
                "SSH",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of the ICMP protocol?",
                new String[]{"File transfer", "Error reporting and diagnostics", "Secure communication", "Domain resolution"},
                "Error reporting and diagnostics",
                2
        ));
        allQuestions.add(new Question(
                "Which addressing scheme is used by IPv6?",
                new String[]{"32-bit", "64-bit", "128-bit", "256-bit"},
                "128-bit",
                2
        ));
        allQuestions.add(new Question(
                "What is a VLAN used for?",
                new String[]{"Increase bandwidth", "Segment network traffic", "Encrypt data", "Route packets"},
                "Segment network traffic",
                2
        ));
        allQuestions.add(new Question(
                "Which protocol is used for VoIP?",
                new String[]{"SIP", "FTP", "SMTP", "DNS"},
                "SIP",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of the Session layer in the OSI model?",
                new String[]{"Data encryption", "Establish and manage sessions", "Physical addressing", "Routing"},
                "Establish and manage sessions",
                2
        ));
        allQuestions.add(new Question(
                "What is the main advantage of a mesh topology?",
                new String[]{"Low cost", "High reliability", "Easy setup", "Centralized control"},
                "High reliability",
                2
        ));
        allQuestions.add(new Question(
                "Which protocol is used for time synchronization?",
                new String[]{"NTP", "SNMP", "FTP", "HTTP"},
                "NTP",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of BGP?",
                new String[]{"Internal routing", "External routing between AS", "File transfer", "Error reporting"},
                "External routing between AS",
                2
        ));
        allQuestions.add(new Question(
                "What is the difference between a hub and a switch?",
                new String[]{"Hub is faster", "Switch forwards to specific devices", "Hub is Layer 2", "Switch broadcasts"},
                "Switch forwards to specific devices",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of QoS?",
                new String[]{"Encrypt data", "Prioritize network traffic", "Resolve IPs", "Assign MAC addresses"},
                "Prioritize network traffic",
                2
        ));
        allQuestions.add(new Question(
                "Which protocol is used for multicasting?",
                new String[]{"IGMP", "ICMP", "ARP", "DHCP"},
                "IGMP",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of the Presentation layer?",
                new String[]{"Data formatting and encryption", "Routing packets", "Physical addressing", "Session management"},
                "Data formatting and encryption",
                2
        ));
        allQuestions.add(new Question(
                "What is the main disadvantage of a bus topology?",
                new String[]{"High cost", "Single point of failure", "Complex setup", "Low reliability"},
                "Single point of failure",
                2
        ));
        allQuestions.add(new Question(
                "Which protocol is used for secure web browsing?",
                new String[]{"HTTP", "HTTPS", "FTP", "SMTP"},
                "HTTPS",
                2
        ));
        allQuestions.add(new Question(
                "What is the purpose of a proxy server?",
                new String[]{"Route packets", "Assign IPs", "Act as intermediary", "Resolve domain names"},
                "Act as intermediary",
                2
        ));

        // Difficult Questions (10)
        allQuestions.add(new Question(
                "What is the purpose of the MPLS protocol?",
                new String[]{"Encrypt data", "Label-based routing", "Resolve IPs", "Assign MAC addresses"},
                "Label-based routing",
                3
        ));
        allQuestions.add(new Question(
                "Which algorithm is used in OSPF routing?",
                new String[]{"Bellman-Ford", "Dijkstra", "Floyd-Warshall", "Kruskal"},
                "Dijkstra",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of the RSTP protocol?",
                new String[]{"Secure file transfer", "Faster loop-free topology", "Time synchronization", "Error reporting"},
                "Faster loop-free topology",
                3
        ));
        allQuestions.add(new Question(
                "Which attack exploits TCP's three-way handshake?",
                new String[]{"SQL Injection", "SYN Flood", "Phishing", "Man-in-the-Middle"},
                "SYN Flood",
                3
        ));
        allQuestions.add(new Question(
                "What is the main challenge of implementing IPv6?",
                new String[]{"Smaller address space", "Compatibility with IPv4", "Lower security", "Slower routing"},
                "Compatibility with IPv4",
                3
        ));
        allQuestions.add(new Question(
                "Which protocol is used for software-defined networking?",
                new String[]{"OpenFlow", "SNMP", "BGP", "IGMP"},
                "OpenFlow",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of the GRE protocol?",
                new String[]{"Encrypt data", "Create tunnels", "Resolve IPs", "Prioritize traffic"},
                "Create tunnels",
                3
        ));
        allQuestions.add(new Question(
                "Which technique mitigates ARP spoofing?",
                new String[]{"Dynamic ARP Inspection", "NAT", "VLAN", "QoS"},
                "Dynamic ARP Inspection",
                3
        ));
        allQuestions.add(new Question(
                "What is the purpose of the IS-IS protocol?",
                new String[]{"File transfer", "Interior gateway routing", "Time synchronization", "Error reporting"},
                "Interior gateway routing",
                3
        ));
        allQuestions.add(new Question(
                "Which protocol is used for secure VPN connections?",
                new String[]{"PPTP", "L2TP/IPsec", "FTP", "Telnet"},
                "L2TP/IPsec",
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
        resultIntent.putExtra("cn_score", scoreResult);
        setResult(RESULT_OK, resultIntent);
        finish(); // End activity
    }
}