package ch.jManagr.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;



public class jManagrTableView {
    @FXML private TableView tableViewTable;
    @FXML private Label tableViewTitle;
    @FXML private ToolBar tableViewTabBar;

    public jManagrTableView() {
        System.out.println("jManagrTableView loaded");
    }
}
