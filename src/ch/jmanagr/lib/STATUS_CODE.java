package ch.jmanagr.lib;

/**
 * Status-Codes for different actions
 */
public enum STATUS_CODE
{
	OK(0),
	FAIL(1),
	ALREADY_EXISTS(2),
	UNKOWN_REASON(3),
	NAME_INVALID(4),
	PASSWORD_INVALID(5),
	PASSWOR_MISSMATCH(6),
	NOT_AUTHORIZED(7),
	USERNAME_INVALID(8);

	public final int value;

	private STATUS_CODE(int i)
	{
		value = i;
	}

	@Override
	public String toString()
	{
		return Integer.toString(value);
	}
}
