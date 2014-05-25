package ch.jmanagr.dal;

import ch.jmanagr.bo.Department;
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


public class Departments extends AbstractDAL<Department>
{
	private static Departments instance;

	private Departments()
	{
		super(Department.class);
	}

	public static Departments getInstance()
	{
		if (instance == null) {
			synchronized (Departments.class) {
				if (instance == null) {
					instance = new Departments();
				}
			}
		}
		return instance;
	}


	@Override
	public STATUS_CODE save(Department bo)
	{
		try (Connection con = DB.getSql2o().open()) {
			bo.setId(
					this.db.save(
							tableName,
							"id,name,active,deleted",
							":id,:name,:active,:deleted",
							true
					).bind(bo).executeUpdate().<Integer>getKey(Integer.class)
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
	public List<Department> fetch(HashMap<String, String> parameters, int limit)
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
					"id,name,active,deleted",
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

			List<Department> departments = q.executeAndFetch(Department.class);

			HashMap<String, String> map = null;
			for (Department department : departments) {
				map = new HashMap<>();
				map.put("Department", ((Integer) department.getId()).toString());
				department.setAgents(
						Users.getInstance().fetch(map, -1)
				);
			}


			return departments;
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
	public List<Department> fetch()
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
	public Department fetch(int id)
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

	 @Override public STATUS_CODE update(Department bo)
	 {
	 try (Connection con = DB.getSql2o().open()) {
	 bo.setId(
	 this.db.update(
	 tableName,
	 "name = :name," +
	 "active = :active," +
	 "deleted = :deleted" +
	 " WHERE id = :id",
	 true
	 ).bind(bo).executeUpdate().<Integer>getKey(Integer.class)
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
