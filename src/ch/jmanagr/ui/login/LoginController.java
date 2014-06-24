package ch.jmanagr.ui.login;

import ch.jmanagr.Main;
import ch.jmanagr.bl.UsersBL;
import ch.jmanagr.bo.User;
import ch.jmanagr.exceptions.jManagrDBException;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.ui.controls.messagebox.MessageBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{


	@FXML
	private TextField userFld;
	@FXML
	private PasswordField passwordFld;
	@FXML
	private Label passwordErrorLbl;

	public LoginController()
	{
	}

	public void initialize(URL location, ResourceBundle resources)
	{
		passwordErrorLbl.setVisible(false);
	}

	public void login()
	{
		try {
			User usr = UsersBL.getInstance().login(this.userFld.getText(), this.passwordFld.getText());
			if (usr != null) {
				Parent root = FXMLLoader.load(getClass().getResource("/ch/jmanagr/ui/main/main.fxml"));
				Main.loggedIn(new Scene(root));
				Main.stage.setTitle(
						String.format(
								"%s - jManagr",
								usr.getUsername()
						)
				);
			} else {
				passwordErrorLbl.setVisible(true);
			}
		} catch (jManagrDBException | IOException e) {
			MessageBox msgBx = new MessageBox(
					"Fehler",
					"Ein fehler ist aufgetreten. Bitte überprüfen Sie die Verbindungseinstellungen und versuchen Sie" +
					" es erneut!\n\n" +
					"Fehlermeldung: \n" +
					e.getLocalizedMessage()
			);
			msgBx.show();
			Logger.log(LOG_LEVEL.ERROR, e);
		}
	}
}
