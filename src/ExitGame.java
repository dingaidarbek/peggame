import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/*
 * This class provide the implementation of exiting the game
 */
public class ExitGame implements EventHandler<ActionEvent>{
    protected Stage stage;
    
    // Constructor
    ExitGame(Stage stage){
        this.stage = stage;
    }

    // When event happens (button is clicked), this method is called
    @Override
    public void handle(ActionEvent arg0) {
        PegGameView.exitProgram(stage);
    }
    
}
