package ch.jmanagr.ui.departments;

import ch.jmanagr.bl.DepartmentsBL;
import ch.jmanagr.bl.UsersBL;
import ch.jmanagr.bo.Department;
import ch.jmanagr.bo.User;
import ch.jmanagr.exceptions.jManagrDBException;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

// ---------------- http://docs.oracle.com/javafx/2/ui_controls/table-view.htm --------- explanation of the
// TableControl!

/**
 * Department Controller, controlls the Department view
 */
public class DepartmentController implements Initializable
{

	private ObservableList<Department> depList;
	private DepartmentsBL bl;
	private UsersBL usersBL;

	@FXML
	private TableView<Department> depTable;
	@FXML
	private TableColumn idCol;
	@FXML
	private TableColumn<Department, String> nameCol;

	@FXML
	private TextField nameField;

	public DepartmentController()
	{
		this.bl = DepartmentsBL.getInstance();
		try {
			usersBL = UsersBL.getInstance();
		} catch (jManagrDBException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}
	}

	// Fill Table with Data
	public void initialize(URL location, ResourceBundle resources)
	{
		idCol.setCellValueFactory(new PropertyValueFactory("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Department, String>("name"));

		// makes nameCol to a textField
		nameCol.setCellFactory(TextFieldTableCell.<Department>forTableColumn());

		// sets the new Value after enterPressed in the ObserverList
		nameCol.setOnEditCommit(
				new EventHandler<CellEditEvent<Department, String>>()
				{
					public void handle(CellEditEvent<Department, String> t)
					{
						Department dep = t.getTableView().getItems().get(
								t.getTablePosition()
								 .getRow()
						); //get changed object
						dep.setName(t.getNewValue()); // set changed value
						bl.save(dep);
						Logger.logln("Updated in table: " + t.getNewValue() + " " + dep.getId());
					}
				}
		);

		// Only checking if add or delete - so dont need it, but leave it for maybe later
		/*ObservableList<Department> b = bl.getAll();
		b.addListener(new ListChangeListener<Department>() {
            @Override
            public void onChanged(Change change) {
                System.out.println("Detected a change! ");
            }
        });*/
		this.refresh();
	}

	public void refresh()
	{
		this.depList = FXCollections.observableArrayList(bl.getAll());
		this.depTable.setItems(this.depList);
		Logger.logln("Refreshed list!");
	}

	public void addDep()
	{
		Department dep = new Department();
		dep.setName(nameField.getText());
		dep.setAgents(new ArrayList<User>());
		dep.setActive(true);
		dep.setDeleted(false);

		bl.save(dep);
		this.depList.add(dep);

		nameField.setText("");
		nameField.requestFocus();
	}

	public void deleteDep()
	{
		Department dep = this.depTable.getSelectionModel().getSelectedItem();
		if (dep != null) {
			Logger.logln("Deleting dep:" + dep.getName() + " " + dep.getId());
			bl.delete(dep);
			depList.remove(this.depTable.getSelectionModel().getSelectedIndex());
		} else {
			Logger.logln("Nothing selected to delete");
		}
	}
}
