package ch.jmanagr.lib;

public enum LOG_LEVEL
{
	VERBOSE(0),
	DEBUG(10),
	MESSAGE(20),
	WARNING(30),
	ERROR(40),
	FATAL_ERROR(50);

	public final int value;

	private LOG_LEVEL(int i)
	{
		value = i;
	}

	public String getName() {
		return super.toString();
	}
}
