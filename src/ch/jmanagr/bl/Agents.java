package ch.jmanagr.bl;

// Notice: I'm not sure, if the following is valid an working as expected (-> extending a singelton)....

import ch.jmanagr.bo.Agent;
import ch.jmanagr.bo.Department;

import java.util.List;

public class Agents extends AbstractUserBL<Agent, ch.jmanagr.dal.Agents>
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

	/**
	 * Get all Agents by a Department
	 *
	 * @param department The Department
	 *
	 * @return A list of all Agents from a Department
	 */
	public List<Agent> getAllByDepartment(Department department)
	{
		return dal.fetch(department);
	}
}
