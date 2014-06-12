package ch.jmanagr.dal;

import ch.jmanagr.bo.User;
import ch.jmanagr.lib.USER_ROLE;

import java.sql.SQLException;

public class UsersDAL extends AbstractDAL<User>
{
	private static UsersDAL instance;

	protected UsersDAL() throws SQLException
	{
		super(User.class);

		// create root user
		if (this.firstUse) {
			User rootUser = new User();
			rootUser.setId(1);
			rootUser.setRole(USER_ROLE.ADMIN);
			rootUser.setUsername("root");
			rootUser.setUnhashedPassword("123"); // fixme hardcoded

			this.save(rootUser);
		}
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
