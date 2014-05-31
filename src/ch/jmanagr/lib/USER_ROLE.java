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
}
