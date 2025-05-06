import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Score extends JFrame implements ActionListener {
    JButton Again;
    String username;
    int userScore;

    public Score(String username, int score) {
        this.username = username;
        this.userScore = score;

        setResizable(false);
        setTitle("Score");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 150, 1200, 600);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.white);

        JLabel heading = new JLabel("The Score of the user is ");
        heading.setForeground(Color.RED);
        heading.setBounds(300, 30, 700, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 26));
        getContentPane().add(heading);

        JLabel lblscore = new JLabel("Your score is: " + score);
        lblscore.setBounds(700, 200, 300, 30);
        lblscore.setFont(new Font("Tahoma", Font.PLAIN, 26));
        getContentPane().add(lblscore);

        Again = new JButton("Play Again");
        Again.setBounds(725, 272, 120, 40);
        Again.setBackground(new Color(30, 144, 255));
        Again.setForeground(Color.WHITE);
        Again.addActionListener(this);
        getContentPane().add(Again);

        saveScoreToDatabase();
    }

    private void saveScoreToDatabase() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizy", "root", "");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO scores (username, score) VALUES (?, ?)");
            stmt.setString(1, username);
            stmt.setInt(2, userScore);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving score to database.");
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == Again) {
            setVisible(false);
            new QuizLauncher().setVisible(true);
        }
    }

    public static void main(String args[]) {
        new Score("TestUser", 0).setVisible(true);
    }
}
