package Main;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

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
            System.out.println("Who do you want to search for?: ");
            Scanner userInput = new Scanner(System.in);
            String inputArtist = userInput.nextLine();
            String query = "SELECT artist_name, song_name, album_name, song_duration FROM artist a, song s, artist_song sa, album ab WHERE sa.artist_id = a.artist_id AND sa.song_id = s.song_id AND ab.artist_id = a.artist_id AND s.song_name = " + "'" + inputArtist+ "'"+ ";";
            ResultSet rs = stmt.executeQuery(query);
            if(rs != null) {
                if(rs.isBeforeFirst()) {
                    while(rs.next()) {
                        System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                    }
                }
            }
            else {
                System.out.println("Could Not Be Found");
            }
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
