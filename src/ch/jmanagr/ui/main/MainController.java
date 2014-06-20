package ch.jmanagr.ui.main;

import ch.jmanagr.bl.UsersBL;
import ch.jmanagr.bo.User;
import ch.jmanagr.exceptions.jManagrDBException;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.USER_ROLE;
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
	@FXML
	private static AnchorPane ticketView;
	@FXML
	private static AnchorPane ticketDetailView;
	@FXML
	private static AnchorPane userView;
	@FXML
	private static AnchorPane userDetailView;
	@FXML
	private static Tab tabTickets;
	@FXML
	private static Tab tabUser;
    @FXML
    private static Tab tabDepartment;
	@FXML
	private static TabPane tabPane;

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
			tabPane.getTabs().remove(tabUser);
            tabPane.getTabs().remove(tabDepartment);
        }
	}

	public static void changeTabContent(String view)
	{
		if (view.equals("ticketDetail")) {
			tabTickets.setContent(ticketDetailView);
		} else if (view.equals("tickets")) {
			tabTickets.setContent(ticketView);
		} else if (view.equals("users")) {
			tabUser.setContent(userView);
		} else if (view.equals("userDetail")) {
			tabUser.setContent(userDetailView);
		}
	}

}