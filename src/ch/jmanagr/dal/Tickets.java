package ch.jmanagr.dal;


import ch.jmanagr.bo.*;
import ch.jmanagr.lib.STATUS_CODE;
import ch.jmanagr.lib.TICKET_STATE;

import java.util.ArrayList;
import java.util.List;

public class Tickets extends AbstractDAL<Ticket>
{
	private static Tickets instance;

	private Tickets()
	{
		super();
		// Todo: add sample data
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
		dataList.add(bo);
		return STATUS_CODE.OK;
	}

	@Override
	public List<Ticket> fetch()
	{
		// TODO: implement
		return dataList;
	}

	/**
	 * Get all Ticktes which have the provided status
	 *
	 * @param state the status of the ticket
	 *
	 * @return a list of tickets
	 */
	public List<Ticket> fetch(TICKET_STATE state)
	{
		List<Ticket> result = new ArrayList<Ticket>();
		for (Ticket ticket : dataList) {
			if (ticket.getStatus() == state) {
				result.add(ticket);
			}
		}
		return result;
	}

	/**
	 * Get all Tickets created by the provided User
	 *
	 * @param user  The user
	 * @param state The status of the ticket
	 *
	 * @return A list of Tickets, which were created by the provided User
	 */
	public List<Ticket> fetch(User user, TICKET_STATE state)
	{
		List<Ticket> result = new ArrayList<Ticket>();
		for (Ticket ticket : dataList) {
			if (ticket.getUser() == user && ticket.getStatus() == state) {
				result.add(ticket);
			}
		}
		return result;
	}

	/**
	 * Get all Tickets assigned to the provided Agent
	 *
	 * @param agent The agent
	 * @param state The status of the ticket
	 *
	 * @return A list of Tickets, which are assigned to the provided Agent
	 */
	public List<Ticket> fetch(Agent agent, TICKET_STATE state)
	{
		List<Ticket> result = new ArrayList<Ticket>();
		for (Ticket ticket : dataList) {
			if (ticket.getAgent() == agent && ticket.getStatus() == state) {
				result.add(ticket);
			}
		}
		return result;
	}

	/**
	 * Get all Tickets that were assigned to the provided Resource
	 *
	 * @param resource The Resource
	 * @param state    The status of the ticket
	 *
	 * @return A list of tickets, which are assigned to the provided Resource
	 */
	public List<Ticket> fetch(Resource resource, TICKET_STATE state)
	{
		List<Ticket> result = new ArrayList<Ticket>();
		for (Ticket ticket : dataList) {
			if (ticket.getRessource() == resource && ticket.getStatus() == state) {
				result.add(ticket);
			}
		}
		return result;
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
	public List<Ticket> fetch(Department department, TICKET_STATE state)
	{
		List<Ticket> result = new ArrayList<Ticket>();
		for (Ticket ticket : dataList) {
			if (ticket.getDepartment() == department && ticket.getStatus() == state) {
				result.add(ticket);
			}
		}
		return result;
	}

	@Override
	public STATUS_CODE update(Ticket bo)
	{
		for (Ticket ticket : dataList) {
			if (ticket.getId() == bo.getId()) {
				dataList.set(dataList.indexOf(ticket), bo);
				return STATUS_CODE.OK;
			}
		}

		return STATUS_CODE.FAIL; // TODO: Status for failed update
		// TODO: implement
	}
}
