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
		super();
		dal = ch.jmanagr.dal.Tickets.getInstance();
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
		return STATUS_CODE.OK;
	}

	public List<Ticket> getAllByUser(User user, TICKET_STATE state)
	{
		return dal.fetch(user, state);
	}

	public List<Ticket> getAllByAgent(Agent agent, TICKET_STATE state)
	{
		return dal.fetch(agent, state);
	}

	public List<Ticket> getAllByResource(Resource resource, TICKET_STATE state)
	{
		return dal.fetch(resource, state);
	}

	public List<Ticket> getAllByDepartment(Department department, TICKET_STATE state)
	{
		return dal.fetch(department, state);
	}

}