
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

public class PegGameSquare_test {
    @Test
    public void test_PossibleMoves() throws PegGameException{
        // setup
        PegGameSquare gameBoard = new PegGameSquare(2); // creating a 3x3 board
        char[][] exampleBoard = {{'o','o','-'}, // Creating values to be inserted to the board
                                 {'o','o','-'},
                                 {'o','o','-'}};
        gameBoard.setGameBoard(exampleBoard); // Inserting values to the created board
        // invoke

        Collection<Move> possibleMoves = gameBoard.getPossibleMoves();
        // analyze
        assertEquals(5, possibleMoves.size()); // checking if the number of possible moves is equal to 4

    }

    @Test
    public void test_MakeMove() throws PegGameException{
        // setup
        PegGameSquare gameBoard = new PegGameSquare(2); // creating a 3x3 board
        char[][] exampleBoard = {{'o','o','-'}, // Creating values to be inserted to the board
                                 {'o','o','-'},
                                 {'o','o','-'}};
        gameBoard.setGameBoard(exampleBoard); // Inserting values to the created board
        // invoke
        gameBoard.makeMove(new Move(new Location(0, 0), new Location(2, 2))); // Move the pegs to a different location
        // analyze 
        assertEquals(GameState.IN_PROGRESS, gameBoard.getGameState());
        assertEquals(5, gameBoard.pegsLeft());
        assertTrue(exampleBoard[0][0] == '-'); // Check if move is done correctly
        assertTrue(exampleBoard[1][1] == '-');
        assertTrue(exampleBoard[2][2] == 'o');
    }

    @Test
    public void test_PegsLeft() throws PegGameException{
        // setup
        PegGameSquare gameBoard = new PegGameSquare(2); // creating a 3x3 board
        char[][] exampleBoard = {{'o','o','-'}, // Creating values to be inserted to the board
                                 {'o','o','-'},
                                 {'o','o','-'}};
        gameBoard.setGameBoard(exampleBoard); // Inserting values to the created board
        // invoke
        int expectedNumberOfPegs = 6;
        int realNumberOfPegs = gameBoard.pegsLeft();
        // analyze
        assertEquals(expectedNumberOfPegs, realNumberOfPegs); // checking to see if the expected pegs left match the actual
    }
    

}
