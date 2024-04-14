import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ExitGame implements EventHandler<ActionEvent>{
    PegGameView view;
    ExitGame(PegGameView view){
        this.view = view;
    }
    @Override
    public void handle(ActionEvent arg0) {
        try {
            view.stop();
        } catch (Exception e) {

        }
    }
    
}
