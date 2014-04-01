package ch.jmanagr.bl;

import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.bo.User;
import ch.jmanagr.lib.STATUS_CODE;

public class Users extends AbstractBL
{
	private static volatile Users instance;

	protected User currentUser;
	protected ch.jmanagr.dal.Users dal;

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
	public STATUS_CODE validate(BusinessObject bo)
	{
		// Todo: implement
		return null;
	}

	public User login(String username, String password)
	{
		// Todo: implement
		return null;
	}

	public User getCurrentUser()
	{
		return currentUser;
	}


}
