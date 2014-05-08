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
