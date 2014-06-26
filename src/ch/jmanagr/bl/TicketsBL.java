package ch.jmanagr.bl;


import ch.jmanagr.bo.Department;
import ch.jmanagr.bo.Resource;
import ch.jmanagr.bo.Ticket;
import ch.jmanagr.bo.User;
import ch.jmanagr.dal.TicketsDAL;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.STATUS_CODE;
import ch.jmanagr.lib.TICKET_STATE;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TicketsBL extends AbstractBL<Ticket, TicketsDAL>
{
	private static volatile TicketsBL instance;

	private TicketsBL()
	{
		super();
		try {
			dal = TicketsDAL.getInstance();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static TicketsBL getInstance()
	{
		if (instance == null) {
			synchronized (TicketsBL.class) {
				if (instance == null) {
					instance = new TicketsBL();
				}
			}
		}
		return instance;
	}


	/**
	 * @param bo The BusinessObject to check
	 *
	 * @return
	 */
	@Override
	public STATUS_CODE validate(Ticket bo)
	{

		try {
			if (this.dal.exists(bo)) {
				return STATUS_CODE.OK;
			}
		} catch (SQLException e) {
			Logger.log(LOG_LEVEL.ERROR, e);

			return STATUS_CODE.FAIL;
		}

		return STATUS_CODE.ALREADY_EXISTS;
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
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", user.getId());
		if (state != null) {
			map.put("status", state);
		}
		map.put("deleted", 0);
		try {
			return dal.fetch(map);
		} catch (SQLException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}

		return new ArrayList<>();
	}

	/**
	 * Get all Tickets created by the provided User
	 *
	 * @param user The user
	 *
	 * @return A list of Tickets, which were created by the provided User
	 */
	public List<Ticket> getAllByUser(User user)
	{
		return this.getAllByUser(user, null);
	}

	/**
	 * Get all Tickets assigned to the provided Agent
	 *
	 * @param agent The agent
	 * @param state The status of the ticket
	 *
	 * @return A list of Tickets, which are assigned to the provided Agent
	 */
	public List<Ticket> getAllByAgent(User agent, TICKET_STATE state)
	{
		HashMap<String, Object> map = new HashMap<>();
		map.put("agent_id", agent.getId());
		if (state != null) {
			map.put("status", state);
		}
		map.put("deleted", 0);
		try {
			return dal.fetch(map);
		} catch (SQLException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}

		return new ArrayList<>();
	}

	/**
	 * Get all Tickets assigned to the provided Agent
	 *
	 * @param agent The agent
	 *
	 * @return A list of Tickets, which are assigned to the provided Agent
	 */
	public List<Ticket> getAllByAgent(User agent)
	{
		return this.getAllByAgent(agent, null);
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
		HashMap<String, Object> map = new HashMap<>();
		map.put("resource_id", resource.getId());
		if (state != null) {
			map.put("status", state);
		}
		map.put("deleted", 0);
		try {
			return dal.fetch(map);
		} catch (SQLException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}

		return new ArrayList<>();
	}

	/**
	 * Get all Tickets that were assigned to the provided Resource
	 *
	 * @param resource The Resource
	 *
	 * @return A list of tickets, which are assigned to the provided Resource
	 */
	public List<Ticket> getAllByResource(Resource resource)
	{
		return this.getAllByResource(resource, null);
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
		HashMap<String, Object> map = new HashMap<>();
		map.put("department_id", department.getId());
		map.put("status", state);
		map.put("deleted", 0);
		try {
			return dal.fetch(map);
		} catch (SQLException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}

		return new ArrayList<>();
	}

	/**
	 * Get all Tickets that are assigned to the provided Department. Attention: This returns only the Tickets which are
	 * directly assigned to the Department, not the ones which are assigned to an Agent in that Department
	 *
	 * @param department The Deaprtment
	 *
	 * @return A list of tickets, which are directly assigned to the provided Deaprtment
	 */
	public List<Ticket> getAllByDepartment(Department department) {
		return this.getAllByDepartment(department, null);
	}

	public List<Ticket> getAllByStatus(TICKET_STATE status)
	{
		try {
			return dal.fetch("status", status);
		} catch (SQLException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}

		return new ArrayList<>();
	}


	@Override
	public STATUS_CODE delete(Ticket bo)
	{
		if (bo.getStatus() == TICKET_STATE.CLOSED) {
			return super.delete(bo);
		}

		return STATUS_CODE.CANNOT_DELETE_OBJECT;
	}
}