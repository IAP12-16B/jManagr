package ch.jmanagr.lib;

/**
 * User roles
 */
public enum USER_ROLE
{
	USER(0),
	AGENT(1),
	ADMIN(2);

	public final int value;

	private USER_ROLE(int i)
	{
		value = i;
	}

	@Override
	public String toString()
	{
		return "" + value;
	}

	public String getName() {
		return super.toString();
	}

	public static USER_ROLE fromString(String role)
	{
		for (USER_ROLE r : USER_ROLE.values()) {
			if (r.getName().equalsIgnoreCase(role)) {
				return r;
			}
		}
		return null;
	}
}
