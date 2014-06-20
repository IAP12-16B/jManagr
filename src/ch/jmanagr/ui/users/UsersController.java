package ch.jmanagr.ui.users;

import ch.jmanagr.bl.UsersBL;
import ch.jmanagr.bo.User;
import ch.jmanagr.exceptions.jManagrDBException;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.USER_ROLE;
import ch.jmanagr.ui.main.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class UsersController implements Initializable
{
	public static ObservableList<User> userList;
	private UsersBL bl;

	@FXML
	private TableView<User> userTable;
	@FXML
	private TableColumn idCol;
	@FXML
	private TableColumn<User, String> lastnameCol;
	@FXML
	private TableColumn<User, String> firstnameCol;
	@FXML
	private TableColumn<User, String> usernameCol;
	@FXML
	private TableColumn<User, String> departmentCol;
	@FXML
	private TableColumn<User, String> roleCol;

	@FXML
	private Button editUsrBtn;

	@FXML
	private TextField nameField;

	public UsersController()
	{
		// @mnewmedia Use constructor to set BL instances -> This way, DB connection etc... only gets established when
		// the controller is instantiated
		try {
			this.bl = UsersBL.getInstance();
		} catch (jManagrDBException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}
	}

	// Fill Table with Data
	public void initialize(URL location, ResourceBundle resources)
	{

		idCol.setCellValueFactory(new PropertyValueFactory("id"));
		lastnameCol.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
		firstnameCol.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
		usernameCol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		departmentCol.setCellValueFactory(new PropertyValueFactory<User, String>("department"));
		roleCol.setCellValueFactory(new PropertyValueFactory("role"));

		roleCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>()
				{
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> a)
					{
						USER_ROLE b = a.getValue().getRole();
						SimpleStringProperty c = new SimpleStringProperty(b.toString());
						return c;
					}
				}
		);


		this.refresh();
	}

	public void refresh()
	{
		userList = FXCollections.observableArrayList(bl.getAll());
		this.userTable.setItems(userList);
		Logger.logln("Refreshed list!");
	}

	public void newUser() //pass actionEvent?
	{
		MainController.changeTabContent("userDetail");
	}

	public void deleteUser()
	{
		User user = this.userTable.getSelectionModel().getSelectedItem();
		if (user != null) {
			Logger.log("Deleting user:" + user.getFirstname() + " " + user.getId());
			bl.delete(user);
			userList.remove(user);
		} else {
			Logger.log("Nothing selected to delete");
		}
	}

	public void editUser()
	{
		User selectedUser = userTable.getSelectionModel().getSelectedItem();
		if (selectedUser != null) {
			int index = userTable.getSelectionModel().getSelectedIndex();
			UserDetailController.fillUser(selectedUser, index);
			MainController.changeTabContent("userDetail");
		}
	}
}
