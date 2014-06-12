package ch.jmanagr.ui.login;

import ch.jmanagr.Main;
import ch.jmanagr.bl.UsersBL;
import ch.jmanagr.bo.User;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{

	private Stage newStage;
	private UsersBL bl;
	private User bo;

	@FXML
	private TextField userFld;
	@FXML
	private PasswordField passwordFld;
	@FXML
	private Label passwordErrorLbl;

	public LoginController()
	{
		this.bl = UsersBL.getInstance();
	}

	public void initialize(URL location, ResourceBundle resources)
	{
		passwordErrorLbl.setVisible(false);
	}

	public void login()
	{
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../main/main.fxml"));
			Main.loggedIn(new Scene(root));
		} catch (IOException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}
	    /*bo = bl.login(userFld.getText(),passwordFld.getText());
        if(bo != null) {
            try {
                this.changeStage();
            } catch (Exception e) {
                System.out.print(e);
            }
        } else {
            passwordErrorLbl.setVisible(true);
        }*/
	}
}
