import java.sql.Connection;
import java.sql.DriverManager;

public class connection {
    Connection connection; 
    java.sql.Statement statement;

    public connection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizy", "root", "");
            statement = connection.createStatement();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new connection();
    }
}