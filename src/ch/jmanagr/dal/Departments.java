package ch.jmanagr.dal;

import ch.jmanagr.bo.Department;
import ch.jmanagr.bo.User;

import java.util.HashMap;


public class Departments extends AbstractDAL<Department>
{
	private static Departments instance;

	private Departments()
	{
		super(Department.class);
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
	protected HashMap<String, String> getSaveFields()
	{
		HashMap<String, String> fields = new HashMap<>();
		fields.put("id", "id");
		fields.put("name", "name");
		fields.put("active", "active");
		fields.put("deleted", "deleted");
		return fields;
	}

	@Override
	protected void afterSave(Department bo)
	{
		for (User user : bo.getAgents()) {
			Users.getInstance().save(user);
		}
	}

	@Override
	protected HashMap<String, String> getFetchFields()
	{
		return this.getSaveFields();
	}

	@Override
	protected void afterFetch(Department department)
	{
		super.afterFetch(department);
		HashMap<String, String> map = new HashMap<>();
		map.put("Department", department.getId().toString());
		/*if (department.getAgents() == null || department.getAgents().isEmpty()) {
			List<User> us = Users.getInstance().fetch(map, -1);
			department.setAgents(
					us
			);
		}*/

	}
}
