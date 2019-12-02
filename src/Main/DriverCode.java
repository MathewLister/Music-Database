package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class DriverCode {
    public static void main(String args[]) throws SQLException, IOException {
        String menu = "*************MENU*************\n0. Exit\n********SEARCH BY********\n1. Song\n2. Artist\n3. Album\n4. Label\n5. Play List\n6. Concert\n7. Genre";
        int option = 100;
        String input;

        //Loop menu until user chooses 0 for exit
        while (option != 0) {
            System.out.println(menu + "\n");
            System.out.print("Enter Menu Option: ");

            Scanner in = new Scanner(System.in);
            option = in.nextInt();
            System.out.println();
            //Might want to validate the input to make sure only nums 0-?
            //Then we can loop here until something valid comes in

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
                 break;
             //Concert
             case 6:
                 break;
             //Genre
             case 7:
                break;
             //IDK?
             default:
                 System.out.println("Please enter an option 0-7");
                 break;
         }
         System.out.println("-------------------------------------------------------------------\n");
         System.out.println();
        }
    }
}
