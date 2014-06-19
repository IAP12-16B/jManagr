package ch.jmanagr.bl;

import ch.jmanagr.bo.Department;
import ch.jmanagr.dal.DepartmentsDAL;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.STATUS_CODE;

import java.sql.SQLException;


public class DepartmentsBL extends AbstractBL<Department, DepartmentsDAL>
{
	private static volatile DepartmentsBL instance;

	private DepartmentsBL()
	{
		super();
		try {
			dal = DepartmentsDAL.getInstance();
		} catch (SQLException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}
	}


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
		// Todo: implement
		return STATUS_CODE.OK;
	}


}