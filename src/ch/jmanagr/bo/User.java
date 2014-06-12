package ch.jmanagr.bo;

import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.PasswordHash;
import ch.jmanagr.lib.USER_ROLE;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@DatabaseTable(tableName = "User")
public class User implements BusinessObject<User>
{
	@DatabaseField(useGetSet = true, generatedId = true, allowGeneratedIdInsert = true)
	private Integer id;

	protected SimpleIntegerProperty idProperty;

	@DatabaseField(defaultValue = "true", useGetSet = true)
	protected boolean active;

	@DatabaseField(defaultValue = "false", useGetSet = true)
	protected boolean deleted;

	@DatabaseField(version = true, useGetSet = true)
	protected Integer version;


	@DatabaseField(useGetSet = true, canBeNull = true)
	protected String firstname;

	protected SimpleStringProperty firstnameProperty;

	@DatabaseField(useGetSet = true, canBeNull = true)
	private String lastname;

	protected SimpleStringProperty lastnameProperty;

	@DatabaseField(useGetSet = true, canBeNull = false)
	private String username;

	protected SimpleStringProperty usernameProperty;

	@DatabaseField(useGetSet = true, canBeNull = false, dataType = DataType.STRING, width = 160)
	private String password;

	protected SimpleStringProperty passwordProperty;

	@DatabaseField(useGetSet = true, defaultValue = "USER", unknownEnumName = "USER")
	protected USER_ROLE role;

	@DatabaseField(useGetSet = true,
	               canBeNull = true,
	               foreign = true,
	               foreignAutoCreate = true,
	               foreignAutoRefresh = true)
	protected Department department;


	public User()
	{
		this.initProperties();
	}

	public User(int id)
	{
		this.initProperties();
		this.setId(id);
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

	protected void initProperties()
	{
		this.idProperty = new SimpleIntegerProperty();
		this.firstnameProperty = new SimpleStringProperty();
		this.lastnameProperty = new SimpleStringProperty();
		this.usernameProperty = new SimpleStringProperty();
		this.passwordProperty = new SimpleStringProperty();
	}

	public String getFirstname()
	{
		return firstnameProperty.get();
	}

	public void setFirstname(String firstnameProperty)
	{
		this.firstnameProperty.set(firstnameProperty);
	}

	public String getLastname()
	{
		return lastnameProperty.get();
	}

	public void setLastname(String lastname)
	{
		this.lastnameProperty.set(lastname);
	}

	public String getUsername()
	{
		return usernameProperty.get();
	}

	public void setUsername(String username)
	{
		this.usernameProperty.set(username);
	}

	public String getPassword()
	{

		return passwordProperty.get();
	}

	/**
	 * Hash the password and sets it
	 *
	 * @param password unhashed password
	 */
	public void setUnhashedPassword(String password)
	{
		this.passwordProperty.set(User.hashPassword(password));
	}

	public void setPassword(String hash)
	{
		this.passwordProperty.set(hash);
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

	public SimpleStringProperty firstnameProperty()
	{
		return this.firstnameProperty;
	}

	public SimpleStringProperty lastnameProperty()
	{
		return this.lastnameProperty;
	}

	public SimpleStringProperty usernameProperty()
	{
		return this.usernameProperty;
	}

	public SimpleStringProperty passwordProperty()
	{
		return this.passwordProperty;
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
		if (firstnameProperty != null ?
		    !firstnameProperty.equals(user.firstnameProperty) :
		    user.firstnameProperty != null) { return false; }
		if (idProperty != null ? !idProperty.equals(user.idProperty) : user.idProperty != null) { return false; }
		if (lastnameProperty != null ?
		    !lastnameProperty.equals(user.lastnameProperty) :
		    user.lastnameProperty != null) { return false; }
		if (passwordProperty != null ?
		    !passwordProperty.equals(user.passwordProperty) :
		    user.passwordProperty != null) { return false; }
		if (role != user.role) { return false; }
		if (usernameProperty != null ?
		    !usernameProperty.equals(user.usernameProperty) :
		    user.usernameProperty != null) { return false; }

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = idProperty != null ? idProperty.hashCode() : 0;
		result = 31 * result + (active ? 1 : 0);
		result = 31 * result + (deleted ? 1 : 0);
		result = 31 * result + (firstnameProperty != null ? firstnameProperty.hashCode() : 0);
		result = 31 * result + (lastnameProperty != null ? lastnameProperty.hashCode() : 0);
		result = 31 * result + (usernameProperty != null ? usernameProperty.hashCode() : 0);
		result = 31 * result + (passwordProperty != null ? passwordProperty.hashCode() : 0);
		result = 31 * result + (role != null ? role.hashCode() : 0);
		result = 31 * result + (department != null ? department.hashCode() : 0);
		return result;
	}

	public Integer getVersion()
	{
		return version;
	}

	public void setVersion(Integer version)
	{
		this.version = version;
	}

	@Override
	public boolean isActive()
	{
		return active;
	}

	@Override
	public Integer getId()

	{
		return idProperty.getValue();
	}

	@Override
	public void setId(Integer id)
	{
		this.idProperty.set(id);
	}

	public SimpleIntegerProperty idProperty()
	{
		return this.idProperty;
	}

	@Override
	public boolean getActive()
	{
		return active;
	}

	@Override
	public void setActive(boolean active)
	{
		this.active = active;
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
}
