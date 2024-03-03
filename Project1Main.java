package peggame;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project1Main {
    public static void main(String[] args) throws FileNotFoundException, PegGameException{
        ReadFile read = new ReadFile();
        Scanner readPath = new Scanner(System.in);
        System.out.println("Please, enter the path of the file: ");
        String path = readPath.nextLine();
<<<<<<< HEAD
        PegGame game = read.readFile(path);
        PegGameCLI.startGame(game);
        System.out.println(game.getGameState());
=======
        try{
          PegGame game = read.readFile(path);
          PegGameCLI.startGame(game);
        }
        catch (FileNotFoundException e){
          System.out.println("Please, enter correct path of the file.");
        }
>>>>>>> 03d863af884eaf8eedcefb6f3b30e9d2dc806e24
        readPath.close();
    }

  // C:\Users\Dimash\repos\gcis124\peggame\boardExample.txt
    
}
