package ch.jmanagr.dal;


import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB
{
	private static volatile DB instance;

	private Sql2o sql2o;
	private ch.jmanagr.bo.Settings settings;

	/**
	 *
	 */
	private DB()
	{
		settings = Settings.getInstance().get();
		sql2o = new Sql2o(String.format("jdbc:mysql://%s:%d/%s", settings.getHost(), settings.getPort(),
				settings.getDatabase()), settings.getUser(), settings.getPassword());
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

	/**
	 * Shortcut for sql2o's createQuery() and executeAndFetch() methode
	 *
	 * @param query      the SQL query
	 * @param returnType class
	 * @param <T>        A class
	 *
	 * @return List<T>
	 */
	public <T> List<T> executeAndFetch(String query, Class<T> returnType)
	{
		return sql2o.createQuery(query).executeAndFetch(returnType);
	}

	/**
	 * Shortcut for sql2o's createQuery() and executeAndFetch() methode with column mapping
	 *
	 * @param query      the SQL query
	 * @param returnType class
	 * @param <T>        A class
	 *
	 * @return List<T>
	 */
	public <T> List<T> executeAndFetch(String query, Class<T> returnType, HashMap<String, String> columnMap)
	{
		Query q = sql2o.createQuery(query);
		for (Map.Entry<String, String> entry : columnMap.entrySet()) {
			q.addColumnMapping(entry.getKey(), entry.getValue());
		}
		return q.executeAndFetch(returnType);
	}
}
