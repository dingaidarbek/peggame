package peggame;

import java.beans.Transient;
import java.util.Collection;

import org.junit.Test;

import peggame.GameState;
import peggame.Location;
import peggame.Move;

public class PegGameSquare_test {
    @Test
    public void test_PossibleMoves(){
        // setup
        PegGameSquare gameBoard = new PegGameSquare(5); // creating a 5x5 board
        // invoke
        gameBoard.makeMove(new Move(new Location(2, 2), new Location(2, 0))); // Move the pegs to a different location
        gameBoard.makeMove(new Move(new Location(3, 3), new Location(4, 3)));

        Collection<Move> possibleMoves = gameBoard.getPossibleMoves();
        // analyze
        assertEquals(4, possibleMoves.size()); // checking if the number of possible moves is equal to 4

    }

    @Test
    public void test_MakeMove(){
        // setup
        PegGameSquare gameBoard = new PegGameSquare(5); // creating a 5x5 board
        // invoke
        gameBoard.makeMove(new Move(new Location(2, 2), new Location(0, 0))); // Move the pegs to a different location
        // analyze 
        assertEquals(GameState.IN_PROGRESS, gameBoard.getGameState());
        assertEquals(4, gameBoard.pegsLeft());
    }

    @Test
    public void test_PegsLeft(){
        // setup
        PegGameSquare gameBoard = new PegGameSquare(5); // creating a 5x5 board
        // invoke
        gameBoard.makeMove(new Move(new Location(2, 2), new Location(0, 0))); // Move the pegs to a different location
        gameBoard.makeMove(new Move(new Location(3, 3), new Location(4, 4)));
        // analyze
        assertEquals(4, gameBoard.pegsLeft()); // checking to see if the expected pegs left match the actual
    }
    

}
