package ch.jmanagr.ui.settings;


import ch.jmanagr.bl.Settings;
import ch.jmanagr.lib.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    ch.jmanagr.bl.Settings bl = ch.jmanagr.bl.Settings.getInstance();
    ch.jmanagr.bo.Settings bo = new ch.jmanagr.bo.Settings();

    @FXML TextField passwordFld;
    @FXML TextField hostFld;
    @FXML TextField dbFld;
    @FXML TextField portFld;
    @FXML TextField userFld;

    @FXML Label connectionErrorLbl;
    @FXML Button saveBtn;

    public void initialize(URL location, ResourceBundle resources)
    {
        this.bo = this.bl.retrieve();
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
