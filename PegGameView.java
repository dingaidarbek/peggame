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


public class PegGameView extends Application {

    private static Label makeLabel(String text, Color foreground){
        Label myLabel = new Label(text);
        myLabel.setFont(new Font("Times New Roman", 24));
        myLabel.setTextFill(foreground);
        myLabel.setPadding(new Insets(10));
        return myLabel;

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("The Peg Game");

        VBox vbox= new VBox();
        vbox.setAlignment(Pos.CENTER);
        Label message = makeLabel("Welcome To The Peg Game!", Color.BLACK);
        Label prompt = makeLabel("Enter the path of the board: ", Color.BLACK);
        TextField path = new TextField();
        //vbox.getChildren().addAll(message, prompt, path);


        ReadFile read = new ReadFile();
        String boardPath = path.getText();
        PegGameSquare game = read.readFile(boardPath);
        int length = game.getLength();
        char[][] b = game.getGameBoard();
        
    
        // Creating the board
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(length+1));
        gridPane.setAlignment(Pos.CENTER);
        for(int col=1; col<=length; col++) {
            for(int row=1; row<=length; row++) {  
                char value = b[row][col];  
                if(value == '-'){
                    Label empty = new Label("-");
                    gridPane.add(empty, col, row);

                } else {
                    Label peg = new Label("o");
                    gridPane.add(peg, col, row);
                }
           
         }
 }

    vbox.getChildren().addAll(message, prompt, path, gridPane);
        Scene scene = new Scene(vbox, 300, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
