/**
 * A simple game of Bulls and Cows
 *
 * @author Feb Beliaeva
 * @version May-13-2021
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
    int numberSize; //keeps track of the number of digits of the number being guessed
    String number[];
    public Game()
    {
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
        
        
    }
}
