package ch.jmanagr.dal;


import ch.jmanagr.bo.Ticket;

import java.sql.SQLException;

public class TicketsDAL extends AbstractDAL<Ticket>
{
	private static TicketsDAL instance;

	protected TicketsDAL() throws SQLException
	{
		super(Ticket.class);
	}

	public static TicketsDAL getInstance() throws SQLException
	{
		if (instance == null) {
			synchronized (ResourcesDAL.class) {
				if (instance == null) {
					instance = new TicketsDAL();
				}
			}
		}
		return instance;
	}

}
