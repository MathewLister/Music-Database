package Main;

import javax.xml.transform.Result;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Scanner;
import java.util.TimeZone;

public class Query {
    public static void song (String userInput) {
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
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
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //Not working!
    public static void artist (String userInput)
    {
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            String query = "SELECT artist_name FROM person_artist pa, artist a, person p WHERE pa.person_id = p.person_id AND pa.artist_id = a.artist_id AND a.artist_name LIKE '%" + userInput + "%';";
            ResultSet rs = stmt.executeQuery(query);

            if(rs != null) {
                if(rs.isBeforeFirst()) {
                    while(rs.next()) {
                        System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                    }
                }
            }
            else {
                System.out.println("Artist" + userInput + "Could Not Be Found");
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void album (Scanner userInput)
    {

    }

    public static void label (Scanner userInput)
    {

    }

    public static void playList (String userInput)
    {
        int duration;
        String tmp;
        Connection con = DatabaseConnection.getConnection();
        String query = "SELECT p.playlist_name, SUM(s.song_duration) AS LENGTH FROM playlist_song ps JOIN song s ON ps.song_id = s.song_id JOIN playlist p ON ps.playlist_id = p.playlist_id WHERE p.playlist_name LIKE '%" + userInput + "%' GROUP BY p.playlist_id;";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs != null) {
                if(rs.isBeforeFirst()) {
                    System.out.println("Playlist Name: || Duration: HH:mm:ss");
                    while(rs.next()) {

                        tmp = rs.getString(2);
                        duration = Integer.parseInt(tmp);
                        Date date = new Date(duration * 1000L);
                        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                        df.setTimeZone(TimeZone.getTimeZone("GMT"));
                        tmp = df.format(date);
                        System.out.println(rs.getString(1) + " || " + tmp);
                    }
                }
            } else {
                System.out.println("Empty Set");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void concert (Scanner userInput)
    {

    }

    public static void genre (String userInput)
    {
        Connection con = DatabaseConnection.getConnection();
        String query = "SELECT artist_name, album_name, genre, album_date, album_id FROM album JOIN artist ON album.artist_id = artist.artist_id WHERE album.genre LIKE '%" + userInput + "%';";
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs != null) {
                if(rs.isBeforeFirst()) {
                    System.out.println("Artist || Album || Genre || Album Date || Album ID");
                    while(rs.next()) {
                        System.out.println(rs.getString(1) + " || " + rs.getString(2) + " || " + rs.getString(3) + " || " + rs.getString(4) + " || " + rs.getString(5));
                    }
                }
            }
            else {
                System.out.println("Could Not Be Found");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String insertSongIntoPlaylist (int songID, int playlistID)
    {
        String returnMessage = "Could not complete insertion";
        String checkQuery = "SELECT playlist_song_id FROM playlist_song WHERE song_id = " + songID + " AND playlist_id = " + playlistID + ";";
        String checkSong = "SELECT * FROM song WHERE song_id = " + songID + ";";
        String checkPlaylist = "SELECT * FROM playlist WHERE playlist_id = " + playlistID + ";";
        String insertQuery = "INSERT INTO playlist_song (song_id, playlist_id) VALUES (" + songID + ", " + playlistID + ");";

        Connection con = DatabaseConnection.getConnection();
        try{
            // create 3 separate statements because only one ResultSet object per Statement object can be open at the same time
            Statement stmt1 = con.createStatement();
            Statement stmt2 = con.createStatement();
            Statement stmt3 = con.createStatement();
            ResultSet rs1 = stmt1.executeQuery(checkQuery);
            ResultSet rs2 = stmt2.executeQuery(checkSong);
            ResultSet rs3 = stmt3.executeQuery(checkPlaylist);
            if(!rs2.next()) { // check if song is in the song table
                returnMessage = "Song does not exist";
            } else if(!rs3.next()) { // check if playlist is in the playlist table
                returnMessage = "Playlist does not exist";
            } else if(rs1.next()) { // check if song is already related to the playlist
                returnMessage = "Song is already in playlist";
            } else {
                PreparedStatement ps = con.prepareStatement(insertQuery);
                int ret = ps.executeUpdate(); // execute query and store return value in ret
                //System.out.println("Return from Update: " + ret);
                if(ret == 1) {
                    returnMessage = "Successful Insertion";
                }
            }
            con.close(); // all lower objects are closed when connection is closed
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMessage;
    }

    public static void insertConcert (String userInput)
    {

    }
}