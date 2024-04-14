import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PegGameStart implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent event) {
        // String boardPath = path.getText();
        // PegGameSquare game = ReadFile.readFile(boardPath);
        // int length = game.getLength();
        // char[][] board = game.getGameBoard();
        // String gs = (game.getGameState().toString());
        // Label state = makeLabel("Game State: " + gs , 24, Color.BLACK);
        // warning = makeLabel("", 24, Color.BLACK);
        // vbox.getChildren().clear();
        System.out.println(event);
    }
    
}
