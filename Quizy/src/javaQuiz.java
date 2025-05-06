import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class javaQuiz extends JFrame implements ActionListener {
    JButton Next, lifeline, submit, back;
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

    public javaQuiz() {
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

        // Questions for Quiz Application (Java Programming)
questions[0][0] = "What is the default value of an int variable in Java?";
questions[0][1] = "0";
questions[0][2] = "1";
questions[0][3] = "null";
questions[0][4] = "undefined";

questions[1][0] = "Which keyword is used to define a constant in Java?";
questions[1][1] = "final";
questions[1][2] = "static";
questions[1][3] = "const";
questions[1][4] = "define";

questions[2][0] = "What is the parent class of all classes in Java?";
questions[2][1] = "Object";
questions[2][2] = "Main";
questions[2][3] = "Super";
questions[2][4] = "Parent";

questions[3][0] = "Which of the following is not a primitive data type in Java?";
questions[3][1] = "String";
questions[3][2] = "int";
questions[3][3] = "boolean";
questions[3][4] = "char";

questions[4][0] = "What is the size of a 'char' data type in Java?";
questions[4][1] = "2 bytes";
questions[4][2] = "1 byte";
questions[4][3] = "4 bytes";
questions[4][4] = "8 bytes";

questions[5][0] = "Which method is the entry point of a Java program?";
questions[5][1] = "public static void main(String[] args)";
questions[5][2] = "public void start(String[] args)";
questions[5][3] = "public static int main(String[] args)";
questions[5][4] = "public void main(String[] args)";

questions[6][0] = "What is the purpose of the 'this' keyword in Java?";
questions[6][1] = "To refer to the current object";
questions[6][2] = "To create a new object";
questions[6][3] = "To call a static method";
questions[6][4] = "To access a superclass method";

questions[7][0] = "Which of the following is used to handle exceptions in Java?";
questions[7][1] = "try-catch block";
questions[7][2] = "if-else statement";
questions[7][3] = "switch-case statement";
questions[7][4] = "for loop";

questions[8][0] = "What is the output of '10 % 3' in Java?";
questions[8][1] = "1";
questions[8][2] = "0";
questions[8][3] = "3";
questions[8][4] = "10";

questions[9][0] = "Which collection class allows duplicate elements in Java?";
questions[9][1] = "ArrayList";
questions[9][2] = "HashSet";
questions[9][3] = "TreeSet";
questions[9][4] = "HashMap";

questions[10][0] = "What is the superclass of all exceptions in Java?";
questions[10][1] = "Throwable";
questions[10][2] = "Error";
questions[10][3] = "RuntimeException";
questions[10][4] = "Exception";

questions[11][0] = "Which keyword is used to inherit a class in Java?";
questions[11][1] = "extends";
questions[11][2] = "implements";
questions[11][3] = "inherits";
questions[11][4] = "super";

questions[12][0] = "What is the default value of a boolean variable in Java?";
questions[12][1] = "false";
questions[12][2] = "true";
questions[12][3] = "null";
questions[12][4] = "0";

questions[13][0] = "Which of the following is a marker interface in Java?";
questions[13][1] = "Serializable";
questions[13][2] = "Runnable";
questions[13][3] = "Comparable";
questions[13][4] = "Cloneable";

questions[14][0] = "What is the purpose of the 'static' keyword in Java?";
questions[14][1] = "To create class-level variables and methods";
questions[14][2] = "To create instance variables";
questions[14][3] = "To define constants";
questions[14][4] = "To create objects";

questions[15][0] = "Which of the following is not a valid access modifier in Java?";
questions[15][1] = "friend";
questions[15][2] = "public";
questions[15][3] = "private";
questions[15][4] = "protected";

questions[16][0] = "What is the output of 'System.out.println(5 + 5) in Java?";
questions[16][1] = "55";
questions[16][2] = "10";
questions[16][3] = "5";
questions[16][4] = "Error";

questions[17][0] = "Which interface is used to sort objects in Java?";
questions[17][1] = "Comparable";
questions[17][2] = "Comparator";
questions[17][3] = "Serializable";
questions[17][4] = "Runnable";

questions[18][0] = "What is the purpose of the 'finalize()' method in Java?";
questions[18][1] = "To perform cleanup operations before garbage collection";
questions[18][2] = "To initialize an object";
questions[18][3] = "To terminate a program";
questions[18][4] = "To create a new thread";

questions[19][0] = "Which of the following is a valid way to create a thread in Java?";
questions[19][1] = "By extending the Thread class";
questions[19][2] = "By using the 'new' keyword";
questions[19][3] = "By using the 'start()' method";
questions[19][4] = "By using the 'run()' method";

questions[20][0] = "What is the default capacity of an ArrayList in Java?";
questions[20][1] = "10";
questions[20][2] = "5";
questions[20][3] = "20";
questions[20][4] = "0";

questions[21][0] = "Which of the following is a functional interface in Java?";
questions[21][1] = "Runnable";
questions[21][2] = "Serializable";
questions[21][3] = "Comparable";
questions[21][4] = "Cloneable";

questions[22][0] = "What is the purpose of the 'super' keyword in Java?";
questions[22][1] = "To refer to the superclass object";
questions[22][2] = "To create a new object";
questions[22][3] = "To call a static method";
questions[22][4] = "To define a constant";

questions[23][0] = "Which of the following is a valid annotation in Java?";
questions[23][1] = "@Override";
questions[23][2] = "@interface";
questions[23][3] = "@Annotation";
questions[23][4] = "@Method";

questions[24][0] = "What is the output of 'Math.floor(3.7)' in Java?";
questions[24][1] = "3.0";
questions[24][2] = "4.0";
questions[24][3] = "3.7";
questions[24][4] = "3";

questions[25][0] = "Which of the following is a valid lambda expression in Java?";
questions[25][1] = "(a, b) -> a + b";
questions[25][2] = "a, b -> a + b";
questions[25][3] = "(a, b) => a + b";
questions[25][4] = "a, b => a + b";

questions[26][0] = "What is the purpose of the 'volatile' keyword in Java?";
questions[26][1] = "To ensure visibility of changes across threads";
questions[26][2] = "To create a constant variable";
questions[26][3] = "To define a static method";
questions[26][4] = "To create a thread-safe class";

questions[27][0] = "Which of the following is a valid way to create a stream in Java?";
questions[27][1] = "List.stream()";
questions[27][2] = "Stream.of()";
questions[27][3] = "Arrays.stream()";
questions[27][4] = "All of the above";

questions[28][0] = "What is the purpose of the 'transient' keyword in Java?";
questions[28][1] = "To exclude a variable from serialization";
questions[28][2] = "To create a temporary variable";
questions[28][3] = "To define a static variable";
questions[28][4] = "To create a thread-safe variable";

questions[29][0] = "Which of the following is a valid way to handle null values in Java?";
questions[29][1] = "Optional";
questions[29][2] = "NullPointerException";
questions[29][3] = "try-catch block";
questions[29][4] = "if-else statement";

// Answers for Quiz Application (Java Programming)
answers[0][1] = "0";
answers[1][1] = "final";
answers[2][1] = "Object";
answers[3][1] = "String";
answers[4][1] = "2 bytes";
answers[5][1] = "public static void main(String[] args)";
answers[6][1] = "To refer to the current object";
answers[7][1] = "try-catch block";
answers[8][1] = "1";
answers[9][1] = "ArrayList";
answers[10][1] = "Throwable";
answers[11][1] = "extends";
answers[12][1] = "false";
answers[13][1] = "Serializable";
answers[14][1] = "To create class-level variables and methods";
answers[15][1] = "friend";
answers[16][1] = "55";
answers[17][1] = "Comparable";
answers[18][1] = "To perform cleanup operations before garbage collection";
answers[19][1] = "By extending the Thread class";
answers[20][1] = "10";
answers[21][1] = "Runnable";
answers[22][1] = "To refer to the superclass object";
answers[23][1] = "@Override";
answers[24][1] = "3.0";
answers[25][1] = "(a, b) -> a + b";
answers[26][1] = "To ensure visibility of changes across threads";
answers[27][1] = "All of the above";
answers[28][1] = "To exclude a variable from serialization";
answers[29][1] = "Optional";

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
        javaQuiz frame = new javaQuiz();
        frame.setVisible(true);
    }
}