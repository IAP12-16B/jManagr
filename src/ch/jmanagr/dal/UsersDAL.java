package ch.jmanagr.dal;

import ch.jmanagr.bo.User;
import ch.jmanagr.lib.STATUS_CODE;
import ch.jmanagr.lib.USER_ROLE;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * DAL class for Resources. Extends {@link AbstractDAL}
 */
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
			rootUser.setUnhashedPassword("123");

			this.save(rootUser);
		}
	}

	/**
	 * Get singelton instance
	 *
	 * @return An UsersDAL instance
	 *
	 * @throws SQLException
	 */
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

	/**
	 * Soft deletes (set deleted to 1) a BusinessObject
	 *
	 * @param bo the BusinessObject to soft-delete
	 *
	 * @return Status code if it was successful
	 *
	 * @throws java.sql.SQLException
	 */
	@Override
	public STATUS_CODE softDelete(User bo) throws SQLException
	{
		if (bo.getRole() == USER_ROLE.ADMIN) {
			Map<String, Object> map = new HashMap<>();
			map.put("role", USER_ROLE.ADMIN);
			map.put("deleted", 0);
			if (this.fetch(map).size() <= 1) {
				return STATUS_CODE.FAIL;
			}
		}

		return super.softDelete(bo);
	}

	/**
	 * Deletes a BusinessObject
	 *
	 * @param bo the BusinessObject to delete
	 *
	 * @return Status code if it was successful
	 *
	 * @throws java.sql.SQLException
	 */
	@Override
	public STATUS_CODE delete(User bo) throws SQLException
	{
		if (bo.getRole() == USER_ROLE.ADMIN) {
			Map<String, Object> map = new HashMap<>();
			map.put("role", USER_ROLE.ADMIN);
			map.put("deleted", 0);
			if (this.fetch(map).size() <= 1) {
				return STATUS_CODE.FAIL;
			}
		}

		return super.delete(bo);
	}
}
