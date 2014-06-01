package ch.jmanagr.ui.tickets;

import ch.jmanagr.bl.Tickets;
import ch.jmanagr.bo.*;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.TICKET_STATE;
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

	public void newTicket() //pass actionEvent?
	{
        MainController.changeTabContent("ticketDetail");
        /*
		*/
	}

	// Todo only archiv and only for admin?
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
