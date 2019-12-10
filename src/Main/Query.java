package Main;

import javafx.util.Pair;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

class Query {
    //Shows all songs w/ the name searched
    static void song(String userInput) {
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            String query = "SELECT s.song_id, s.song_name, a.artist_name, al.album_name, s.song_duration FROM artist_song sa JOIN artist a ON sa.artist_id = a.artist_id JOIN song s on sa.song_id = s.song_id JOIN album al ON s.album_id = al.album_id WHERE s.song_name LIKE '%" + userInput + "%' ORDER BY a.artist_name, al.album_name, s.song_name;";
            String myFormat = "| %-3s | %-50s | %-30s | %-50s | %-10s |%n";
            String tmp;
            Date date;
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
            int duration;
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.format(myFormat, "ID", "Song Name", "Artist Name", "Album Name", "HH:mm:ss");
                System.out.println("----------------------------------------------------------------------------------------------------");
                do {
                    duration = rs.getInt(5);
                    date = new Date(duration * 1000L);
                    tmp = df.format(date);
                    System.out.format(myFormat, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), tmp);
                } while (rs.next());
                System.out.println("----------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("Could Not Be Found");
            }
            con.close();
        } catch (SQLException ex) {
            //ex.printStackTrace();
        }
    }

    //Shows artist discography
    static void artist(String userInput) {
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            String getArtist = "SELECT a.artist_id, a.artist_name, al.album_name, s.song_name, s.song_duration  FROM artist a, album al, artist_song artsong, song s WHERE a.artist_id = al.artist_id AND artsong.artist_id = a.artist_id AND artsong.song_id = s.song_id AND al.album_id = s.album_id AND a.artist_name LIKE '%" + userInput + "%';";
            String myFormat = "| %-3s | %-30s | %-50s | %-50s | %-10s |%n";
            String tmp;
            Date date;
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
            int duration;
            ResultSet rs = stmt.executeQuery(getArtist);

            if (rs.next()) {
                System.out.format(myFormat, "ID", "Artist Name", "Album Name", "Song Name", "HH:mm:ss");
                System.out.println("----------------------------------------------------------------------------------------------------");
                do {
                    duration = rs.getInt(5);
                    date = new Date(duration * 1000L);
                    tmp = df.format(date);
                    System.out.format(myFormat, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), tmp);
                } while (rs.next());
            } else {
                System.out.println("No Discography Found");
            }
            con.close();
        } catch (SQLException ex) {
            //ex.printStackTrace();
        }
    }

    //Show all the songs in a album
    static void album(String userInput) {
        String myFormat = "| %-30s | %-50s | %-50s | %-10s |%n";
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            String getAlbum = "SELECT a.artist_name, al.album_name, s.song_name, s.song_duration  FROM artist a, album al, artist_song artsong, song s WHERE a.artist_id = al.artist_id AND artsong.artist_id = a.artist_id AND artsong.song_id = s.song_id AND al.album_id = s.album_id AND al.album_name LIKE '%" + userInput + "%';";
            ResultSet rs = stmt.executeQuery(getAlbum);
            if (rs.next()) {
                System.out.format(myFormat, "Artist", "Album", "Song", "HH:mm:ss");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
                do {
                    System.out.format(myFormat, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                } while (rs.next());
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("Album" + userInput + "Could Not Be Found");
            }
            con.close();
        } catch (SQLException ex) {
            //ex.printStackTrace();
        }
    }

    static void label(String userInput) {
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            String getAlbum = "SELECT l.label_name, al.album_name FROM label l, album al WHERE l.label_id = al.label_id AND l.label_name LIKE '%" + userInput + "%';";
            String myFormat = "| %-30s | %-50s |%n";
            ResultSet rs = stmt.executeQuery(getAlbum);

            if (rs.next()) {
                System.out.format(myFormat, "Label Name", "Album Name");
                System.out.println("------------------------------------------------------------------------");
                do {
                    System.out.format(myFormat, rs.getString(1), rs.getString(2));
                } while (rs.next());
                System.out.println("-------------------------------------------------------------------------");
            } else {
                System.out.println("Label " + userInput + " Could Not Be Found");
            }
            con.close();
        } catch (SQLException ex) {
            //ex.printStackTrace();
        }
    }

    static void playList(String userInput) {
        int duration;
        String tmp;
        Connection con = DatabaseConnection.getConnection();
        String getPlaylistIDs = "SELECT playlist_id FROM playlist WHERE playlist_name LIKE ? ;";
        String getPlaylistLength = "SELECT SUM(s.song_duration) AS LENGTH FROM playlist_song ps JOIN song s ON ps.song_id = s.song_id JOIN playlist p ON ps.playlist_id = p.playlist_id WHERE p.playlist_id = ? ;";
        String getPlaylistName = "SELECT p.playlist_name FROM playlist p WHERE p.playlist_id = ? ;";
        String showSongs = "SELECT artist_name, song_name, album_name, song_duration FROM artist a, song s, artist_song sa, album ab, playlist_song playSong, playlist play WHERE sa.artist_id = a.artist_id AND sa.song_id = s.song_id AND ab.artist_id = a.artist_id AND playSong.playlist_id = play.playlist_id AND playSong.song_id = s.song_id AND play.playlist_id = ? ;";
        String myFormat1 = "| %-30s | %-10s |%n";
        String myFormat2 = "| %-30s | %-50s | %-50s | %-10s |%n";
        PreparedStatement ps1, ps2, ps3;
        ResultSet rs1, rs2, rs3;
        ArrayList<Integer> playlistIDs = new ArrayList<>();

        try {
            ps1 = con.prepareStatement(getPlaylistIDs);
            ps1.setString(1, "%" + userInput + "%");
            rs1 = ps1.executeQuery();
            if (rs1.next()) {
                do {
                    playlistIDs.add(rs1.getInt(1));
                } while (rs1.next());
                rs1.close();
                ps1.close();
            } else {
                System.out.println("No Playlists found");
                con.close();
                return;
            }

            for (Integer ID : playlistIDs) {
                try {
                    ps1 = con.prepareStatement(getPlaylistName);
                    ps1.setInt(1, ID);
                    ps2 = con.prepareStatement(getPlaylistLength);
                    ps2.setInt(1, ID);
                    ps3 = con.prepareStatement(showSongs);
                    ps3.setInt(1, ID);
                    rs1 = ps1.executeQuery();
                    rs2 = ps2.executeQuery();
                    rs3 = ps3.executeQuery();
                    if (rs1.next()) {
                        rs2.next();
                        System.out.format(myFormat1, "Playlist Name", "Duration: HH:mm:ss");
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
                        tmp = rs2.getString(1);
                        if (tmp != null) {
                            duration = Integer.parseInt(tmp);
                            Date date = new Date(duration * 1000L);
                            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                            df.setTimeZone(TimeZone.getTimeZone("GMT"));
                            tmp = df.format(date);
                        } else {
                            tmp = "00:00:00";
                        }
                        System.out.format(myFormat1, rs1.getString(1), tmp);
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
                    }
                    System.out.format(myFormat2, "Artist", "Song", "Album", "Song Duration");
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
                    if (rs3.next()) {
                        do {
                            tmp = rs3.getString(4);
                            duration = Integer.parseInt(tmp);
                            Date date = new Date(duration * 1000L);
                            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                            df.setTimeZone(TimeZone.getTimeZone("GMT"));
                            tmp = df.format(date);
                            System.out.format(myFormat2, rs3.getString(1), rs3.getString(2), rs3.getString(3), tmp);
                        } while (rs3.next());

                    } else {
                        System.out.println("| Empty");
                    }
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                    ps1.close();
                    ps2.close();
                    ps3.close();
                    rs1.close();
                    rs2.close();
                    rs3.close();
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");

            con.close();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    static void concert(String userInput) {
        Connection con = DatabaseConnection.getConnection();
        String myFormat = "| %-30s | %-30s | %-30s |%n";
        String query = "SELECT concert_name, concert_location, concert_date FROM concert WHERE concert_name LIKE '%" + userInput + "%';";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                System.out.format(myFormat, "Concert", "Location", "Date");
                System.out.println("----------------------------------------------------------------------------------------------------");
                do {
                    System.out.format(myFormat, rs.getString(1), rs.getString(2), rs.getString(3));
                } while (rs.next());
                System.out.println("----------------------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    static void genre(String userInput) {
        Connection con = DatabaseConnection.getConnection();
        String myFormat = "| %-30s | %-50s | %-30s | %-4s | %-3s |%n";
        String query = "SELECT artist_name, album_name, genre, album_date, album_id FROM album JOIN artist ON album.artist_id = artist.artist_id WHERE album.genre LIKE '%" + userInput + "%';";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                System.out.format(myFormat, "Artist", "Album", "Genre", "Album Date", "Album ID");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
                do {
                    System.out.format(myFormat, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                }
                while (rs.next());
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("Could Not Be Found");
            }
            con.close();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    static void getAllSongs() {
        String getSongs = "SELECT s.song_id, s.song_name, a.artist_name, al.album_name, al.genre FROM (artist_song sa JOIN artist a ON sa.artist_id = a.artist_id JOIN song s ON sa.song_id = s.song_id) JOIN album al ON s.album_id = al.album_id GROUP BY a.artist_name, s.song_name, al.album_name, s.song_id ORDER BY a.artist_name ASC;";
        String myFormat = "| %-3s | %-50s | %-30s | %-50s | %-30s |%n";
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getSongs);
            if (rs.next()) {
                System.out.format(myFormat, "ID", "Song Name", "Artist Name", "Album Name", "Genre");
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                do {
                    System.out.format(myFormat, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                } while (rs.next());
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("Could Not Be Found");
            }
            con.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    static void getAllArtists() {
        String getArtists = "SELECT artist_id, artist_name FROM artist";
        String myFormat = "| %-3s | %-30s |%n";
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getArtists);
            if (rs.next()) {
                System.out.format("+ ID  + Artist Name");
                System.out.println("----------------------------------------------------------------------------------------------------");
                do {
                    System.out.format(myFormat, rs.getString(1), rs.getString(2));
                } while (rs.next());
                System.out.println("----------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("No Artists");
            }
            con.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }

    }

    static void getAllAlbums() {
        String getAlbums = "SELECT a.artist_name, al.album_name, al.album_id FROM artist a JOIN album al ON a.artist_id = al.artist_id GROUP BY a.artist_name, al.album_name, al.album_id ORDER BY a.artist_name;";
        String myFormat = "| %-30s | %-50s | %-3s |%n";
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getAlbums);
            if (rs.next()) {
                System.out.format(myFormat, "Artist Name", "Album Name", "ID");
                System.out.println("----------------------------------------------------------------------------------------------------");
                do {
                    System.out.format(myFormat, rs.getString(1), rs.getString(2), rs.getString(3));
                } while (rs.next());
                System.out.println("----------------------------------------------------------------------------------------------------");
            }
            con.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    static void getAllLabels() {
        String getLabels = "SELECT label_id, label_name FROM label;";
        String myFormat = "| %-3s | %-30s |%n";
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getLabels);
            if (rs.next()) {
                System.out.format(myFormat, "ID", "Label Name");
                System.out.println("----------------------------------------------------------------------------------------------------");
                do {
                    System.out.format(myFormat, rs.getString(1), rs.getString(2));
                } while (rs.next());
                System.out.println("----------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("No Labels Found");
            }
            con.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    static void getAllPlaylists() {
        String getPlaylists = "SELECT p.playlist_name, SUM(s.song_duration) AS LENGTH FROM playlist_song ps JOIN song s ON ps.song_id = s.song_id JOIN playlist p ON ps.playlist_id = p.playlist_id GROUP BY p.playlist_name;";
        String getEmptyPlaylists = "SELECT playlist_name FROM playlist WHERE playlist_id NOT IN (SELECT p.playlist_id FROM playlist p JOIN playlist_song ps on p.playlist_id = ps.playlist_id JOIN song s ON ps.song_id = s.song_id);";
        String myFormat = "| %-30s | %-10s |%n";
        String tmp;
        Date date;
        SimpleDateFormat df;
        int duration;
        boolean foundPlaylist = false;
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt1 = con.createStatement();
            Statement stmt2 = con.createStatement();
            ResultSet rs1 = stmt1.executeQuery(getPlaylists);
            ResultSet rs2 = stmt2.executeQuery(getEmptyPlaylists);

            System.out.format(myFormat, "Playlist Name", "HH:mm:ss");
            System.out.println("----------------------------------------------------------------------------------------------------");
            if (rs2.next()) {
                do {
                    System.out.format(myFormat, rs2.getString(1), "00:00:00");
                } while (rs2.next());
                foundPlaylist = true;
            }
            if (rs1.next()) {
                do {
                    tmp = rs1.getString(2);
                    duration = Integer.parseInt(tmp);
                    date = new Date(duration * 1000L);
                    df = new SimpleDateFormat("HH:mm:ss");
                    df.setTimeZone(TimeZone.getTimeZone("GMT"));
                    tmp = df.format(date);
                    System.out.format(myFormat, rs1.getString(1), tmp);
                } while (rs1.next());
                foundPlaylist = true;
            }
            if (!foundPlaylist) {
                System.out.println("No Playlists Found");
            }
            System.out.println("----------------------------------------------------------------------------------------------------");
            con.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    static void getAllConcerts() {
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            String getConcert = "SELECT concert_name, concert_date, concert_location FROM concert;";
            String myFormat = "| %-30s | %-30s | %-30s |%n";
            ResultSet rs = stmt.executeQuery(getConcert);
            if (rs.next()) {
                System.out.format(myFormat, "Concert", "Date", "Location");
                System.out.println("----------------------------------------------------------------------------------------------------");
                do {
                    System.out.format(myFormat, rs.getString(1), rs.getString(2), rs.getString(3));
                } while (rs.next());
                System.out.println("----------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("No concert found");
            }
            con.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    static void getAllGenres() {
        String getGenres = "SELECT DISTINCT genre FROM album ORDER BY genre;";
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getGenres);
            if (rs.next()) {
                System.out.println("Genres: ");
                System.out.println("----------------------------------------------------------------------------------------------------");
                do {
                    System.out.println(rs.getString(1));
                } while (rs.next());
                System.out.println("----------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("No Genres Found");
            }
            con.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Invalid Input");
        }
    }

    static void getAllMembers() {
        String getMembers = "SELECT p.first_name, p.last_name, a.artist_name FROM person_artist pa JOIN artist a ON pa.artist_id = a.artist_id JOIN person p ON pa.person_id = p.person_id GROUP BY a.artist_name, p.last_name, p.first_name ORDER BY a.artist_name ASC;";
        String myFormat = "| %-30s | %-30s | %-30s |%n";
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(getMembers);
            if (rs.next()) {
                System.out.format(myFormat, "First Name", "Last Name", "Artist Name");
                System.out.println("----------------------------------------------------------------------------------------------------");
                do {
                    System.out.format(myFormat, rs.getString(1), rs.getString(2), rs.getString(3));
                } while (rs.next());
                System.out.println("----------------------------------------------------------------------------------------------------");
            } else {
                System.out.println("Could Not Find any Members");
            }
            con.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Invalid Input");
        }
    }

    static void insertSongsIntoPlaylist(String userInput) {
        Map<Pair<String, String>, Integer> cache = new HashMap<>();
        Pair<String, String> artistSong;
        String getSongs1 = "SELECT a.artist_name, s.song_name, s.song_id FROM song s JOIN artist_song sa ON s.song_id = sa.song_id JOIN artist a ON a.artist_id = sa.artist_id WHERE s.song_name LIKE '%";
        String getSongs2 = "%';";
        String getPlaylistID = "SELECT playlist_id FROM playlist WHERE playlist_name = '" + userInput + "';";
        String insertSongIntoPlaylist = "INSERT INTO playlist_song (song_id, playlist_id) VALUES (?, ?);";
        String formatMap = "| %-30s | %-50s |%n";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String searchSong, input, artist, song;
        int choice = 1, songID = -1, playlistID = -1;
        Integer keyID = null;

        Connection con = DatabaseConnection.getConnection();
        try { // check if playlist name returns an ID
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(getPlaylistID);
            if (rs.next()) {
                playlistID = rs.getInt(1);
            } else {
                System.out.println("Could Not Find Playlist");
                return;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Invalid Input");
            return;
        }

        //This grabs options from console
        Scanner in = new Scanner(System.in);
        //This grabs strings from console for queries
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (choice == 1) {
                System.out.println("\n1. Enter New Song\n2. Exit");
                try {
                    // read in user input
                    input = reader.readLine();
                    choice = Integer.parseInt(input);
                    if (choice == 1) {
                        System.out.print("Enter Song Name (Keyword Search): ");
                        searchSong = reader.readLine(); // read in song name
                        ps = con.prepareStatement(getSongs1 + searchSong + getSongs2); // get songs related to search
                        rs = ps.executeQuery();

                        if (rs.next()) {
                            do { // enter all songs into a hashmap
                                cache.put(new Pair<>(rs.getString(1), rs.getString(2)), rs.getInt(3));
                            } while (rs.next());
                        }
                        ps.close();
                        rs.close();
                        System.out.format(formatMap, "Artist Name", "Song");
                        System.out.println("----------------------------------------------------------------------------------------------------");
                        cache.forEach((key, value) -> System.out.format(formatMap, key.getKey(), key.getValue()));
                        System.out.println("----------------------------------------------------------------------------------------------------");
                        if (cache.isEmpty()) {
                            System.out.println("No Results");
                            continue;
                        }
                        System.out.print("Enter Artist Name (Case Sensitive): ");
                        artist = reader.readLine();
                        System.out.print("Enter Song Name (Case Sensitive): ");
                        song = reader.readLine();
                        keyID = cache.get(new Pair<>(artist, song));
                        if (keyID != null) {
                            // insert song into playlist
                            ps = con.prepareStatement(insertSongIntoPlaylist);
                            ps.setInt(1, keyID);
                            ps.setInt(2, playlistID);
                            ps.executeUpdate();
                            System.out.println("Insertion Successful");
                        }
                        else {
                            System.out.println("Unsuccessful Insertion");
                        }
                    }
                    cache.clear();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Invalid Input");
                }
            }
            con.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Invalid Input");
        }
    }

    static String insertConcert(String concertName, String concertDate, String concertLocation, String playListName) {
        String returnMessage = "Could not complete insertion";
        String checkConcert = "SELECT * FROM concert WHERE concert_name = '" + concertName + "';";
        String getPlaylistID = "SELECT playlist_id FROM playlist WHERE playlist_name = '" + playListName + "';";


        Connection con = DatabaseConnection.getConnection();
        try {
            // create 3 separate statements because only one ResultSet object per Statement object can be open at the same time
            Statement stmt1 = con.createStatement();
            Statement stmt2 = con.createStatement();
            ResultSet rs1 = stmt1.executeQuery(checkConcert);


            if (rs1.next()) { // check if concert is in the playlist table
                returnMessage = "Concert already exists";
            } else {
                insertPlayList(playListName); // Make playlist for concert
                ResultSet rs2 = stmt2.executeQuery(getPlaylistID);
                rs2.next();
                int ID = rs2.getInt(1);

                String insertQuery = "INSERT INTO concert (concert_date, concert_name, concert_location, playlist_id) VALUES ('" + concertDate + "', '" + concertName + "', '" + concertLocation + "', " + ID + ");";
                PreparedStatement ps = con.prepareStatement(insertQuery);
                ps.executeUpdate(); // execute query and store return value in ret
                returnMessage = "Successful Insertion";
            }
            con.close(); // all lower objects are closed when connection is closed
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Invalid Input");
        }
        return returnMessage;
    }

    static String insertPlayList(String name) {
        String returnMessage = "Could not complete insertion";
        String checkPlaylist = "SELECT * FROM playlist WHERE playlist_name = '" + name + "';";
        String insertQuery = "INSERT INTO playlist (playlist_name) VALUES ('" + name + "');";

        int option = 100;
        Connection con = DatabaseConnection.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(checkPlaylist);
            if (rs.next()) { // check if playlist exists
                System.out.println("There is already a playlist(s) with the name: " + name);
                playList(name);
                System.out.print("Press 0 to exit or 1 to add this play list anyways: ");
                Scanner in = new Scanner(System.in);
                option = in.nextInt();
                if (option == 1) {
                    PreparedStatement ps = con.prepareStatement(insertQuery);
                    int ret = ps.executeUpdate(); // execute query and store return value in ret
                    if (ret == 1) {
                        returnMessage = "Successful Insertion";
                    }
                } else if (option == 0) {
                    return returnMessage = "No playlist inserted";
                } else {
                    return returnMessage;
                }
            } else { // No playlist found w/ this name
                PreparedStatement ps = con.prepareStatement(insertQuery);
                int ret = ps.executeUpdate(); // execute query and store return value in ret
                //System.out.println("Return from Update: " + ret);
                if (ret == 1) {
                    returnMessage = "Successful Insertion";
                }
            }
            con.close(); // all lower objects are closed when connection is closed
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Invalid Input");
        }
        return returnMessage;
    }

    static void removeSongFromPlaylist()
    {
        String getPlaylists = "SELECT playlist_name, playlist_id FROM playlist WHERE playlist_name LIKE ? ;";
        String getSongs = "SELECT a.artist_name, s.song_name, s.song_id FROM artist a JOIN artist_song sa ON a.artist_id = sa.artist_id JOIN song s ON sa.song_id = s.song_id JOIN playlist_song ps ON ps.song_id = s.song_id WHERE ps.playlist_id = ? ;";
        String removeSong = "DELETE FROM playlist_song WHERE song_id = ? AND playlist_id = ? ;";
        String playlistFormat = "| %-50s |%n";
        String songFormat = "| %-30s | %-50s |%n";
        String input, artistName, songName;
        int option = 1;
        Integer playlistID, songID;
        PreparedStatement ps;
        ResultSet rs;
        Map<String, Integer> playlists = new HashMap<>();
        Map<Pair<String, String>, Integer> songs = new HashMap<>();

        Connection con = DatabaseConnection.getConnection();

        //This grabs options from console
        Scanner in = new Scanner(System.in);
        //This grabs strings from console for queries
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter Playlist Name (Keyword Search): ");
        try {
            input = reader.readLine();
            ps = con.prepareStatement(getPlaylists);
            ps.setString(1, input);
            rs = ps.executeQuery();
            if(rs.next()) {
                System.out.format(playlistFormat, "Playlist Name");
                System.out.println("----------------------------------------------------------------------------------------------------");
                do {
                    playlists.put(rs.getString(1), rs.getInt(2));
                    System.out.format(playlistFormat, rs.getString(1)); // output and store playlist names and ID's
                } while(rs.next());
                System.out.println("----------------------------------------------------------------------------------------------------");
                rs.close();
                ps.close();
            } else {
                System.out.println("No Playlists Found");
                con.close();
                return;
            }
            System.out.print("Enter Playlist Name to remove songs from (case sensitive): ");
            input = reader.readLine();
            playlistID = playlists.get(input);
            if(playlistID == null) { // check if input is in map
                System.out.println("Invalid Playlist Name. Exiting");
                con.close();
                return;
            }
            while(option == 1) {
                System.out.println("1. Remove song From Playlist\n2. Exit\n");
                input = reader.readLine();
                option = Integer.parseInt(input);
                if(option != 1) {
                    continue;
                }
                ps = con.prepareStatement(getSongs);
                ps.setInt(1, playlistID);
                rs = ps.executeQuery();
                if(rs.next()) {
                    System.out.format(songFormat, "Artist Name", "Song Name");
                    System.out.println("----------------------------------------------------------------------------------------------------");
                    do {
                        songs.put(new Pair<>(rs.getString(1), rs.getString(2)), rs.getInt(3));
                        System.out.format(songFormat, rs.getString(1), rs.getString(2));
                    } while (rs.next());
                    rs.close();
                    ps.close();
                    System.out.println("----------------------------------------------------------------------------------------------------");
                    System.out.print("Enter Artist Name (Case Sensitive): ");
                    artistName = reader.readLine();
                    System.out.print("Enter Song Name (Case Sensitive): ");
                    songName = reader.readLine();
                    songID = songs.get(new Pair<>(artistName, songName));
                    if(songID == null) {
                        System.out.println("Could not find song");
                        continue;
                    }
                    ps = con.prepareStatement(removeSong);
                    ps.setInt(1, songID);
                    ps.setInt(2, playlistID);
                    ps.executeUpdate();
                    System.out.println("Removed Song");
                } else {
                    System.out.println("Empty Playlist");
                    con.close();
                    return;
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Invalid Input");
        }

    }

    // don't test until insert playlist works!!
    static void deletePlaylist() {
        Map<String, Integer> cache = new HashMap<>();
        String getPlaylists = "SELECT playlist_name, playlist_id FROM playlist WHERE playlist_name LIKE ? ;";
        String removeSongsFromPlaylist = "DELETE FROM playlist_song WHERE playlist_id = ? ;";
        String deletePlaylist = "DELETE FROM playlist WHERE playlist_id = ? ;";
        String format = "| %-50s |%n";
        Integer playlistID = null;
        String input;
        PreparedStatement ps;
        ResultSet rs;

        //This grabs options from console
        Scanner in = new Scanner(System.in);
        //This grabs strings from console for queries
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            Connection con = DatabaseConnection.getConnection();
            System.out.print("Enter playlist name to delete: ");
            input = reader.readLine();
            ps = con.prepareStatement(getPlaylists);
            ps.setString(1, "%" + input + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.format(format, "Playlist Name");
                System.out.println("----------------------------------------------------------------------------------------------------");
                do {
                    cache.put(rs.getString(1), rs.getInt(2));
                    System.out.format(format, rs.getString(1));
                } while (rs.next());
                ps.close();
                rs.close();
                System.out.println("----------------------------------------------------------------------------------------------------");
                System.out.print("Enter playlist name: ");
                input = reader.readLine();
                playlistID = cache.get(input);
                if (playlistID != null) {
                    ps = con.prepareStatement(removeSongsFromPlaylist);
                    ps.setInt(1, playlistID);
                    ps.executeUpdate();
                    ps.close();
                    ps = con.prepareStatement(deletePlaylist);
                    ps.setInt(1, playlistID);
                    ps.executeUpdate();
                    ps.close();
                    System.out.println("Delete Successful");
                } else {
                    System.out.println("Invalid Playlist");
                    return;
                }
            } else {
                System.out.println("Could Not Find Playlists");
                return;
            }
            con.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Invalid Input");
        }
    }

    static void deleteConcert() {
        Map<String, Integer> cache = new HashMap<>();
        String getConcerts = "SELECT concert_name, concert_id FROM concert WHERE concert_name LIKE ? ;";
        String delConcert = "DELETE FROM concert WHERE concert_id = ? ;";
        String concertFormat = "| %-30s |%n";
        String input;
        Integer ID;
        PreparedStatement ps;
        ResultSet rs;

        //This grabs options from console
        Scanner in = new Scanner(System.in);
        //This grabs strings from console for queries
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Connection con = DatabaseConnection.getConnection();
            System.out.print("Enter Concert Name: ");
            input = reader.readLine();
            ps = con.prepareStatement(getConcerts);
            ps.setString(1, "%" + input + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                do {
                    cache.put(rs.getString(1), rs.getInt(2));
                } while (rs.next());
            }
            rs.close();
            ps.close();

            System.out.format(concertFormat, "Concert Name");
            cache.forEach((cName, cID) -> {
                System.out.format(concertFormat, cName);
            });
            System.out.println("------------------------------------------------------------\n");
            System.out.print("Enter Concert Name to delete: ");
            input = reader.readLine();
            ID = cache.get(input);
            if (ID != null) {
                ps = con.prepareStatement(delConcert);
                ps.setInt(1, ID);
                ps.executeUpdate();
                System.out.println("Delete Successful");
            } else {
                System.out.println("Invalid Concert");
            }
            con.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Invalid Input");
        }
    }

    static void updateConcert(String userInput) {
        Map<String, Integer> cache = new HashMap<>();


        String concert = "SELECT concert_name, concert_date, concert_location, concert_id FROM concert WHERE concert_name LIKE ? ;";

        Integer concertID = null;
        String myFormat = "| %-30s | %-30s | %-30s |%n";
        String input;
        int option = 100;
        String menu = "********UPDATE CONCERT********\n0. Exit\n1. Name\n2. Date\n3. Location\n";
        PreparedStatement ps;
        ResultSet rs1;
        ResultSet rs2;

        Scanner in = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            Connection con = DatabaseConnection.getConnection();
            ps = con.prepareStatement(concert);
            ps.setString(1, "%" + userInput + "%");
            rs1 = ps.executeQuery();
            if (rs1.next()) {
                System.out.format(myFormat, "Concert", "Date", "Location");
                System.out.println("----------------------------------------------------------------------------------------------------");
                do {
                    cache.put(rs1.getString(1), rs1.getInt(4));
                    System.out.format(myFormat, rs1.getString(1), rs1.getString(2), rs1.getString(3));
                } while (rs1.next());
                ps.close();
                rs1.close();
                System.out.println("----------------------------------------------------------------------------------------------------");
                System.out.print("(*Case Sensitive) Enter a concert name: ");

                input = reader.readLine();
                concertID = cache.get(input);
                if (concertID != null) {
                    while (option != 0) {
                      System.out.println(menu);
                      System.out.print("Enter a menu value: ");
                      option = in.nextInt();
                      switch (option) {
                          //exit
                          case 0:
                              option = 0;
                              break;
                          //update name
                          case 1:
                              System.out.print("Enter the new name: ");
                              input = reader.readLine();
                              String updateName = "UPDATE concert SET concert_name = '" + input + "' WHERE concert_id = " + concertID + ";";
                              ps = con.prepareStatement(updateName);
                              ps.executeUpdate();
                              ps.close();
                              System.out.println("Successful update");
                              break;
                          //update date
                          case 2:
                              System.out.print("Enter the new date and time as, YYYY-MM-DD HH:mm:ss : ");
                              input = reader.readLine();
                              String updateDate = "UPDATE concert SET concert_date = '" + input + "' WHERE concert_id = " + concertID + ";";
                              ps = con.prepareStatement(updateDate);
                              ps.executeUpdate();
                              ps.close();
                              System.out.println("Successful update");
                              break;
                          //update location
                          case 3:
                              System.out.print("Enter the new location: ");
                              input = reader.readLine();
                              String updateLocation = "UPDATE concert SET concert_location = '" + input + "' WHERE concert_id = " + concertID + ";";
                              ps = con.prepareStatement(updateLocation);
                              ps.executeUpdate();
                              ps.close();
                              System.out.println("Successful update");
                              break;
                      }
                    }
                }
            } else {
                System.out.println("No results found");
            }
        } catch (IOException | SQLException e) {
            //e.printStackTrace();
            System.out.println("Invalid Input");
        }
    }
}
