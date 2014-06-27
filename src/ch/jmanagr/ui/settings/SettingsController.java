package ch.jmanagr.ui.settings;


import ch.jmanagr.bl.SettingsBL;
import ch.jmanagr.lib.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the settings tab
 */
public class SettingsController implements Initializable
{

	SettingsBL bl;
	ch.jmanagr.bo.Settings bo;

	@FXML
	TextField passwordFld;
	@FXML
	TextField hostFld;
	@FXML
	TextField dbFld;
	@FXML
	TextField portFld;
	@FXML
	TextField userFld;

	@FXML
	Label connectionErrorLbl;
	@FXML
	Button saveBtn;

	public SettingsController()
	{
		this.bl = SettingsBL.getInstance();
		this.bo = new ch.jmanagr.bo.Settings();
	}


	public void initialize(URL location, ResourceBundle resources)
	{
		this.bo = this.bl.retrieve();
		this.hostFld.setText(bo.getHost());
		this.dbFld.setText(bo.getDatabase());
		this.portFld.setText(String.valueOf(bo.getPort()));
		this.userFld.setText(bo.getUser());

	}

	public void saveSettings()
	{
		Logger.log("asd");
		this.bo.setHost(this.hostFld.getText());
		this.bo.setPort(Integer.valueOf(this.portFld.getText()));
		this.bo.setDatabase(this.dbFld.getText());
		this.bo.setUser(this.userFld.getText());
		this.bo.setPassword(this.passwordFld.getText());
		this.bl.store(bo);
	}
}
