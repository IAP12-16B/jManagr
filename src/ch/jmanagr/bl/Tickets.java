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

	/**
	 * Get all Tickets created by the provided User
	 *
	 * @param user  The user
	 * @param state The status of the ticket
	 *
	 * @return A list of Tickets, which were created by the provided User
	 */
	public List<Ticket> getAllByUser(User user, TICKET_STATE state)
	{
		return dal.fetch(user, state);
	}

	/**
	 * Get all Tickets assigned to the provided Agent
	 *
	 * @param agent The agent
	 * @param state The status of the ticket
	 *
	 * @return A list of Tickets, which are assigned to the provided Agent
	 */
	public List<Ticket> getAllByAgent(Agent agent, TICKET_STATE state)
	{
		return dal.fetch(agent, state);
	}

	/**
	 * Get all Tickets that were assigned to the provided Resource
	 *
	 * @param resource The Resource
	 * @param state    The status of the ticket
	 *
	 * @return A list of tickets, which are assigned to the provided Resource
	 */
	public List<Ticket> getAllByResource(Resource resource, TICKET_STATE state)
	{
		return dal.fetch(resource, state);
	}

	/**
	 * Get all Tickets that are assigned to the provided Department. Attention: This returns only the Tickets which are
	 * directly assigned to the Department, not the ones which are assigned to an Agent in that Department
	 *
	 * @param department The Deaprtment
	 * @param state      The status of the ticket
	 *
	 * @return A list of tickets, which are directly assigned to the provided Deaprtment
	 */
	public List<Ticket> getAllByDepartment(Department department, TICKET_STATE state)
	{
		return dal.fetch(department, state);
	}

}