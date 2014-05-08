package ch.jmanagr.bl;

// Notice: I'm not sure, if the following is valid an working as expected (-> extending a singelton)....

import ch.jmanagr.bo.Agent;
import ch.jmanagr.bo.Department;

import java.util.List;

public class Agents extends Users
{
	private static volatile Agents instance;

	protected Agents()
	{
		super();
		/* dal =
			DAL als Singelton?
		 */
		// TODO: implement
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
		// Todo: implement
		return null;
	}
}
