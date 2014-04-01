package ch.jmanagr.bo;

import ch.jmanagr.lib.USER_ROLE;

public class Admin extends Agent
{
	public Admin(int id, String firstname, String lastname, String username, String password, USER_ROLE role,
	             Department department)
	{
		super(id, firstname, lastname, username, password, role, department);
	}
}
