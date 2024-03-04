package peggame;

import java.io.FileNotFoundException;
import java.util.Scanner;
/*
 * Main file to run the game
 * Accepts a user input: path of the file
 * Reads the file and creates an instance of the PegGameSquare
 * 
 * Then, starts the game based on the created instance.
 * When the game is over (there are no possible moves or user used "quit" command), program ends.
 * 
 * Work done by:
 * Dingmukhammed Aidarbek
 * Madya Alfalasi
 * Lana Al Tajer
 */
public class Project1Main {
    public static void main(String[] args) throws FileNotFoundException, PegGameException{
        ReadFile read = new ReadFile();
        Scanner readPath = new Scanner(System.in);
        System.out.println("Please, enter the path of the file: ");
        String path = readPath.nextLine(); // Taking input from user
        try{
          PegGameSquare game = read.readFile(path); // Created a game with a square board, based on the readed file
          PegGameCLI.startGame(game); // Start game
        }
        catch (FileNotFoundException e){ // If user enters incorrect path, an exception is thrown
          System.out.println("Please, enter correct path of the file and try again.");
        }
        readPath.close();
    }
  
}
