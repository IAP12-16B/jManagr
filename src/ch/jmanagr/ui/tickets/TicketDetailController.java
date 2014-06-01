package ch.jmanagr.ui.tickets;

import ch.jmanagr.bl.Tickets;
import ch.jmanagr.bo.*;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.TICKET_STATE;
import ch.jmanagr.ui.main.MainController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TicketDetailController implements Initializable
{
    private Tickets bl = Tickets.getInstance();
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void saveTicket() {
        // Todo @kije Errors maybe cause sql2o 1.5?
        // Todo: @kije simplify this.... It should not be necessary to first create a bunch of dummy objects before
        // you can save the main object itself
        Date d = new Date();
        Resource r = BusinessObjectManager.getInstance(Resource.class, null);
        Department de = BusinessObjectManager.getInstance(Department.class, null);
        User u = BusinessObjectManager.getInstance(User.class, null); //Users.getInstance().getCurrentUser();
        User agent = BusinessObjectManager.getInstance(User.class, null);
        Ticket ticket = BusinessObjectManager.getInstance(Ticket.class, null);
        ticket.setUser(u);
        ticket.setAgent(agent);
        ticket.setDepartment(de);
        ticket.setResource(r);
        ticket.setDate(d);
        ticket.setStatus(TICKET_STATE.OPEN);
        bl.save(ticket);
        Logger.logln("Insertet new Ticket: ");

        //If it worked, return to list
        MainController.changeTabContent("tickets");
    }

    public void cancelTicket() {
        MainController.changeTabContent("tickets");
    }

}
