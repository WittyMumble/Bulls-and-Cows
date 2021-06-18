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
        int numberSize = input.nextInt(); //
        if(numberSize > MAXDIGITS) System.out.println("Please reset the Java Engine.\n And for the sake of your own sanity, please don't pick such a large number");
        else if(numberSize <= 0) System.out.println("Please pick a number above 0");
        else{
            while (digit < numberSize) {
                number[digit] = RandomNumber(0,9); //generates all the values. These values are currently being printed for test purposes.
                digit++;
            }
            
        }
        //converting the int array into a string array
        //it seems like the easiest way for me to compare them
        String numberStr[] = new String[number.length];
        for (int i = 0; i < number.length; i++) {
            numberStr[i] = String.valueOf(number[i]);
        } // i found a tutorial for this one too
        
        //GUESSING
        while (bulls < numberSize){
           String guess = input.nextLine();
           gDigit = 0;
           
           //converter that takes the guessed string, then converts it into characters,
           //then converts those characters back into strings to place into a string array
           String guessStr[] = new String[guess.length()];
           for (int i =0; i < guess.length(); i++) {
               char charG = guess.charAt(i); //converts each character in a string to its own character
               guessStr[i] = Character.toString(charG); //records those characters as strings in an array
            } //also mostly from a tutorial 
            
           //finding the bulls and cows
           while(gDigit < guess.length()){
               if (numberStr[gDigit].equals(guessStr[gDigit])){ 
                   System.out.println("Matched bull " + gDigit);
                   guess.length();
                   bulls++;
               } else {
                   for (int i = 0; i < guess.length(); i++){
                       if ((numberStr[gDigit].equals(guessStr[gDigit]) == false) && (numberStr[gDigit].equals(guessStr[i]))) {
                           System.out.println("Matched cow " + i);
                           cows++;
                        }
                    }
                }
               gDigit++;
           }
            System.out.println("there are " + bulls + " bulls\nand " + cows + " cows");
           if (bulls < numberSize) {
               bulls = 0;
               cows = 0;
            }
        }
        
        if (bulls == numberSize){
            System.out.println("Congratulations! You guessed the number!");
        }
    }
}
