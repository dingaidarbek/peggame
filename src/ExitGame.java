import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ExitGame implements EventHandler<ActionEvent>{
    Stage stage;
    ExitGame(Stage stage){
        this.stage = stage;
    }
    @Override
    public void handle(ActionEvent arg0) {
        PegGameView.exitProgram(stage);
    }
    
}
