package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class DriverCode {
    //Validates that option input is within our case range 0-...
    public static boolean ValidOption(int choice) {
        return (choice >= 0) && (choice <= 19);
    }

    //Driver Code
    public static void main(String args[]) throws SQLException, IOException {
        String menu = "*************MENU*************\n0. Exit\n********SEARCH BY********\n1. Song\n2. Artist\n3. Album\n4. Label\n5. Play List\n6. Concert\n7. Genre";
        String searchAll = "********Get All********\n8. Songs\n9. Artists\n10. Albums\n11. Labels\n12. Playlists\n13. Concerts\n14. Genres\n15. Members of an Artist\n";
        String insert = "********INSERT********\n16. Inert Playlist\n17. Insert Song in Playlist\n18. Insert a Concert";
        String delete = "********DELETE********\n19. Delete a Playlist\n20. Delete a Concert";
        int option = 100;
        String input;
        String output;
        int playlistID, songID;
        //This grabs options from console
        Scanner in = new Scanner(System.in);
        //This grabs strings from console for queries
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Loop menu until user chooses 0 for exit
        while (option != 0) {
            System.out.println(menu + "\n");
            System.out.println(searchAll + "\n");
            System.out.println(insert + "\n");
            System.out.println(delete + "\n");
            System.out.print("Enter Menu Option: ");
            //option = in.nextInt();
            String tmp = in.nextLine(); // get input from user
            try {
                option = Integer.parseInt(tmp); // try to convert string to int
            } catch (NumberFormatException e) {
                //e.printStackTrace();
                System.out.println("Wrong Input");
                System.out.println("-------------------------------------------------------------------\n");
                System.out.println();
                continue; // skip to next loop iteration
            }

            System.out.println();

            if (!ValidOption(option)) { // check if option is a valid option in menu
                System.out.println("Invalid input! Please try again");
                System.out.println("-------------------------------------------------------------------\n");
                System.out.println();
                continue; // skip to next loop iteration
            }
            switch (option) {
                //exit
                case 0:
                    return;
                //Search by:
                //Song
                case 1:
                    System.out.print("Song name to search: ");
                    input = reader.readLine();
                    Query.song(input);
                    break;
                //Artist
                case 2:
                    System.out.print("Artist name to search: ");
                    input = reader.readLine();
                    Query.artist(input);
                    break;
                //Album
                case 3:
                    System.out.print("Album name to search: ");
                    input = reader.readLine();
                    Query.album(input);
                //Label
                case 4:
                    System.out.print("Label name to search: ");
                    input = reader.readLine();
                    Query.label(input);
                    break;
                //Play List
                case 5:
                    System.out.print("Playlist name to search: ");
                    input = reader.readLine();
                    Query.playList(input);
                    break;
                //Concert
                case 6:
                    break;
                //Genre
                case 7:
                    System.out.print("Genre name: ");
                    input = reader.readLine();
                    Query.genre(input);
                    break;
                case 8:
                    // get all songs
                    // currently not working, waiting on arias' reply
                    Query.getAllSongs();
                    break;
                case 9:
                    // get all artists
                    Query.getAllArtists();
                    break;
                case 10:
                    // get all albums
                    Query.getAllAlbums();
                    break;
                case 11:
                    // get all labels
                    Query.getAllLabels();
                    break;
                case 12:
                    // get all playlists
                    Query.getAllPlaylists();
                    break;
                case 13:
                    // get all concerts
                    Query.getAllConcerts();
                    break;
                case 14:
                    //get all genres
                    Query.getAllGenres();
                    break;
                case 15:
                    // get members of every artist
                    Query.getAllMembers();
                    break;
                case 16:
                    //Insert playlist
                    System.out.print("Enter playlist name: ");
                    input = reader.readLine();
                    output = Query.insertPlayList(input);
                    System.out.println(output);
                    break;
                case 17:
                    // insert song in playlist
                    System.out.print("Enter Playlist Name: ");
                    input = reader.readLine();
                    Query.insertSongsIntoPlaylist(input);
                    break;
                case 18: //NOT WORKING
                    //insert a concert
                    String name;
                    String date;
                    String location;
                    String playName;

                    System.out.print("Enter Concert Name: ");
                    name = reader.readLine();
                    System.out.print("Enter Concert Date: ");
                    date = reader.readLine();
                    System.out.print("Enter Concert Location: ");
                    location = reader.readLine();

                    System.out.print("Enter a name for a playlist of songs for the concert: ");
                    playName = reader.readLine();

                    output = Query.insertConcert(name, date, location, playName);
                    System.out.println(output);
                    break;
                case 19:
                    //delete playlist
                    playlistID = -1;
                    try {
                        System.out.print("Enter playlist ID (integer): ");
                        input = reader.readLine();
                        playlistID = Integer.parseInt(input);
                        System.out.println(Query.deletePlaylist(playlistID));
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Invalid Input");
                    }
                    break;
                case 20:
                    //delete concert
                    break;

                default:
                    System.out.println("Please enter an option 0-9");
                    break;
            }
            System.out.println();
        }
    }
}
