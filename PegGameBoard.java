package peggame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;

public class PegGameBoard implements PegGame {
    // setting variables
    private int length;
    private int[][] gameBoard;
    private GameState gameState;
     
    // constructor
    public PegGameBoard(int length){
        this.length = length;
        this.gameBoard = new int[this.length][this.length]; /*we need a square board. Thus, numOfRows == numOfCols*/
        this.gameState = GameState.NOT_STARTED; /*beginning of the game --> no moves were made yet*/
    }

    // Method to print the board as a String
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

    public static void main(String[] args) throws FileNotFoundException {
        ReadFile read = new ReadFile();
        Scanner readPath = new Scanner(System.in);
        System.out.println("Please, enter the path of the file: ");
        String path = readPath.nextLine();
        System.out.println(read.readFile(path));
        readPath.close();
    }

    // Method to set the value at the given coordinate (row, col)
    public void setValue(int row, int col, int value){
        this.gameBoard[row][col] = value;
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
