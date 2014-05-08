package ch.jmanagr.bl;

import ch.jmanagr.bo.Department;
import ch.jmanagr.lib.STATUS_CODE;


public class Departments extends AbstractBL<Department, ch.jmanagr.dal.Departments>
{
	private static volatile Departments instance;

	private Departments()
	{
		super();
		dal = ch.jmanagr.dal.Departments.getInstance();
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