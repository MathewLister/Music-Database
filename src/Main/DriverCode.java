package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class DriverCode {
    //Validates that option input is within our case range 0-...
    public static boolean ValidOption(int choice) {
        if ((choice < 0) || (choice > 18)) {
            return false;
        }
        return true;
    }

    //Driver Code
    public static void main(String args[]) throws SQLException, IOException {
        String menu = "*************MENU*************\n0. Exit\n********SEARCH BY********\n1. Song\n2. Artist\n3. Album\n4. Label\n5. Play List\n6. Concert\n7. Genre";
        String searchAll = "********Get All********\n8. Songs\n9. Artists\n10. Albums\n11. Labels\n12. Playlists\n13. Concerts\n14. Genres\n";
        String insert = "********INSERT********\n15. Insert Song in Playlist\n16. Insert a Concert";
        String delete = "********DELETE********\n17. Delete a Playlist\n18. Delete a Concert";
        int option = 100;
        String input;
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
                    //Query.artist();
                    break;
                //Album
                case 3:
                    break;
                //Label
                case 4:
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
                    Query.getAllSongs();
                    break;
                case 9:
                    // get all artists
                    break;
                case 10:
                    // get all albums
                    break;
                case 11:
                    // get all labels
                    break;
                case 12:
                    // get all playlists
                    break;
                case 13:
                    // get all concerts
                    break;
                case 14:
                    //get all genres
                    break;
                case 15:
                    // insert song in playlist
                    int songID, playlistID;
                    String output;
                    try{
                        System.out.print("Song ID (integer): ");
                        input = reader.readLine();
                        songID = Integer.parseInt(input);
                        System.out.print("Playlist ID (integer): ");
                        input = reader.readLine();
                        playlistID = Integer.parseInt(input);
                        output = Query.insertSongIntoPlaylist(songID, playlistID);
                        System.out.println(output);
                    } catch (Exception e) {
                        //e.printStackTrace();
                        System.out.println("Invalid Input");
                    }
                    break;

                default:
                    System.out.println("Please enter an option 0-9");
                    break;
            }
            System.out.println("-------------------------------------------------------------------\n");
            System.out.println();
        }
    }
}
