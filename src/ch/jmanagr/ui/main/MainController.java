package ch.jmanagr.ui.main;

import ch.jmanagr.lib.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable
{
    @FXML private static AnchorPane ticketView;
    @FXML private static AnchorPane ticketDetailView;
    @FXML private static Tab tabTickets;
    @FXML private static TabPane tabPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        // can't make new instance because then the fxml property wouldnt be loaded
        // also cant make it singleton cause the constructor has to be public
    }

    public static void removeTabsByUserRole() {
        //tabPane.getTabs().remove(tabTickets);
    }

    public static void changeTabContent(String $view) {
        if ($view.equals("ticketDetail")) {
            tabTickets.setContent(ticketDetailView);
        } else if ($view.equals("tickets")) {
            tabTickets.setContent(ticketView);
        }
    }

}