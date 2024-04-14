import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MoveMaker implements EventHandler<ActionEvent>{
    int row;
    int col;
    PegGameView view;
    Location selectedLocation;
    Button button;
    PegGameSquare game;

    private static Image imageNoPeg = new Image ("file:pegs/emptyPeg.png");
    private static Image imagePeg = new Image ("file:pegs/fullPeg.png");
    private static Image imageSelectedPeg = new Image ("file:pegs/selectedPeg.png");
    
    MoveMaker(int row, int col, PegGameView view, Button button, PegGameSquare game){
        this.row = row;
        this.col = col;
        this.view = view;
        this.button = button;
        this.game = game;
    }

    @Override
    public void handle(ActionEvent event) {
        selectedLocation = PegGameView.selectedLocation;
        if (selectedLocation == null){
            PegGameView.selectedLocation = new Location(row,col);
            if (game.getGameBoard()[PegGameView.selectedLocation.getRow()][PegGameView.selectedLocation.getCol()] == 'o'){
                button.setGraphic(new ImageView(imageSelectedPeg));
                PegGameView.temp = button;
            }
        }
        else{
            try{
                // System.out.println("Sel Loc3: " + selectedLocation);
                view.makeMove(selectedLocation.getRow(), selectedLocation.getCol(), row, col);
                PegGameView.temp.setGraphic(new ImageView(imageNoPeg));
                view.game.setGameState(GameState.IN_PROGRESS);
                view.updateGameState();
            }
            catch(PegGameException e){
                PegGameView.temp.setGraphic(new ImageView(imagePeg));
            }
            if (game.getPossibleMoves().size() == 0){
                if(game.pegsLeft() == 1){
                    game.setGameState(GameState.WON);
                }
                else{
                    game.setGameState(GameState.STALEMATE);
                }
                view.updateGameState();
            }
            PegGameView.selectedLocation = null;
        }
    }
}
