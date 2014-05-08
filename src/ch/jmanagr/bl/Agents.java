package ch.jmanagr.bl;

// Notice: I'm not sure, if the following is valid an working as expected (-> extending a singelton)....

import ch.jmanagr.bo.Agent;
import ch.jmanagr.bo.Department;
import ch.jmanagr.lib.USER_ROLE;

import java.util.ArrayList;
import java.util.List;

public class Agents extends Users
{
	private static volatile Agents instance;

	protected Agents()
	{
		super();
	}

	public static Agents getInstance()
	{
		if (instance == null) {
			synchronized (Agents.class) {
				if (instance == null) {
					instance = new Agents();
				}
			}
		}
		return instance;
	}

	public List<Agent> getAllByDepartment(Department department)
	{
		// Todo: implement with dal

		List<Agent> result = new ArrayList<Agent>();

		for (int i = 0, num = (int) (Math.random() * 10); i < num; i++) {
			Agent testAgent = new Agent(
					i,
					"Test",
					"Agent",
					"agent" + i,
					"pw",
					USER_ROLE.AGENT,
					department
			);
			result.add(testAgent);
		}
		return result;
	}
}
