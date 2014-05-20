package ch.jmanagr;

import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

	public static void main(String[] args) throws InterruptedException
	{
		try {
			launch(args);
		} catch (Exception e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		} finally {
			Logger.log(LOG_LEVEL.ERROR, "Uncatched Exception!");
		}

	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Parent root = FXMLLoader.load(getClass().getResource("ui/main/main.fxml"));
		primaryStage.setTitle("jManagr");
		primaryStage.setScene(new Scene(root));
		primaryStage.setMinHeight(650);
		primaryStage.setMinWidth(500);
		primaryStage.setMaxHeight(700);

		primaryStage.show();
	}
}
