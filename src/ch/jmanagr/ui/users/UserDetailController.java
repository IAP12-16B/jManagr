package ch.jmanagr.ui.users;

import ch.jmanagr.bl.Tickets;
import ch.jmanagr.bl.Users;
import ch.jmanagr.bo.*;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.TICKET_STATE;
import ch.jmanagr.lib.USER_ROLE;
import ch.jmanagr.ui.main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class UserDetailController implements Initializable
{
    private Users bl = Users.getInstance();
    @FXML private TextField lastnameFld;
    @FXML private TextField firstnameFld;
    @FXML private TextField usernameFld;
    @FXML private TextField passwordFld;
    @FXML private ComboBox departementCbox;
    @FXML private ComboBox roleCbox;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void saveUser() {
        // you can save the main object itself
        User user = new User();
        user.setLastname(lastnameFld.getText());
        user.setFirstname(firstnameFld.getText());
        user.setUsername(usernameFld.getText());
        user.setPassword(passwordFld.getText()); // Todo @kije had to make pw field in db bigger
        //user.setDepartment();
        user.setRole(USER_ROLE.USER);
        bl.save(user);
        Logger.logln("Insertet new User: ");

        //If it worked, return to list
        MainController.changeTabContent("users");
    }

    public void cancelUser() {
        MainController.changeTabContent("users");
    }

}
