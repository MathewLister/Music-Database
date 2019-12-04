package Main;

import javax.xml.transform.Result;
import java.sql.*;


public class DatabaseConnection {
    private static final String DBUser = "bettagj";
    private static final String pass = "Password0*";
    private static final String url = "jdbc:mysql://leia.cs.spu.edu/bettagj_db?serverTimezone=UTC";

    static Connection getConnection()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return  DriverManager.getConnection(url, DBUser, pass);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    //**Test Connections**
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
    }
}
