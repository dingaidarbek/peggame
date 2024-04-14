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

/*
 * This class provide the GUI implementation of the Peg Game
 */

public class PegGameView extends Application{
    // Images: square without peg, square with peg
    protected static Image imageNoPeg = new Image ("file:pegs/emptyPeg.png");
    protected static Image imagePeg = new Image ("file:pegs/fullPeg.png");

    // Setup
    protected PegGameSquare game = new PegGameSquare(4);
    final protected int LENGTH = game.getLength();
    protected GridPane gridPane = new GridPane();
    protected  static Location selectedLocation;
    protected Label gameState = new Label(game.getGameState().toString());
    protected  static Button temp;
    protected Label warning;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Setting up the window
        primaryStage.setTitle("The Peg Game");
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        // Initial window
        Label message = makeLabel("Welcome To The Peg Game!", 36,  Color.BLACK);
        Label prompt = makeLabel("Enter the path of the board: ", 24, Color.BLACK);
        TextField path = new TextField();
        path.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, new Insets(1))));

        path.setMaxWidth(500);

        Button startGame = new Button("Start");
        startGame.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, new Insets(1))));

        vbox.getChildren().addAll(message, prompt, path, startGame);
        vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, new Insets(0))));

        // adding the vbox into a scene
        Scene startScene = new Scene(vbox, 1500, 800);
        primaryStage.setMaximized(true);;
        primaryStage.setScene(startScene);
        primaryStage.show();

        // When start game is pressed, scene changes to the board if the path is entered correctly
        startGame.setOnAction(e ->{
            String boardPath = path.getText();
            try {
                game = ReadFile.readFile(boardPath);
                startScene.getWindow();
                vbox.getChildren().clear();

                int length = game.getLength();

                // Label appears when user does impossible move, not visible by default
                warning = makeLabel("Impossible move!", 24, Color.BLUE);
                warning.setVisible(false);

                // Filling the gridpane with buttons and pegs on them
                for (int row = 0; row < length; row ++){
                    for (int col = 0; col < length; col++){
                        gridPane.add(makePegButton(row, col),col, row);
                    }
                }

                // Setting up controls and making them custom
                gameState = new Label(game.getGameState().toString());
                Button saveButton = new Button("Save");
                saveButton.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, new Insets(1))));

                TextField fileName = new TextField();
                fileName.setMaxWidth(500);
                fileName.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, new Insets(1))));

                Label saveLabel = new Label("Enter the name of the file to save:");
                saveLabel.setPadding(new Insets(5));

                // By default, save button and field is not visible
                saveLabel.setVisible(false);
                saveButton.setVisible(false);
                fileName.setVisible(false);

                
                Button openSave = new Button("Save game");
                openSave.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, new Insets(1))));
                
                // When user presses on "Save" he sees the field to enter the name and the button to save file
                openSave.setOnAction(d -> {
                    saveLabel.setVisible(true);
                    saveButton.setVisible(true);
                    fileName.setVisible(true);
                    openSave.setVisible(false);
                });

                // When "save" button is pressed, file is saved with given name
                saveButton.setOnAction(new SaveGame(fileName, this, game));

                // When "exit" is pressed, window closes
                Button exitButton = new Button("Exit");
                exitButton.setPadding(new Insets(5));
                exitButton.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, new Insets(1))));
                exitButton.setOnAction(new ExitGame(primaryStage));
                
                // Putting controls into HBox to make it visually attractive
                HBox hbox = new HBox();
                hbox.setAlignment(Pos.CENTER);
                hbox.setPadding(new Insets(5));
                hbox.getChildren().addAll(openSave, saveButton, exitButton);

                // Adding all controls and the board itself to the centered VBox
                vbox.getChildren().addAll(warning, gameState, gridPane, saveLabel, fileName, hbox);
                vbox.setAlignment(Pos.CENTER);

                gridPane.setAlignment(Pos.CENTER);

                // Showing the window
                primaryStage.setScene(startScene);
                primaryStage.show();
            } 
            catch (FileNotFoundException e1) {
                // If user inputs incorrect path, he will be asked to try again
                prompt.setText("Invalid path. Please, try again");
            }

    });
    }
    // When user presses "exit", window closes
    static void exitProgram(Stage stage){
        stage.close();
    }
        /**
     * The method makeLabel(String text, int size, Color foreground) is a factory method that is used to create 
     * labels and customize them.
     * @param text this specifies the text you want to be written inside the label.
     * @param size specifies the size of the text.
     * @param foreground color of the text.
     */

    private Label makeLabel(String text, int size, Color foreground){
        Label myLabel = new Label(text);
        myLabel.setFont(new Font("Times New Roman", size));
        myLabel.setTextFill(foreground);
        myLabel.setPadding(new Insets(5));
        return myLabel;
    }

        /**
     * The method makeMove(int fromRow, int fromCol, int toRow, int toCol) is a method that is used make moves.
     * @param fromRow,FromCol this specifies the coordinates you want to move peg from.
     * @param toRow,toCol specifies the coordinates you want to move peg to.
     * @throws PegGameException if user made an impossible move.
     */

    void makeMove(int fromRow, int fromCol, int toRow, int toCol) throws PegGameException{
        try {
            // If possible move is done, warning disappears (if it was shown) and move is being made
            warning.setVisible(false);
            game.makeMove(new Move(new Location(fromRow, fromCol), new Location(toRow, toCol)));            
        } 
        catch (PegGameException e) {
            // If impossible move is done, user gets warning
            warning.setVisible(true);
            throw new PegGameException("Impossible move");
        }
        // Updating the board after move
        for (int rows = 0; rows < LENGTH; rows ++){
            for (int cols = 0; cols < LENGTH; cols++){
                gridPane.add(makePegButton(rows, cols),cols, rows);
            }
        }
        
    }


    /**
     * The method mmakePegButton(int row, int col) is a method that is used to create Buttons for GridPane.
     * @param row,col this specifies the coordinates of the peg
     */
    Button makePegButton(int row, int col){
        // Creating new button
        Button button = new Button("");

        // Settings of new button
        button.setBackground(new Background(new BackgroundImage(imageNoPeg,
        BackgroundRepeat.NO_REPEAT, 
        BackgroundRepeat.NO_REPEAT, 
        BackgroundPosition.CENTER,
        BackgroundSize.DEFAULT)));
        boolean havePeg = false; 

        if (game.getGameBoard()[row][col] == 'o'){ // Check if this field has a peg
            ImageView view = new ImageView(imagePeg);
            button.setGraphic(view); // Draw the peg
            havePeg = true;
        }

        // Setting visuals for the button
        button.setPadding(new Insets(0));
        button.setPrefSize(134, 134);
        
        // When button is pressed, an instance of MoveMaker is created and (row, col, this, button, game, havePeg) params are passed
        button.setOnAction(new MoveMaker(row, col, this, button, game, havePeg));
        return button;
    }

    // This method updates GameState on GUI
    void updateGameState(){
        gameState.setText(game.getGameState().toString());
    }
}
