package ch.jmanagr.lib;

/**
 * Status-Codes for different actions
 */
public enum STATUS_CODE
{
	OK(0),
	FAIL(10),
	ALREADY_EXISTS(20),
	FAIL_WITH_UNKOWN_REASON(30),
	NAME_INVALID(40),
	PASSWORD_INVALID(50),
	PASSWOR_MISSMATCH(60),
	NOT_AUTHORIZED(70),
	USERNAME_INVALID(80),
	NO_CONNECTION_TO_SERVER(90);

	public final int value;

	private STATUS_CODE(int i)
	{
		value = i;
	}
}
