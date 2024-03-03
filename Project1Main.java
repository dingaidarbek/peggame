package peggame;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project1Main {
    public static void main(String[] args) throws FileNotFoundException, PegGameException{
        ReadFile read = new ReadFile();
        Scanner readPath = new Scanner(System.in);
        System.out.println("Please, enter the path of the file: ");
        String path = readPath.nextLine();
        PegGame game = read.readFile(path);
        PegGameCLI.startGame(game);
        System.out.println(game.getGameState());
        readPath.close();
    }

  // C:\Users\Dimash\repos\gcis124\peggame\boardExample.txt
    
}
