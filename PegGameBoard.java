package peggame;

import java.util.Collection;

public class PegGameBoard implements PegGame {
    // setting variables
    private int length;
    private int[][] gameBoard;
    private GameState gameState;
     
    // constructor
    public PegGameBoard(int length){
        this.length = length;
        this.gameBoard = new int[length][length]; /*we need a square board. Thus, numOfRows == numOfCols*/
        this.gameState = GameState.NOT_STARTED; /*beginning of the game --> no moves were made yet*/
    }

    public String toString(){
        String stringTable = "";
        for (int i = 0; i < this.length; i++){
            for (int j = 0; j < this.length; j++){
                if (gameBoard[i][j] == 1){
                    stringTable += "o";
                }
                else if (gameBoard[i][j] == 0){
                    stringTable += "-";
                }
            }
            stringTable += "\n";
        }
        return stringTable;
    }

    // implementing the methods in the interface PegGame
    @Override
    public Collection<Move> getPossibleMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPossibleMoves'");
    }

    @Override
    public GameState getGameState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGameState'");
    }

    @Override
    public void makeMove() throws PegGameException {
        //
    }




}
