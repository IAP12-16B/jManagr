package ch.jmanagr.bl;

import ch.jmanagr.bo.User;
import ch.jmanagr.lib.STATUS_CODE;
import ch.jmanagr.lib.USER_ROLE;

public class Users extends AbstractBL<User, ch.jmanagr.dal.Users>
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


	@Override
	public STATUS_CODE validate(User bo)
	{
		if (!this.validateUsername(bo.getUsername())) {
			return STATUS_CODE.USERNAME_INVALID;
		}

		if (!this.validateName(bo.getFirstname()) || !this.validateName(bo.getLastname())) {
			return STATUS_CODE.NAME_INVALID;
		}

		if (!this.validatePassword(bo.getPassword())) { // Todo: how to get the unhashed password?
			return STATUS_CODE.PASSWORD_INVALID;
		}

		// Todo: check if user already exists

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
	 */
	private boolean validatePassword(String passwort)
	{
		return passwort.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.*(.+)\\1\\1).{4,}$");
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
		// Todo: Implemenent real login
		// simulate login as long as DAL is not yet implemented
		if (username.equals("root") && password.equals("123")) {
			this.currentUser = new User(1, "Test", "User", username, password, USER_ROLE.ADMIN);
			return this.currentUser;
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
