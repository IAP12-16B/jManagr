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

public class TicketDetailController implements Initializable
{
	@FXML public static ComboBox<TICKET_STATE> ticketStateCbox;
	@FXML public static ComboBox<Department> departementCbox;
	@FXML public static ComboBox<Resource> resourceCbox;
	@FXML public static ComboBox<User> agentCbox;

	@FXML private static TextArea descriptionFld;
	@FXML private static TextField nameFld;

	private TicketsBL bl;
	private DepartmentsBL depBl;
	private ResourcesBL resBL;
	private UsersBL usersBL;
    private User currentUser;

    private static Ticket updateCurrTicket;
    private static String sourceBtn;

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

    public static void fillTicket(Ticket editingTicket, String srcBtn)
    {
        sourceBtn = srcBtn;
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
            ticket.setResource(resourceCbox.getSelectionModel().getSelectedItem()); //todo @mnewmedia use treeView instead
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
            updateCurrTicket.setStatus(ticketStateCbox.getValue());

            // update all tables because ticket changed
            userTicketsController.ticketList.remove(updateCurrTicket);
            TicketController.ticketList.remove(updateCurrTicket);
            TicketController.allTicketList.remove(updateCurrTicket);

            if ((TicketController.ticketsFilter.getValue() == updateCurrTicket.getStatus()) && (currentUser == updateCurrTicket.getAgent())) {
                TicketController.ticketList.add(updateCurrTicket);
            }
            if ((TicketController.allTicketsFilter.getValue() == updateCurrTicket.getStatus()) && (currentUser.getDepartment().toString().equals(updateCurrTicket.getDepartment().toString()))) {
                TicketController.allTicketList.add(updateCurrTicket);
            }
            if ((userTicketsController.myTicketsFilter.getValue() == updateCurrTicket.getStatus()) && (currentUser == updateCurrTicket.getUser())) {
                userTicketsController.ticketList.add(updateCurrTicket);
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

    public void clearFields() {
        nameFld.setText("");
        descriptionFld.setText("");
        agentCbox.getSelectionModel().selectFirst();
        ticketStateCbox.getSelectionModel().selectFirst();
        resourceCbox.getSelectionModel().selectFirst();
        ticketStateCbox.getSelectionModel().selectFirst();
    }

    public void closeEditView() {
        if (sourceBtn.equals("myEditBtn")) {
            MainController.changeTabContent("tickets", true);
        } else {
            MainController.changeTabContent("tickets");
        }
        sourceBtn = null;
    }

    public static void refreshComboboxes() {

    }
}
