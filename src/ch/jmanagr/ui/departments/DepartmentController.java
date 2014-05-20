package ch.jmanagr.ui.departments;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ResourceBundle;

import ch.jmanagr.bl.Departments;
import ch.jmanagr.bo.Department;

// ---------------- http://docs.oracle.com/javafx/2/ui_controls/table-view.htm --------- explanation of the TableControl!
public class DepartmentController implements Initializable {

    private ObservableList<Department> depList;
    private Departments bl = Departments.getInstance();

    @FXML private TableView<Department> depTable;
    @FXML private TableColumn idCol;
    @FXML private TableColumn<Department, String> nameCol;

    @FXML private Button addBtn;
    @FXML private TextField nameField;

    // Fill Table with Data
    public void initialize(URL location, ResourceBundle resources) {
        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Department, String>("name"));

        // makes nameCol to a textField
        nameCol.setCellFactory(TextFieldTableCell.<Department>forTableColumn());

        // sets the new Value after enterPressed in the ObserverList
        nameCol.setOnEditCommit(
                new EventHandler<CellEditEvent<Department, String>>() {
                    public void handle(CellEditEvent<Department, String> t) {
                        (t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
                        System.out.println("CellEditEvent: " + t.getNewValue());
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

        //depTable.getItems().setAll(bl.getAll());
        depList = bl.getAll();
        depTable.setItems(depList); // todo dont fetch direct because this stops obserablelist working
    }

    // Add new Data
    public void addDep() {
        System.out.println("Inerted new Department: " + nameField.getText());
    }
}
