
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
/*
 * This class provide the implementation for saving the Peg Game into new file
 */

public class SaveGame extends PegGameView implements EventHandler <ActionEvent>{
    protected TextField fileName;
    protected PegGameView view;
    protected PegGameSquare game;

    // Constructor
    SaveGame(TextField fileName, PegGameView view, PegGameSquare game){
        this.fileName = fileName;
        this.view = view;
        this.game = game;
    }
    
    // When event happens (button is clicked), this method is called
    @Override 
    public void handle(ActionEvent event)
    {
        // Create new file using FileWriter and enter there values using PrintWriter
        try (FileWriter fw = new FileWriter(fileName.getText() + ".txt");PrintWriter pw = new PrintWriter(fw)) {
            pw.println(game.getLength()); // Entering the length of file

            // Parsing the table into the file
            for(int row = 0; row < game.getLength(); row++) {
                for(int col = 0; col < game.getLength(); col++){
                    if(game.getGameBoard()[row][col] == 'o'){
                    pw.print(game.getGameBoard()[row][col]);
                    }
                    else{
                        pw.print("."); // Change the '-' char to '.', as given in the format
                    }
                }
                pw.println(); // Next line
            }
            pw.flush(); // Send text to the file
            pw.close();
        } catch (IOException e1) {
            // If user enters impossible name of the file, an exception occurs
            warning.setText("Impossible name of the file. Please, change and try again.");
        }
    }    
}
