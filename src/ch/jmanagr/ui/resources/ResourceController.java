package ch.jmanagr.ui.resources;

import ch.jmanagr.bl.ResourcesBL;
import ch.jmanagr.bo.Resource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import sun.reflect.generics.tree.Tree;

import java.net.URL;
import java.util.ResourceBundle;

public class ResourceController implements Initializable
{

	private ResourcesBL bl;
	private ObservableList<Resource> res;

	@FXML private TreeView<Resource> treeView;
	@FXML private TextField renameFld;
    @FXML private TextField newFld;

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
	}

	public void add()
	{
        /*Resource parent = treeView.getSelectionModel().getSelectedItem().getValue();
        TreeItem parentItem = treeView.getSelectionModel().getSelectedItem();

        TreeItem<Resource> newTreeItem = new TreeItem<Resource>();
        Resource r = new Resource();
        r.setName(newFld.getText());
        r.setParent(parent);
        newTreeItem.setValue(r);
        parentItem.getChildren().add(newTreeItem);*/
	}

	public void rename()
	{
        TreeItem<Resource> currentTreeItem = treeView.getSelectionModel().getSelectedItem();
        Resource currentResource = currentTreeItem.getValue();
        //int index = treeView.getSelectionModel().getSelectedIndex();

        currentResource.setName(renameFld.getText());
        //currentTreeItem.setValue(currentResource);

        //treeView.getRoot().getChildren().add(index, currentTreeItem);
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
}