package ch.jmanagr.ui.tickets;

import ch.jmanagr.bl.DepartmentsBL;
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
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TicketController implements Initializable
{
	public static ObservableList<Ticket> ticketList;
	private TicketsBL bl;
    private UsersBL usersBL;
    private DepartmentsBL depBl;
    private User currentUser;

	@FXML private static TableView<Ticket> ticketTable;
	@FXML private TableColumn idCol;
	@FXML private TableColumn<Ticket, String> nameCol;
    @FXML private TableColumn<Ticket, Date> dateCol;
    @FXML private TableColumn<User, String> userCol;
    @FXML private TableColumn<User, String> agentCol;
    @FXML private TableColumn<Department, String> departmentCol;
    @FXML private TableColumn<Resource, String> resourceCol;

	@FXML private TextField nameField;
    @FXML private Button delBtn;

	public TicketController()
	{
		this.bl = TicketsBL.getInstance();
        try {
            this.usersBL = UsersBL.getInstance();
        } catch (jManagrDBException e) {
            Logger.log(LOG_LEVEL.ERROR, e);
        }
        this.depBl = DepartmentsBL.getInstance();
        this.currentUser = this.usersBL.getCurrentUser();
	}

	public void initialize(URL location, ResourceBundle resources)
	{
        // Fill Table with Data
		idCol.setCellValueFactory(new PropertyValueFactory("id"));
		departmentCol.setCellValueFactory(new PropertyValueFactory<Department, String>("department"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Ticket, String>("name"));
		dateCol.setCellValueFactory(new PropertyValueFactory<Ticket, Date>("date"));
		userCol.setCellValueFactory(new PropertyValueFactory<User, String>("user")); //Todo make simpleProperty or
		// Dont show User here?
		agentCol.setCellValueFactory(new PropertyValueFactory<User, String>("agent"));
        //statusCol.setCellValueFactory(new PropertyValueFactory<User, String>("status"));
        resourceCol.setCellValueFactory(new PropertyValueFactory<Resource, String>("resource"));

		// makes nameCol to a textField
		nameCol.setCellFactory(TextFieldTableCell.<Ticket>forTableColumn());

		// sets the new Value after enterPressed in the ObserverList
		nameCol.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<Ticket, String>>()
				{
					public void handle(TableColumn.CellEditEvent<Ticket, String> t)
					{
						Ticket ticket = t.getTableView().getItems().get(
								t.getTablePosition()
								 .getRow()
						); //get changed object
						ticket.setName(t.getNewValue()); // set changed value
						bl.save(ticket);
						Logger.log("Updated in table ticket: " + t.getNewValue() + " " + ticket.getId());
					}
				}
		);

        // only delte ticket if is not user
        if (this.currentUser.getRole() != USER_ROLE.USER) {
            delBtn.setVisible(false);
        }

		this.refresh();
	}

	public void refresh()
	{
        if (this.currentUser.getRole() == USER_ROLE.USER) {
            ticketList = FXCollections.observableArrayList(bl.getAllByUser(currentUser, TICKET_STATE.OPEN)); // todo detect state by button
        } else {
            ticketList = FXCollections.observableArrayList(bl.getAll());
            ticketTable.setItems(ticketList);
        }
        Logger.logln("Refreshed Ticket list!");
        ticketTable.setItems(ticketList);
	}

    public static void softRefresh() {
        ticketTable.getColumns().get(0).setVisible(false);
        ticketTable.getColumns().get(0).setVisible(true);
    }

	public void newTicket()
	{
		MainController.changeTabContent("ticketDetail");
	}

	public void deleteTicket()
	{
		Ticket ticket = ticketTable.getSelectionModel().getSelectedItem();
		if (ticket != null) {
			Logger.log("Deleting ticket:" + ticket.getName() + " " + ticket.getId());
			bl.delete(ticket);
			ticketList.remove(ticket);
		} else {
			Logger.log("Nothing selected to delete");
		}
	}

	public void editTicket()
	{
        TicketDetailController.departementCbox.setItems(FXCollections.observableArrayList(depBl.getAll()));
        TicketDetailController.agentCbox.setItems(FXCollections.observableArrayList(usersBL.getByUserRole(USER_ROLE.AGENT)));

		Ticket selectedTicket = ticketTable.getSelectionModel().getSelectedItem();
		if (selectedTicket != null) {
			int index = ticketTable.getSelectionModel().getSelectedIndex();
			TicketDetailController.fillTicket(selectedTicket, index);
            Logger.logln("Edit: " + selectedTicket);
			MainController.changeTabContent("ticketDetail");
		}
	}
}
