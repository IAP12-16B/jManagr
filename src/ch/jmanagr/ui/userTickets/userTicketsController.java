package ch.jmanagr.ui.userTickets;

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
import ch.jmanagr.ui.tickets.TicketDetailController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Controller controlls the ticket/user tab
 */
public class userTicketsController implements Initializable
{
	public static ObservableList<Ticket> ticketList;

    private TicketsBL bl;
    private UsersBL usersBL;
    private DepartmentsBL depBL;
    private ResourcesBL resBL;
    private User currentUser;

    // Controls for Table MyTickets
	@FXML private static TableView<Ticket> myTicketTable;
	@FXML private TableColumn myIdCol;
	@FXML private TableColumn<Ticket, String> myNameCol;
    @FXML private TableColumn<Ticket, Date> myDateCol;
    @FXML private TableColumn<User, String> myAgentCol;
    @FXML private TableColumn<Department, String> myDepartmentCol;
    @FXML private TableColumn<Resource, String> myResourceCol;
    @FXML public static ComboBox<TICKET_STATE> myTicketsFilter;

	public userTicketsController()
	{
		this.bl = TicketsBL.getInstance();
        try {
            this.usersBL = UsersBL.getInstance();
        } catch (jManagrDBException e) {
            Logger.log(LOG_LEVEL.ERROR, e);
        }
        this.resBL = ResourcesBL.getInstance();
        this.depBL = DepartmentsBL.getInstance();
        this.currentUser = this.usersBL.getCurrentUser();
	}

	public void initialize(URL location, ResourceBundle resources)
	{
        // --------------------- Table My Tickets ------------------------
        // Fill Table with Data
        myIdCol.setCellValueFactory(new PropertyValueFactory("id"));
        myDepartmentCol.setCellValueFactory(new PropertyValueFactory<Department, String>("department"));
        myNameCol.setCellValueFactory(new PropertyValueFactory<Ticket, String>("name"));
        myDateCol.setCellValueFactory(new PropertyValueFactory<Ticket, Date>("date"));
        myAgentCol.setCellValueFactory(new PropertyValueFactory<User, String>("agent"));
        myResourceCol.setCellValueFactory(new PropertyValueFactory<Resource, String>("resource"));

        // Fill ticketsFilter Combobox
        myTicketsFilter.getItems().addAll(TICKET_STATE.values());
        myTicketsFilter.getSelectionModel().selectFirst();

		this.refresh();
	}

	public void refresh()
	{
        ticketList = FXCollections.observableArrayList(bl.getAllByUser(currentUser, myTicketsFilter.getValue()));
        myTicketTable.setItems(ticketList);
	}

    public static void softRefresh() {
        myTicketTable.getColumns().get(0).setVisible(false);
        myTicketTable.getColumns().get(0).setVisible(true);
    }

	public void newTicket()
	{
        this.refreshComboBoxes();
		MainController.changeTabContent("ticketDetail", true);
	}

	public void editTicket(ActionEvent event)
	{
        Button sourceBtn = (Button) event.getSource();

        this.refreshComboBoxes();
		Ticket selectedTicket = myTicketTable.getSelectionModel().getSelectedItem();
		if (selectedTicket != null) {
			TicketDetailController.fillTicket(selectedTicket, sourceBtn.getId());
			MainController.changeTabContent("ticketDetail", true);
		}
	}

    public void refreshComboBoxes() {
        TicketDetailController.departementCbox.setItems(FXCollections.observableArrayList(depBL.getAll()));
        TicketDetailController.agentCbox.setItems(FXCollections.observableArrayList(usersBL.getByUserRole(USER_ROLE.AGENT)));
        TicketDetailController.agentCbox.getItems().addAll(FXCollections.observableArrayList(usersBL.getByUserRole(USER_ROLE.ADMIN)));
        TicketDetailController.resourceCbox.setItems(FXCollections.observableArrayList(resBL.getAll()));

        TicketDetailController.departementCbox.getSelectionModel().selectFirst();
        TicketDetailController.agentCbox.getSelectionModel().selectFirst();
        TicketDetailController.resourceCbox.getSelectionModel().selectFirst();
    }
}
