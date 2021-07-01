/**
 * A simple game of Bulls and Cows
 *
 * @author Feb Beliaev(a)
 * @version Jun-23-2021
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
    final int MAXDIGITS = 10; //the largest possible number of digits you could have
    int number[] = new int[MAXDIGITS];
    int matched[] = new int[MAXDIGITS];
    String gNumber[] = new String[MAXDIGITS];
    int digit = 0; //keeps track of place in array for the initial generation
    int gDigit; //keeps track of guessed digits in the array
    int guesses = 0; //counts the number of guesses
    boolean end = false;
    boolean start = true;
    boolean read = false;
    
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

        
        
        Scanner input = new Scanner (System.in);
        String commands = input.nextLine();
        switch (commands){
            case "help" : read = true;
                break;
            case "play" : end = false;
                break;
            case "quit" : end = true;
                break;
            default : System.out.println("Command not understood");
                break;
        }
        
        
        
        while (end == false) {
                      
            //Generating the Number
            System.out.println("How many digits do you want the number to have?");
            int numberSize = input.nextInt(); //makes the scanner track for number size
            if(numberSize > MAXDIGITS) System.out.println("For the sake of your own sanity, please don't pick such a large number");
            else if(numberSize <= 0) System.out.println("Please pick a number above 0");
            else{
                while (digit < numberSize) {
                    number[digit] = RandomNumber(0,9); //generates all the values. These values are currently being printed for test purposes.
                    matched [digit] = 0;
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
                String guess = input.nextLine(); //from this point onwards, any inputs are treated as guesses
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
                    if (numberStr[gDigit].equals(guessStr[gDigit])){ //bulls
                        //System.out.println("Matched bull " + gDigit);
                        guess.length();
                        bulls++;
                        matched[gDigit] = 1; //marks bull
                    } else {
                        for (int i = 0; i < guess.length(); i++){
                            if ((numberStr[gDigit].equals(guessStr[gDigit]) == false) && (numberStr[gDigit].equals(guessStr[i]))) {
                                if ((matched[gDigit]==0) && (matched[i] == 0)) {
                                    matched[gDigit] = 1;
                                    cows++;
                                    //System.out.println("Matched cow " + i);
                                }
                            }
                        }
                    }
                    gDigit++;
                } //end of bulls and cows while

                //Grammar
                if(bulls == 1)System.out.println ("there is 1 bull");
                else System.out.println ("there are " + bulls + " bulls");
                if(cows == 1)System.out.println ("and 1 cow");
                else System.out.println ("and " + cows + " cows");

                //Resets everything for the next round
                if (bulls < numberSize) {
                    bulls = 0;
                    cows = 0;
                    for (var i = 0; i < numberStr.length; i++){
                        matched[i] = 0; 
                    }
                }
                guesses++;
            } //end of guessing while

            if (bulls == numberSize){
                System.out.println("Congratulations! You guessed the number!");
                System.out.println("it took you " + (guesses - 1) + " guesses"); //it counts selecting the digits as a guess and I don't want to deal with that rn
                end = true;
            }
        }
    }
}
