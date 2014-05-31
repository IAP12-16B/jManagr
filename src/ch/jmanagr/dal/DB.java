package ch.jmanagr.dal;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

public class DB
{
	private static volatile DB instance;

	private Sql2o sql2o;

	private ch.jmanagr.bo.Settings settings;

	private Connection connection; // todo maybe a persistent connection?

	/**
	 *
	 */
	private DB()
	{
		this.setSettings(Settings.getInstance().retrieve());
	}

	public void setSettings(ch.jmanagr.bo.Settings settings)
	{
		this.settings = settings;
		sql2o = new Sql2o(
				String.format(
						"jdbc:mysql://%s:%d/%s", settings.getHost(), settings.getPort(),
						settings.getDatabase()
				), settings.getUser(), settings.getPassword()
		);
	}


	/**
	 * @return DB instance
	 */
	public static DB getInstance()
	{
		if (instance == null) {
			synchronized (DB.class) {
				if (instance == null) {
					instance = new DB();
				}
			}
		}
		return instance;
	}

	/**
	 * @return Sql2o object
	 */
	public static Sql2o getSql2o()
	{
		return getInstance().sql2o;
	}

	public Query insert(String table, String columns, String values, boolean returnGeneratedKey)
	{
		return this.sql2o.beginTransaction().createQuery(
				String.format("INSERT INTO (%s) VALUES (%s);", columns, values),
				returnGeneratedKey
		);
	}

	public Query update(String table, String query, boolean returnGeneratedKey)
	{
		return this.sql2o.beginTransaction().createQuery(
				String.format("UPDATE %s SET %s;", table, query),
				returnGeneratedKey
		);
	}

	public Query delete(String table, String where)
	{
		return this.sql2o.beginTransaction().createQuery(String.format("DELETE FROM %s WHERE %s;", table, where));
	}

	public Query save(String table, String columns, String values, boolean returnGeneratedKey)
	{
		String pairs = "";
		String[] cols = columns.split(",");

		for (int i = 0; i < cols.length; i++) {
			pairs += String.format("%s = VALUES(%s)", cols[i], cols[i]);
			if (i != (cols.length - 1)) {
				pairs += ", ";
			}
		}

		return this.sql2o.beginTransaction().createQuery(
				String.format(
						"INSERT INTO %s (%s) VALUES (%s) ON DUPLICATE KEY UPDATE %s;",
						table,
						columns,
						values,
						pairs
				),
				returnGeneratedKey
		);
	}

	public Query softDelete(String table, String where)
	{
		return this.update(table, " deleted = 1 WHERE " + where, false);
	}

	public Query select(String columns, String table, String where, String groupBy, String having, String orderBy,
	                    int limit)
	{
		String query = String.format("SELECT %s FROM %s", columns, table);

		if (!where.isEmpty()) {
			query += String.format(" WHERE %s", where);
		}

		if (!groupBy.isEmpty()) {
			query += String.format(" GROUP BY %s", groupBy);
		}

		if (!having.isEmpty()) {
			query += String.format(" HAVING %s", having);
		}

		if (!orderBy.isEmpty()) {
			query += String.format(" ORDER BY %s", orderBy);
		}

		if (limit != -1) {
			query += String.format(" LIMIT %d", limit);
		}

		query += ";";

		return this.sql2o.createQuery(query);
	}

	public Query select(String columns, String table, String where, String groupBy, String having, String orderBy)
	{
		return this.select(columns, table, where, groupBy, having, orderBy, -1);
	}

	public Query select(String columns, String table, String where, int limit)
	{
		return this.select(columns, table, where, "", "", "", limit);
	}

	public Query select(String columns, String table, int limit)
	{
		return this.select(columns, table, "", limit);
	}

	public Query select(String columns, String table, String where)
	{
		return this.select(columns, table, where, -1);
	}

	public Query select(String columns, String table)
	{
		return this.select(columns, table, -1);
	}

	public Query rawQuery(String query)
	{
		return this.sql2o.createQuery(query + ";");
	}

	public <BusinessObjectType extends BusinessObject> BusinessObjectType resolveRelation(BusinessObject bo,
	                                                                                      DAL<BusinessObjectType>
			                                                                                      relationsDAL,
	                                                                                      String field)
	{
		try (Connection con = DB.getSql2o().open()) {
			Integer relationalId = this.select(field, bo.getClass().getSimpleName(), "`id` = :id", 1).addParameter(
					"id",
					bo.getId()
			).executeScalar(Integer.class);
			return relationsDAL.fetch(relationalId); // here potentially exists the possibility of an endless
			// recursion loop
		} catch (Sql2oException e) {
			Logger.log(LOG_LEVEL.ERROR, "Relation mapping failed!", e);
		}

		return null;
	}

}
