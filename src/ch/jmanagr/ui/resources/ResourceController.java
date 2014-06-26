package ch.jmanagr.ui.resources;

import ch.jmanagr.bl.ResourcesBL;
import ch.jmanagr.bo.Resource;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ResourceBundle;

public class ResourceController implements Initializable
{

	@FXML
	public TableView<Resource.ResourceData> dataTable;
	@FXML
	public TextField newDataFld;
	private ResourcesBL bl;
	private ObservableList<Resource> res;

	@FXML private TreeView<Resource> treeView;
	@FXML private TextField renameFld;
    @FXML private TextField newFld;
	@FXML
	public TableColumn<Resource.ResourceData, String> keyCol;
	@FXML
	public TableColumn<Resource.ResourceData, String> valueCol;

	public ResourceController()
	{
		this.bl = ResourcesBL.getInstance();
	}

	public void initialize(URL location, ResourceBundle resources)
	{
		// setup
		this.res = FXCollections.observableArrayList(bl.getAllRootResources());
		treeView.setShowRoot(false);

		// set root
		TreeItem<Resource> rootItem = new TreeItem<>();
		treeView.setRoot(rootItem);

		// loop
		for (Resource r : res) {
			TreeItem<Resource> newItem = new TreeItem<>();
			newItem.setValue(r);
			rootItem.getChildren().add(newItem);

			this.addChildItems(newItem);
		}

		// listen for selection
		treeView.getSelectionModel().selectedItemProperty().addListener(new TreeSelectionListener());

		keyCol.setCellValueFactory(new PropertyValueFactory<Resource.ResourceData, String>("key"));
		valueCol.setCellValueFactory(new PropertyValueFactory<Resource.ResourceData, String>("value"));

		// makes cols to a textField
		keyCol.setCellFactory(TextFieldTableCell.<Resource.ResourceData>forTableColumn());
		valueCol.setCellFactory(TextFieldTableCell.<Resource.ResourceData>forTableColumn());

		// sets the new Value after enterPressed in the ObserverList
		keyCol.setOnEditCommit(
				new DataTableEditEventHandler()
		);
		valueCol.setOnEditCommit(
				new DataTableEditEventHandler()
		);
	}

	public void add()
	{
		TreeItem<Resource> parentItem = treeView.getSelectionModel().getSelectedItem();

		if (parentItem == null) {
			parentItem = treeView.getRoot();
		}

		Resource parent = parentItem.getValue();


		TreeItem<Resource> newTreeItem = new TreeItem<Resource>();
        Resource r = new Resource();
        r.setName(newFld.getText());
        r.setParent(parent);
        bl.save(r);
        newTreeItem.setValue(r);
        parentItem.getChildren().add(newTreeItem);
		parentItem.setExpanded(true);
        newFld.setText("");
	}

	public void addData()
	{
		TreeItem<Resource> selectedItem = treeView.getSelectionModel().getSelectedItem();

		if (selectedItem != null) {
			Resource value = selectedItem.getValue();

			if (dataTable.getItems() == null) {
				dataTable.setItems(FXCollections.observableArrayList(value.getData()));
			}

			Resource.ResourceData resourceData = new Resource.ResourceData(value, newDataFld.getText(), "");

			if (value.containsData(resourceData.getKey())) {
				value.addData(resourceData);
				dataTable.getItems().add(resourceData);
				this.bl.save(value);
			}
		}
	}

	public void rename()
	{
        TreeItem<Resource> currentTreeItem = treeView.getSelectionModel().getSelectedItem();
        Resource currentResource = currentTreeItem.getValue();

        currentResource.setName(renameFld.getText());
		bl.save(currentResource);
		currentTreeItem.setValue(currentResource);

		currentTreeItem.setExpanded(true);
		currentTreeItem.setExpanded(false);

        renameFld.setText("");
	}

	public void addChildItems(TreeItem<Resource> parentItem)
	{
		Resource resource = parentItem.getValue();

		for (Resource childResource : resource.getChildren()) {
			TreeItem<Resource> childItem = new TreeItem<>();
			childItem.setValue(childResource);

			parentItem.getChildren().add(childItem);


			this.addChildItems(childItem); // recursion
		}
	}


	// chnangelistener
	public class TreeSelectionListener implements ChangeListener<TreeItem<Resource>>
	{
		@Override
		public void changed(ObservableValue<? extends TreeItem<Resource>> observableValue,
		                    TreeItem<Resource> oldValue, TreeItem<Resource> selectedItem)
		{
			if (selectedItem != null && selectedItem.getValue() != null && selectedItem.getValue().getData() != null) {
				dataTable.setItems(
						FXCollections.observableArrayList(
								selectedItem.getValue().getData()
						)
				);
			} else {
				dataTable.setItems(null);
			}
		}
	}

	public class DataTableEditEventHandler
			implements EventHandler<TableColumn.CellEditEvent<Resource.ResourceData, String>>
	{

		public void handle(TableColumn.CellEditEvent<Resource.ResourceData, String> t)
		{
			Resource.ResourceData resData = t.getRowValue();

			if (t.getTablePosition().getTableColumn().equals(keyCol)) {
				if (!resData.getResource().containsData(t.getNewValue())) {
					resData.setKey(t.getNewValue());
				}
			} else if (t.getTablePosition().getTableColumn().equals(valueCol)) {
				resData.setValue(t.getNewValue());
			}

			bl.save(resData.getResource());
		}
	}
}