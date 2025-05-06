import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class QuizLauncher extends JFrame implements ActionListener {
    JButton javaButton, animalbtn, mathbtn, computerbtn;
    JLabel titleLabel, imageLabel, bottomLabel;
    JPanel leftPanel, rightPanel, bottomPanel;

    public QuizLauncher() {
        setTitle("Quiz Launcher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null); // Center the frame on the screen

        
        ImageIcon icon = new ImageIcon("quizbg.jpg");
        Image img = icon.getImage(); // Convert to Image
        Image scaledImg = img.getScaledInstance(500, 900, Image.SCALE_SMOOTH); // Resize
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        imageLabel = new JLabel(scaledIcon);



        // Title Label (Right Side)
        titleLabel = new JLabel("Choose one of the Categories", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Best of Luck Label (Bottom)
        bottomLabel = new JLabel("Best of Luck!", SwingConstants.CENTER);
        bottomLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Create quiz buttons
        javaButton = new JButton("Java Quiz");
        javaButton.setFont(new Font("Arial", Font.BOLD, 16));
        javaButton.addActionListener(this);

        animalbtn = new JButton("Animal Quiz");
        animalbtn.setFont(new Font("Arial", Font.BOLD, 16));
        animalbtn.addActionListener(this);

        mathbtn = new JButton("Maths Quiz");
        mathbtn.setFont(new Font("Arial", Font.BOLD, 16));
        mathbtn.addActionListener(this);

        computerbtn = new JButton("Computer Quiz");
        computerbtn.setFont(new Font("Arial", Font.BOLD, 16));
        computerbtn.addActionListener(this);

        // Left Panel (Image)
        leftPanel = new JPanel();
        leftPanel.add(imageLabel);

        // Right Panel (Buttons & Title)
        rightPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        rightPanel.add(titleLabel);
        rightPanel.add(javaButton);
        rightPanel.add(animalbtn);
        rightPanel.add(mathbtn);
        rightPanel.add(computerbtn);

        // Bottom Panel
        bottomPanel = new JPanel();
        bottomPanel.add(bottomLabel);

        // Main Layout
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == javaButton) {
            javaQuiz quizFrame = new javaQuiz();
            quizFrame.setVisible(true);
        } else if (e.getSource() == animalbtn) {
            AnimalQuiz quizFrame = new AnimalQuiz();
            quizFrame.setVisible(true);
        } else if (e.getSource() == mathbtn) {
            MathsQuiz quizFrame = new MathsQuiz();
            quizFrame.setVisible(true);
        } else if (e.getSource() == computerbtn) {
            ComputerQuiz quizFrame = new ComputerQuiz();
            quizFrame.setVisible(true);
        }
        this.dispose(); // Close the launcher window after selecting a quiz
    }

    public static void main(String[] args) {
        QuizLauncher launcher = new QuizLauncher();
        launcher.setVisible(true);
    }
}
