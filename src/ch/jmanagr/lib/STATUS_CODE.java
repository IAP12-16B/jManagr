package ch.jmanagr.lib;


public enum STATUS_CODE
{
	OK(1),
	FAIL(2),
	ALREADY_EXISTS(3),
	UNKOWN_REASON(4),
	NAME_INVALID(5),
	PASSWORD_INVALID(6),
	PASSWOR_MISSMATCH(7),
	NOT_AUTHORIZED(8),
	USERNAME_INVALID(9);

	public final int value;

	private STATUS_CODE(int i)
	{
		value = i;
	}
}
