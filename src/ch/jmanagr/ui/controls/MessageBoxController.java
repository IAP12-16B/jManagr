package ch.jmanagr.ui.controls;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageBoxController implements Initializable
{
	@FXML
	private AnchorPane messageBoxPane;

	@FXML
	private Label messageBoxTitle;

	@FXML
	private Label messageLabel;

	private Stage stage;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle)
	{

	}

	public void clickOk()
	{
		if (this.stage != null) {
			this.stage.close();
		}
	}

	public void setStage(Stage stage)
	{
		this.stage = stage;
	}

	public Stage getStage()
	{
		return this.stage;
	}

	public void setMessage(String message)
	{
		this.messageLabel.setText(message);
	}

	public void setTitle(String title)
	{
		this.messageBoxTitle.setText(title);
	}
}
