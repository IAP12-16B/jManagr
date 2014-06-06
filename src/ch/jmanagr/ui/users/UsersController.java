package ch.jmanagr.ui.users;

import ch.jmanagr.bl.Users;
import ch.jmanagr.bo.User;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.USER_ROLE;
import ch.jmanagr.ui.main.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class UsersController implements Initializable
{
	private ObservableList<User> userList;
	private Users bl = Users.getInstance();

	@FXML private TableView<User> ticketTable;
	@FXML private TableColumn idCol;
	@FXML private TableColumn<User, String> lastnameCol;
    @FXML private TableColumn<User, String> firstnameCol;
    @FXML private TableColumn<User, String> usernameCol;
    @FXML private TableColumn<User, String> departmentCol;
    @FXML private TableColumn<User, String> roleCol;

	@FXML private TextField nameField;

	// Fill Table with Data
	public void initialize(URL location, ResourceBundle resources)
	{

		idCol.setCellValueFactory(new PropertyValueFactory("id"));
		lastnameCol.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<User, String>("department"));
        roleCol.setCellValueFactory(new PropertyValueFactory("role"));

       /* roleCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> a) {
                USER_ROLE b = a.getValue().getRole();
                SimpleStringProperty c = new SimpleStringProperty(b.getName());
                return c;
            }
        });
        */
		this.refresh();
	}

	public void refresh()
	{
		this.userList = bl.getAll();
		this.ticketTable.setItems(this.userList);
		Logger.logln("Refreshed list!");
	}

	public void newUser() //pass actionEvent?
	{
        MainController.changeTabContent("userDetail");
	}

	// Todo only archiv and only for admin?
	public void deleteUser()
	{/*
	    Ticket ticket = this.ticketTable.getSelectionModel().getSelectedItem();
        if (ticket != null) {
            Logger.log("Deleting ticket:" + ticket.getName() + " " + ticket.getId());
            bl.delete(ticket);
        } else {
            Logger.log("Nothing selected to delete");
        }*/
	}
}
