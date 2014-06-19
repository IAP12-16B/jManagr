package ch.jmanagr.ui.resources;

import ch.jmanagr.bl.ResourcesBL;
import ch.jmanagr.bo.Resource;
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
		this.res = bl.getAll();
        treeView.setShowRoot(false);

        // set root
        TreeItem<Resource> rootItem = new TreeItem<>();
        treeView.setRoot(rootItem);

        // loop
        for(Resource r : res) {
            TreeItem<Resource> newItem = new TreeItem<>();
            newItem.setValue(r);

            // if is root item add it
            if(r.getParent() == null) {
                rootItem.getChildren().add(newItem);
            } else {
	            TreeItem<Resource> parent = this.findParentItem(r);
	            if (parent != null) {
		            parent.getChildren().add(newItem);
	            } else {
		            // todo what if its parent is not in list? here we need recursion again,
		            // I would suppose to add the items per level (e.g. first step add all root resources,
		            // second step add all children of that resource, etc...
	            }

            }
        }
	}

	public TreeItem<Resource> findNearestExistingParentItem(Resource r)
	{
		// if is not root, find root
		TreeItem<Resource> parent = this.findParentItem(r);
		if (parent != null) {
			return parent;
		}

		return this.findNearestExistingParentItem(r.getParent());
	}

	public TreeItem<Resource> findParentItem(Resource r) {
        TreeItem<Resource> rootItem = treeView.getRoot();

	    return this.findParentItem(r, rootItem);
    }

	public TreeItem<Resource> findParentItem(Resource r, TreeItem<Resource> rootItem)
	{

		// split this into 2 loops -> hopefully better performance
		for (TreeItem<Resource> treeItem : rootItem.getChildren()) {
			if (treeItem.getValue().equals(r.getParent())) {
				return treeItem; // return it, as soon as we have found it, so the loop gets not further executed
			}
		}

		// this loop gets only executed, when parentItem is not a direct child of rootItem
		for (TreeItem<Resource> treeItem : rootItem.getChildren()) {
			TreeItem<Resource> recursedTreeItem = this.findParentItem(r, treeItem);
			if (recursedTreeItem != null) {
				return recursedTreeItem;
			}
		}

		return null;
	}
}
