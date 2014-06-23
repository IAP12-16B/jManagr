package ch.jmanagr.ui.users;

import ch.jmanagr.bl.DepartmentsBL;
import ch.jmanagr.bl.UsersBL;
import ch.jmanagr.bo.Department;
import ch.jmanagr.bo.User;
import ch.jmanagr.exceptions.jManagrDBException;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.USER_ROLE;
import ch.jmanagr.ui.main.MainController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserDetailController implements Initializable
{
	private UsersBL bl;
	private DepartmentsBL depBl;
	@FXML private static TextField lastnameFld;
	@FXML private static TextField firstnameFld;
	@FXML private static TextField usernameFld;
	@FXML private static TextField passwordFld;
	@FXML private static TextField password2Fld;
	@FXML private Label userDetailErrorLbl;
	@FXML public static ComboBox<Department> departementCbox;
	@FXML private static ComboBox<USER_ROLE> roleCbox;

	@FXML private AnchorPane userDetailView;

	private static User updateCurrUser;
	private static int updateCurrIndex;

	public UserDetailController()
	{
		try {
			this.bl = UsersBL.getInstance();
		} catch (jManagrDBException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}
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
		departementCbox.setItems(FXCollections.observableArrayList(depBl.getAll()));
		departementCbox.getSelectionModel().selectFirst();

		// hide error lbl
		this.userDetailErrorLbl.setVisible(false);
	}

	public static void fillUser(User editingUser, int index)
	{
		updateCurrUser = editingUser;
		if (updateCurrUser != null) {
			lastnameFld.setText(updateCurrUser.getLastname());
			firstnameFld.setText(updateCurrUser.getFirstname());
			usernameFld.setText(updateCurrUser.getUsername());
			departementCbox.getSelectionModel().select(updateCurrUser.getDepartment());
			roleCbox.getSelectionModel().select(updateCurrUser.getRole());
		}
		updateCurrIndex = index;
	}

	public boolean validationPassed()
	{
		this.userDetailErrorLbl.setVisible(true);
		if (usernameFld.getText().isEmpty()) {
			this.userDetailErrorLbl.setText("Benutzername darf nicht leer sein!");
			return false;
		} else if (passwordFld.getText().isEmpty() && (updateCurrUser == null)) {
			this.userDetailErrorLbl.setText("Passwort darf nicht leer sein!");
			return false;
		} else if (!passwordFld.getText().equals(password2Fld.getText())) {
			this.userDetailErrorLbl.setText("Passwörter müssen übereinstimmen!");
			return false;
		} else {
			this.userDetailErrorLbl.setVisible(false);
			return true;
		}
	}

	public void saveUser()
	{
		if (validationPassed()) {

			Department d = departementCbox.getSelectionModel().getSelectedItem();
			USER_ROLE r = roleCbox.getSelectionModel().getSelectedItem();

			// if new user
			if (updateCurrUser == null) {
				User user = new User();
				user.setLastname(lastnameFld.getText());
				user.setFirstname(firstnameFld.getText());
				user.setUsername(usernameFld.getText());
				user.setUnhashedPassword(passwordFld.getText());
				user.setDepartment(d);
				user.setRole(r);

				//save
				bl.save(user);
				UsersController.userList.add(user);
			} else { //if update existing user
				// set all edited stuff
				updateCurrUser.setLastname(lastnameFld.getText());
				updateCurrUser.setFirstname(firstnameFld.getText());
				updateCurrUser.setUsername(usernameFld.getText());
				updateCurrUser.setUnhashedPassword(password2Fld.getText());
				updateCurrUser.setDepartment(departementCbox.getValue());
                Logger.logln("Dep: " + d);
				updateCurrUser.setRole(r);

				// save
				bl.save(updateCurrUser);
				UsersController.userList.set(updateCurrIndex, updateCurrUser);
                UsersController.softRefresh();
			}
			this.clearFields();
			MainController.changeTabContent("users");
		}
	}


	public void cancelUser()
	{
		MainController.changeTabContent("users");
		this.clearFields();
	}

	public void clearFields()
	{
		updateCurrUser = null;
		lastnameFld.setText("");
		firstnameFld.setText("");
		usernameFld.setText("");
		password2Fld.setText("");
		passwordFld.setText("");
		departementCbox.getSelectionModel().selectFirst();
		roleCbox.getSelectionModel().selectFirst();
	}

}
