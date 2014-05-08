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
		dataList.add(bo);
		return STATUS_CODE.OK;
	}

	@Override
	public List<Ticket> fetch()
	{
		// TODO: implement
		return dataList;
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
