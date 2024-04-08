package peggame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BoardView extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("The Peg Game");

        VBox vbox= new VBox();
        Label message = new Label("Welcome to the game!!");
        Label prompt = new Label("Enter the path of the board to start: ");
        TextField path = new TextField();
        vbox.getChildren().addAll(message, prompt, path);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
    
}
