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

import java.net.URL;
import java.util.ResourceBundle;

public class ResourceController implements Initializable
{

	private ResourcesBL bl;
	private ObservableList<Resource> res;

	@FXML
	private TreeView<Resource> treeView;
	@FXML
	private TextField renameFld;

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

       /* treeView.setEditable(true);
        treeView.setCellFactory(new Callback<TreeView<Resource>, TreeCell<Resource>>() {
            @Override
            public TreeCell<Resource> call(TreeView<Resource> resourceTreeView) {
                return new TextFieldTreeCellImpl();
            }
        });

        treeView.setOnEditCommit(new EventHandler<TreeView.EditEvent<String>>() {
            @Override
            public void handle(TreeView.EditEvent<Resource> resourceEditEvent) {
                resourceEditEvent.getTreeItem().getValue().setName(resourceEditEvent.getNewValue());
            }
        });*/

	}

    public void add() {

    }
    public void rename() {

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
   /* private final class TextFieldTreeCellImpl extends TreeCell<Resource> {

        private TextField textField;

        public TextFieldTreeCellImpl() {
        }

        @Override
        public void startEdit() {
            super.startEdit();

            if (textField == null) {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            //setText((String) getItem());
            setText(getItem().getName());
            setGraphic(getTreeItem().getGraphic());
        }

        @Override
        //public void updateItem(String item, boolean empty) {
        public void updateItem(Resource item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(textField.getText());
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }*/
}