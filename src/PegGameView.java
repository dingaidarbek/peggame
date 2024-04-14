import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PegGameView extends Application{

    private static Image imageNoPeg = new Image ("file:pegs/emptyPeg.png");
    private static Image imagePeg = new Image ("file:pegs/fullPeg.png");
    private static Image imageSelectedPeg = new Image ("file:pegs/selectedPeg.png");


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
        String boardPath = "C:\\Users\\Dimash\\repos\\gcis124\\Dimash\\peggame\\peggameGUI\\src\\boardExample.txt";
        game = ReadFile.readFile(boardPath);
        int length = game.getLength();
        Label warning = new Label("");
        for (int row = 0; row < length; row ++){
            for (int col = 0; col < length; col++){
                gridPane.add(makePegButton(row, col),col, row);
            }
        }
        gameState = new Label(game.getGameState().toString());
        Button saveButton = new Button("Save game");
        TextField fileName = new TextField();
        Button exitButton = new Button("Exit");
        vbox.getChildren().addAll(warning, gameState, gridPane, fileName, saveButton, exitButton);
        vbox.setAlignment(Pos.CENTER);
        gridPane.setAlignment(Pos.CENTER);
        Scene pathScene = new Scene (vbox,1200, 700);
        primaryStage.setScene(pathScene);
        primaryStage.show();
        saveButton.setOnAction(new SaveGame(fileName, this, game, warning));
        exitButton.setOnAction(new ExitGame(this));
    }
    void highlightPeg(int row, int col){
        
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



    public static void main(String[] args) {
        launch(args);
    }
}
