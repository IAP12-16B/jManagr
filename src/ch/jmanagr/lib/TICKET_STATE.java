package ch.jmanagr.lib;

/**
 * Ticket statis
 */
public enum TICKET_STATE
{
	OPEN(0),
	CLOSED(1);

	public final int value;

	private TICKET_STATE(int i)
	{
		value = i;
	}

}
