package ch.jmanagr.dal;


import ch.jmanagr.bo.*;
import ch.jmanagr.lib.STATUS_CODE;
import ch.jmanagr.lib.TICKET_STATE;
import org.sql2o.Connection;

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
		try (Connection con = DB.getSql2o().open()) {
			bo.setId((Integer)
					con.createQuery("INSERT INTO " + Ticket.class.getSimpleName() + " (status, date, name, " +
							"description, Department, Agent, Resource, User) VALUES (:status, :date, :name, " +
							":description, :department, :agent, :resource, :user);", true)
							.bind(bo)
							.executeUpdate()
							.getKey()
			);
			return STATUS_CODE.OK;
		}

	}

	@Override
	public List<Ticket> fetch()
	{
		try (Connection con = DB.getSql2o().open()) {
			return con
					.createQuery("SELECT * FROM " + Ticket.class.getSimpleName())
					.executeAndFetch(Ticket.class);
		}
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
		try (Connection con = DB.getSql2o().open()) {
			return con
					.createQuery("SELECT * FROM " + Ticket.class.getSimpleName() + " WHERE `status` = :status")
					.addParameter("status", state)
					.executeAndFetch(Ticket.class);
		}

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
		try (Connection con = DB.getSql2o().open()) {
			return con
					.createQuery("SELECT * FROM " + Ticket.class.getSimpleName() + " WHERE `status` = :status AND " +
							"`User` " +
							"= :user_id")
					.addParameter("status", state)
					.addParameter("user_id", user.getId())
					.executeAndFetch(Ticket.class);
		}
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
		try (Connection con = DB.getSql2o().open()) {
			return con
					.createQuery("SELECT * FROM " + Ticket.class.getSimpleName() + " WHERE `status` = :status AND " +
							"`Agent`" +
							" = :agent_id")
					.addParameter("status", state)
					.addParameter("agent_id", agent.getId())
					.executeAndFetch(Ticket.class);
		}
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
		try (Connection con = DB.getSql2o().open()) {
			return con
					.createQuery("SELECT * FROM " + Ticket.class.getSimpleName() + " WHERE `status` = :status AND " +
							"`Resource` = :resource_id")
					.addParameter("status", state)
					.addParameter("resource_id", resource.getId())
					.executeAndFetch(Ticket.class);
		}
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
		try (Connection con = DB.getSql2o().open()) {
			return con
					.createQuery("SELECT * FROM " + Ticket.class.getSimpleName() + " WHERE `status` = :status AND " +
							"`Department` = :department_id")
					.addParameter("status", state)
					.addParameter("department_id", department.getId())
					.executeAndFetch(Ticket.class);
		}
	}

	@Override
	public STATUS_CODE update(Ticket bo)
	{
		try (Connection con = DB.getSql2o().open()) {
			bo.setId((Integer)
					con.createQuery(
							"UPDATE " + Ticket.class.getSimpleName() + " SET " +
									"status = :status, " +
									"date = :date, " +
									"name = :name, " +
									"description = :description, " +
									"Department = :department, " +
									"Agent = :agent, " +
									"Resource = :resource, " +
									"User = :user" +
									"WHERE id = :id",
							true
					).bind(bo)
							.executeUpdate()
							.getKey()
			);

			return STATUS_CODE.OK;

		}
	}
}
