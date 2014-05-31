package ch.jmanagr.ui.tickets;

import ch.jmanagr.ui.main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class TicketDetailController implements Initializable
{
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void saveTicket() {

    }

    public void cancelTicket() {
        MainController.changeTabContent("tickets");
    }

}
