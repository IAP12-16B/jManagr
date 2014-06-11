package ch.jmanagr.ui.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainLoginController implements Initializable {

    @FXML private static AnchorPane loginView;
    @FXML private static AnchorPane settingsView;
    @FXML private static TabPane tabPane;
    @FXML private static Tab tabLogin;
    @FXML private static Tab tabSettings;

    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    public static void changeTabContent(String view) {
        if (view.equals("login")) {
            tabLogin.setContent(loginView);
        } else if (view.equals("settings")) {
            tabSettings.setContent(settingsView);
        }
    }
}
