import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DBConnection {

    private String url = "jdbc:mysql://localhost:3306/hospitals";
    private String username = "root";
    private String password = "XXXX";

    public DBConnection(){
    }

    public Connection getConnections() throws SQLException{
        return DriverManager.getConnection(url, username,password);
    }

    public Scanner getScanner(){
        return new Scanner(System.in);
    }
}
