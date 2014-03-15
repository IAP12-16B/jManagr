package ch.jManagr;

import ch.jManagr.BO.TestBO;
import ch.jManagr.DAL.DBCredentials;
import ch.jManagr.DAL.dalTest;
import ch.jManagr.lib.ShutdownManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    // Following is an example how to reference something from the panel


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UI/MainView.fxml"));
        primaryStage.setTitle("jManagr");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(500);
        primaryStage.setMaxHeight(700);

        primaryStage.show();
    }


    public static void main(String[] args) throws InterruptedException{
        // Register Shutdown-Manager
        Runtime r1 = Runtime.getRuntime();
        r1.addShutdownHook(ShutdownManager.getInstance());

        dalTest n = new dalTest();
        System.out.println(n.fetchAllOfType((Class)TestBO.class).toString());

        launch(args);
    }
}
