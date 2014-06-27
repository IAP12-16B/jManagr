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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TicketController implements Initializable
{
	public static ObservableList<Ticket> ticketList;
    public static ObservableList<Ticket> allTicketList;

    private TicketsBL bl;
    private UsersBL usersBL;
    private DepartmentsBL depBL;
    private ResourcesBL resBL;
    private User currentUser;

    // Controls for Table MyTickets
	@FXML private static TableView<Ticket> ticketTable;
	@FXML private TableColumn idCol;
	@FXML private TableColumn<Ticket, String> nameCol;
    @FXML private TableColumn<Ticket, Date> dateCol;
    @FXML private TableColumn<User, String> userCol;
    @FXML private TableColumn<User, String> agentCol;
    @FXML private TableColumn<Department, String> departmentCol;
    @FXML private TableColumn<Resource, String> resourceCol;
    @FXML private ComboBox<TICKET_STATE> ticketsFilter;
    @FXML private Button delBtn;

    // Controls for Table AllTickets
    @FXML private static TableView<Ticket> allTicketsTable;
    @FXML private TableColumn allIdCol;
    @FXML private TableColumn<Department, String> allDepartmentCol;
    @FXML private TableColumn<Ticket, String> allNameCol;
    @FXML private TableColumn<Ticket, Date> allDateCol;
    @FXML private TableColumn<User, String> allUserCol;
    @FXML private TableColumn<User, String> allAgentCol;
    @FXML private TableColumn<Resource, String> allResourceCol;
    @FXML private ComboBox<TICKET_STATE> allTicketsFilter;
    
	public TicketController()
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
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<Department, String>("department"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Ticket, String>("name"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Ticket, Date>("date"));
        userCol.setCellValueFactory(new PropertyValueFactory<User, String>("user")); //Todo make simpleProperty or
        // Dont show User here?
        agentCol.setCellValueFactory(new PropertyValueFactory<User, String>("agent"));
        resourceCol.setCellValueFactory(new PropertyValueFactory<Resource, String>("resource"));

        // only delete ticket if is not user
        if (this.currentUser.getRole() == USER_ROLE.USER) {
            this.delBtn.setVisible(false);
        }

        // Fill ticketsFilter Combobox
        ticketsFilter.getItems().addAll(TICKET_STATE.values());
        ticketsFilter.getSelectionModel().selectFirst();

        // --------------------- Table All Tickets ------------------------
        // Fill Table with Data
        allIdCol.setCellValueFactory(new PropertyValueFactory("id"));
        allDepartmentCol.setCellValueFactory(new PropertyValueFactory<Department, String>("department"));
        allNameCol.setCellValueFactory(new PropertyValueFactory<Ticket, String>("name"));
        allDateCol.setCellValueFactory(new PropertyValueFactory<Ticket, Date>("date"));
        allUserCol.setCellValueFactory(new PropertyValueFactory<User, String>("user"));
        //Todo make simpleProperty or dont show User here?
        allAgentCol.setCellValueFactory(new PropertyValueFactory<User, String>("agent"));
        allResourceCol.setCellValueFactory(new PropertyValueFactory<Resource, String>("resource"));

        // Fill ticketsFilter Combobox
        allTicketsFilter.getItems().addAll(TICKET_STATE.values());
        allTicketsFilter.getSelectionModel().selectFirst();

		this.refresh();
	}

	public void refresh()
	{
        if (this.currentUser.getRole() == USER_ROLE.USER) {
            ticketList = FXCollections.observableArrayList(bl.getAllByUser(currentUser, ticketsFilter.getValue()));
        } else {
            ticketList = FXCollections.observableArrayList(bl.getAllByAgent(currentUser, ticketsFilter.getValue()));
        }
        ticketTable.setItems(ticketList);
        allTicketList = FXCollections.observableArrayList(bl.getAllByStatus(allTicketsFilter.getValue()));
        allTicketsTable.setItems(allTicketList);
	}

    public static void softRefresh() {
        ticketTable.getColumns().get(0).setVisible(false);
        ticketTable.getColumns().get(0).setVisible(true);
        allTicketsTable.getColumns().get(0).setVisible(false);
        allTicketsTable.getColumns().get(0).setVisible(true);
    }

	public void deleteTicket(ActionEvent event)
	{
        Button sourceBtn = (Button) event.getSource();
        Ticket selectedTicket;

        if (sourceBtn.getId().equals("delBtn")) {
            selectedTicket = ticketTable.getSelectionModel().getSelectedItem();
            ticketList.remove(selectedTicket);
        } else {
            selectedTicket = allTicketsTable.getSelectionModel().getSelectedItem();
            allTicketList.remove(selectedTicket);
        }
		if (selectedTicket != null) {
			bl.delete(selectedTicket);
		}
	}

	public void editTicket(ActionEvent event)
	{
        this.refreshComboBoxes();

        Button sourceBtn = (Button) event.getSource();
        Ticket selectedTicket;

        if(sourceBtn.getId().equals("editBtn")) {
            selectedTicket = ticketTable.getSelectionModel().getSelectedItem();
        } else {
            selectedTicket = allTicketsTable.getSelectionModel().getSelectedItem();
        }

		if (selectedTicket != null) {
			TicketDetailController.fillTicket(selectedTicket);
			MainController.changeTabContent("ticketDetail");
		}
	}

    public void refreshComboBoxes() {
        TicketDetailController.departementCbox.setItems(FXCollections.observableArrayList(depBL.getAll()));
        TicketDetailController.agentCbox.setItems(FXCollections.observableArrayList(usersBL.getByUserRole(USER_ROLE.AGENT)));
        TicketDetailController.resourceCbox.setItems(FXCollections.observableArrayList(resBL.getAll()));

        TicketDetailController.departementCbox.getSelectionModel().selectFirst();
        TicketDetailController.agentCbox.getSelectionModel().selectFirst();
        TicketDetailController.resourceCbox.getSelectionModel().selectFirst();
    }
}
