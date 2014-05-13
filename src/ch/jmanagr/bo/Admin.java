package ch.jmanagr.bo;

import ch.jmanagr.lib.USER_ROLE;

public class Admin extends Agent
{
	public Admin() {}

	public Admin(int id, String firstname, String lastname, String username, String password,
	             Department department)
	{
		super(id, firstname, lastname, username, password, USER_ROLE.ADMIN, department);
	}

	public Admin(String firstname, String lastname, String username, String password,
	             Department department)
	{
		super(firstname, lastname, username, password, USER_ROLE.ADMIN, department);
	}


}
