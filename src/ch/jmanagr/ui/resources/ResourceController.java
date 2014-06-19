package ch.jmanagr.ui.resources;

import ch.jmanagr.bl.ResourcesBL;
import ch.jmanagr.bo.Resource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

public class ResourceController implements Initializable
{

	private ResourcesBL bl;
	private ObservableList<Resource> res;

    @FXML
	private TreeView<Resource> treeView;

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
        for(Resource r : res) {
	        TreeItem<Resource> newItem = new TreeItem<>();
	        newItem.setValue(r);
	        rootItem.getChildren().add(newItem);

	        this.addChildItems(newItem);
        }
	}

	public void addChildItems(TreeItem<Resource> parentItem)
	{
		Resource resource = parentItem.getValue();

		for (Resource childResource : resource.getChildren()) {
			TreeItem<Resource> childItem = new TreeItem<>();
			childItem.setValue(childResource);

			parentItem.getChildren().add(childItem);

			this.addChildItems(childItem); // recusrion
		}

	}
}
