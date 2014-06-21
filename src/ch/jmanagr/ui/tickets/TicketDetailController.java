package ch.jmanagr.ui.tickets;

import ch.jmanagr.bl.DepartmentsBL;
import ch.jmanagr.bl.TicketsBL;
import ch.jmanagr.bl.UsersBL;
import ch.jmanagr.bo.Department;
import ch.jmanagr.bo.Ticket;
import ch.jmanagr.bo.User;
import ch.jmanagr.exceptions.jManagrDBException;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.TICKET_STATE;
import ch.jmanagr.ui.main.MainController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TicketDetailController implements Initializable
{
	@FXML
	public ComboBox<TICKET_STATE> ticketStateCbox;
	@FXML
	public static ComboBox<Department> departementCbox;


	@FXML
	private TextArea descriptionFld;
	@FXML
	private TextField nameFld;

	private TicketsBL bl;
	private DepartmentsBL depBl;

	public TicketDetailController()
	{
		bl = TicketsBL.getInstance();
		depBl = DepartmentsBL.getInstance();
	}

	public void initialize(URL location, ResourceBundle resources)
	{
		ticketStateCbox.setItems(
				FXCollections.observableArrayList(TICKET_STATE.values())
		);
	}

	public void saveTicket()
	{
		Ticket ticket = new Ticket();

		Date d = new Date();
		User u = null;
		try {
			u = UsersBL.getInstance().getCurrentUser();
		} catch (jManagrDBException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}
		Department de = departementCbox.getValue();
		/*Resource r = new Resource(); // todo resource combo box
		User agent = new User();
		ticket.setAgent(agent);
		ticket.setResource(r);*/
		ticket.setDepartment(de);
		ticket.setUser(u);
		ticket.setDate(d);
		ticket.setStatus(TICKET_STATE.OPEN); // todo state combo box
		ticket.setDescription(descriptionFld.getText());
		ticket.setName(nameFld.getText());
		bl.save(ticket);
		Logger.logln("Insertet new Ticket: ");

		bl.save(ticket);
		TicketController.ticketList.add(ticket);
		Logger.logln("Insertet new Ticket: " + ticket);

		//If it worked, return to list
		MainController.changeTabContent("tickets");
	}

	public void cancelTicket()
	{
		MainController.changeTabContent("tickets");
	}

}
