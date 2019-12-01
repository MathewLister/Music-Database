package Main;
// james
import javax.xml.transform.Result;
import java.sql.*;

public class Main {
    private static String DBUser = "bettagj";
    private static String pass = "Password0*";
    private static String url = "jdbc:mysql://leia.cs.spu.edu/bettagj_db?serverTimezone=UTC";

    public static void main(String args[])
    {

        try{
            int i = 1;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, DBUser, pass);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM person");
            if(rs != null) {
                if(rs.isBeforeFirst()) {
                    while(rs.next()) {
                        System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                    }
                }
            }
            else {
                System.out.println("Empty Set");
            }
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
