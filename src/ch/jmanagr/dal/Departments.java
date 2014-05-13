package ch.jmanagr.dal;


import ch.jmanagr.bo.Department;
import ch.jmanagr.lib.STATUS_CODE;
import org.sql2o.Connection;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class Departments extends AbstractDAL<Department>
{

	private static Departments instance;

	private Departments()
	{
		super();
		// Todo: add sample data
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
		try (Connection con = DB.getSql2o().open()) {
			int key = new BigDecimal((Long)
					con.createQuery("INSERT INTO " + Department.class.getSimpleName() + " (id, name) VALUES (:id, " +
							":name);", true)
							.bind(bo)
							.executeUpdate()
							.getKey()
			).intValueExact();
			bo.setId(key);

			// todo insert agents
			return STATUS_CODE.OK;
		}
	}

	@Override
	public List<Department> fetch()
	{
		return this.fetch("id, name", "");
	}

	@Override
	public Department fetch(int id)
	{
		HashMap<String, Object> params = new HashMap<>();
		params.put("id", id);
		return this.fetch("id, name", "WEHRE id = :id LIMIT 1;", params).get(0);
	}

	@Override
	public STATUS_CODE update(Department bo)
	{
		for (Department department : dataList) {
			if (department.getId() == bo.getId()) {
				dataList.set(dataList.indexOf(department), bo);
				return STATUS_CODE.OK;
			}
		}

		return STATUS_CODE.FAIL; // TODO: Status for failed update
		// TODO: implement
	}
}
