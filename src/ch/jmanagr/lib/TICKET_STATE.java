package ch.jmanagr.lib;

/**
 * Ticket statis
 */
public enum TICKET_STATE
{
	OPEN(1),
	CLOSED(2);

	public final int value;

	private TICKET_STATE(int i)
	{
		value = i;
	}
}
