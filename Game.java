/**
 * A simple game of Bulls and Cows
 *
 * @author Feb Beliaeva
 * @version May-20-2021
 */

import java.util.Scanner; //Scans for user input
import java.util.Random; //Generates random numbers
import java.io.File; //Scans for and interacts with files
import java.io.IOException; //handles file-based errors
public class Game
{
    String filename = "intro.txt"; //stores name of file containing introductory text
    int bulls;
    int cows;
    double min;
    double max;
    final int MAXDIGITS = 5;
    int number[] = new int[MAXDIGITS];
    int digit = 0; //keeps track of place in array for the initial generation
    //keeps track of the number of digits of the number being guesses
    //initializing the number generator
    int RandomNumber(int min,int max){
            int x = (int)Math.floor((Math.random()*((max-min)+1))+min); 
            return x;
        }//I got this from a java tutorial online
    //main block
    public Game()
    {
        System.out.println('\u000c'); //clears terminal
        //Reading intro text from a file. storing it in a file is easier since it takes up less space and is easier to edit.
        File theFile=new File(filename);
        try {
            Scanner fileRead = new Scanner (theFile);
            while (fileRead.hasNextLine()){
                System.out.println(fileRead.nextLine());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something is wrong with a file needed for this program");
        }
        //scanner for the actual game
        Scanner input = new Scanner (System.in);
        
        //Generating the Number
        System.out.println("How many digits do you want the number to have?");
        int numberSize = input.nextInt();
        if(numberSize > MAXDIGITS) System.out.println("for the sake of your own sanity, please don't pick such a large number");
        while (digit < numberSize) {
            number[digit] = RandomNumber(0,9);
            digit++;
        }
    }
}

