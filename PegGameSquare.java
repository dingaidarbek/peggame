package peggame;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class PegGameSquare implements PegGame {
    // setting variables
    private int length;
    private char[][] gameBoard;
    private GameState gameState;
   

     

    // constructor
    public PegGameSquare(int length){
        this.length = length + 1;
        this.gameBoard = new char[this.length][this.length]; /*we need a square board. Thus, numOfRows == numOfCols*/
        this.gameState = GameState.NOT_STARTED; /*beginning of the game --> no moves were made yet*/
    }

    // Method to print the board as a String
    public String toString(){
        String stringTable = "[#]";
        for (int i = 0; i < this.length; i++){
            stringTable +=  "  [" + i + "]";
        }
        stringTable += "\n\n";
        for (int i = 0; i < this.length; i++){
            stringTable += "[" + i + "]   ";
            for (int j = 0; j < this.length; j++){
                stringTable += gameBoard[i][j] + "    ";
            }
            stringTable += "\n\n";
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
    public void setValue(int row, int col, char value){
        this.gameBoard[row][col] = value;
    }


    // Method that checks if there is a peg at this location on the board 
    public boolean isFull(int row, int column){
        char value = gameBoard[row][column];
        if(value == '-')
            return false;
        return true;
    }


    /**implementing the methods in the interface PegGame*/
    @Override
    public Collection<Move> getPossibleMoves() {
        // data structure that will containn all possible moves for the peg
        ArrayList<Move> possibleMoves= new ArrayList<Move>();

        for(int row=0; row < this.length - 2; row++){
            for(int column=0; column < this.length - 2; column++){
                // checking if there is a peg at the source location
                if(isFull(row, column) == true){
                
                    // multiple scenarios where a move could be possible if the conditions are met
                    if((isFull(row, column+2) == false) && (isFull(row, column+1) == true)){
                        possibleMoves.add(new Move(new Location(row, column),new Location(row, column+2)));
                    }
                    /**if((isFull(row, column-2) == false) && (isFull(row, column-1) == true)){
                        possibleMoves.add(new Move(new Location(row, column),new Location(row, column-2)));
                    }*/
                    if((isFull(row+2, column) == false) && (isFull(row+1, column) == true)){
                        possibleMoves.add(new Move(new Location(row, column),new Location(row+2, column)));
                    }
                    /**if((isFull(row-2, column) == false) && (isFull(row-1, column-1) == true)){
                        possibleMoves.add(new Move(new Location(row, column),new Location(row-2, column)));
                    }*/
                }
        }
    } 
    // returning an array of possible moves for a peg
    return possibleMoves;
}



    @Override
    public GameState getGameState() {
        Collection<Move> possibleMoves = getPossibleMoves();
         if(possibleMoves == null){
            return GameState.STALEMATE;
        }
        else{
            return GameState.IN_PROGRESS;
        }
    }


    @Override
    public void makeMove(Move move) throws PegGameException {
        // collection of possible moves to check if our move is valid
        Collection<Move> possibleMoves = getPossibleMoves();

        // getting inputs for the locations
        Scanner myscanner=new Scanner(System.in);
        System.out.println("Enter the numbers for the row and column of the peg that you would like to move: ");
        int srcRow=myscanner.nextInt();
        int srcColumn=myscanner.nextInt();
        System.out.println("Enter the numbers for the row and column that you would like to move your peg to: ");
        int destRow=myscanner.nextInt();
        int destColumn=myscanner.nextInt();
        myscanner.close();
        for(Move possibleMove : possibleMoves){
            if(move == possibleMove){
                gameBoard[srcRow][srcColumn] = '-';
                gameBoard[destRow-1][destColumn-1] = '-';
                gameBoard[destRow][destColumn] = 'o';
            }
        }

       
    }

}
