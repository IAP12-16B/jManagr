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
import ch.jmanagr.lib.USER_ROLE;
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
		resourceCbox.setItems(FXCollections.observableArrayList(resBL.getAll()));
        resourceCbox.getSelectionModel().selectFirst();
		agentCbox.setItems(FXCollections.observableArrayList(usersBL.getByUserRole(USER_ROLE.AGENT)));
        agentCbox.getSelectionModel().selectFirst();
        departementCbox.setItems(FXCollections.observableArrayList(depBl.getAll()));
        departementCbox.getSelectionModel().selectFirst();
	}

    public static void fillTicket(Ticket editingTicket)
    {
        updateCurrTicket = editingTicket;
        Logger.logln("updat curr ticket " + updateCurrTicket);
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

            //save
            bl.save(ticket);
            userTicketsController.ticketList.add(ticket); //todo High @mnewmedia add only if combobox status is same as ticket status
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
                TicketController.ticketList.remove(updateCurrTicket);
            }

            //save
            bl.save(updateCurrTicket);
            TicketController.softRefresh();
        }
        this.clearFields();
        TicketController.softRefresh();
		MainController.changeTabContent("tickets");
        updateCurrTicket = null;
	}

	public void cancelTicket()
	{
        // potential bug, if agent edits his own ticket from ticketBearbeiten tab
        if ((updateCurrTicket == null) || (currentUser == updateCurrTicket.getUser())) {
            MainController.changeTabContent("tickets", true);
        } else {
            MainController.changeTabContent("tickets");
        }
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
}
