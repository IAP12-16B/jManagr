package ch.jmanagr.dal;

import ch.jmanagr.bo.Agent;
import ch.jmanagr.bo.Department;
import ch.jmanagr.bo.User;
import ch.jmanagr.lib.STATUS_CODE;
import ch.jmanagr.lib.USER_ROLE;
import org.sql2o.Connection;
import org.sql2o.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Users extends AbstractDAL<User>
{
	private static Users instance;

	private Users()
	{
		super();
	}


	protected List<Agent> mapRelations(List<Agent> agents)
	{
		return this.addDepartment(agents);
	}

	/**
	 * @return Users instance
	 */
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
	public STATUS_CODE create(User bo)
	{
		try (Connection con = DB.getSql2o().open()) {
			int key = new BigDecimal((Long)
					con.createQuery("INSERT INTO " + User.class.getSimpleName() + " (id,firstname,lastname,username," +
							"password,role) VALUES (:id,:firstname,:lastname,:username,:password,:role);", true)
							.bind(bo)
							.addParameter("role", bo.getRole().toString())
							.executeUpdate()
							.getKey()
			).intValueExact();
			bo.setId(key);
			return STATUS_CODE.OK;
		}
	}

	public STATUS_CODE create(Agent bo)
	{
		try (Connection con = DB.getSql2o().open()) {
			int key = new BigDecimal((Long)
					con.createQuery("INSERT INTO " + User.class.getSimpleName() + " (id,firstname,lastname,username," +
							"password,role,department) VALUES (:id,:firstname,:lastname,:username,:password,:role," +
							":departmentId);", true)
							.bind(bo)
							.addParameter("role", bo.getRole().toString())
							.addParameter("departmentId", bo.getDepartment().getId())
							.executeUpdate()
							.getKey()
			).intValueExact();
			bo.setId(key);

			return STATUS_CODE.OK;
		}
	}

	private List<Agent> addDepartment(List<Agent> users)
	{
		try (Connection con = DB.getSql2o().open()) {
			Query getDepartmentIdQuery = con.createQuery(String.format("SELECT department FROM %s WHERE id = " +
					":agent_id LIMIT 1;",
					User.class.getSimpleName()));

			for (Agent agent : users) {
				Integer departmentId = getDepartmentIdQuery.addParameter("agent_id",
						agent.getId()).executeScalar(Integer.class);
				agent.setDepartment(Departments.getInstance().fetch(departmentId));
			}
		}

		return users;
	}

	@Override
	public User fetch(int id)
	{
		HashMap<String, Object> params = new HashMap<>();
		params.put("id", id);
		return this.fetch("id,firstname,lastname,username,password,role", "WEHRE id = :id LIMIT 1;", params).get(0);
	}

	@Override
	public List<User> fetch()
	{
		return this.fetch("id,firstname,lastname,username,password,role", "");
	}


	/**
	 * Get all Agents from the provided Deaprtment
	 *
	 * @param department the Deaprtment
	 *
	 * @return a list of Agents
	 */
	public List<Agent> fetchAgent(Department department)
	{
		// Todo
		List<Agent> result = new ArrayList<>();
		for (User user : dataList) {
			if (user.getRole() == USER_ROLE.AGENT) {
				Agent agent = (Agent) user;
				if (agent.getDepartment() == department) {
					result.add(agent);
				}
			}
		}
		return result;
	}

	/**
	 * Get all Agents from the provided Deaprtment
	 *
	 * @return a list of Agents
	 */
	public Agent fetchAgent(int id)
	{
		HashMap<String, Object> params = new HashMap<>();
		params.put("id", id);
		return this.mapRelations((List<Agent>) (List<?>) this.fetch("id,firstname,lastname,username,password,role",
				"WEHRE id = :id LIMIT 1;", params)).get(0);
	}

	@Override
	public STATUS_CODE update(User bo)
	{
		for (User user : dataList) {
			if (user.getId() == bo.getId()) {
				dataList.set(dataList.indexOf(user), bo);
				return STATUS_CODE.OK;
			}
		}

		return STATUS_CODE.FAIL; // TODO: Status for failed update
		// TODO: implement
	}
}
