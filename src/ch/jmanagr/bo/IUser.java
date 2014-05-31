package ch.jmanagr.bo;


import ch.jmanagr.lib.USER_ROLE;

@Deprecated
public interface IUser<T extends BusinessObject> extends BusinessObject<T>
{
	public boolean isDeleted();

	public void setDeleted(boolean deleted);

	public boolean isActive();

	public void setActive(boolean active);

	public String getFirstname();

	public void setFirstname(String firstname);

	public String getLastname();

	public void setLastname(String lastname);

	public String getUsername();

	public void setUsername(String username);

	public String getPassword();

	public void setPassword(String password);

	public USER_ROLE getRole();

	public void setRole(USER_ROLE role);

	public boolean checkPassword(String password);

	public void setHashedPassword(String hash);

}
