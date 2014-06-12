package ch.jmanagr.ui.users;

import ch.jmanagr.bl.DepartmentsBL;
import ch.jmanagr.bl.UsersBL;
import ch.jmanagr.bo.Department;
import ch.jmanagr.bo.User;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.USER_ROLE;
import ch.jmanagr.ui.main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
	private ComboBox departementCbox;
	@FXML
	private ComboBox roleCbox;

	public UserDetailController()
	{
		// @mnewmedia Use constructor to set BL instances -> This way, DB connection etc... only gets established when
		// the controller is instantiated
		this.bl = UsersBL.getInstance();
		this.depBl = DepartmentsBL.getInstance();
	}

	public void initialize(URL location, ResourceBundle resources)
	{

		// Fill UserRoles Combobox
		for (USER_ROLE r : USER_ROLE.values()) {
			roleCbox.getItems().add(r.getName());
		}
		roleCbox.getSelectionModel().selectFirst();

		// Fill Departement Combobox
		departementCbox.setItems(depBl.getAll()); // use ToString in order To Display nice name in ComboBox
		departementCbox.getSelectionModel().selectFirst();
	}

	public void saveUser()
	{
		Department d = (Department) departementCbox.getSelectionModel().getSelectedItem();
		String roleName = (String) roleCbox.getSelectionModel().getSelectedItem();
		USER_ROLE r = USER_ROLE.fromString(roleName);

		User user = new User();
		user.setLastname(lastnameFld.getText());
		user.setFirstname(firstnameFld.getText());
		user.setUsername(usernameFld.getText());
		user.setUnhashedPassword(passwordFld.getText());
		user.setDepartment(d); //Todo Super High Priority: @kije was für e fehler isch das? @mnewmedia ????
		user.setRole(USER_ROLE.USER);
		bl.save(user);

		Logger.logln("Insertet new User: ");
		MainController.changeTabContent("users");
	}

	public void cancelUser()
	{
		MainController.changeTabContent("users");
	}

}
