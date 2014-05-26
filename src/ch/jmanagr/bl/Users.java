package ch.jmanagr.bl;

import ch.jmanagr.bo.User;

import java.util.HashMap;

public class Users extends AbstractUserBL<User, ch.jmanagr.dal.Users>
{
	private static volatile Users instance;

	protected User currentUser;

	protected Users()
	{
		super();
		dal = ch.jmanagr.dal.Users.getInstance();
	}


	public static Users getInstance()
	{
		if (instance == null) {
			synchronized (Users.class) {
				if (instance == null) {
					instance = new Users();
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
	public User login(String username, String password)
	{
		HashMap<String, String> map = new HashMap<>();
		map.put("username", username);
		User u = this.dal.fetch(map, 1).get(0);
		if (u.checkPassword(password)) {
			this.currentUser = u;
			return u;
		}
		return null;
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


}
