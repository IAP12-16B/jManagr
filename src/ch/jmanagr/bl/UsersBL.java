package ch.jmanagr.bl;

import ch.jmanagr.bo.User;
import ch.jmanagr.dal.UsersDAL;
import ch.jmanagr.exceptions.jManagrDBException;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.STATUS_CODE;
import ch.jmanagr.lib.USER_ROLE;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UsersBL extends AbstractBL<User, UsersDAL>
{
	private static volatile UsersBL instance;

	protected User currentUser;

	protected UsersBL() throws jManagrDBException
	{
		super();

		try {
			dal = UsersDAL.getInstance();
		} catch (SQLException e) {
			throw new jManagrDBException(e);
		}
	}


	public static UsersBL getInstance() throws jManagrDBException
	{
		if (instance == null) {
			synchronized (UsersBL.class) {
				if (instance == null) {
					instance = new UsersBL();
				}
			}
		}
		return instance;
	}

	/**
	 * Authenticates a user. If successful, the returned user is set as current user.
	 *
	 * @param username The username
	 * @param password Password
	 *
	 * @return The logged in user if successful, null if unsuccessful
	 */
	public User login(String username, String password) throws jManagrDBException
	{
		HashMap<String, Object> map = new HashMap<>();
		map.put("username", username);
		map.put("deleted", 0);
		try {
			List<User> user = this.dal.fetch(map, 1);
			if (!user.isEmpty()) {
				User u = user.get(0);
				if (u.checkPassword(password)) {
					this.setCurrentUser(u);
					return u;
				}
			}
		} catch (SQLException e) {
			throw new jManagrDBException(e);
		}


		return null;
	}

	@Override
	public STATUS_CODE delete(User bo)
	{
		// prevent deleting yourself!
		if (bo.equals(this.getCurrentUser())) {
			return STATUS_CODE.FAIL;
		}

		return super.delete(bo);
	}

	/**
	 * Returns the current user.
	 *
	 * @return the current user. Null if no user is logged in.
	 */
	public User getCurrentUser()
	{
		return currentUser;
	}

	private void setCurrentUser(User u)
	{
		this.currentUser = u;
	}

	/**
	 * @param bo The BusinessObject to check
	 *
	 * @return
	 */
	@Override
	public STATUS_CODE validate(User bo)
	{
		if (!this.validateUsername(bo.getUsername())) {
			return STATUS_CODE.USERNAME_INVALID;
		}

		if (!this.validateName(bo.getFirstname()) || !this.validateName(bo.getLastname())) {
			return STATUS_CODE.NAME_INVALID;
		}


		try {
			if (this.dal.exists(bo)) {
				return STATUS_CODE.ALREADY_EXISTS;
			}
		} catch (SQLException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
			return STATUS_CODE.FAIL;
		}

		return STATUS_CODE.OK;
	}

	/**
	 * Checks the username. Username may only contain letters (lower- and uppercase), numbers, _, ., @,# and hyphens
	 *
	 * @param username The username
	 *
	 * @return true if the username passed the test, false if not
	 */
	private boolean validateUsername(String username)
	{
		return username.matches("[A-z0-9_.@#-]+");
	}

	/**
	 * Checks the name. Name may only contain letters  (lower- and uppercase), numbers, umlauts, space, «, », ─ and
	 * hyphens
	 *
	 * @param name The name
	 *
	 * @return true if the name passed the test, false if not.
	 */
	private boolean validateName(String name)
	{
		return name.matches("[A-z0-9äöüéàèâëÄÖÜÈÉÀÁÂË «»─-]+");
	}

	/**
	 * Checks the Password. Password must be at least 4 characters long, must contain at least one number and one
	 * uppercase letter, one lowercase letter and may only contain two repetitive characters
	 *
	 * @param passwort The password
	 *
	 * @return true if it passed the test, false if not.

	public boolean validatePassword(String passwort)
	{
	return passwort.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.*(.+)\\1\\1).{4,}$");
	} */


	/**
	 * @param role
	 *
	 * @return
	 */
	public List<User> getByUserRole(USER_ROLE role)
	{
		HashMap<String, Object> map = new HashMap<>();
		map.put("role", role);
		map.put("deleted", 0);

		try {
			return this.dal.fetch(map);
		} catch (SQLException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}

		return new ArrayList<>();
	}

}
