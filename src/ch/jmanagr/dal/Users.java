package ch.jmanagr.dal;

import ch.jmanagr.bo.User;
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


public class Users extends AbstractDAL<User>
{
	private static Users instance;

	private Users()
	{
		super(User.class);
	}

	public static Users getInstance()
	{
		if (instance == null) {
			synchronized (Users.class) {
				if (instance == null) {
					instance = new Users();
				}
			}
		}
		return instance;
	}

	@Override
	public STATUS_CODE save(User bo)
	{
		try (Connection con = DB.getSql2o().open()) {
			bo.setId(
					this.db.save(
							tableName,
							"`id`,`firstname`,`lastname`,`username`,`password`,`role`,`active`,`deleted`,`Department`",
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
	public List<User> fetch(HashMap<String, String> parameters, int limit)
	{
		Logger.log(parameters);
		try (Connection con = DB.getSql2o().open()) {
			String where = "";
			Iterator<Map.Entry<String, String>> it = parameters.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> pairs = it.next();
				where += String.format("`%s` = :%s", pairs.getKey(), pairs.getKey());
				it.remove();
			}

			Query q = this.db.select(
					"`id`,`firstname`,`lastname`,`username`,`password`,`role`,`active`,`deleted`",
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

			List<User> users = q.executeAndFetch(User.class);

			for (User user : users) {
				user.setDepartment(
						this.db.resolveRelation(user, Departments.getInstance(), "Department")
				);
			}

			return users;
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
	public List<User> fetch()
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
	public User fetch(int id)
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
