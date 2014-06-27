package ch.jmanagr.lib;

/**
 * User roles for rights management
 */
public enum USER_ROLE
{
	USER(1),
	AGENT(2),
	ADMIN(4);

	public final int value;

	private USER_ROLE(int i)
	{
		value = i;
	}
}
