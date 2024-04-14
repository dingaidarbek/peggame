import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * This class provide the implementation of making moves on table
 */

public class MoveMaker implements EventHandler<ActionEvent>{
    protected int row;
    protected int col;
    protected PegGameView view;
    protected Location selectedLocation;
    protected Button button;
    protected PegGameSquare game;
    protected boolean havePeg;

    // Images: square without peg, square with peg, square with selected peg
    protected static Image imageNoPeg = new Image ("file:pegs/emptyPeg.png");
    protected static Image imagePeg = new Image ("file:pegs/fullPeg.png");
    protected static Image imageSelectedPeg = new Image ("file:pegs/selectedPeg.png");
    
    //Constructor
    MoveMaker(int row, int col, PegGameView view, Button button, PegGameSquare game, boolean havePeg){
        this.row = row;
        this.col = col;
        this.view = view;
        this.button = button;
        this.game = game;
        this.havePeg = havePeg;
    }

    // When event happens (button is clicked), this method is called
    @Override
    public void handle(ActionEvent event) {
        selectedLocation = PegGameView.selectedLocation; // Take the location of the clicked button
        if (selectedLocation == null && havePeg){
            /*If there is peg on the location and it is the first click create */ 
            PegGameView.selectedLocation = new Location(row,col); 
            
            if (game.getGameBoard()[PegGameView.selectedLocation.getRow()][PegGameView.selectedLocation.getCol()] == 'o'){
                // Make it visually different from other pegs
                button.setGraphic(new ImageView(imageSelectedPeg));
                PegGameView.temp = button;
            }
        }
        else{
            try{
                // If selectedLocation is not null, that means it is the second click (place where peg should move to)
                view.makeMove(selectedLocation.getRow(), selectedLocation.getCol(), row, col); // Check if move is valid and make it if it is
                PegGameView.temp.setGraphic(new ImageView(imageNoPeg)); // If move is made, delete peg from initial location
                view.game.setGameState(GameState.IN_PROGRESS); // Move is made, then change the GameState
                view.updateGameState(); // Update the GameState on the screen
            }
            catch(PegGameException e){ // If the move is impossible, unselect the initial peg
                PegGameView.temp.setGraphic(new ImageView(imagePeg));
            }
            if (game.getPossibleMoves().size() == 0){ // If there are no moves, that leads to either win or stalemate
                if(game.pegsLeft() == 1){ // Only one peg left => win
                    game.setGameState(GameState.WON);
                }
                else{// Else => stalemate
                    game.setGameState(GameState.STALEMATE);
                }
                view.updateGameState();// Update the GameState on the screen

            }
            PegGameView.selectedLocation = null; // Clear the saved location
        }
    }
}
