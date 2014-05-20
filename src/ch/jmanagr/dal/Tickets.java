package ch.jmanagr.dal;


import ch.jmanagr.bo.*;
import ch.jmanagr.lib.STATUS_CODE;
import ch.jmanagr.lib.TICKET_STATE;
import org.sql2o.Connection;
import org.sql2o.Query;

import java.math.BigDecimal;
import java.util.HashMap;
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
			int key = new BigDecimal((Long)
					con.createQuery("INSERT INTO " + Ticket.class.getSimpleName() + " (id,status, date, name, " +
							"description, Department, Agent, Resource, User) VALUES (:id, :status, :date, :name, " +
							":description, :departmentId, :agentId, :resourceId, :userId);", true)
							.bind(bo)
							.addParameter("departmentId", bo.getDepartment().getId())
							.addParameter("agentId", bo.getAgent().getId())
							.addParameter("resourceId", bo.getResource().getId())
							.addParameter("userId", bo.getUser().getId())
							.addParameter("status", bo.getStatus().toString())
							.executeUpdate()
							.getKey()
			).intValueExact();
			bo.setId(key);

			return STATUS_CODE.OK;
		}
	}

	private List<Ticket> addUser(List<Ticket> tickets)
	{
		try (Connection con = DB.getSql2o().open()) {
			Query getUserIdQuery = con.createQuery(String.format("SELECT User FROM %s WHERE id = :ticket_id LIMIT 1;",
					Ticket.class.getSimpleName()));

			for (Ticket ticket : tickets) {
				Integer userId = getUserIdQuery.addParameter("ticket_id", ticket.getId()).executeScalar(Integer.class);
				ticket.setUser(Users.getInstance().fetch(userId));
			}
		}

		return tickets;
	}

	private List<Ticket> addResource(List<Ticket> tickets)
	{
		try (Connection con = DB.getSql2o().open()) {
			Query getResourceIdQuery = con.createQuery(String.format("SELECT Resource FROM %s WHERE id = :ticket_id " +
					"LIMIT 1;",
					Ticket.class.getSimpleName()));

			for (Ticket ticket : tickets) {
				Integer resourceId = getResourceIdQuery.addParameter("ticket_id", ticket.getId()).executeScalar
						(Integer.class);
				ticket.setResource(Resources.getInstance().fetch(resourceId));
			}
		}

		return tickets;
	}

	private List<Ticket> addDepartment(List<Ticket> tickets)
	{
		try (Connection con = DB.getSql2o().open()) {
			Query getDepartmentIdQuery = con.createQuery(String.format("SELECT Department FROM %s WHERE id = " +
					":ticket_id LIMIT 1;",
					Ticket.class.getSimpleName()));

			for (Ticket ticket : tickets) {
				Integer departmentId = getDepartmentIdQuery.addParameter("ticket_id",
						ticket.getId()).executeScalar(Integer.class);
				ticket.setResource(Resources.getInstance().fetch(departmentId));
			}
		}

		return tickets;
	}

	private List<Ticket> addAgent(List<Ticket> tickets)
	{
		try (Connection con = DB.getSql2o().open()) {
			Query getAgentIdQuery = con.createQuery(String.format("SELECT Agent FROM %s WHERE id = :ticket_id LIMIT " +
					"1;",
					Ticket.class.getSimpleName()));

			for (Ticket ticket : tickets) {
				Integer agentId = getAgentIdQuery.addParameter("ticket_id", ticket.getId()).executeScalar(Integer
						.class);
				ticket.setAgent(Users.getInstance().fetchAgent(agentId));
			}
		}

		return tickets;
	}

	protected List<Ticket> mapRelations(List<Ticket> tickets)
	{
		return this.addUser(this.addAgent(this.addDepartment(this.addResource(tickets))));
	}

	@Override
	public Ticket fetch(int id)
	{
		HashMap<String, Object> params = new HashMap<>();
		params.put("id", id);
		return this.mapRelations(this.fetch("id,status,date,name,description", "WEHRE id = :id LIMIT 1;",
				params)).get(0);
	}


	@Override
	public List<Ticket> fetch()
	{
		return this.mapRelations(this.fetch("id,status,date,name,description"));
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
		HashMap<String, Object> params = new HashMap<>();
		params.put("status", state);
		return this.mapRelations(this.fetch("id,status,date,name,description", "WHERE `status` = :status", params));
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
		HashMap<String, Object> params = new HashMap<>();
		params.put("status", state);
		params.put("user_id", user.getId());
		return this.mapRelations(this.fetch("id,status,date,name,description", "WHERE `status` = :status AND `User` " +
				"=" +
				" " +
				":user_id", params));
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
		HashMap<String, Object> params = new HashMap<>();
		params.put("status", state);
		params.put("agent_id", agent.getId());
		return this.mapRelations(this.fetch("id,status,date,name,description", "WHERE `status` = :status AND `Agent`" +
				" " +
				"=" +
				" :agent_id", params));
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
		HashMap<String, Object> params = new HashMap<>();
		params.put("status", state);
		params.put("resource_id", resource.getId());
		return this.mapRelations(this.fetch("id,status,date,name,description", "WHERE `status` = :status AND " +
				"`Resource` = :resource_id", params));
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
		HashMap<String, Object> params = new HashMap<>();
		params.put("status", state);
		params.put("department_id", department.getId());
		return this.mapRelations(this.fetch("id,status,date,name,description", "WHERE `status` = :status AND " +
				"`Department` = :department_id", params));
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
