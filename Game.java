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
    String gNumber[] = new String[MAXDIGITS];
    int digit = 0;
    int gDigit;//keeps track of place in array for the initial generation
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
        if(numberSize > MAXDIGITS) System.out.println("Please reset the Java Engine.\n And for the sake of your own sanity, please don't pick such a large number");
        while (digit < numberSize) {
            System.out.println(number[digit] = RandomNumber(0,10));
            digit++;
        }
        
        //converting the int array into a string array, as I am not willing to convert anything else
        String numberStr[] = new String[number.length];
        for (int i = 0; i < number.length; i++) {
            numberStr[i] = String.valueOf(number[i]);
        }
        
        //GUESSING
        while (bulls < numberSize){
           String guess = input.nextLine();
           
           //converter that takes the guessed string, then converts it into characters,
           //then converts those characters back into strings to place into a string array
           String guessStr[] = new String[guess.length()];
           for (int i =0; i < guess.length(); i++) {
               char digitG = guess.charAt(i);
               System.out.println(guessStr[i] = Character.toString(digitG));
            }
           
           //finding the bulls
           gDigit = 0;
           while(gDigit < guess.length()){
               bulls= bulls + 1;
               gDigit++;
           }
           System.out.println("there are " + bulls + " bulls");
        }
    }
}
