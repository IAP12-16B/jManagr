package ch.jmanagr.dal;


import org.sql2o.Sql2o;

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
		settings = Settings.getInstance().retrieve();
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

}
