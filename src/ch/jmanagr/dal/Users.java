package ch.jmanagr.dal;

import ch.jmanagr.bo.User;
import org.sql2o.Query;

import java.util.HashMap;


public class Users extends AbstractDAL<User>
{
	private static Users instance;

	private Users()
	{
		super(User.class);
	}

	public static Users getInstance()
	{
		if (instance == null) {
			synchronized (Users.class) {
				if (instance == null) {
					instance = new Users();
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
		fields.put("firstname", "firstname");
		fields.put("lastname", "lastname");
		fields.put("username", "username");
		fields.put("password", "password");
		fields.put("role", "role");
		fields.put("Department", "department_id");
		fields.put("active", "active");
		fields.put("deleted", "deleted");
		return fields;
	}

	@Override
	protected Query beforeSave(Query q, User bo)
	{
		Integer department_id = null;
		if (bo.getDepartment() != null) {
			Departments.getInstance().save(bo.getDepartment());
			department_id = bo.getDepartment().getId();
		}
		return super.beforeSave(q, bo).addParameter("department_id", department_id);
	}

	@Override
	protected HashMap<String, String> getFetchFields()
	{
		HashMap<String, String> fields = new HashMap<>();
		fields.put("id", "id");
		fields.put("firstname", "firstname");
		fields.put("lastname", "lastname");
		fields.put("username", "username");
		fields.put("password", "password");
		fields.put("role", "role");
		fields.put("active", "active");
		fields.put("deleted", "deleted");
		return fields;
	}

	@Override
	protected void afterFetch(User user)
	{
		super.afterFetch(user);
		user.setDepartment(
				this.db.resolveRelation(user, Departments.getInstance(), "Department")
		);
	}
}
