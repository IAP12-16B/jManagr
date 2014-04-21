package ch.jmanagr.lib;

public enum USER_ROLE
{
	USER(1),
	AGENT(2),
	ADMIN(3);

	public final int Value;

	private USER_ROLE(int i)
	{
		Value = i;
	}
}
