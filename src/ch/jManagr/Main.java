package ch.jManagr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    // Following is an example how to reference something from the panel


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI/jManagr.fxml"));
        primaryStage.setTitle("jManagr");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(500);
        primaryStage.setMaxHeight(700);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
