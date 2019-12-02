package Main;

import java.util.Scanner;

public class DriverCode {
    public static void main(String args[])
    {
        String menu = "*************MENU*************\n0. Exit\n********SEARCH BY********\n1. Song\n2. Artist\n3. Album\n4. Label\n5. Play List\n6. Concert\n7. Genre";
        int option = 100;
        //We will make a query object here that can be used in all our switch statements to run queries
        //Query search = new Query();

        //Loop menu until user chooses 0 for exit
        while (option != 0) {
            System.out.println(menu + "\n");
            System.out.print("Enter Menu Option: ");

            Scanner in = new Scanner(System.in);
            option = in.nextInt();
            System.out.println();
            //Might want to validate the input to make sure only nums 0-?
            //Then we can loop here until something valid comes in

         switch (option) {
             //exit
             case 0:
                return;
         //Search by:
             //Song
             case 1:
                //search.song();
                break;
             //Artist
             case 2:
                 //search.artist();
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
                 break;
         }
         System.out.println("-------------------------------------------------------------------\n");
         System.out.println();
        }
    }
}
