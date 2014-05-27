package ch.jmanagr.bo;

import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.PasswordHash;
import ch.jmanagr.lib.USER_ROLE;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class User extends AbstractBusinessObject implements IUser, IAgent
{
	protected String firstname;
	protected String lastname;
	protected String username;
	protected String password;
	protected USER_ROLE role;
	protected Department department;


	public User(int id, String firstname, String lastname, String username, String password, USER_ROLE role,
	            Department department, boolean active, boolean deleted)
	{
		super(id, active, deleted);
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
		super(active, deleted);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setUsername(username);
		this.setPassword(password);
		this.setRole(role);
		this.setDepartment(department);
	}


	public User()
	{
		super();
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


	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{

		return password;
	}

	/**
	 * Hash the password and sets it
	 *
	 * @param password unhashed password
	 */
	public void setPassword(String password)
	{
		this.password = User.hashPassword(password);
	}

	public void setHashedPassword(String hash)
	{
		this.password = hash;
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


}
