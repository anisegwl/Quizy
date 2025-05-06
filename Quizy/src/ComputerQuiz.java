import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class ComputerQuiz extends JFrame implements ActionListener {
    JButton Next, lifeline, submit;
    int Score = 0;
    String questions[][] = new String[30][5]; // 30 questions
    String answers[][] = new String[30][2]; // 30 answers
    String Userans[][] = new String[10][1]; // Only 10 user answers
	HashMap<Integer, String> correctAnswers = new HashMap<>(); // Store correct answers
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JLabel Qno;
    JLabel Question, Marks;
    public int count = 0;
    public int score = 0;
    String[] sel_answer = new String[10]; // Only 10 selected answers
    ArrayList<Integer> questionOrder = new ArrayList<>(); // To store the order of questions

    public ComputerQuiz() {
        setResizable(false);

        // Set the Title and location, size of frame
        setTitle("Java Quiz Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 0, 1200, 750);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.white);

        // Add the Image on the Frame
        ImageIcon i1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("quizbg.jpg"));
        JLabel imageLabel = new JLabel(i1);
        // Add the label to the frame
        imageLabel.setBounds(0, 0, 1200, 400);
        getContentPane().add(imageLabel);

        // Adding the Next button
        Next = new JButton("Next");
        Next.setBounds(900, 450, 150, 50);
        Next.setVisible(true);
        Next.addActionListener(this);
        getContentPane().add(Next);

        // Adding the 50-50 Lifeline Button
        lifeline = new JButton("50-50 lifeline");
        lifeline.setBounds(900, 520, 150, 50);
        lifeline.setVisible(true);
        lifeline.addActionListener(this);
        getContentPane().add(lifeline);

        // Creating the Submit Button
        submit = new JButton("Submit");
        submit.setBounds(900, 590, 150, 50);
        submit.setVisible(true);
        submit.addActionListener(this);
        getContentPane().add(submit);
        submit.setEnabled(false);

        // Creating the Question Number
        Qno = new JLabel();
        Qno.setFont(new Font("Arial", Font.BOLD, 20));
        Qno.setBounds(20, 410, 40, 60);
        Qno.setVisible(true);
        getContentPane().add(Qno);

        // Creating the Marks label to display the Question
        Marks = new JLabel("10 M");
        Marks.setForeground(new Color(255, 0, 0));
        Marks.setFont(new Font("Arial", Font.BOLD, 20));
        Marks.setBounds(750, 420, 700, 40);
        Marks.setVisible(true);
        getContentPane().add(Marks);

        // Creating the Question label to display the Question
        Question = new JLabel();
        Question.setFont(new Font("Arial", Font.BOLD, 20));
        Question.setBounds(60, 420, 700, 40);
        Question.setVisible(true);
        getContentPane().add(Question);

        // Creating the JRadioButton to choose one option among them using ButtonGroup
        opt1 = new JRadioButton();
        opt1.setBounds(70, 470, 700, 30);
        opt1.setBackground(Color.WHITE);
        opt1.setFont(new Font("Dialog", Font.PLAIN, 20));
        getContentPane().add(opt1);

        opt2 = new JRadioButton();
        opt2.setBounds(70, 500, 700, 30);
        opt2.setBackground(Color.WHITE);
        opt2.setFont(new Font("Dialog", Font.PLAIN, 20));
        getContentPane().add(opt2);

        opt3 = new JRadioButton();
        opt3.setBounds(70, 530, 700, 30);
        opt3.setBackground(Color.WHITE);
        opt3.setFont(new Font("Dialog", Font.PLAIN, 20));
        getContentPane().add(opt3);

        opt4 = new JRadioButton();
        opt4.setBounds(70, 560, 700, 30);
        opt4.setBackground(Color.WHITE);
        opt4.setFont(new Font("Dialog", Font.PLAIN, 20));
        getContentPane().add(opt4);

        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);

        // Questions for Quiz Application (Computer Fundamentals)
questions[0][0] = "What does CPU stand for?";
questions[0][1] = "Central Processing Unit";
questions[0][2] = "Computer Processing Unit";
questions[0][3] = "Central Processor Unit";
questions[0][4] = "Control Processing Unit";

questions[1][0] = "Which of the following is a volatile memory?";
questions[1][1] = "RAM";
questions[1][2] = "ROM";
questions[1][3] = "Hard Disk";
questions[1][4] = "SSD";

questions[2][0] = "What is the binary system used for in computers?";
questions[2][1] = "To represent data using 0s and 1s";
questions[2][2] = "To perform arithmetic operations";
questions[2][3] = "To store files";
questions[2][4] = "To display graphics";

questions[3][0] = "Which component is responsible for storing data permanently?";
questions[3][1] = "Hard Disk";
questions[3][2] = "RAM";
questions[3][3] = "CPU";
questions[3][4] = "Cache";

questions[4][0] = "What is the primary function of an operating system?";
questions[4][1] = "To manage hardware and software resources";
questions[4][2] = "To create documents";
questions[4][3] = "To connect to the internet";
questions[4][4] = "To display graphics";

questions[5][0] = "Which of the following is an example of an input device?";
questions[5][1] = "Keyboard";
questions[5][2] = "Monitor";
questions[5][3] = "Printer";
questions[5][4] = "Speaker";

questions[6][0] = "What does GUI stand for?";
questions[6][1] = "Graphical User Interface";
questions[6][2] = "General User Interface";
questions[6][3] = "Graphical Unit Interface";
questions[6][4] = "General Unit Interface";

questions[7][0] = "Which programming language is known as the 'mother of all languages'?";
questions[7][1] = "C";
questions[7][2] = "Java";
questions[7][3] = "Python";
questions[7][4] = "Assembly";

questions[8][0] = "What is the smallest unit of data in a computer?";
questions[8][1] = "Bit";
questions[8][2] = "Byte";
questions[8][3] = "Kilobyte";
questions[8][4] = "Megabyte";

questions[9][0] = "Which protocol is used for sending emails?";
questions[9][1] = "SMTP";
questions[9][2] = "HTTP";
questions[9][3] = "FTP";
questions[9][4] = "TCP";

questions[10][0] = "What does HTML stand for?";
questions[10][1] = "HyperText Markup Language";
questions[10][2] = "HighText Machine Language";
questions[10][3] = "HyperText Machine Language";
questions[10][4] = "HighText Markup Language";

questions[11][0] = "Which of the following is a high-level programming language?";
questions[11][1] = "Python";
questions[11][2] = "Assembly";
questions[11][3] = "Machine Code";
questions[11][4] = "Binary";

questions[12][0] = "What is the main purpose of a compiler?";
questions[12][1] = "To convert high-level code to machine code";
questions[12][2] = "To execute programs";
questions[12][3] = "To debug code";
questions[12][4] = "To store data";

questions[13][0] = "Which of the following is an example of a non-volatile memory?";
questions[13][1] = "ROM";
questions[13][2] = "RAM";
questions[13][3] = "Cache";
questions[13][4] = "Register";

questions[14][0] = "What is the function of a GPU?";
questions[14][1] = "To handle graphics rendering";
questions[14][2] = "To manage memory";
questions[14][3] = "To process data";
questions[14][4] = "To store files";

questions[15][0] = "What does URL stand for?";
questions[15][1] = "Uniform Resource Locator";
questions[15][2] = "Universal Resource Locator";
questions[15][3] = "Uniform Resource Link";
questions[15][4] = "Universal Resource Link";

questions[16][0] = "Which of the following is a web browser?";
questions[16][1] = "Google Chrome";
questions[16][2] = "Microsoft Word";
questions[16][3] = "Adobe Photoshop";
questions[16][4] = "Windows Explorer";

questions[17][0] = "What is the purpose of an IP address?";
questions[17][1] = "To identify a device on a network";
questions[17][2] = "To store data";
questions[17][3] = "To display websites";
questions[17][4] = "To process requests";

questions[18][0] = "Which of the following is a database management system?";
questions[18][1] = "MySQL";
questions[18][2] = "Excel";
questions[18][3] = "Word";
questions[18][4] = "PowerPoint";

questions[19][0] = "What is the full form of VPN?";
questions[19][1] = "Virtual Private Network";
questions[19][2] = "Virtual Public Network";
questions[19][3] = "Verified Private Network";
questions[19][4] = "Verified Public Network";

questions[20][0] = "Which of the following is an example of cloud storage?";
questions[20][1] = "Google Drive";
questions[20][2] = "Hard Disk";
questions[20][3] = "USB Drive";
questions[20][4] = "CD-ROM";

questions[21][0] = "What is the purpose of a firewall?";
questions[21][1] = "To protect a network from unauthorized access";
questions[21][2] = "To store data";
questions[21][3] = "To process data";
questions[21][4] = "To display graphics";

questions[22][0] = "Which of the following is an example of an output device?";
questions[22][1] = "Monitor";
questions[22][2] = "Keyboard";
questions[22][3] = "Mouse";
questions[22][4] = "Scanner";

questions[23][0] = "What is the function of an ALU in a CPU?";
questions[23][1] = "To perform arithmetic and logical operations";
questions[23][2] = "To store data";
questions[23][3] = "To manage memory";
questions[23][4] = "To control input/output devices";

questions[24][0] = "Which of the following is a type of malware?";
questions[24][1] = "Virus";
questions[24][2] = "Firewall";
questions[24][3] = "Antivirus";
questions[24][4] = "Encryption";

questions[25][0] = "What is the full form of BIOS?";
questions[25][1] = "Basic Input/Output System";
questions[25][2] = "Binary Input/Output System";
questions[25][3] = "Basic Integrated Operating System";
questions[25][4] = "Binary Integrated Operating System";

questions[26][0] = "Which of the following is a programming paradigm?";
questions[26][1] = "Object-Oriented Programming";
questions[26][2] = "Graphical User Interface";
questions[26][3] = "Central Processing Unit";
questions[26][4] = "Random Access Memory";

questions[27][0] = "What is the purpose of a cache memory?";
questions[27][1] = "To store frequently accessed data for faster retrieval";
questions[27][2] = "To store permanent data";
questions[27][3] = "To manage network connections";
questions[27][4] = "To display graphics";

questions[28][0] = "Which of the following is a markup language?";
questions[28][1] = "HTML";
questions[28][2] = "Python";
questions[28][3] = "Java";
questions[28][4] = "C++";

questions[29][0] = "What is the full form of HTTP?";
questions[29][1] = "HyperText Transfer Protocol";
questions[29][2] = "HighText Transfer Protocol";
questions[29][3] = "HyperText Transmission Protocol";
questions[29][4] = "HighText Transmission Protocol";

// Answers for Quiz Application (Computer Fundamentals)
answers[0][1] = "Central Processing Unit";
answers[1][1] = "RAM";
answers[2][1] = "To represent data using 0s and 1s";
answers[3][1] = "Hard Disk";
answers[4][1] = "To manage hardware and software resources";
answers[5][1] = "Keyboard";
answers[6][1] = "Graphical User Interface";
answers[7][1] = "C";
answers[8][1] = "Bit";
answers[9][1] = "SMTP";
answers[10][1] = "HyperText Markup Language";
answers[11][1] = "Python";
answers[12][1] = "To convert high-level code to machine code";
answers[13][1] = "ROM";
answers[14][1] = "To handle graphics rendering";
answers[15][1] = "Uniform Resource Locator";
answers[16][1] = "Google Chrome";
answers[17][1] = "To identify a device on a network";
answers[18][1] = "MySQL";
answers[19][1] = "Virtual Private Network";
answers[20][1] = "Google Drive";
answers[21][1] = "To protect a network from unauthorized access";
answers[22][1] = "Monitor";
answers[23][1] = "To perform arithmetic and logical operations";
answers[24][1] = "Virus";
answers[25][1] = "Basic Input/Output System";
answers[26][1] = "Object-Oriented Programming";
answers[27][1] = "To store frequently accessed data for faster retrieval";
answers[28][1] = "HTML";
answers[29][1] = "HyperText Transfer Protocol";

        // Randomize the order of questions and select the first 10
        for (int i = 0; i < 30; i++) {
            questionOrder.add(i);
        }
        Collections.shuffle(questionOrder);

        // Only keep the first 10 questions
        questionOrder = new ArrayList<>(questionOrder.subList(0, 10));

        enter(questionOrder.get(count));
    }
	

   public void enter(int index) {
    Qno.setText("" + (count + 1) + ".  ");
    Question.setText(questions[index][0]);

    // Store correct answer before shuffling
    String correctAnswer = answers[index][1];

    // Create an array to hold options
    String[] options = {questions[index][1], questions[index][2], questions[index][3], questions[index][4]};

    // Shuffle the options randomly
    List<String> optionList = Arrays.asList(options);
    Collections.shuffle(optionList);

    // Set the shuffled options to the radio buttons
    opt1.setText(optionList.get(0));
    opt1.setActionCommand(optionList.get(0));

    opt2.setText(optionList.get(1));
    opt2.setActionCommand(optionList.get(1));

    opt3.setText(optionList.get(2));
    opt3.setActionCommand(optionList.get(2));

    opt4.setText(optionList.get(3));
    opt4.setActionCommand(optionList.get(3));

    // Store the correct answer in a new map for checking
    correctAnswers.put(index, correctAnswer);

    groupoptions.clearSelection();
}


    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == Next) {
        // Add the validation check here
        if (groupoptions.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "Please select an option before proceeding!", "No Option Selected", JOptionPane.WARNING_MESSAGE);
            return; // Exit the method if no option is selected
        }

        // Rest of the code for handling the "Next" button
        repaint();
        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);

        // Store the selected answer
        Userans[count][0] = groupoptions.getSelection().getActionCommand();

        if (count >= questionOrder.size() - 1) { // Prevent accessing out of bounds
			Next.setEnabled(false);
			submit.setEnabled(true);
		}
		

        if (count < questionOrder.size() - 1) {
			count++;
			enter(questionOrder.get(count));
		}
		

    } else if (e.getSource() == submit) {
        // Assume the username is stored somewhere, e.g., "currentUser"
        String currentUser = JOptionPane.showInputDialog(this, "Enter your username:");
        
        if (currentUser == null || currentUser.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username is required to save your score!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Store the selected answer for the last question
        if (groupoptions.getSelection() == null) {
            Userans[count][0] = "";
        } else {
            Userans[count][0] = groupoptions.getSelection().getActionCommand();
        }
    
        // Calculate the score
        for (int i = 0; i < Userans.length; i++) {
            sel_answer[i] = Userans[i][0];
            if (Userans[i][0].equals(correctAnswers.get(questionOrder.get(i)))) {
                score += 10;
            }
        }
    
        // Show the score screen and save it to the database
        setVisible(false);
        new Score(currentUser, score).setVisible(true);
    }
    
}

    public static void main(String args[]) {
        ComputerQuiz frame = new ComputerQuiz();
        frame.setVisible(true);
    }
}