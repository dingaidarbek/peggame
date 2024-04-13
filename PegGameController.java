package peggame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;



public class PegGameController {
    private PegGameView view;
    private static PegGameSquare model;
    private Location selectedLocation;


    public static PegGameSquare startGame1(String path){
        
    }

    private void handleCellClick(int row, int col, MouseEvent event) {
    if (selectedLocation == null) {
        selectedLocation = new Location(row, col); // select the peg
        view.highlightCell(row, col, true); // highlight the selected peg
    } else {
        // Second click - attempt move
        try {
            Move move = new Move(selectedLocation, new Location(row, col));
            if (model.moveIsPossible(move)) {
                model.makeMove(move);
                view.updateBoard(model); // update the board in the view
                view.highlightCell(selectedLocation.getRow(), selectedLocation.getCol(), false); // don't highlight the selected cell
                selectedLocation = null; // reset selectedLocation after a successful move
            } else {
                view.showMessage("Invalid move. Try again.", Color.RED);
                view.highlightCell(selectedLocation.getRow(), selectedLocation.getCol(), false); // if the move is invalid don't highlight the selected peg
                selectedLocation = null; // reset even if move is invalid to allow new selection
            }
        } catch (PegGameException e) { // handling exceptions like invalid moves
            view.showMessage(e.getMessage(), Color.RED); 
            if (selectedLocation != null) {
                view.highlightCell(selectedLocation.getRow(), selectedLocation.getCol(), false); // make sure to not highlight in case of error
            }
            selectedLocation = null; // deslecting the peg
        }
    }
}

        // This method starts the game saving process by opening a file chooser and allowing the user to select a name and location for the file
        public void saveGame() {
        FileChooser fileChooser = new FileChooser(); // creating a file chooser object so the user selects where to save the file 
        fileChooser.setTitle("Save Game"); // setting the title of the window
        File file = fileChooser.showSaveDialog(view.getPrimaryStage()); // display the content
        if (file != null) { // checking if the user selected a file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) { // initiating a buffered anf file writer for the selected file
                writer.write(model.toString()); // writing a string representation of the game to the file
            } catch (IOException e) {
                view.showMessage("Failed to save game.", Color.RED); // notifying the user if ann error occured whilst trying to save the game
            }
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

