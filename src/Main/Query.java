package Main;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.Duration;
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

    public static void playList (String userInput)
    {
        int hours, minutes, seconds, duration;
        String tmp;
        Connection con = DatabaseConnection.getConnection();
        String query = "SELECT p.playlist_name, SUM(s.song_duration) AS LENGTH FROM playlist_song ps JOIN song s ON ps.song_id = s.song_id JOIN playlist p ON ps.playlist_id = p.playlist_id WHERE p.playlist_name LIKE '%" + userInput + "%' GROUP BY p.playlist_id;";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs != null) {
                if(rs.isBeforeFirst()) {
                    System.out.println("Playlist Name: || Duration: HH:MM:ss");
                    while(rs.next()) {
                        tmp = rs.getString(2);
                        duration = Integer.parseInt(tmp);
                        hours = duration / 3600;
                        minutes = (duration % 3600) / 60;
                        seconds = duration % 60;
                        System.out.println(rs.getString(1) + " || " + hours + ":" + minutes + ":" + seconds);
                    }
                }
            } else {
                System.out.println("Empty Set");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void concert (Scanner userInput)
    {

    }

    public static void genre (Scanner userInput)
    {

    }
}
