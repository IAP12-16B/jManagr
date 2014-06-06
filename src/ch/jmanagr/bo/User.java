package ch.jmanagr.bo;

import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.PasswordHash;
import ch.jmanagr.lib.USER_ROLE;
import com.sun.istack.internal.NotNull;
import com.sun.javafx.beans.IDProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@IDProperty("id")
public class User implements BusinessObject<User>, IUser<User>, IAgent<User>
{
	protected SimpleIntegerProperty id;
	@NotNull
	protected boolean active;
	@NotNull
	protected boolean deleted;


	protected SimpleStringProperty firstname;
	protected SimpleStringProperty lastname;
	@NotNull
	protected SimpleStringProperty username;
	@NotNull
	protected SimpleStringProperty password;
	@NotNull
	protected USER_ROLE role;
	protected Department department;


	public User(int id, String firstname, String lastname, String username, String password, USER_ROLE role,
	            Department department, boolean active, boolean deleted)
	{
		this.initProperties();
		this.setId(id);
		this.setActive(active);
		this.setDeleted(deleted);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setUsername(username);
		this.setPassword(password);
		this.setRole(role);
		this.setDepartment(department);
	}

	public User(String firstname, String lastname, String username, String password, USER_ROLE role,
	            Department department, boolean active,
	            boolean deleted)
	{
		this.initProperties();
		this.setActive(active);
		this.setDeleted(deleted);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setUsername(username);
		this.setPassword(password);
		this.setRole(role);
		this.setDepartment(department);
	}

	public User(int id, String firstname, String lastname, String username, String password, USER_ROLE role,
	            boolean active, boolean deleted)
	{
		this.initProperties();
		this.setId(id);
		this.setActive(active);
		this.setDeleted(deleted);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setUsername(username);
		this.setPassword(password);
		this.setRole(role);
	}

	public User(String firstname, String lastname, String username, String password, USER_ROLE role, boolean active,
	            boolean deleted)
	{
		this.initProperties();
		this.setActive(active);
		this.setDeleted(deleted);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setUsername(username);
		this.setPassword(password);
		this.setRole(role);
	}


	public User()
	{
		this.initProperties();
	}

	/**
	 * Hashes a passwor, so it can be stored securely into the DB
	 *
	 * @param password the password to hash
	 *
	 * @return The hashed password + salt and iteration count
	 */
	public static String hashPassword(String password)
	{
		try {
			return PasswordHash.createHash(password);
		} catch (NoSuchAlgorithmException e) {
			Logger.log(LOG_LEVEL.WARNING, "Appropriate hash algorithm is missing!", e);
		} catch (InvalidKeySpecException e) {
			Logger.log(LOG_LEVEL.WARNING, "Wrong key spec!", e);
		}
		return null;
	}

	/**
	 * Checks a password again its hashed version from the db
	 *
	 * @param password The password in plain text
	 * @param hash     the hash from the DB
	 *
	 * @return true if the passwords match, false if not
	 */
	public static boolean checkPassword(String password, String hash)
	{
		try {
			return PasswordHash.validatePassword(password, hash);
		} catch (NoSuchAlgorithmException e) {
			Logger.log(LOG_LEVEL.WARNING, "Appropriate hash algorithm is missing!", e);
		} catch (InvalidKeySpecException e) {
			Logger.log(LOG_LEVEL.WARNING, "Wrong key spec!", e);
		}
		return false;
	}

	private void initProperties()
	{
		this.id = new SimpleIntegerProperty();
		this.firstname = new SimpleStringProperty();
		this.lastname = new SimpleStringProperty();
		this.username = new SimpleStringProperty();
		this.password = new SimpleStringProperty();
	}

	public String getFirstname()
	{
		return firstname.get();
	}

	public void setFirstname(String firstname)
	{
		this.firstname.set(firstname);
	}

	public String getLastname()
	{
		return lastname.get();
	}

	public void setLastname(String lastname)
	{
		this.lastname.set(lastname);
	}

	public String getUsername()
	{
		return username.get();
	}

	public void setUsername(String username)
	{
		this.username.set(username);
	}

	public String getPassword()
	{

		return password.get();
	}

	/**
	 * Hash the password and sets it
	 *
	 * @param password unhashed password
	 */
	public void setPassword(String password)
	{
		this.password.set(User.hashPassword(password));
	}

	public void setHashedPassword(String hash)
	{
		this.password.set(hash);
	}

	public USER_ROLE getRole()
	{
		return role;
	}

	public void setRole(USER_ROLE role)
	{
		this.role = role;
	}

	/**
	 * Validates the provided password against the hash of this user
	 *
	 * @param password the password (plain text)
	 *
	 * @return @see checkPassword()
	 */
	public boolean checkPassword(String password)
	{
		return User.checkPassword(password, this.getPassword());
	}

	public Department getDepartment()

	{
		return department;
	}

	public void setDepartment(Department department)
	{
		this.department = department;
	}


	@Override
	public boolean getActive()
	{
		return active;
	}

	@Override
	public boolean getDeleted()
	{
		return deleted;
	}

	@Override
	public boolean isDeleted()
	{
		return deleted;
	}

	@Override
	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
	}

	/**
	 * Copies the values from an other object
	 *
	 * @param bo
	 */
	@Override
	public void copyFromObject(User bo)
	{
		this.setId(bo.getId());
		this.setActive(bo.getActive());
		this.setDeleted(bo.getDeleted());
		this.setFirstname(bo.getFirstname());
		this.setLastname(bo.getLastname());
		this.setUsername(bo.getUsername());
		this.setPassword(bo.getPassword());
		this.setRole(bo.getRole());
		this.setDepartment(bo.getDepartment());
	}

	@Override
	public boolean isActive()
	{
		return active;
	}

	@Override
	public void setActive(boolean active)
	{
		this.active = active;
	}

	@Override
	public Integer getId()

	{
		return id.getValue();
	}

	@Override
	public void setId(int id)
	{
		this.id.set(id);
	}

	public SimpleIntegerProperty idProperty()
	{
		return this.id;
	}

	public SimpleStringProperty firstnameProperty()
	{
		return this.firstname;
	}

	public SimpleStringProperty lastnameProperty()
	{
		return this.lastname;
	}

	public SimpleStringProperty usernameProperty()
	{
		return this.username;
	}

	public SimpleStringProperty passwordProperty()
	{
		return this.password;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) { return true; }
		if (!(o instanceof User)) { return false; }

		User user = (User) o;

		if (active != user.active) { return false; }
		if (deleted != user.deleted) { return false; }
		if (department != null ? !department.equals(user.department) : user.department != null) { return false; }
		if (firstname != null ? !firstname.equals(user.firstname) : user.firstname != null) { return false; }
		if (id != null ? !id.equals(user.id) : user.id != null) { return false; }
		if (lastname != null ? !lastname.equals(user.lastname) : user.lastname != null) { return false; }
		if (password != null ? !password.equals(user.password) : user.password != null) { return false; }
		if (role != user.role) { return false; }
		if (username != null ? !username.equals(user.username) : user.username != null) { return false; }

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (active ? 1 : 0);
		result = 31 * result + (deleted ? 1 : 0);
		result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
		result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (role != null ? role.hashCode() : 0);
		result = 31 * result + (department != null ? department.hashCode() : 0);
		return result;
	}
}
