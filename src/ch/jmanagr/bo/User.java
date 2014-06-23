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
import java.util.Date;

/**
 * User BusinessObject
 */
@DatabaseTable(tableName = "User")
public class User implements BusinessObject<User>
{
	@DatabaseField(useGetSet = true, generatedId = true, allowGeneratedIdInsert = true, index = true)
	private Integer id;
	protected SimpleIntegerProperty idProperty;

	@DatabaseField(useGetSet = true, canBeNull = true)
	protected String firstname;
	protected SimpleStringProperty firstnameProperty;

	@DatabaseField(useGetSet = true, canBeNull = true)
	private String lastname;
	protected SimpleStringProperty lastnameProperty;

	@DatabaseField(useGetSet = true, canBeNull = false, uniqueIndex = true)
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


	@DatabaseField(useGetSet = true)
	protected boolean active = true;
	@DatabaseField(useGetSet = true, index = true)
	protected boolean deleted = false;
	@DatabaseField(version = true, useGetSet = true, dataType = DataType.DATE_LONG)
	protected Date version;


	public User()
	{
		this.initProperties();
	}

	/**
	 * @param id The id of the User
	 */
	public User(int id)
	{
		this.initProperties();
		this.setId(id);
	}

	/**
	 * Hashes a password, so it can be stored securely into the DB
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

	/**
	 * @return firstname
	 */
	public String getFirstname()
	{
		return firstnameProperty.get();
	}

	/**
	 * @param firstname firstname
	 */
	public void setFirstname(String firstname)
	{
		this.firstnameProperty.set(firstname);
	}

	/**
	 * @return lastname
	 */
	public String getLastname()
	{
		return lastnameProperty.get();
	}

	/**
	 * @param lastname lastname
	 */
	public void setLastname(String lastname)
	{
		this.lastnameProperty.set(lastname);
	}

	/**
	 * @return th username
	 */
	public String getUsername()
	{
		return usernameProperty.get();
	}

	/**
	 * Username MUST be unique
	 *
	 * @param username the username
	 */
	public void setUsername(String username)
	{
		this.usernameProperty.set(username);
	}

	/**
	 * @return hashed password
	 */
	public String getPassword()
	{

		return passwordProperty.get();
	}

	/**
	 * @param hash salted hash of the password
	 */
	public void setPassword(String hash)
	{
		this.passwordProperty.set(hash);
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

	/**
	 * @return the user's role
	 */
	public USER_ROLE getRole()
	{
		return role;
	}

	/**
	 * @param role role to set
	 */
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

	/**
	 * Only for Agents
	 *
	 * @return the Department
	 */
	public Department getDepartment()

	{
		return department;
	}

	/**
	 * Only for Agents
	 *
	 * @param department the Department
	 */
	public void setDepartment(Department department)
	{
		this.department = department;
	}

	/**
	 * Copies the values from an other object
	 *
	 * @param bo the User toc copy from
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

	/**
	 * First name property (required for JavaFX)
	 *
	 * @return the first name property
	 */
	public SimpleStringProperty firstnameProperty()
	{
		return this.firstnameProperty;
	}

	/**
	 * Last name property (required for JavaFX)
	 *
	 * @return the last name property
	 */
	public SimpleStringProperty lastnameProperty()
	{
		return this.lastnameProperty;
	}

	/**
	 * Username property (required for JavaFX)
	 *
	 * @return the username property
	 */
	public SimpleStringProperty usernameProperty()
	{
		return this.usernameProperty;
	}

	/**
	 * Password property (required for JavaFX)
	 *
	 * @return the password property
	 */
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
		return result;
	}

	/**
	 * @return the version
	 */
	@Override
	public Date getVersion()
	{
		return version;
	}

	/**
	 * Sets the version of this BusinessObject
	 *
	 * @param version the version
	 */
	@Override
	public void setVersion(Date version)
	{
		this.version = version;
	}

	/**
	 * Get the id of the BusinessObject
	 *
	 * @return the id
	 */
	@Override
	public Integer getId()

	{
		return idProperty.getValue();
	}

	/**
	 * Set ID
	 *
	 * @param id the id
	 */
	@Override
	public void setId(Integer id)
	{
		this.idProperty.set(id);
	}

	/**
	 * Id property (used by JavaFX)
	 *
	 * @return the id property
	 */
	public SimpleIntegerProperty idProperty()
	{
		return this.idProperty;
	}

	/**
	 * @return whether the object is active
	 */
	@Override
	public boolean getActive()
	{
		return active;
	}

	/**
	 * Sets the active flag of the object
	 *
	 * @param active active flag
	 */
	@Override
	public void setActive(boolean active)
	{
		this.active = active;
	}

	/**
	 * @return whether the object is deleted
	 */
	@Override
	public boolean getDeleted()
	{
		return deleted;
	}

	/**
	 * Sets the deleted flag of the object
	 *
	 * @param deleted deleted flag
	 */
	@Override
	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
	}

	/**
	 * Convert Object to string
	 *
	 * @return the name of the Object
	 */
	@Override
	public String toString()
	{
		return this.firstnameProperty.get() + " " + this.lastnameProperty.get();
	}
}
