package ch.jmanagr.dal;

import ch.jmanagr.bo.Agent;
import ch.jmanagr.bo.Department;
import ch.jmanagr.bo.User;
import ch.jmanagr.lib.STATUS_CODE;
import ch.jmanagr.lib.USER_ROLE;

import java.util.ArrayList;
import java.util.List;

public class Users extends AbstractDAL<User>
{
	private static Users instance;

	private Users()
	{
		super();
		// Todo: add sample data
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
		// TODO: implement
		dataList.add(bo);
		return STATUS_CODE.OK;
	}

	@Override
	public List<User> fetch()
	{
		// TODO: implement
		return dataList;
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
		List<Agent> result = new ArrayList<Agent>();
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
