package ch.jmanagr;

import ch.jmanagr.dal.db.DB;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

	public static Scene scene;
	public static Stage stage;

	public static void main(String[] args) throws InterruptedException
	{
		try {
			launch(args);
		} catch (Exception e) {
			Logger.log(LOG_LEVEL.ERROR, "Oh that was close. Cached exception in main() ", e);
		} finally {
			// shutdown db connection
			DB.getInstance().shutdown();
		}

	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("ui/login/mainLogin.fxml"));
        scene = new Scene(root);
		stage.setTitle("jManagr");
        stage.setScene(scene);
		stage.setMinHeight(650);
		stage.setMinWidth(500);
		stage.setMaxHeight(700);

		stage.show();
	}

    public static void loggedIn(Scene scene) {
        stage.setScene(scene);
    }

}
