package ch.jmanagr.dal;


import ch.jmanagr.bo.Department;
import ch.jmanagr.lib.STATUS_CODE;

import java.util.List;

public class Departments extends AbstractDAL<Department>
{
	private static Departments instance;

	private Departments()
	{
		super();
	}

	/**
	 * @return Departments instance
	 */
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
	public STATUS_CODE create(Department bo)
	{
		return null;
		// TODO: implement
	}

	@Override
	public List<Department> fetch()
	{
		List<Department> deps = db.executeAndFetch("SELECT id, name FROM departments", Department.class);
		return deps;
		// TODO: implement
	}

	@Override
	public STATUS_CODE update(Department bo)
	{
		return null;
		// TODO: implement
	}
}
