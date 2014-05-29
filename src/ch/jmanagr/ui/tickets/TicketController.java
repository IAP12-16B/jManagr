package ch.jmanagr.ui.tickets;

import ch.jmanagr.bl.Tickets;
import ch.jmanagr.bo.*;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.TICKET_STATE;
import ch.jmanagr.lib.USER_ROLE;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class TicketController implements Initializable
{
	private ObservableList<Ticket> ticketList;
	private Tickets bl = Tickets.getInstance();

	@FXML
	private TableView<Ticket> ticketTable;
	@FXML
	private TableColumn idCol;
	@FXML
	private TableColumn<Ticket, String> nameCol;

	@FXML
	private TextField nameField;

	// Fill Table with Data
	public void initialize(URL location, ResourceBundle resources)
	{

		idCol.setCellValueFactory(new PropertyValueFactory("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Ticket, String>("name"));

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
		this.refresh();
	}

	public void refresh()
	{
		this.ticketList = bl.getAll();
		this.ticketTable.setItems(this.ticketList);
		Logger.logln("Refreshed list!");
	}

	public void newTicket()
	{
		//todo @pablo missing params for created objs, anyway wait for kim fixing this

		// Todo: @kije simplify this.... It should not be neccessary to first create a bunch of dummy objects before
		// you can save the main object itselfs
		Date d = new Date();
		Resource r = new Resource(
				"",
				new ArrayList<ResourceData>(),
				new ArrayList<Ticket>(),
				null,
				new ArrayList<Resource>(),
				true,
				false
		);

		Department de;
		ObservableList<Department> l = ch.jmanagr.bl.Departments.getInstance().getAll();
		if (!l.isEmpty()) {
			de = l.get(0);
		} else {
			de = new Department("", new ArrayList<User>(), true, false);
		}
		User u = new User(
				"",
				"",
				"" + Math.random(),
				"hkj",
				USER_ROLE.USER,
				de,
				true,
				false
		); //Users.getInstance().getCurrentUser();
		User agent = new User("", "", "", "hkj", USER_ROLE.AGENT, de, true, false);
		Ticket ticket = new Ticket("test", "", TICKET_STATE.OPEN, d, r, agent, de, u, true, false);
		bl.save(ticket);
        this.refresh();
		Logger.logln("Insertet new Department: ");
		//todo change view on newTicket()

	}

	// only archiv and only for admin?
	public void deleteDep()
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
