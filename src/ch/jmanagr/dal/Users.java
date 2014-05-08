package ch.jmanagr.dal;

import ch.jmanagr.bo.User;
import ch.jmanagr.lib.STATUS_CODE;

import java.util.List;

public class Users extends AbstractDAL<User>
{
	private static Users instance;

	private Users()
	{
		super();
	}

	/**
	 * @return Users instance
	 */
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
	public STATUS_CODE create(User bo)
	{
		// TODO: implement
		return null;
	}

	@Override
	public List<User> fetch()
	{
		// TODO: implement
		return null;
	}

	@Override
	public STATUS_CODE update(User bo)
	{
		// TODO: implement
		return null;
	}
}
