import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PegGameView extends Application{

    private static Image imageNoPeg = new Image ("file:pegs/emptyPeg.png");
    private static Image imagePeg = new Image ("file:pegs/fullPeg.png");


    PegGameSquare game = new PegGameSquare(4);
    final int LENGTH = game.getLength();
    GridPane gridPane = new GridPane();
    public static Location selectedLocation;
    Label gameState = new Label(game.getGameState().toString());
    public static Button temp;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("The Peg Game");
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        Label message = makeLabel("Welcome To The Peg Game!", 36,  Color.BLACK);
        Label prompt = makeLabel("Enter the path of the board: ", 24, Color.BLACK);
        TextField path = new TextField();
        path.setMaxWidth(500);
        // path.setMaxSize(100, 100);
        Button startGame = new Button("Start");
        vbox.getChildren().addAll(message, prompt, path, startGame);
        vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, new Insets(0))));
        // adding the vbox into a scene
        Scene startScene = new Scene(vbox, 1500, 800);
        primaryStage.setMaximized(true);;
        primaryStage.setScene(startScene);
        primaryStage.show();

        startGame.setOnAction(e ->{
            String boardPath = path.getText();
            try {
                game = ReadFile.readFile(boardPath);
                startScene.getWindow();
                vbox.getChildren().clear();

                int length = game.getLength();

                Label warning = new Label("");

                for (int row = 0; row < length; row ++){
                    for (int col = 0; col < length; col++){
                        gridPane.add(makePegButton(row, col),col, row);
                    }
                }

                gameState = new Label(game.getGameState().toString());
                Button saveButton = new Button("Save");

                TextField fileName = new TextField();
                fileName.setMaxWidth(500);

                Label saveLabel = new Label("Enter the name of the file to save:");
                saveLabel.setPadding(new Insets(5));

                saveLabel.setVisible(false);
                saveButton.setVisible(false);
                fileName.setVisible(false);

                
                Button openSave = new Button("Save game");
                openSave.setOnAction(d -> {
                    saveLabel.setVisible(true);
                    saveButton.setVisible(true);
                    fileName.setVisible(true);
                    openSave.setVisible(false);
                });

                saveButton.setOnAction(new SaveGame(fileName, this, game, warning));

                Button exitButton = new Button("Exit");
                exitButton.setPadding(new Insets(5));

                exitButton.setOnAction(new ExitGame(primaryStage));
                
                HBox hbox = new HBox();
                hbox.setAlignment(Pos.CENTER);
                hbox.setPadding(new Insets(5));
                hbox.getChildren().addAll(openSave, saveButton, exitButton);

                vbox.getChildren().addAll(warning, gameState, gridPane, saveLabel, fileName, hbox);
                vbox.setAlignment(Pos.CENTER);

                gridPane.setAlignment(Pos.CENTER);

                primaryStage.setScene(startScene);
                primaryStage.show();
            } 
            catch (FileNotFoundException e1) {
                prompt.setText("Invalid path. Please, try again");
            }

    });
    }
    static void exitProgram(Stage stage){
        stage.close();
    }
    private Label makeLabel(String text, int size, Color foreground){
        Label myLabel = new Label(text);
        myLabel.setFont(new Font("Times New Roman", size));
        myLabel.setTextFill(foreground);
        myLabel.setPadding(new Insets(10));
        return myLabel;
    }


    void makeMove(int fromRow, int fromCol, int toRow, int toCol) throws PegGameException{
        try {
            game.makeMove(new Move(new Location(fromRow, fromCol), new Location(toRow, toCol)));            
        } 
        catch (PegGameException e) {
            throw new PegGameException("Impossible move");
        }
        for (int rows = 0; rows < LENGTH; rows ++){
            for (int cols = 0; cols < LENGTH; cols++){
                gridPane.add(makePegButton(rows, cols),cols, rows);
            }
        }
        
    }



    Button makePegButton(int row, int col){
        Button button = new Button("");
        button.setBackground(new Background(new BackgroundImage(imageNoPeg,
        BackgroundRepeat.NO_REPEAT, 
        BackgroundRepeat.NO_REPEAT, 
        BackgroundPosition.CENTER,
        BackgroundSize.DEFAULT)));

        if (game.getGameBoard()[row][col] == 'o'){
            ImageView view = new ImageView(imagePeg);
            button.setGraphic(view);
        }

        button.setPadding(new Insets(0));
        button.setPrefSize(134, 134);
        button.setOnAction(new MoveMaker(row, col, this, button, game));
        return button;
    }


    void updateGameState(){
        gameState.setText(game.getGameState().toString());
    }
}
