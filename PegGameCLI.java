package peggame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PegGameCLI {
    /**
     * The class contains one method startGame(PegGame game) and enables the user to type the commands in to play the game;
     * @param game 
     * @exception PegGameException if move is invalid or impossible to make.
     * @exception InputMismatchException inappropriate format for the command was given.
     */
    public static void startGame(PegGame game){
        // Setting down the CLI
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        String userInput;

        while (!(quit) && (game.getPossibleMoves().size() != 0)){ // Game is played until there are no possible moves or user types "quit"
            System.out.println("Game state: " + game.getGameState()); // Print the gameState
            System.out.println(game); // Print the board
                // System.out.println("\nPossible moves:\n" + game.getPossibleMoves());
                userInput = scanner.next();
                if (userInput.equals("quit")){ // If user types "quit", game ends
                    quit = true;
                }
                else if (userInput.equals("move")){ // If user types "move", program make a move
                    try{ // Throws an exception, if user did not write coordinates properly
                        int r1 = scanner.nextInt(), c1 = scanner.nextInt(), r2 = scanner.nextInt(), c2 = scanner.nextInt(); // Taking two coordinates to make move from (r1,c1) to (r2,c2)
                        try{
                            game.makeMove(new Move(new Location(r1, c1), new Location(r2,c2))); // Try to make a move. Successful, if this move is possible
                        }
                        catch (PegGameException e){
                            System.out.println("Impossible move!"); // If move is impossible, an exception is thrown and this line is printed to the user
                        }
                    }
                    catch(InputMismatchException e){ // User used a command, but did not provide appropriate format of the command
                        System.out.println("Please, make sure that you type commands correctly. Possible commands:\n1. 'quit' - quit the game\n2. 'move r1 c1 r2 c2' - move a peg from Location (r1,c1) to Location (r2,c2)");
                    }
                }
                else{ // User did not use any of the commands
                    System.out.println("Cannot identify the command. Possible commands:\n1. 'quit' - quit the game\n2. 'move r1 c1 r2 c2' - move a peg from Location (r1,c1) to Location (r2,c2)");
                }
        }

        if (!quit){ // Game ended because there are no possible moves, but not because user typed "quit"
            if (game.pegsLeft() == 1){ // If only 1 peg left, user WON.
                game.setGameState(GameState.WON);
            }
            else{
                game.setGameState(GameState.STALEMATE); // If more than 1 peg left, the result is STALEMATE.
            }
            System.out.println("Game state: " + game.getGameState()); // Print the gameState
            System.out.println(game);
        }
        System.out.println("Thank you for playing! Good bye!");
        scanner.close();
    }
}
