import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class AnimalQuiz extends JFrame implements ActionListener {
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

    public AnimalQuiz() {
        setResizable(false);

        // Set the Title and location, size of frame
        setTitle("Animal Quiz Application");
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

        // Initialize questions and answers
        initializeQuestions();

        // Randomize the order of questions and select the first 10
        for (int i = 0; i < 30; i++) {
            questionOrder.add(i);
        }
        Collections.shuffle(questionOrder);

        // Only keep the first 10 questions
        questionOrder = new ArrayList<>(questionOrder.subList(0, 10));

        enter(questionOrder.get(count));
    }

    // Method to initialize questions and answers
    private void initializeQuestions() {
        // Questions for Quiz Application
        questions[0][0] = "What is the National animal of Nepal?";
        questions[0][1] = "Cow";
        questions[0][2] = "Crow";
        questions[0][3] = "Danfe";
        questions[0][4] = "Donkey";
        answers[0][1] = "Danfe";

        questions[1][0] = "Which animal is known as the 'King of the Jungle'?";
        questions[1][1] = "Lion";
        questions[1][2] = "Tiger";
        questions[1][3] = "Elephant";
        questions[1][4] = "Giraffe";
        answers[1][1] = "Lion";

        // Add more questions and answers here...
    }

    // Method to replace a question in the questions array
    public void replaceQuestion(int index, String[] newQuestion) {
        if (index >= 0 && index < questions.length) {
            questions[index] = newQuestion; // Replace the question
            answers[index][1] = newQuestion[5]; // Update the correct answer
        }
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
        AnimalQuiz frame = new AnimalQuiz();
        frame.setVisible(true);
    }
}