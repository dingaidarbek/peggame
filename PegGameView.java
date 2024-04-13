package PEGGAME;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 * This class provide the GUI implementation of the Peg Game
 */

public class PegGameView extends Application {

    /**
     * The method makeLabel(String text, int size, Color foreground) is a factory method that is used to create 
     * labels and customize them.
     * @param text this specifies the text you want to be written inside the label.
     * @param size specifies the size of the text.
     * @param foreground color of the text.
     */
    private static Label makeLabel(String text, int size, Color foreground){
        Label myLabel = new Label(text);
        myLabel.setFont(new Font("Times New Roman", size));
        myLabel.setTextFill(foreground);
        myLabel.setPadding(new Insets(10));
        return myLabel;

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("The Peg Game");

        // Constructing the controls for the peg game.
        VBox vbox= new VBox();
        vbox.setAlignment(Pos.CENTER);
        Label message = makeLabel("Welcome To The Peg Game!", 36,  Color.BLACK);
        Label prompt = makeLabel("Enter the path of the board: ", 24, Color.BLACK);
        String gs = (String)(getGameState());
        TextField path = new TextField();
        Label state = makeLabel("current state of the game: " + gs, 24, Color.BLACK);
        

        // reading th eboard from the text file.
        ReadFile read = new ReadFile();
        String boardPath = path.getText();
        PegGameSquare game = read.readFile(boardPath);
        int length = game.getLength();
        char[][] board = game.getGameBoard();
        
    
        // Creating the board using gridpane layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(length+1));
        gridPane.setAlignment(Pos.CENTER);
        for(int col=1; col<=length; col++) {
            for(int row=1; row<=length; row++) {  
                char value = board[row][col];  
                if(value == '-'){
                    Label empty = makeLabel("-", 24,  Color.RED);
                    empty.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                    gridPane.add(empty, col, row);

                } else {
                    Label peg = makeLabel("o", 24,  Color.RED);
                    peg.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                    gridPane.add(peg, col, row);
                }
           
            }
        }


        // puting all the controls into the vertical box.
        vbox.getChildren().addAll(message, prompt, path, state, gridPane);

        // adding the vbox into a scene
        Scene scene = new Scene(vbox, 300, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    // getter for the game board we created using gridpane layout
    public GridPane getGameBoardGridpane(){return gridPane;}

}
