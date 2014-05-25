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

}
