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
        return gameBoard[row][column] == 'o';
    }


    /**implementing the methods in the interface PegGame*/
    @Override
    public Collection<Move> getPossibleMoves() {
        // data structure that will contain all possible moves for the peg
        ArrayList<Move> possibleMoves= new ArrayList<Move>();
        
        for(int row=0; row < this.length; row++){
            for(int column=0; column < this.length; column++){
                // checking if there is a peg at the source location
                if(isFull(row, column) == true){
                    try{
                        // multiple scenarios where a move could be possible if the conditions are met
                        if((isFull(row, column+2) == false) && (isFull(row, column+1) == true)){ // Move from left to right
                            possibleMoves.add(new Move(new Location(row, column),new Location(row, column+2)));
                        }
                    }
                    catch (IndexOutOfBoundsException e){}
                    try{
                        if((isFull(row, column-2) == false) && (isFull(row, column-1) == true)){ // Move right to left
                            possibleMoves.add(new Move(new Location(row, column),new Location(row, column-2)));
                        }
                    }
                    catch (IndexOutOfBoundsException e) {}
                    try{
                        if((isFull(row+2, column) == false) && (isFull(row+1, column) == true)){ // Move from up to down
                            possibleMoves.add(new Move(new Location(row, column),new Location(row+2, column)));
                        }
                    }
                    catch (IndexOutOfBoundsException e){}
                    try{
                        if((isFull(row-2, column) == false) && (isFull(row-1, column) == true)){ // Move from down to up
                            possibleMoves.add(new Move(new Location(row, column),new Location(row-2, column)));
                        }
                    }
                    catch (IndexOutOfBoundsException e){}
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
            return this.gameState;
        }
    }


    @Override
    public void makeMove(Move move) throws PegGameException {
        if (moveIsPossible(move)){
            if(move.getFrom().getRow() == move.getTo().getRow()){ // Peg moves in the same row
                if (move.getFrom().getCol() - move.getTo().getCol() < 0){ // Peg moves from left to right
                    gameBoard[move.getFrom().getRow()][move.getFrom().getCol()] = '-';
                    gameBoard[move.getTo().getRow()][move.getTo().getCol()-1] = '-';
                    gameBoard[move.getTo().getRow()][move.getTo().getCol()] = 'o';
                }
                else if (move.getFrom().getCol() - move.getTo().getCol() > 0){ // If we move from right to left
                    gameBoard[move.getFrom().getRow()][move.getFrom().getCol()] = '-';
                    gameBoard[move.getTo().getRow()][move.getTo().getCol()+1] = '-';
                    gameBoard[move.getTo().getRow()][move.getTo().getCol()] = 'o';
                }
            }
            else if (move.getFrom().getCol() == move.getTo().getCol()){ // Peg moves in the same column
                if (move.getFrom().getRow() - move.getTo().getRow() < 0){ // Peg moves from up to down
                    gameBoard[move.getFrom().getRow()][move.getFrom().getCol()] = '-';
                    gameBoard[move.getTo().getRow()-1][move.getTo().getCol()] = '-';
                    gameBoard[move.getTo().getRow()][move.getTo().getCol()] = 'o';
                }
                else if (move.getFrom().getRow() - move.getTo().getRow() > 0){ // Peg moves from down to up
                    gameBoard[move.getFrom().getRow()][move.getFrom().getCol()] = '-';
                    gameBoard[move.getTo().getRow()+1][move.getTo().getCol()] = '-';
                    gameBoard[move.getTo().getRow()][move.getTo().getCol()] = 'o';
                }
            }
        }
        else{
            System.out.println("Impossible move.");
        }
    }
    public boolean moveIsPossible(Move move){
        Collection<Move> possibleMoves = getPossibleMoves();
        return possibleMoves.contains(move);
    }

    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }

    public int pegsLeft(){
        int numberOfPegs = 0;
        for (int i = 0; i < this.length; i++){
            for (int j = 0; j < this.length; j++){
                if (gameBoard[j][i] == 'o'){
                    numberOfPegs++;
                }
            }
        }
        return numberOfPegs;
    }
}
