package ch.jmanagr.lib;

public enum LOG_LEVEL
{
	VERBOSE(0),
	DEBUG(1),
	MESSAGE(2),
	WARNING(3),
	ERROR(4),
	FATAL_ERROR(5);

	public final int value;

	private LOG_LEVEL(int i)
	{
		value = i;
	}
}
