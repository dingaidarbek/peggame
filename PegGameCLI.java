package peggame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PegGameCLI {
    public static void startGame(PegGame game){
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean havePossibleMoves = true;
        String userInput;
        do{
            System.out.println("Game state: " + game.getGameState());
            System.out.println(game);
            if (game.getPossibleMoves().size() != 0){
                // System.out.println("\nPossible moves:\n" + game.getPossibleMoves());
                userInput = scanner.next();
                if (userInput.equals("quit")){ // If user types "quit", game ends
                    quit = true;
                }
                else if (userInput.equals("move")){
                    if (game.getPossibleMoves().size() != 0){
                    try{
                        int r1 = scanner.nextInt(), c1 = scanner.nextInt(), r2 = scanner.nextInt(), c2 = scanner.nextInt();
                        try{
                            game.makeMove(new Move(new Location(r1, c1), new Location(r2,c2)));
                        }
                        catch (PegGameException e){
                            System.out.println("Impossible move!");
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Please, make sure that you type commands correctly. Possible commands:\n1. 'quit' - quit the game\n2. 'move r1 c1 r2 c2' - move a peg from Location (r1,c1) to Location (r2,c2)");
                    }
                    }
                }
                else{
                    System.out.println("Cannot identify the command. Possible commands:\n1. 'quit' - quit the game\n2. 'move r1 c1 r2 c2' - move a peg from Location (r1,c1) to Location (r2,c2)");
                }
            }
            else{
                havePossibleMoves = false;
                if (game.pegsLeft() == 1){
                    game.setGameState(GameState.WON);
                    System.out.println("Game state: " + game.getGameState());
                    System.out.println(game);
                }
                else{
                    game.setGameState(GameState.STALEMATE);
                    System.out.println("Game state: " + game.getGameState());
                    System.out.println(game);
                }
            }
        }
        while (!(quit) && (havePossibleMoves)); 
        System.out.println("Thank you for playing! Good bye!");
        scanner.close();
    }
}
