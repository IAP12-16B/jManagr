package ch.jmanagr.dal;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.bo.Department;
import ch.jmanagr.lib.STATUS_CODE;

import java.util.List;

public class Departments extends AbstractDAL
{
	@Override
	public STATUS_CODE create(BusinessObject bo)
	{
		return null;
		// TODO: implement
	}

	@Override
	public List<Department> fetchAll()
	{
		List<Department> deps = db.executeAndFetch("SELECT id, name FROM departments", Department.class);
		return deps;
		// TODO: implement
	}

	@Override
	public STATUS_CODE update(BusinessObject bo)
	{
		return null;
		// TODO: implement
	}
}
