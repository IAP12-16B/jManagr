package ch.jmanagr.ui.main;

import ch.jmanagr.bl.DepartmentsBL;
import ch.jmanagr.bl.UsersBL;
import ch.jmanagr.bo.User;
import ch.jmanagr.exceptions.jManagrDBException;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.USER_ROLE;
import ch.jmanagr.ui.tickets.TicketDetailController;
import ch.jmanagr.ui.users.UserDetailController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable
{
	private static UsersBL usersBL;
	private static DepartmentsBL depBl;
    @FXML private static AnchorPane myTicketsView;
	@FXML private static AnchorPane ticketView;
	@FXML private static AnchorPane ticketDetailView;
	@FXML private static AnchorPane userView;
	@FXML private static AnchorPane userDetailView;
	@FXML private static Tab tabTickets;
    @FXML private static Tab tabMyTickets;
	@FXML private static Tab tabUser;
	@FXML private static Tab tabDepartment;
    @FXML private static Tab tabResource;
	@FXML private static TabPane tabPane;

	public MainController()
	{
		depBl = DepartmentsBL.getInstance();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle)
	{
		removeTabsByUserRole();
	}

	public static void removeTabsByUserRole()
	{
		User user = null;
		try {
			user = UsersBL.getInstance().getCurrentUser();
		} catch (jManagrDBException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}
		if (user.getRole() == USER_ROLE.USER) {
            tabPane.getTabs().remove(tabTickets);
			tabPane.getTabs().remove(tabUser);
			tabPane.getTabs().remove(tabDepartment);
		}
	}

	public static void changeTabContent(String view)
	{
		switch (view) {
			case "ticketDetail": {
				tabTickets.setContent(ticketDetailView);
				break;
			}
			case "tickets": {
				tabTickets.setContent(ticketView);
				break;
			}
            case "myTicketsView": {
                tabMyTickets.setContent(myTicketsView);
                break;
            }
			case "users": {
				tabUser.setContent(userView);
				break;
			}
			case "userDetail": {
				tabUser.setContent(userDetailView);
				break;
			}
		}
	}
    public static void changeTabContent(String view, boolean inMyTicketsTab)
    {
        switch (view) {
            case "ticketDetail": {
                tabMyTickets.setContent(ticketDetailView);
                break;
            }
            case "tickets": {
                tabMyTickets.setContent(myTicketsView);
                break;
            }
        }
    }
}