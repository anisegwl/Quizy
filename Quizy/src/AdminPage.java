// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.sql.*;

// public class AdminPage extends JFrame {
//     private JButton addQuestionButton, removeQuestionButton;

//     public AdminPage() {
//         setTitle("Admin - Manage Questions");
//         setSize(400, 300);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLocationRelativeTo(null);
        
//         JPanel panel = new JPanel();
//         panel.setLayout(new GridLayout(2, 1));

//         addQuestionButton = new JButton("Add Question");
//         addQuestionButton.addActionListener(e -> new AddQuestionPage().setVisible(true));
//         removeQuestionButton = new JButton("Remove Question");
//         removeQuestionButton.addActionListener(e -> new RemoveQuestionPage().setVisible(true));

//         panel.add(addQuestionButton);
//         panel.add(removeQuestionButton);

//         add(panel);
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> new AdminPage().setVisible(true));
//     }
// }
