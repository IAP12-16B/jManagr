package ch.jmanagr.ui.tickets;

import ch.jmanagr.bl.DepartmentsBL;
import ch.jmanagr.bl.TicketsBL;
import ch.jmanagr.bl.UsersBL;
import ch.jmanagr.bo.User;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.ui.main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TicketDetailController implements Initializable
{
	@FXML
	private ComboBox departementCbox;
	private TicketsBL bl;
	private DepartmentsBL depBl;

	public TicketDetailController()
	{
		// @mnewmedia Use constructor to set BL instances -> This way, DB connection etc... only gets established when
		// the controller is instantiated
		bl = TicketsBL.getInstance();
		depBl = DepartmentsBL.getInstance();
	}

	public void initialize(URL location, ResourceBundle resources)
	{
		// Fill Departement Combobox
		departementCbox.setItems(depBl.getAll()); // use ToString in order To Display nice name in ComboBox
		departementCbox.getSelectionModel().selectFirst();
	}

	public void saveTicket()
	{
		Date d = new Date();
		User u = UsersBL.getInstance().getCurrentUser();
		/*Resource r = new Resource();
		Department de = new Department();
		User agent = new User();
		Ticket ticket = new Ticket();
		ticket.setUser(u);
		ticket.setAgent(agent);
		ticket.setDepartment(de);
		ticket.setResource(r);
		ticket.setDate(d);
		ticket.setStatus(TICKET_STATE.OPEN);
		bl.save(ticket);*/
		Logger.logln("Insertet new Ticket: ");

		//If it worked, return to list
		MainController.changeTabContent("tickets");
	}

	public void cancelTicket()
	{
		MainController.changeTabContent("tickets");
	}

}
