import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class MathsQuiz extends JFrame implements ActionListener {
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

    public MathsQuiz() {
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

        // Questions for Quiz Application (Mathematics)
questions[0][0] = "What is the value of π (pi) rounded to two decimal places?";
questions[0][1] = "3.14";
questions[0][2] = "3.16";
questions[0][3] = "3.12";
questions[0][4] = "3.18";

questions[1][0] = "What is the square root of 64?";
questions[1][1] = "8";
questions[1][2] = "6";
questions[1][3] = "10";
questions[1][4] = "12";

questions[2][0] = "What is the result of 5! (5 factorial)?";
questions[2][1] = "120";
questions[2][2] = "60";
questions[2][3] = "24";
questions[2][4] = "720";

questions[3][0] = "What is the area of a rectangle with length 8 and width 5?";
questions[3][1] = "40";
questions[3][2] = "13";
questions[3][3] = "20";
questions[3][4] = "32";

questions[4][0] = "What is the value of 2^10 (2 raised to the power of 10)?";
questions[4][1] = "1024";
questions[4][2] = "512";
questions[4][3] = "256";
questions[4][4] = "2048";

questions[5][0] = "What is the sum of the angles in a triangle?";
questions[5][1] = "180 degrees";
questions[5][2] = "90 degrees";
questions[5][3] = "360 degrees";
questions[5][4] = "270 degrees";

questions[6][0] = "What is the value of log₁₀100?";
questions[6][1] = "2";
questions[6][2] = "1";
questions[6][3] = "10";
questions[6][4] = "100";

questions[7][0] = "What is the formula for the circumference of a circle?";
questions[7][1] = "2πr";
questions[7][2] = "πr²";
questions[7][3] = "πd";
questions[7][4] = "2πd";

questions[8][0] = "What is the next prime number after 7?";
questions[8][1] = "11";
questions[8][2] = "9";
questions[8][3] = "13";
questions[8][4] = "17";

questions[9][0] = "What is the value of sin(90°)?";
questions[9][1] = "1";
questions[9][2] = "0";
questions[9][3] = "0.5";
questions[9][4] = "-1";

questions[10][0] = "What is the derivative of x² with respect to x?";
questions[10][1] = "2x";
questions[10][2] = "x";
questions[10][3] = "2";
questions[10][4] = "x²";

questions[11][0] = "What is the integral of 2x with respect to x?";
questions[11][1] = "x² + C";
questions[11][2] = "2x² + C";
questions[11][3] = "x + C";
questions[11][4] = "2 + C";

questions[12][0] = "What is the value of 3 × (4 + 2) ÷ 2?";
questions[12][1] = "9";
questions[12][2] = "6";
questions[12][3] = "12";
questions[12][4] = "8";

questions[13][0] = "What is the slope of the line y = 3x + 2?";
questions[13][1] = "3";
questions[13][2] = "2";
questions[13][3] = "1";
questions[13][4] = "0";

questions[14][0] = "What is the value of 1 + 2 + 3 + ... + 10?";
questions[14][1] = "55";
questions[14][2] = "45";
questions[14][3] = "50";
questions[14][4] = "60";

questions[15][0] = "What is the Pythagorean theorem?";
questions[15][1] = "a² + b² = c²";
questions[15][2] = "a + b = c";
questions[15][3] = "a × b = c";
questions[15][4] = "a² - b² = c²";

questions[16][0] = "What is the value of tan(45°)?";
questions[16][1] = "1";
questions[16][2] = "0";
questions[16][3] = "0.5";
questions[16][4] = "2";

questions[17][0] = "What is the value of 7 × 8 - 6 ÷ 2?";
questions[17][1] = "53";
questions[17][2] = "50";
questions[17][3] = "55";
questions[17][4] = "60";

questions[18][0] = "What is the value of e (Euler's number) rounded to two decimal places?";
questions[18][1] = "2.72";
questions[18][2] = "3.14";
questions[18][3] = "1.62";
questions[18][4] = "2.50";

questions[19][0] = "What is the value of 1/2 + 1/4?";
questions[19][1] = "3/4";
questions[19][2] = "1/2";
questions[19][3] = "1/4";
questions[19][4] = "1/8";

questions[20][0] = "What is the value of √144?";
questions[20][1] = "12";
questions[20][2] = "14";
questions[20][3] = "16";
questions[20][4] = "10";

questions[21][0] = "What is the value of 5² + 12²?";
questions[21][1] = "169";
questions[21][2] = "144";
questions[21][3] = "25";
questions[21][4] = "194";

questions[22][0] = "What is the value of 0! (0 factorial)?";
questions[22][1] = "1";
questions[22][2] = "0";
questions[22][3] = "Undefined";
questions[22][4] = "10";

questions[23][0] = "What is the value of 2³ × 3²?";
questions[23][1] = "72";
questions[23][2] = "36";
questions[23][3] = "64";
questions[23][4] = "81";

questions[24][0] = "What is the value of log₂8?";
questions[24][1] = "3";
questions[24][2] = "2";
questions[24][3] = "4";
questions[24][4] = "1";

questions[25][0] = "What is the value of 1 + 2 × 3?";
questions[25][1] = "7";
questions[25][2] = "9";
questions[25][3] = "5";
questions[25][4] = "6";

questions[26][0] = "What is the value of 100 ÷ (2 + 3) × 2?";
questions[26][1] = "40";
questions[26][2] = "20";
questions[26][3] = "50";
questions[26][4] = "10";

questions[27][0] = "What is the value of sin(30°)?";
questions[27][1] = "0.5";
questions[27][2] = "1";
questions[27][3] = "0";
questions[27][4] = "0.75";

questions[28][0] = "What is the value of 3⁴ (3 raised to the power of 4)?";
questions[28][1] = "81";
questions[28][2] = "64";
questions[28][3] = "27";
questions[28][4] = "12";

questions[29][0] = "What is the value of 1/3 + 1/6?";
questions[29][1] = "1/2";
questions[29][2] = "1/3";
questions[29][3] = "1/6";
questions[29][4] = "1/4";

// Answers for Quiz Application (Mathematics)
answers[0][1] = "3.14";
answers[1][1] = "8";
answers[2][1] = "120";
answers[3][1] = "40";
answers[4][1] = "1024";
answers[5][1] = "180 degrees";
answers[6][1] = "2";
answers[7][1] = "2πr";
answers[8][1] = "11";
answers[9][1] = "1";
answers[10][1] = "2x";
answers[11][1] = "x² + C";
answers[12][1] = "9";
answers[13][1] = "3";
answers[14][1] = "55";
answers[15][1] = "a² + b² = c²";
answers[16][1] = "1";
answers[17][1] = "53";
answers[18][1] = "2.72";
answers[19][1] = "3/4";
answers[20][1] = "12";
answers[21][1] = "169";
answers[22][1] = "1";
answers[23][1] = "72";
answers[24][1] = "3";
answers[25][1] = "7";
answers[26][1] = "40";
answers[27][1] = "0.5";
answers[28][1] = "81";
answers[29][1] = "1/2";

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
		

    } else if (e.getSource() == lifeline) {
        opt2.setEnabled(false);
        opt4.setEnabled(false);
        lifeline.setEnabled(false);
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
        MathsQuiz frame = new MathsQuiz();
        frame.setVisible(true);
    }
}