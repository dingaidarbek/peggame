
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class SaveGame extends PegGameView implements EventHandler <ActionEvent>{
    TextField fileName;
    PegGameView view;
    PegGameSquare game;
    Label warning;

    SaveGame(TextField fileName, PegGameView view, PegGameSquare game, Label warning){
        this.fileName = fileName;
        this.view = view;
        this.game = game;
        this.warning = warning;
    }
    
    @Override
    public void handle(ActionEvent event)
    {
        System.out.println(fileName.getText());
        try (FileWriter fw = new FileWriter(fileName.getText() + ".txt");PrintWriter pw = new PrintWriter(fw)) {
            pw.println(game.getLength());
            for(int row = 0; row < game.getLength(); row++) {
                for(int col = 0; col < game.getLength(); col++){
                    pw.print(game.getGameBoard()[row][col]);
                }
                pw.println();
            }
            pw.flush();
            pw.close();
        } catch (IOException e1) {
            warning.setText("Impossible name of the file. Please, change and try again.");
        }
    }    
}
