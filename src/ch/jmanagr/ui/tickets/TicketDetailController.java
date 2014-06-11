package ch.jmanagr.ui.tickets;

import ch.jmanagr.bl.Tickets;
import ch.jmanagr.bo.Department;
import ch.jmanagr.bo.Resource;
import ch.jmanagr.bo.Ticket;
import ch.jmanagr.bo.User;
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

	public void initialize(URL location, ResourceBundle resources)
	{

	}

	public void saveTicket()
	{
		// Todo @kije Errors maybe cause sql2o 1.5?
		// Todo: @kije simplify this.... It should not be necessary to first create a bunch of dummy objects before
		// you can save the main object itself
		Date d = new Date();
		Resource r = new Resource();
		Department de = new Department();
		User u = new User(); //Users.getInstance().getCurrentUser();
		User agent = new User();
		Ticket ticket = new Ticket();
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

	public void cancelTicket()
	{
		MainController.changeTabContent("tickets");
	}

}
