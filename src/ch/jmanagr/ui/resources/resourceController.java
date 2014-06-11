package ch.jmanagr.ui.resources;

import ch.jmanagr.bl.Resources;
import ch.jmanagr.bo.Resource;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

public class resourceController implements Initializable {

    private Resources bl;
    private ObservableList<Resource> res;
    private TreeItem<Resource> parentItem;

    @FXML private TreeView<Resource> treeView;

    public void initialize(URL location, ResourceBundle resources)
    {
        //this.res = bl.getAll();

        /* for(Resource r : o) {
            TreeItem<Resource> newItem = new TreeItem<>();
            newItem.setValue(r);

            if(r.getParent() == null) {
                treeView.setRoot(newItem);
            }
           // newItem.getChildren().add(r.getChildren().get(0));
        }*/
    }
}
