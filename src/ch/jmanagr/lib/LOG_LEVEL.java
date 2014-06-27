package ch.jmanagr.lib;

/**
 * Enum to represent a LOG_LEVEL
 */
public enum LOG_LEVEL
{
	VERBOSE(1),
	DEBUG(2),
	MESSAGE(4),
	WARNING(8),
	ERROR(16),
	FATAL_ERROR(32);

	public final int value;

	private LOG_LEVEL(int i)
	{
		value = i;
	}

}
