package ch.jmanagr.ui.users;

import ch.jmanagr.bl.Users;
import ch.jmanagr.bo.User;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.ui.main.MainController;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ResourceBundle;

public class UsersController implements Initializable
{
	private ObservableList<User> userList;
	private Users bl = Users.getInstance();

	@FXML
    private TableView<User> ticketTable;
	@FXML
	private TableColumn idCol;
	@FXML
	private TableColumn<User, String> nameCol;

	@FXML
	private TextField nameField;

	// Fill Table with Data
	public void initialize(URL location, ResourceBundle resources)
	{

		idCol.setCellValueFactory(new PropertyValueFactory("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<User, String>("name"));

		// makes nameCol to a textField
		nameCol.setCellFactory(TextFieldTableCell.<User>forTableColumn());

		// sets the new Value after enterPressed in the ObserverList
		nameCol.setOnEditCommit(
				new EventHandler<TableColumn.CellEditEvent<User, String>>()
				{
					public void handle(TableColumn.CellEditEvent<User, String> t)
					{

						User user = t.getTableView().getItems().get(
								t.getTablePosition()
								 .getRow()
						); //get changed object
						user.setLastname(t.getNewValue()); // set changed value
						bl.save(user);
						Logger.log("Updated in table ticket: " + t.getNewValue() + " " + user.getId());
					}
				}
		);
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
