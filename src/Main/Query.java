package Main;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

public class Query {
    public static void song (String userInput) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT artist_name, song_name, album_name, song_duration FROM artist a, song s, artist_song sa, album ab WHERE sa.artist_id = a.artist_id AND sa.song_id = s.song_id AND ab.artist_id = a.artist_id AND s.song_name LIKE '%" + userInput + "%';";
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
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void artist (Scanner userInput)
    {

    }

    public static void album (Scanner userInput)
    {

    }

    public static void label (Scanner userInput)
    {

    }

    public static void playList (Scanner userInput)
    {

    }

    public static void concert (Scanner userInput)
    {

    }

    public static void genre (Scanner userInput)
    {

    }
}
