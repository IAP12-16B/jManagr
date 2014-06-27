package ch.jmanagr.ui.controls.messagebox;

import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * MessageBox class, controlls the Messagebox
 */
public class MessageBox extends Observable implements Observer
{
	public final static String CLICK_OK_EVENT = MessageBoxController.CLICK_OK_EVENT;

	private Stage stage;
	private Scene scene;
	private Parent messageboxRoot;
	private FXMLLoader fxmlLoader;
	private MessageBoxController controller;

	/**
	 * MessageBox
	 *
	 * @param title   the title
	 * @param message the message
	 */
	public MessageBox(String title, String message)
	{
		this.stage = new Stage();

		try {
			fxmlLoader = new FXMLLoader(getClass().getResource("MessageBox.fxml"));
			this.messageboxRoot = (Parent) fxmlLoader.load();

			this.controller = fxmlLoader.getController();

			this.controller.setStage(this.stage);

			this.scene = new Scene(this.messageboxRoot);
			this.stage.setTitle(title);
			this.stage.setScene(this.scene);
			this.stage.setResizable(false);
			this.stage.setMinWidth(this.scene.getWidth());
			this.stage.setMinHeight(this.scene.getHeight());


			this.controller.setMessage(message);
			this.controller.setTitle(title);

			this.controller.addObserver(this);

		} catch (IOException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}
	}

	/**
	 * Show the MessageBox
	 */
	public void show()
	{
		this.stage.toFront();
		this.stage.show();
	}

	/**
	 * Set the title
	 *
	 * @param s the title
	 */
	public void setTitle(String s)
	{stage.setTitle(s);}

	/**
	 * get the title
	 *
	 * @return the title
	 */
	public String getTitle()
	{return stage.getTitle();}

	/**
	 * set the MessageBox
	 *
	 * @param message msg box
	 */
	public void setMessage(String message)
	{controller.setMessage(message);}

	/**
	 * close the messagebox
	 */
	public void close()
	{stage.close();}

	/**
	 * hide the messagebox
	 */
	public void hide()
	{stage.hide();}

	/**
	 * get showing state
	 *
	 * @return
	 */
	public boolean isShowing()
	{return stage.isShowing();}

	/**
	 * Update
	 *
	 * @param o   observable
	 * @param arg argument passed
	 */
	@Override
	public void update(Observable o, Object arg)
	{
		if (o.equals(this.controller)) {
			if (arg.equals(CLICK_OK_EVENT)) {
				this.close();
				this.notifyObservers(CLICK_OK_EVENT);
			}
		}
	}
}
