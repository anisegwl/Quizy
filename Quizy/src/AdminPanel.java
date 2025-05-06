import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminPanel extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JPanel dashboardPanel;
    private JList<String> userList;
    private DefaultListModel<String> userModel;

    public AdminPanel() {
        setTitle("Quiz Admin Panel");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(6, 1, 10, 10));
        sidebar.setPreferredSize(new Dimension(250, 700));
        sidebar.setBackground(new Color(44, 62, 80));

        JButton dashboardBtn = createSidebarButton("Dashboard");
        JButton manageQuestionsBtn = createSidebarButton("Manage Questions");
        JButton reportsBtn = createSidebarButton("Reports");
        JButton logoutBtn = createSidebarButton("Logout");

        sidebar.add(dashboardBtn);
        sidebar.add(manageQuestionsBtn);
        sidebar.add(reportsBtn);
        sidebar.add(logoutBtn);

        dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(new Color(236, 240, 241));
        JLabel titleLabel = new JLabel("Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        dashboardPanel.add(titleLabel, BorderLayout.NORTH);

        userModel = new DefaultListModel<>();
        userList = new JList<>(userModel);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    String selectedUser = userList.getSelectedValue();
                    if (selectedUser != null) {
                        openUserScore(selectedUser);
                    }
                }
            }
        });
        JScrollPane userScrollPane = new JScrollPane(userList);
        dashboardPanel.add(userScrollPane, BorderLayout.CENTER);

        loadUsers();

        contentPanel.add(dashboardPanel, "Dashboard");
        contentPanel.add(createManageQuestionsPanel(), "Manage Questions"); // Add Manage Questions panel
        contentPanel.add(createReportsPanel(), "Reports");
        contentPanel.add(createContentPanel("Settings", "Configure application settings."), "Settings");

        dashboardBtn.addActionListener(e -> cardLayout.show(contentPanel, "Dashboard"));
        manageQuestionsBtn.addActionListener(e -> cardLayout.show(contentPanel, "Manage Questions"));
        reportsBtn.addActionListener(e -> cardLayout.show(contentPanel, "Reports"));
        logoutBtn.addActionListener(e -> logout());

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createManageQuestionsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(236, 240, 241));

        JLabel titleLabel = new JLabel("Manage Questions", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Table to display questions
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Question");
        tableModel.addColumn("Option A");
        tableModel.addColumn("Option B");
        tableModel.addColumn("Option C");
        tableModel.addColumn("Option D");
        tableModel.addColumn("Correct Answer");

        JTable questionTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(questionTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Button to add a new question
        JButton addQuestionBtn = new JButton("Add Question");
        addQuestionBtn.setFont(new Font("Arial", Font.BOLD, 14));
        addQuestionBtn.setBackground(new Color(39, 174, 96));
        addQuestionBtn.setForeground(Color.WHITE);
        addQuestionBtn.setFocusPainted(false);
        addQuestionBtn.addActionListener(e -> addQuestion(tableModel));

        // Button to delete a selected question
        JButton deleteQuestionBtn = new JButton("Delete Question");
        deleteQuestionBtn.setFont(new Font("Arial", Font.BOLD, 14));
        deleteQuestionBtn.setBackground(new Color(231, 76, 60));
        deleteQuestionBtn.setForeground(Color.WHITE);
        deleteQuestionBtn.setFocusPainted(false);
        deleteQuestionBtn.addActionListener(e -> deleteQuestion(tableModel, questionTable));

        // Button to edit a selected question
        JButton editQuestionBtn = new JButton("Edit Question");
        editQuestionBtn.setFont(new Font("Arial", Font.BOLD, 14));
        editQuestionBtn.setBackground(new Color(52, 152, 219));
        editQuestionBtn.setForeground(Color.WHITE);
        editQuestionBtn.setFocusPainted(false);
        editQuestionBtn.addActionListener(e -> editQuestion(tableModel, questionTable));

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addQuestionBtn);
        buttonPanel.add(editQuestionBtn);
        buttonPanel.add(deleteQuestionBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Load existing questions into the table
        loadQuestions(tableModel);

        return panel;
    }

    private void loadQuestions(DefaultTableModel tableModel) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizy", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM questions");

            tableModel.setRowCount(0); // Clear existing data
            while (rs.next()) {
                String question = rs.getString("question");
                String optionA = rs.getString("option_a");
                String optionB = rs.getString("option_b");
                String optionC = rs.getString("option_c");
                String optionD = rs.getString("option_d");
                String correctAnswer = rs.getString("correct_answer");
                tableModel.addRow(new Object[]{question, optionA, optionB, optionC, optionD, correctAnswer});
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading questions.");
        }
    }

    private void addQuestion(DefaultTableModel tableModel) {
        JTextField questionField = new JTextField();
        JTextField optionAField = new JTextField();
        JTextField optionBField = new JTextField();
        JTextField optionCField = new JTextField();
        JTextField optionDField = new JTextField();
        JTextField answerField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Question:"));
        panel.add(questionField);
        panel.add(new JLabel("Option A:"));
        panel.add(optionAField);
        panel.add(new JLabel("Option B:"));
        panel.add(optionBField);
        panel.add(new JLabel("Option C:"));
        panel.add(optionCField);
        panel.add(new JLabel("Option D:"));
        panel.add(optionDField);
        panel.add(new JLabel("Correct Answer:"));
        panel.add(answerField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Question", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String question = questionField.getText();
            String optionA = optionAField.getText();
            String optionB = optionBField.getText();
            String optionC = optionCField.getText();
            String optionD = optionDField.getText();
            String answer = answerField.getText();

            if (!question.isEmpty() && !optionA.isEmpty() && !optionB.isEmpty() && !optionC.isEmpty() && !optionD.isEmpty() && !answer.isEmpty()) {
                saveQuestionToDatabase(question, optionA, optionB, optionC, optionD, answer);
                tableModel.addRow(new Object[]{question, optionA, optionB, optionC, optionD, answer});
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields!");
            }
        }
    }

    private void saveQuestionToDatabase(String question, String optionA, String optionB, String optionC, String optionD, String answer) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizy", "root", "");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO questions (question, option_a, option_b, option_c, option_d, correct_answer) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, question);
            stmt.setString(2, optionA);
            stmt.setString(3, optionB);
            stmt.setString(4, optionC);
            stmt.setString(5, optionD);
            stmt.setString(6, answer);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(this, "Question added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving the question!");
        }
    }

    private void deleteQuestion(DefaultTableModel tableModel, JTable questionTable) {
        int selectedRow = questionTable.getSelectedRow();
        if (selectedRow >= 0) {
            String question = (String) tableModel.getValueAt(selectedRow, 0);
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizy", "root", "");
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM questions WHERE question = ?");
                stmt.setString(1, question);
                stmt.executeUpdate();

                stmt.close();
                conn.close();
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Question deleted successfully!");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting the question!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a question to delete!");
        }
    }

    private void editQuestion(DefaultTableModel tableModel, JTable questionTable) {
        int selectedRow = questionTable.getSelectedRow();
        if (selectedRow >= 0) {
            String question = (String) tableModel.getValueAt(selectedRow, 0);
            String optionA = (String) tableModel.getValueAt(selectedRow, 1);
            String optionB = (String) tableModel.getValueAt(selectedRow, 2);
            String optionC = (String) tableModel.getValueAt(selectedRow, 3);
            String optionD = (String) tableModel.getValueAt(selectedRow, 4);
            String answer = (String) tableModel.getValueAt(selectedRow, 5);

            JTextField questionField = new JTextField(question);
            JTextField optionAField = new JTextField(optionA);
            JTextField optionBField = new JTextField(optionB);
            JTextField optionCField = new JTextField(optionC);
            JTextField optionDField = new JTextField(optionD);
            JTextField answerField = new JTextField(answer);

            JPanel panel = new JPanel(new GridLayout(6, 2));
            panel.add(new JLabel("Question:"));
            panel.add(questionField);
            panel.add(new JLabel("Option A:"));
            panel.add(optionAField);
            panel.add(new JLabel("Option B:"));
            panel.add(optionBField);
            panel.add(new JLabel("Option C:"));
            panel.add(optionCField);
            panel.add(new JLabel("Option D:"));
            panel.add(optionDField);
            panel.add(new JLabel("Correct Answer:"));
            panel.add(answerField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Edit Question", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String newQuestion = questionField.getText();
                String newOptionA = optionAField.getText();
                String newOptionB = optionBField.getText();
                String newOptionC = optionCField.getText();
                String newOptionD = optionDField.getText();
                String newAnswer = answerField.getText();

                if (!newQuestion.isEmpty() && !newOptionA.isEmpty() && !newOptionB.isEmpty() && !newOptionC.isEmpty() && !newOptionD.isEmpty() && !newAnswer.isEmpty()) {
                    updateQuestionInDatabase(question, newQuestion, newOptionA, newOptionB, newOptionC, newOptionD, newAnswer);
                    tableModel.setValueAt(newQuestion, selectedRow, 0);
                    tableModel.setValueAt(newOptionA, selectedRow, 1);
                    tableModel.setValueAt(newOptionB, selectedRow, 2);
                    tableModel.setValueAt(newOptionC, selectedRow, 3);
                    tableModel.setValueAt(newOptionD, selectedRow, 4);
                    tableModel.setValueAt(newAnswer, selectedRow, 5);
                } else {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a question to edit!");
        }
    }

    private void updateQuestionInDatabase(String oldQuestion, String newQuestion, String optionA, String optionB, String optionC, String optionD, String answer) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizy", "root", "");
            PreparedStatement stmt = conn.prepareStatement("UPDATE questions SET question = ?, option_a = ?, option_b = ?, option_c = ?, option_d = ?, correct_answer = ? WHERE question = ?");
            stmt.setString(1, newQuestion);
            stmt.setString(2, optionA);
            stmt.setString(3, optionB);
            stmt.setString(4, optionC);
            stmt.setString(5, optionD);
            stmt.setString(6, answer);
            stmt.setString(7, oldQuestion);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(this, "Question updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating the question!");
        }
    }

    private JPanel createReportsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(236, 240, 241));

        JLabel titleLabel = new JLabel("Quiz Performance Reports", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Table to display quiz reports
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Username");
        tableModel.addColumn("Score");
        tableModel.addColumn("Date");

        JTable reportTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(reportTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Button to load reports
        JButton loadReportsBtn = new JButton("Load Reports");
        loadReportsBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loadReportsBtn.setBackground(new Color(52, 152, 219));
        loadReportsBtn.setForeground(Color.WHITE);
        loadReportsBtn.setFocusPainted(false);
        loadReportsBtn.addActionListener(e -> loadQuizReports(tableModel));

        // Button to export reports to a file
        JButton exportReportsBtn = new JButton("Export Reports");
        exportReportsBtn.setFont(new Font("Arial", Font.BOLD, 14));
        exportReportsBtn.setBackground(new Color(39, 174, 96));
        exportReportsBtn.setForeground(Color.WHITE);
        exportReportsBtn.setFocusPainted(false);
        exportReportsBtn.addActionListener(e -> exportReportsToFile(tableModel));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadReportsBtn);
        buttonPanel.add(exportReportsBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void loadQuizReports(DefaultTableModel tableModel) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizy", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT username, score, quiz_date FROM scores ORDER BY quiz_date DESC");

            tableModel.setRowCount(0); // Clear existing data
            while (rs.next()) {
                String username = rs.getString("username");
                int score = rs.getInt("score");
                String date = rs.getString("quiz_date");
                tableModel.addRow(new Object[]{username, score, date});
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading quiz reports.");
        }
    }

    private void exportReportsToFile(DefaultTableModel tableModel) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("quiz_reports.csv"));
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                writer.write(tableModel.getColumnName(i) + ",");
            }
            writer.newLine();

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    writer.write(tableModel.getValueAt(i, j).toString() + ",");
                }
                writer.newLine();
            }

            writer.close();
            JOptionPane.showMessageDialog(this, "Reports exported to quiz_reports.csv");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error exporting reports.");
        }
    }

    private void loadUsers() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizy", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT username FROM scores");
            userModel.clear();
            while (rs.next()) {
                userModel.addElement(rs.getString("username"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openUserScore(String username) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizy", "root", "");
            PreparedStatement stmt = conn.prepareStatement("SELECT score FROM scores WHERE username = ? ORDER BY quiz_date DESC LIMIT 1");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int score = rs.getInt("score");
                new Score(username, score).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No score found for user: " + username);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading user score.");
        }
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }

    private JPanel createContentPanel(String title, String description) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(236, 240, 241));
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JLabel descLabel = new JLabel(description, SwingConstants.CENTER);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(descLabel, BorderLayout.CENTER);
        return panel;
    }

    private void logout() {
        JOptionPane.showMessageDialog(this, "Logging out...");
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminPanel adminPanel = new AdminPanel();
            adminPanel.setVisible(true);
        });
    }
}