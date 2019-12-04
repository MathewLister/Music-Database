package Main;

import javax.swing.plaf.nimbus.State;
import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Scanner;
import java.util.TimeZone;

public class Query {
    //Shows all songs w/ the name searched
    static void song(String userInput) {
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            String query = "SELECT artist_name, song_name, album_name, song_duration FROM artist a, song s, artist_song sa, album ab WHERE sa.artist_id = a.artist_id AND sa.song_id = s.song_id AND ab.artist_id = a.artist_id AND s.song_name LIKE '%" + userInput + "%';";
            String myFormat = "| %-30s | %-50s | %-50s | %-10s |%n";
            String tmp;
            Date date;
            SimpleDateFormat df;
            int duration;
            ResultSet rs = stmt.executeQuery(query);

            if(rs.next()) {
                System.out.format(myFormat, "Artist Name", "Song Name", "Album Name", "HH:mm:ss");
                System.out.println("---------------------------------------------------------------------------------------------");;
                do {
                    tmp = rs.getString(4);
                    duration = Integer.parseInt(tmp);
                    date = new Date(duration * 1000L);
                    df = new SimpleDateFormat("HH:mm:ss");
                    df.setTimeZone(TimeZone.getTimeZone("GMT"));
                    tmp = df.format(date);
                    System.out.format(myFormat, rs.getString(1), rs.getString(2), rs.getString(3), tmp);
                } while(rs.next());
                System.out.println("---------------------------------------------------------------------------------------------");
            } else {
                System.out.println("Could Not Be Found");
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //Shows artist discography
    static void artist(String userInput)
    {
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            String getArtist = "SELECT a.artist_name, al.album_name, s.song_name, s.song_duration  FROM artist a, album al, artist_song artsong, song s WHERE a.artist_id = al.artist_id AND artsong.artist_id = a.artist_id AND artsong.song_id = s.song_id AND al.album_id = s.album_id AND a.artist_name LIKE '%" + userInput + "%';";
            ResultSet rs = stmt.executeQuery(getArtist);

            if(rs != null) {
                if(rs.isBeforeFirst()) {
                    System.out.println("Artist || Album || Song || Song Duration");
                    System.out.println("---------------------------------------------------------------------------------------------");;
                    while(rs.next()) {
                        System.out.println(rs.getString(1) + " || " + rs.getString(2) + " || " + rs.getString(3) + " || " + rs.getString(4));
                    }
                }
                System.out.println("---------------------------------------------------------------------------------------------");
            }
            else {
                System.out.println("Artist" + userInput + "Could Not Be Found");
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //Show all the songs in a album
    static void album(String userInput)
    {
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            String getAlbum = "SELECT a.artist_name, al.album_name, s.song_name, s.song_duration  FROM artist a, album al, artist_song artsong, song s WHERE a.artist_id = al.artist_id AND artsong.artist_id = a.artist_id AND artsong.song_id = s.song_id AND al.album_id = s.album_id AND al.album_name LIKE '%" + userInput + "%';";
            ResultSet rs = stmt.executeQuery(getAlbum);
            if(rs != null) {
                if(rs.isBeforeFirst()) {
                    System.out.println("Artist || Album || Song || Song Duration");
                    System.out.println("---------------------------------------------------------------------------------------------");
                    while(rs.next()) {
                        System.out.println(rs.getString(1) + " || " + rs.getString(2) + " || " + rs.getString(3) + " || " + rs.getString(4));
                    }
                }
                System.out.println("---------------------------------------------------------------------------------------------");
            }
            else {
                System.out.println("Album" + userInput + "Could Not Be Found");
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    static void label(String userInput)
    {
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            String getAlbum = "SELECT l.label_name, al.album_name FROM label l, album al WHERE l.label_id = al.label_id AND l.label_name LIKE '%" + userInput + "%';";
            ResultSet rs = stmt.executeQuery(getAlbum);
            if(rs != null) {
                if(rs.isBeforeFirst()) {
                    System.out.println("Label || Album");
                    System.out.println("---------------------------------------------------------------------------------------------");;
                    while(rs.next()) {
                        System.out.println(rs.getString(1) + " || " + rs.getString(2));
                    }
                }
                System.out.println("---------------------------------------------------------------------------------------------");
            }
            else {
                System.out.println("Label " + userInput + "Could Not Be Found");
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    static void playList(String userInput)
    {
        int duration;
        String tmp;
        Connection con = DatabaseConnection.getConnection();
        String getPlayList = "SELECT p.playlist_name, SUM(s.song_duration) AS LENGTH FROM playlist_song ps JOIN song s ON ps.song_id = s.song_id JOIN playlist p ON ps.playlist_id = p.playlist_id WHERE p.playlist_name LIKE '%" + userInput + "%' GROUP BY p.playlist_id;";
        String showSongs = "SELECT artist_name, song_name, album_name, song_duration FROM artist a, song s, artist_song sa, album ab, playlist_song playSong, playlist play WHERE sa.artist_id = a.artist_id AND sa.song_id = s.song_id AND ab.artist_id = a.artist_id AND playSong.playlist_id = play.playlist_id AND playSong.song_id = s.song_id AND play.playlist_name LIKE '%" + userInput + "%';";
        try {
            Statement stmt1 = con.createStatement();
            Statement stmt2 = con.createStatement();

            ResultSet rs1 = stmt1.executeQuery(getPlayList);
            ResultSet rs2 = stmt2.executeQuery(showSongs);
            if (rs1 != null && rs2 != null) {
                if(rs1.isBeforeFirst()) {
                    System.out.println("Playlist Name: || Duration: HH:mm:ss");
                    System.out.println("---------------------------------------------------------------------------------------------");
                    while(rs1.next()) {

                        tmp = rs1.getString(2);
                        duration = Integer.parseInt(tmp);
                        Date date = new Date(duration * 1000L);
                        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                        df.setTimeZone(TimeZone.getTimeZone("GMT"));
                        tmp = df.format(date);
                        System.out.println(rs1.getString(1) + " || " + tmp);
                    }
                }
                System.out.println("---------------------------------------------------------------------------------------------\n");
                System.out.println("Artist || Song || Album || Song Duration");
                System.out.println("---------------------------------------------------------------------------------------------");
                while(rs2.next()) {
                    System.out.println(rs2.getString(1) + " || " + rs2.getString(2) + " || " + rs2.getString(3) + " || " + rs2.getString(4));
                }
                System.out.println("---------------------------------------------------------------------------------------------");
            }
            else {
                System.out.println("Empty Set");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void concert(String userInput)
    {
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            String getConcert = "SELECT con.concert_name, con.concert_date, con.concert_location FROM concert con WHERE con.concert_name LIKE '%" + userInput + "%';";
            ResultSet rs = stmt.executeQuery(getConcert);
            if(rs != null) {
                if(rs.isBeforeFirst()) {
                    System.out.println("Concert || Date || Location");
                    System.out.println("---------------------------------------------------------------------------------------------");
                    while(rs.next()) {
                        System.out.println(rs.getString(1) + " || " + rs.getString(2) + " || " + rs.getString(3));
                    }
                    System.out.println("Songs played:");
                }
                System.out.println("---------------------------------------------------------------------------------------------");
            }
            else {
                System.out.println("Concert " + userInput + "Could Not Be Found");
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        playList(userInput);
    }

    static void genre(String userInput)
    {
        Connection con = DatabaseConnection.getConnection();
        String query = "SELECT artist_name, album_name, genre, album_date, album_id FROM album JOIN artist ON album.artist_id = artist.artist_id WHERE album.genre LIKE '%" + userInput + "%';";
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs != null) {
                if(rs.isBeforeFirst()) {
                    System.out.println("Artist || Album || Genre || Album Date || Album ID");
                    System.out.println("---------------------------------------------------------------------------------------------");
                    while(rs.next()) {
                        System.out.println(rs.getString(1) + " || " + rs.getString(2) + " || " + rs.getString(3) + " || " + rs.getString(4) + " || " + rs.getString(5));
                    }
                }
                System.out.println("---------------------------------------------------------------------------------------------");
            }
            else {
                System.out.println("Could Not Be Found");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    static void getAllSongs()
    {
        String getSongs = "SELECT s.song_id, s.song_name, a.artist_name, al.album_name, al.genre FROM (artist_song sa JOIN artist a ON sa.artist_id = a.artist_id JOIN song s ON sa.song_id = s.song_id) JOIN album al ON s.album_id = al.album_id GROUP BY a.artist_name, s.song_name, al.album_name, s.song_id ORDER BY a.artist_name ASC;";
        String myFormat = "| %-3s | %-50s | %-30s | %-50s | %-30s |%n";
        Connection con = DatabaseConnection.getConnection();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getSongs);
            if(rs.next()) {
                System.out.format(myFormat, "ID", "Song Name", "Artist Name", "Album Name", "Genre");
                System.out.println("---------------------------------------------------------------------------------------------");
                do{
                    System.out.format(myFormat, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                } while(rs.next());
                System.out.println("---------------------------------------------------------------------------------------------");
            } else {
                System.out.println("Could Not Be Found");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void getAllArtists()
    {
        String getArtists = "SELECT artist_id, artist_name FROM artist";
        String myFormat = "| %-3s | %-30s |%n";
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getArtists);
            if(rs.next()) {
                System.out.format("+ ID  + Artist Name");
                System.out.println("---------------------------------------------------------------------------------------------");
                do {
                    System.out.format(myFormat, rs.getString(1), rs.getString(2));
                } while (rs.next());
                System.out.println("---------------------------------------------------------------------------------------------");
            } else {
                System.out.println("No Artists");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void getAllAlbums()
    {
        String getAlbums = "SELECT a.artist_name, al.album_name, al.album_id FROM artist a JOIN album al ON a.artist_id = al.artist_id GROUP BY a.artist_name, al.album_name, al.album_id ORDER BY a.artist_name;";
        String myFormat = "| %-30s | %-50s | %-3s |%n";
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getAlbums);
            if(rs.next()) {
                System.out.format(myFormat, "Artist Name", "Album Name", "ID");
                System.out.println("---------------------------------------------------------------------------------------------");
                do {
                    System.out.format(myFormat, rs.getString(1), rs.getString(2), rs.getString(3));
                } while(rs.next());
                System.out.println("---------------------------------------------------------------------------------------------");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void getAllLabels()
    {
        String getLabels = "SELECT label_id, label_name FROM label;";
        String myFormat = "| %-3s | %-30s |%n";
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getLabels);
            if(rs.next()) {
                System.out.format(myFormat, "ID", "Label Name");
                System.out.println("----------------------------------------");
                do {
                    System.out.format(myFormat, rs.getString(1), rs.getString(2));
                } while (rs.next());
                System.out.println("----------------------------------------");
            } else {
                System.out.println("No Labels Found");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void getAllPlaylists()
    {
        String getPlaylists = "SELECT p.playlist_name, SUM(s.song_duration) AS LENGTH FROM playlist_song ps JOIN song s ON ps.song_id = s.song_id JOIN playlist p ON ps.playlist_id = p.playlist_id GROUP BY p.playlist_name;";
        String myFormat = "| %-30s | %-10s |%n";
        String tmp;
        Date date;
        SimpleDateFormat df;
        int duration;
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getPlaylists);
            if(rs.next()) {
                System.out.format(myFormat, "Playlist Name", "HH:mm:ss");
                System.out.println("-----------------------------------------------");
                do {
                    tmp = rs.getString(2);
                    duration = Integer.parseInt(tmp);
                    date = new Date(duration * 1000L);
                    df = new SimpleDateFormat("HH:mm:ss");
                    df.setTimeZone(TimeZone.getTimeZone("GMT"));
                    tmp = df.format(date);
                    System.out.format(myFormat, rs.getString(1), tmp);
                } while (rs.next());
                System.out.println("-----------------------------------------------");
            } else {
                System.out.println("No playlists found");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void getAllConcerts()
    {
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            String getConcert = "SELECT concert_name, concert_date, concert_location FROM concert;";
            String myFormat = "| %-30s | %-30s | %-30s |%n";
            ResultSet rs = stmt.executeQuery(getConcert);
                if(rs.next()) {
                    System.out.format(myFormat, "Concert", "Date", "Location");
                    System.out.println("----------------------------------------------------------------------------------------------------");
                    do {
                        System.out.format(myFormat, rs.getString(1), rs.getString(2), rs.getString(3));
                    } while (rs.next());
                    System.out.println("----------------------------------------------------------------------------------------------------");
                }
            else {
                System.out.println("No concert found");
            }
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void getAllGenres()
    {
        String getGenres = "SELECT DISTINCT genre FROM album;";
        Connection con = DatabaseConnection.getConnection();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getGenres);
            if(rs.next()) {
                System.out.println("Genres: ");
                System.out.println("---------------------------------------------------------------------------------------------");
                do{
                    System.out.println(rs.getString(1));
                } while (rs.next());
                System.out.println("---------------------------------------------------------------------------------------------");
            }
            else {
                System.out.println("No Genres Found");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void getAllMembers()
    {
        String getMembers = "SELECT p.first_name, p.last_name, a.artist_name FROM person_artist pa JOIN artist a ON pa.artist_id = a.artist_id JOIN person p ON pa.person_id = p.person_id GROUP BY a.artist_name, p.last_name, p.first_name ORDER BY a.artist_name ASC;";
        String myFormat = "| %-30s | %-30s | %-30s |%n";
        Connection con = DatabaseConnection.getConnection();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getMembers);
            if(rs.next()) {
                System.out.format(myFormat, "First Name", "Last Name", "Artist Name");
                System.out.println("----------------------------------------------------------------------------------------------------");
                do{
                    System.out.format(myFormat, rs.getString(1), rs.getString(2), rs.getString(3));
                } while (rs.next());
                System.out.println("----------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("Could Not Find any Members");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String insertSongIntoPlaylist(int songID, int playlistID)
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
        //Working on it...
    }

    public static void deletePlaylist (String userInput)
    {

    }

    public static void deleteConcert (String userInput)
    {

    }
}