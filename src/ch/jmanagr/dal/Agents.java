package ch.jmanagr.dal;

import ch.jmanagr.bo.Agent;
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


public class Agents extends AbstractDAL<Agent>
{
	private static Agents instance;

	private Agents()
	{
		super(Agent.class);
	}

	public static Agents getInstance()
	{
		if (instance == null) {
			synchronized (Agents.class) {
				if (instance == null) {
					instance = new Agents();
				}
			}
		}
		return instance;
	}

	@Override
	public STATUS_CODE save(Agent bo)
	{
		try (Connection con = DB.getSql2o().open()) {
			bo.setId(
					this.db.save(
							tableName,
							"id,firstname,lastname,username,password,role,active,deleted,Department",
							":id,:firstname,:lastname,:username,:password,:role,:active,:deleted,:department_id",
							true
					).bind(bo)
					       .addParameter("department_id", bo.getDepartment().getId())
					       .executeUpdate()
					       .<Integer>getKey(Integer.class)
			);
			return STATUS_CODE.OK;
		} catch (Sql2oException e) {
			Logger.log(
					LOG_LEVEL.ERROR,
					String.format(
							"Creation of %s with id %d failed!",
							bo.getClass().getName(),
							bo.getId()
					),
					e
			);
		}
		return STATUS_CODE.FAIL;
	}

	@Override
	public List<Agent> fetch(HashMap<String, String> parameters, int limit)
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
					"id,firstname,lastname,username,password,role,active,deleted",
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

			List<Agent> agents = q.executeAndFetch(Agent.class);

			for (Agent agent : agents) {
				agent.setDepartment(
						this.db.resolveRelation(agent, Departments.getInstance(), "Department")
				);
			}

			return agents;
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
	public List<Agent> fetch()
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
	public Agent fetch(int id)
	{
		HashMap<String, String> map = new HashMap<>();
		map.put("id", ((Integer) id).toString());
		return this.fetch(map, 1).get(0);
	}

	/**
	 * Updates a BusinessObject
	 *
	 * @param bo the BusinessObject
	 *
	 * @return Whether it was successful or not.

	 @Override public STATUS_CODE update(Agent bo)
	 {
	 try (Connection con = DB.getSql2o().open()) {
	 bo.setId(
	 this.db.update(
	 tableName,
	 "firstname = :firstname," +
	 "lastname = :lastname," +
	 "username = :username," +
	 "password = :password," +
	 "role = :role," +
	 "active = :active," +
	 "deleted = :deleted," +
	 "Department = :department_id" +
	 " WHERE id = :id",
	 true
	 ).bind(bo)
	 .addParameter("department_id", bo.getDepartment().getId())
	 .executeUpdate()
	 .<Integer>getKey(Integer.class)
	 );
	 return STATUS_CODE.OK;
	 } catch (Sql2oException e) {
	 Logger.log(
	 LOG_LEVEL.ERROR,
	 String.format(
	 "Update of %s with id %d failed!",
	 bo.getClass().getName(),
	 bo.getId()
	 ),
	 e
	 );
	 }

	 return STATUS_CODE.FAIL;
	 }*/
}
