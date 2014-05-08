package ch.jmanagr.dal;


import ch.jmanagr.bo.Ticket;
import ch.jmanagr.lib.STATUS_CODE;

import java.util.List;

public class Tickets extends AbstractDAL<Ticket>
{
	private static Tickets instance;

	private Tickets()
	{
		super();
	}

	/**
	 * @return Tickets instance
	 */
	public static Tickets getInstance()
	{
		if (instance == null) {
			synchronized (Tickets.class) {
				if (instance == null) {
					instance = new Tickets();
				}
			}
		}
		return instance;
	}

	@Override
	public STATUS_CODE create(Ticket bo)
	{
		// TODO: implement
		return null;
	}

	@Override
	public List<Ticket> fetch()
	{
		// TODO: implement
		return null;
	}

	@Override
	public STATUS_CODE update(Ticket bo)
	{
		// TODO: implement
		return null;
	}
}
