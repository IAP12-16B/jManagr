package ch.jmanagr.ui.tickets;

import ch.jmanagr.bl.DepartmentsBL;
import ch.jmanagr.bl.ResourcesBL;
import ch.jmanagr.bl.TicketsBL;
import ch.jmanagr.bl.UsersBL;
import ch.jmanagr.bo.Department;
import ch.jmanagr.bo.Resource;
import ch.jmanagr.bo.Ticket;
import ch.jmanagr.bo.User;
import ch.jmanagr.exceptions.jManagrDBException;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.TICKET_STATE;
import ch.jmanagr.ui.main.MainController;
import ch.jmanagr.ui.userTickets.userTicketsController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;


/**
 * Controller for the Edit view of a Ticket
 */
public class TicketDetailController implements Initializable
{
	@FXML
	public static ComboBox<TICKET_STATE> ticketStateCbox;
	@FXML
	public static ComboBox<Department> departementCbox;
	@FXML
	public static ComboBox<Resource> resourceCbox;
	@FXML
	public static ComboBox<User> agentCbox;

	@FXML
	private static TextArea descriptionFld;
	@FXML
	private static TextField nameFld;

	private TicketsBL bl;
	private DepartmentsBL depBl;
	private ResourcesBL resBL;
	private UsersBL usersBL;
	private User currentUser;

	private static Ticket updateCurrTicket;
	private static boolean editFromMyTickets;

	public TicketDetailController()
	{
		bl = TicketsBL.getInstance();
		depBl = DepartmentsBL.getInstance();
		resBL = ResourcesBL.getInstance();
		try {
			usersBL = UsersBL.getInstance();
		} catch (jManagrDBException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}
		this.currentUser = usersBL.getCurrentUser();
	}

	public void initialize(URL location, ResourceBundle resources)
	{
		ticketStateCbox.setItems(FXCollections.observableArrayList(TICKET_STATE.values()));
		ticketStateCbox.getSelectionModel().selectFirst();
	}

	public static void fillTicket(Ticket editingTicket)
	{
		updateCurrTicket = editingTicket;
		if (updateCurrTicket != null) {
			nameFld.setText(updateCurrTicket.getName());
			descriptionFld.setText(updateCurrTicket.getDescription());

			ticketStateCbox.getSelectionModel().select(updateCurrTicket.getStatus());
			departementCbox.getSelectionModel().select(updateCurrTicket.getDepartment());
			resourceCbox.getSelectionModel().select(updateCurrTicket.getResource());
			agentCbox.getSelectionModel().select(updateCurrTicket.getAgent());
		}
	}

	public static void fillTicket(Ticket editingTicket, boolean fromMyTickets)
	{
		updateCurrTicket = editingTicket;
		editFromMyTickets = fromMyTickets;
		if (updateCurrTicket != null) {
			nameFld.setText(updateCurrTicket.getName());
			descriptionFld.setText(updateCurrTicket.getDescription());

			ticketStateCbox.getSelectionModel().select(updateCurrTicket.getStatus());
			departementCbox.getSelectionModel().select(updateCurrTicket.getDepartment());
			resourceCbox.getSelectionModel().select(updateCurrTicket.getResource());
			agentCbox.getSelectionModel().select(updateCurrTicket.getAgent());
		}
	}

	public void saveTicket()
	{
		// if new ticket
		if (updateCurrTicket == null) {
			Ticket ticket = new Ticket();
			Date d = new Date();

			this.currentUser = usersBL.getCurrentUser();

			ticket.setUser(this.currentUser);
			ticket.setDate(d);
			ticket.setDescription(descriptionFld.getText());
			ticket.setName(nameFld.getText());
			ticket.setDepartment(departementCbox.getSelectionModel().getSelectedItem());
			ticket.setAgent(agentCbox.getSelectionModel().getSelectedItem());
			ticket.setResource(
					resourceCbox.getSelectionModel()
					            .getSelectedItem()
			); //todo @mnewmedia use treeView instead
			ticket.setStatus(ticketStateCbox.getSelectionModel().getSelectedItem());

			bl.save(ticket);

			if (ticket.getStatus() == userTicketsController.myTicketsFilter.getValue()) {
				userTicketsController.ticketList.add(ticket);
			}

			MainController.changeTabContent("tickets", true);
		} else {
			updateCurrTicket.setName(nameFld.getText());
			updateCurrTicket.setDescription(descriptionFld.getText());
			updateCurrTicket.setDepartment(departementCbox.getSelectionModel().getSelectedItem());
			updateCurrTicket.setAgent(agentCbox.getSelectionModel().getSelectedItem());
			updateCurrTicket.setResource(resourceCbox.getSelectionModel().getSelectedItem());

			// remove tickets from table if status did change..
			if (updateCurrTicket.getStatus() != ticketStateCbox.getValue()) {
				updateCurrTicket.setStatus(ticketStateCbox.getValue());
				TicketController.ticketList.remove(updateCurrTicket); //todo bug?
			}

			// if agent takes a ticket, show it in upper Table
			if ((updateCurrTicket.getAgent() == currentUser) &&
			    (TicketController.ticketsFilter.getValue() == updateCurrTicket.getStatus())) {
				TicketController.ticketList.remove(updateCurrTicket); // make sure if its already there it doesn't get
				// displayed twice
				TicketController.ticketList.add(updateCurrTicket);
			}
			// if agent, changes the tickets agent to somebody else then him self remove it from upper table
			if (updateCurrTicket.getAgent() != currentUser) {
				TicketController.ticketList.remove(updateCurrTicket);
			}

			//save
			bl.save(updateCurrTicket);
			this.closeEditView();
		}
		this.clearFields();
		TicketController.softRefresh();
		userTicketsController.softRefresh();
		updateCurrTicket = null;
	}

	public void cancelTicket()
	{
		this.closeEditView();
		this.clearFields();
	}

	public void clearFields()
	{
		nameFld.setText("");
		descriptionFld.setText("");
		agentCbox.getSelectionModel().selectFirst();
		ticketStateCbox.getSelectionModel().selectFirst();
		resourceCbox.getSelectionModel().selectFirst();
		ticketStateCbox.getSelectionModel().selectFirst();
	}

	public void closeEditView()
	{
		if (editFromMyTickets) {
			MainController.changeTabContent("tickets", true);
		} else {
			MainController.changeTabContent("tickets");
		}
		editFromMyTickets = false;
	}

	public static void refreshComboboxes()
	{

	}
}
