package ch.jmanagr.dal;

import ch.jmanagr.bo.Department;

import java.sql.SQLException;

/**
 * DAL class for Department. Extends {@link AbstractDAL}
 */
public class DepartmentsDAL extends AbstractDAL<Department>
{
	private static DepartmentsDAL instance;

	protected DepartmentsDAL() throws SQLException
	{
		super(Department.class);
	}

	public static DepartmentsDAL getInstance() throws SQLException
	{
		if (instance == null) {
			synchronized (DepartmentsDAL.class) {
				if (instance == null) {
					instance = new DepartmentsDAL();
				}
			}
		}
		return instance;
	}
}
