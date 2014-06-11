package ch.jmanagr.ui.departments;

import ch.jmanagr.bl.Departments;
import ch.jmanagr.bo.Department;
import ch.jmanagr.bo.User;
import ch.jmanagr.lib.Logger;
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
public class DepartmentController implements Initializable
{

	private ObservableList<Department> depList;
	private Departments bl = Departments.getInstance();

	@FXML
	private TableView<Department> depTable;
	@FXML
	private TableColumn idCol;
	@FXML
	private TableColumn<Department, String> nameCol;

	@FXML
	private TextField nameField;

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

						// Todo Wenn bim umbenenne nüt gänderet wir, gits e NullPointerException.... suscht (wenn
						// öbbis gänderet worde isch) funktioniert alles ... wieso? @kije @mnewmedia
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
		this.depList = bl.getAll();
		this.depTable.setItems(this.depList);
		Logger.logln("Refreshed list!");
	}

	public void addDep()
	{
		// TODO: @mnewmedia mach do das es funktioniert xD cha susch nid teste xD hahhah..... okee vilivht sette mir
		// eh mol Unit Tests schribe xP
		// Change Name to changed of a departement
		/*Department dep = this.depList.get(2);
		dep.setName("changed");
		Department dep2 = this.depList.get(2);
		Logger.logln(dep2.getName());*/


		// Add Deparment
		Department dep = new Department(); // @mnewmedia ab jetzt immer so neui instanze vo BO's mache.
		dep.setName(nameField.getText());
		dep.setAgents(new ArrayList<User>());
		dep.setActive(true);
		dep.setDeleted(false);
		Logger.logln(dep); // logger can log BusinessObjects :D
		bl.save(dep);
		this.refresh();
		Logger.logln("Insertet new Department: " + nameField.getText());

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
