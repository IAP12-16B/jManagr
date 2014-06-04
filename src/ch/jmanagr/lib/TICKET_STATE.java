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

	@Override
	public String toString()
	{
		return "" + value;
	}

	public String getName() {
		return super.toString();
	}

	public static TICKET_STATE fromString(String state)
	{
		for (TICKET_STATE s : TICKET_STATE.values()) {
			if (s.getName().equalsIgnoreCase(state)) {
				return s;
			}
		}
		return null;
	}
}
