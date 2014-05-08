package ch.jmanagr.bl;


import ch.jmanagr.bo.*;
import ch.jmanagr.lib.STATUS_CODE;
import ch.jmanagr.lib.TICKET_STATE;

import java.util.List;


public class Tickets extends AbstractBL<Ticket, ch.jmanagr.dal.Tickets>
{
	private static volatile Tickets instance;

	private Tickets()
	{
		/* dal =
			DAL als Singelton?
		 */
		// TODO: implement
	}


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
	public STATUS_CODE validate(Ticket bo)
	{
		// Todo: implement
		return null;
	}

	public List<Ticket> getAllByUser(User user, TICKET_STATE state)
	{
		// Todo: implement
		return null;
	}

	public List<Ticket> getAllByAgent(Agent agent, TICKET_STATE state)
	{
		// Todo: implement
		return null;
	}

	public List<Ticket> getAllByResource(Resource resource, TICKET_STATE state)
	{
		// Todo: implement
		return null;
	}

	public List<Ticket> getAllByDepartment(Department department, TICKET_STATE state)
	{
		// Todo: implement
		return null;
	}

}