package peggame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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


    // Method that checks if there is a peg at this location on the board 
    public boolean isFull(int row, int column){
        if(gameBoard[row][column] == 0)
            return false;
        return true;
    }


    /**implementing the methods in the interface PegGame*/
    @Override
    public Collection<Move> getPossibleMoves() {
        // data structure that will containn all possible moves for the peg
        ArrayList<Move> possibleMoves= new ArrayList<Move>();

        for(int row=0; row < length; row++){
            for(int column=0; column < length; column++){
                // checking if there is a peg at the source location
                if(isFull(row, column) == true){
                
                    // multiple scenarios where a move could be possible if the conditions are met
                    if((isFull(row, column+2) == false) && (isFull(row, column+1) == true)){
                        possibleMoves.add(new Move(new Location(row, column),new Location(row, column+2)));
                    }
                    if((isFull(row, column-2) == false) && (isFull(row, column-1) == true)){
                        possibleMoves.add(new Move(new Location(row, column),new Location(row, column-2)));
                    }
                    if((isFull(row+2, column) == false) && (isFull(row+1, column) == true)){
                        possibleMoves.add(new Move(new Location(row, column),new Location(row+2, column)));
                    }
                    if((isFull(row-2, column) == false) && (isFull(row-1, column-1) == true)){
                        possibleMoves.add(new Move(new Location(row, column),new Location(row-2, column)));
                    }
                }
        }
    // returning an array of posiible moves for a peg
    } return possibleMoves;
}



    @Override
    public GameState getGameState() {
        if
    }

    @Override
    public void makeMove(Move move) throws PegGameException {
        
       
    }




}
