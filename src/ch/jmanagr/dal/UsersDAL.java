package ch.jmanagr.dal;

import ch.jmanagr.bo.User;

import java.sql.SQLException;

public class UsersDAL extends AbstractDAL<User>
{
	private static UsersDAL instance;

	protected UsersDAL() throws SQLException
	{
		super(User.class);
	}

	public static UsersDAL getInstance() throws SQLException
	{
		if (instance == null) {
			synchronized (UsersDAL.class) {
				if (instance == null) {
					instance = new UsersDAL();
				}
			}
		}
		return instance;
	}
}
