package ch.jmanagr.bl;

import ch.jmanagr.bo.Department;
import ch.jmanagr.dal.DepartmentsDAL;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.STATUS_CODE;

import java.sql.SQLException;

/**
 * BL class for Departments. Extends {@link ch.jmanagr.bl.AbstractBL}
 */
public class DepartmentsBL extends AbstractBL<Department, DepartmentsDAL>
{
	private static DepartmentsBL instance;

	private DepartmentsBL()
	{
		super();
		try {
			dal = DepartmentsDAL.getInstance();
		} catch (SQLException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}
	}


	/**
	 * @return a singleton instance
	 */
	public static DepartmentsBL getInstance()
	{
		if (instance == null) {
			synchronized (DepartmentsBL.class) {
				if (instance == null) {
					instance = new DepartmentsBL();
				}
			}
		}
		return instance;
	}


	@Override
	public STATUS_CODE validate(Department bo)
	{
		try {
			if (!this.dal.exists(bo)) {
				return STATUS_CODE.OK;
			}
		} catch (SQLException e) {
			Logger.log(LOG_LEVEL.WARNING, e);

			return STATUS_CODE.FAIL;
		}
		return STATUS_CODE.ALREADY_EXISTS;
	}


}