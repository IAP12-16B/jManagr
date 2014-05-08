package ch.jmanagr.lib;

public enum USER_ROLE
{
	USER(1),
	AGENT(2),
	ADMIN(3);

	public final int value;

	private USER_ROLE(int i)
	{
		value = i;
	}
}
