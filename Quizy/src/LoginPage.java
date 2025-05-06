import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener{
    JTextField tusername;
    JTextField tpassword;
    JButton login , back, register; 
    
    
   
    LoginPage(){
        JLabel username = new JLabel("username");
        username.setBounds(40,20,100,30);
        add(username);

        tusername = new JTextField();
        tusername.setBounds(110,30,100,20);
        add(tusername);

        JLabel Password = new JLabel("Password");
        Password.setBounds(40,70,100,30);
        add(Password);

        tpassword = new JPasswordField();
        tpassword.setBounds(110,70,100,20);
        add(tpassword);

        login = new JButton("LOGIN");
        login.setBounds(110,100,100,20);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);

        back = new JButton("Back");
        back.setBounds(210,100,100,20);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        register = new JButton("Register");
        register.setBounds(310, 100, 100, 20);
        register.setBackground(Color.BLACK);
        register.setForeground(Color.white);
        register.addActionListener(this);
        add(register);

    


         // Add the Image on the Frame
				 ImageIcon i1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage("quizbg.jpg"));
				 Image i2 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
			     ImageIcon i3 = new ImageIcon(i2);
			     //add the label to display image on it
			     JLabel imageLabel = new JLabel(i3);
				 imageLabel.setBounds(10,25,587,500);
				 getContentPane().add(imageLabel);

        setSize(600,300);
        setLocation(450, 200);
        setLayout(null);
        setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()== login){
        try{
            String username = tusername.getText();
            String password = tpassword.getText();

            connection conn = new connection();
            
            String query = "select * from users where username = '"+username+"' and password = '"+password+"'";
            ResultSet resultset = conn.statement.executeQuery(query);
            if(resultset.next()){
                setVisible(false);
                QuizLauncher q = new QuizLauncher();
                q.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null,"invalid username or password");
            }
                    

        }catch(Exception E){
            E.printStackTrace();
        }

       } else if(e.getSource()== register){
        new RegisterPage();

       }else if(e.getSource()== back) {
        setVisible(false);
       }
    }
    public static void main(String[] args) {
        new LoginPage();
    }
}
