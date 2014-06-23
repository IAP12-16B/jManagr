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

    private static Ticket updateCurrTicket;
    private static int updateCurrIndex;

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
	}

	public void initialize(URL location, ResourceBundle resources)
	{
		ticketStateCbox.setItems(
				FXCollections.observableArrayList(TICKET_STATE.values())
		);
		resourceCbox.setItems(
				FXCollections.observableArrayList(
						resBL.getAll()
				)
		);
		agentCbox.setItems(
                FXCollections.observableArrayList(
                        usersBL.getByUserRole(USER_ROLE.AGENT)
                )
        );
	}

    public static void fillTicket(Ticket editingTicket, int index)
    {
        updateCurrTicket = editingTicket;
        Logger.logln("updat curr ticket " + updateCurrTicket);
        if (updateCurrTicket != null) {
            nameFld.setText(updateCurrTicket.getName());
            descriptionFld.setText(updateCurrTicket.getDescription());

            ticketStateCbox.getSelectionModel().select(updateCurrTicket.getStatus());
            departementCbox.getSelectionModel().select(updateCurrTicket.getDepartment());
            Logger.logln("fillTicket :" + updateCurrTicket.getDepartment());
            departementCbox.getSelectionModel().select(2);
            resourceCbox.getSelectionModel().select(updateCurrTicket.getResource());
            agentCbox.getSelectionModel().select(updateCurrTicket.getAgent());
        }
        updateCurrIndex = index;
    }

	public void saveTicket()
	{
        // if new ticket
        if (updateCurrTicket == null) {
            Ticket ticket = new Ticket();
            Date d = new Date();

            User u = null;
            try {
                u = UsersBL.getInstance().getCurrentUser();
            } catch (jManagrDBException e) {
                Logger.log(LOG_LEVEL.ERROR, e);
            }

            ticket.setUser(u);
            ticket.setDate(d);
            ticket.setDescription(descriptionFld.getText());
            ticket.setName(nameFld.getText());
            ticket.setDepartment(departementCbox.getSelectionModel().getSelectedItem());
            ticket.setAgent(agentCbox.getSelectionModel().getSelectedItem());
            ticket.setResource(resourceCbox.getSelectionModel().getSelectedItem()); //todo use treeView instead
            ticket.setStatus(ticketStateCbox.getSelectionModel().getSelectedItem());

            //save
            bl.save(ticket);
            TicketController.ticketList.add(ticket);
            Logger.logln("Insertet new Ticket: " + ticket);
        } else {
            updateCurrTicket.setName(nameFld.getText());
            updateCurrTicket.setDescription(descriptionFld.getText());
            updateCurrTicket.setDepartment(departementCbox.getSelectionModel().getSelectedItem());
            updateCurrTicket.setAgent(agentCbox.getSelectionModel().getSelectedItem());
            updateCurrTicket.setStatus(ticketStateCbox.getSelectionModel().getSelectedItem());
            updateCurrTicket.setResource(resourceCbox.getSelectionModel().getSelectedItem());

            //save
            bl.save(updateCurrTicket);
            TicketController.ticketList.set(updateCurrIndex, updateCurrTicket);
            Logger.logln("Updated Ticket: " + updateCurrTicket);
        }
        this.clearFields();
		MainController.changeTabContent("tickets");
	}

	public void cancelTicket()
	{
		MainController.changeTabContent("tickets");
        this.clearFields();
	}

    public void clearFields() {
        nameFld.setText(null);
        descriptionFld.setText(null);
        agentCbox.getSelectionModel().selectFirst();
        ticketStateCbox.getSelectionModel().selectFirst();
        resourceCbox.getSelectionModel().selectFirst();
        ticketStateCbox.getSelectionModel().selectFirst();
    }
}
