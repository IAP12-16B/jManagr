package ch.jmanagr.bo;

import ch.jmanagr.lib.USER_ROLE;

public class Agent extends User
{
	protected Department department;

	public Agent(int id, String firstname, String lastname, String username, String password,
	             Department department)
	{
		super(id, firstname, lastname, username, password, USER_ROLE.AGENT);
		this.department = department;
	}

	public Agent(String firstname, String lastname, String username, String password,
	             Department department)
	{
		super(firstname, lastname, username, password, USER_ROLE.AGENT);
		this.department = department;
	}

	protected Agent(String firstname, String lastname, String username, String password, USER_ROLE role,
	                Department department)
	{
		super(firstname, lastname, username, password, role);
		this.department = department;
	}

	protected Agent(int id, String firstname, String lastname, String username, String password, USER_ROLE role,
	                Department department)
	{
		super(id, firstname, lastname, username, password, role);
		this.department = department;
	}

	public Agent() {}


	public Department getDepartment()

	{
		return department;
	}

	public void setDepartment(Department department)
	{
		this.department = department;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		Agent agent = (Agent) o;

		if (department != null ? !department.equals(agent.department) : agent.department != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = super.hashCode();
		result = 31 * result + (department != null ? department.hashCode() : 0);
		return result;
	}
}
