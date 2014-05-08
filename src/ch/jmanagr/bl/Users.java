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
		/* dal =
			DAL als Singelton?
		 */
		// TODO: implement
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
		// Todo: implement
		return null;
	}

	public User login(String username, String password)
	{
		if (Math.random() > 0.5) {
			this.currentUser = new User(1, "Test", "User", username, password, USER_ROLE.ADMIN); // Todo: Implemenent
			// real login
			return this.currentUser;
		}
		return null;
	}

	public User getCurrentUser()
	{
		return currentUser;
	}


}
