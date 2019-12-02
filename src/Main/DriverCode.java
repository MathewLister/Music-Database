package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DriverCode {
//Validates that option input is within our case range 0-...
    public static boolean ValidOption(int choice){
        if((choice < 0) || (choice > 9 )) {
            return false;
        }
        return true;
    }

//Driver Code
    public static void main(String args[]) throws SQLException, IOException {
        String menu = "*************MENU*************\n0. Exit\n********SEARCH BY********\n1. Song\n2. Artist\n3. Album\n4. Label\n5. Play List\n6. Concert\n7. Genre";
        int option = 100;
        String input;
        //This grabs options from console
        Scanner in = new Scanner(System.in);
        //This grabs strings from console for queries
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Loop menu until user chooses 0 for exit
        while (option != 0) {
            System.out.println(menu + "\n");
            System.out.print("Enter Menu Option: ");
            option = in.nextInt();
            //IDK how to make this work --> if we cant figure it out its all good
            //This try should make sure the user only enters int for the option so the program wont crash
           /* try {
                option = in.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("You have entered invalid data!");
            }
*           */
            System.out.println();
            //Validating that the user is entering 0-...
            while (ValidOption(option) == false) {
                System.out.println("Invalid input! Please try again");
                System.out.println(menu + "\n");
                System.out.print("Enter Menu Option: ");

                option = in.nextInt();
                System.out.println();
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
                 break;
             //Concert
             case 6:
                 break;
             //Genre
             case 7:
                break;
             default:
                 System.out.println("Please enter an option 0-7");
                 break;
         }
         System.out.println("-------------------------------------------------------------------\n");
         System.out.println();
        }
    }
}
