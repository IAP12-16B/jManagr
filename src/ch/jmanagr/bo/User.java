package ch.jmanagr.bo;

import ch.jmanagr.lib.USER_ROLE;

public class User implements BusinessObject
{
	protected int id;
	protected String firstname;
	protected String lastname;
	protected String username;
	protected String password;
	protected USER_ROLE role;

	public User(int id, String firstname, String lastname, String username, String password, USER_ROLE role)
	{
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public User(String firstname, String lastname, String username, String password, USER_ROLE role) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.role = role;
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

		User user = (User) o;

		if (id != user.id) {
			return false;
		}
		if (firstname != null ? !firstname.equals(user.firstname) : user.firstname != null) {
			return false;
		}
		if (lastname != null ? !lastname.equals(user.lastname) : user.lastname != null) {
			return false;
		}
		if (password != null ? !password.equals(user.password) : user.password != null) {
			return false;
		}
		if (role != user.role) {
			return false;
		}
		if (username != null ? !username.equals(user.username) : user.username != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = id;
		result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
		result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (role != null ? role.hashCode() : 0);
		return result;
	}

	public int getId()

	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public USER_ROLE getRole()
	{
		return role;
	}

	public void setRole(USER_ROLE role)
	{
		this.role = role;
	}
}
