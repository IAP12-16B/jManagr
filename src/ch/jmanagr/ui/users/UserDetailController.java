package ch.jmanagr.ui.users;

import ch.jmanagr.bl.DepartmentsBL;
import ch.jmanagr.bl.UsersBL;
import ch.jmanagr.bo.Department;
import ch.jmanagr.bo.User;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.USER_ROLE;
import ch.jmanagr.ui.main.MainController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDetailController implements Initializable
{
	private UsersBL bl;
	private DepartmentsBL depBl;
	@FXML
	private TextField lastnameFld;
	@FXML
	private TextField firstnameFld;
	@FXML
	private TextField usernameFld;
	@FXML
	private TextField passwordFld;
	@FXML
	private TextField password2Fld;
	@FXML
	private ComboBox<Department> departementCbox;
	@FXML
	private ComboBox<USER_ROLE> roleCbox;

	@FXML
	private AnchorPane userDetailView;

	public UserDetailController()
	{
		this.bl = UsersBL.getInstance();
		this.depBl = DepartmentsBL.getInstance();
	}

	public void initialize(URL location, ResourceBundle resources)
	{
		// Fill UserRoles Combobox
		for (USER_ROLE r : USER_ROLE.values()) {
			roleCbox.getItems().add(r);
		}
		roleCbox.getSelectionModel().selectFirst();

		// Fill Departement Combobox
		departementCbox.setItems(
				FXCollections.observableArrayList(depBl.getAll())
		); // use ToString in order To Display nice name in ComboBox
		departementCbox.getSelectionModel().selectFirst();

		this.setData();

	}

	protected void setData()
	{
		User data = (User) userDetailView.getUserData();
		if (data != null) {
			firstnameFld.setText(data.getFirstname());
			lastnameFld.setText(data.getLastname());
			usernameFld.setText(data.getUsername());
			departementCbox.setValue(data.getDepartment());
			roleCbox.setValue(data.getRole());
		} else {
			firstnameFld.setText(null);
			lastnameFld.setText(null);
			usernameFld.setText(null);
			departementCbox.setValue(null);
			roleCbox.setValue(null);
		}
	}

	public void saveUser()
	{
		// todo save user when edit user
		if (!this.usernameFld.getText().isEmpty() ||
		    !(this.passwordFld.getText().equals(this.password2Fld.getText()))) {
			Department d = departementCbox.getSelectionModel().getSelectedItem();
			USER_ROLE r = roleCbox.getSelectionModel().getSelectedItem();

			User user = new User();
			user.setLastname(lastnameFld.getText());
			user.setFirstname(firstnameFld.getText());
			user.setUsername(usernameFld.getText());
			user.setUnhashedPassword(passwordFld.getText());
			user.setDepartment(d);
			user.setRole(r);
			bl.save(user);
			UsersController.userList.add(user);

			Logger.logln("Inserted new User: ");
			MainController.changeTabContent("users");
		} else {
			// Todo warn that pws are not equal or username not set
		}

	}

	public void cancelUser()
	{
		MainController.changeTabContent("users");
	}


}
