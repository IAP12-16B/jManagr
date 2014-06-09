package ch.jmanagr.bl;

import ch.jmanagr.bo.Department;
import ch.jmanagr.dal.DepartmentsDAL;
import ch.jmanagr.lib.STATUS_CODE;

import java.sql.SQLException;


public class Departments extends AbstractBL<Department, DepartmentsDAL>
{
	private static volatile Departments instance;

	private Departments()
	{
		super();
		try {
			dal = DepartmentsDAL.getInstance();
		} catch (SQLException e) {
			e.printStackTrace(); // todo log
		}
	}


	public static Departments getInstance()
	{
		if (instance == null) {
			synchronized (Departments.class) {
				if (instance == null) {
					instance = new Departments();
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