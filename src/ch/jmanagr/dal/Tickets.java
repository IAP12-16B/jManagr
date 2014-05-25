package ch.jmanagr.dal;

import ch.jmanagr.bo.Ticket;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.STATUS_CODE;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2oException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Tickets extends AbstractDAL<Ticket>
{
	private static Tickets instance;

	private Tickets()
	{
		super(Ticket.class);
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
	public STATUS_CODE save(Ticket bo)
	{
		try (Connection con = DB.getSql2o().open()) {
			bo.setId(
					this.db.save(
							tableName,
							"id,name,description,status,date,Resource,Agent,Department,User,active,deleted",
							":id,:name,:description,:status,:date,:resource_id,:agent_id,:department_id,:user_id," +
							":active,:deleted",
							true
					).bind(bo)
					       .addParameter("resource_id", bo.getResource().getId())
					       .addParameter("agent_id", bo.getAgent().getId())
					       .addParameter("department_id", bo.getDepartment().getId())
					       .addParameter("user_id", bo.getUser().getId())
					       .executeUpdate()
					       .<Integer>getKey(Integer.class)
			);
			return STATUS_CODE.OK;
		} catch (Sql2oException e) {
			Logger.log(
					LOG_LEVEL.ERROR,
					String.format(
							"Save of %s with id %d failed!",
							bo.getClass().getName(),
							bo.getId()
					),
					e
			);
		}
		return STATUS_CODE.FAIL;
	}

	@Override
	public List<Ticket> fetch(HashMap<String, String> parameters, int limit)
	{
		try (Connection con = DB.getSql2o().open()) {
			String where = "";
			Iterator<Map.Entry<String, String>> it = parameters.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> pairs = it.next();
				where += String.format("%s = :%s", pairs.getKey(), pairs.getKey());
				it.remove();
			}

			Query q = this.db.select(
					"id,name,description,status,date,active,deleted",
					this.tableName,
					where,
					limit
			);
			it = parameters.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> pairs = it.next();
				q.addParameter(pairs.getKey(), pairs.getValue());
				it.remove();
			}

			List<Ticket> tickets = q.executeAndFetch(Ticket.class);

			for (Ticket ticket : tickets) {
				ticket.setResource(
						this.db.resolveRelation(ticket, Resources.getInstance(), "Resource")
				);

				ticket.setAgent(
						this.db.resolveRelation(ticket, Users.getInstance(), "Agent")
				);

				ticket.setUser(
						this.db.resolveRelation(ticket, Users.getInstance(), "User")
				);

				ticket.setDepartment(
						this.db.resolveRelation(ticket, Departments.getInstance(), "Department")
				);
			}

			return tickets;
		} catch (Sql2oException e) {
			Logger.log(
					LOG_LEVEL.ERROR,
					"Fetch failed!",
					e
			);
		}
		return null;
	}

	/**
	 * Fetch all BusinessObjects from DB
	 *
	 * @return a list of all BusinessObjects
	 */
	@Override
	public List<Ticket> fetch()
	{
		return this.fetch(new HashMap<String, String>(), -1);
	}

	/**
	 * Fetch a BusinessObject by id
	 *
	 * @param id the id of the BusinessObject
	 *
	 * @return the BusinessObject
	 */
	@Override
	public Ticket fetch(int id)
	{
		HashMap<String, String> map = new HashMap<>();
		map.put("id", ((Integer) id).toString());
		return this.fetch(map, 1).get(0);
	}

}
