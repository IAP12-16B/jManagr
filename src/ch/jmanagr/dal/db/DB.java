package ch.jmanagr.dal.db;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.dal.SettingsDAL;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Basic class to provide DB access Current implementation uses ORMlite
 */
public class DB
{
	private static DB instance;
	private ch.jmanagr.bo.Settings settings;

	private JdbcPooledConnectionSource connectionSource;


	/**
	 * @throws java.sql.SQLException
	 */
	private DB() throws SQLException
	{
		this.setSettings(SettingsDAL.getInstance().retrieve());
	}

	/**
	 * @return DB instance
	 * @throws java.sql.SQLException
	 */
	public static DB getInstance() throws SQLException
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
	 * Changes the used Settings for DB connection
	 * @param settings the Settings to use
	 * @throws SQLException
	 */
	public void setSettings(ch.jmanagr.bo.Settings settings) throws SQLException
	{
		this.settings = settings;
		this.updateConnection();

	}

	/**
	 * Updates the connection source with the current setting
	 * @throws SQLException
	 */
	protected void updateConnection() throws SQLException
	{
		connectionSource =
				new JdbcPooledConnectionSource(
						String.format(
								"jdbc:mysql://%s:%d",
								this.settings.getHost(),
								this.settings.getPort()
						),
						this.settings.getUser(),
						this.settings.getPassword()
				);

		// create database if not exists
		connectionSource.getReadWriteConnection().executeStatement(
				String.format(
						"CREATE DATABASE IF NOT EXISTS `%s`;",
						this.settings.getDatabase()
				),
				DatabaseConnection.DEFAULT_RESULT_FLAGS
		);

		connectionSource.setUrl(
				String.format(
						"jdbc:mysql://%s:%d/%s",
						this.settings.getHost(),
						this.settings.getPort(),
						this.settings.getDatabase()
				)
		);


		// setup connectionsource
		connectionSource.setMaxConnectionAgeMillis(4 * 60 * 1000);
		connectionSource.setCheckConnectionsEveryMillis(60 * 1000);
		connectionSource.setTestBeforeGet(true);
	}

	/**
	 * Closes the connection source
	 */
	public void closeConnection()
	{
		try {
			connectionSource.close();
		} catch (SQLException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}
	}

	/**
	 * Shuts the DB connection down.
	 */
	public static void shutdown()
	{
		if (instance != null) {
			instance.closeConnection();
		}
	}

	/**
	 * Get the current connection source
	 * @return the current connection source
	 */
	public ConnectionSource getConnectionSource()
	{
		return this.connectionSource;
	}

	/**
	 * Issue the database statements to create the table associated with a class.
	 *
	 * @param cls The class for which a table will be created.
	 *
	 * @return The number of statements executed to do so.
	 */
	public int createTable(Class<? extends BusinessObject> cls) throws SQLException
	{
		return TableUtils.createTable(this.connectionSource, cls);
	}

	/**
	 * @see DB#createTableIfNotExists(Class) Create a table if it does not already exist. This is not supported by all
	 * databases.
	 */
	public int createTableIfNotExists(Class cls) throws SQLException
	{
		return TableUtils.createTableIfNotExists(this.connectionSource, cls);
	}

	/**
	 * Issue the database statements to drop the table associated with a class. <p/> <p> <b>WARNING:</b> This is
	 * [obviously] very destructive and is unrecoverable. </p>
	 *
	 * @param dataClass    The class for which a table will be dropped.
	 * @param ignoreErrors If set to true then try each statement regardless of {@link java.sql.SQLException} thrown
	 *                     previously.
	 *
	 * @return The number of statements executed to do so.
	 */
	public <T, ID> int dropTable(Class<? extends BusinessObject> dataClass, boolean ignoreErrors) throws SQLException
	{
		return TableUtils.dropTable(this.connectionSource, dataClass, ignoreErrors);
	}


	/**
	 * Clear all data out of the table. For certain database types and with large sized tables, which may take a long
	 * time. In some configurations, it may be faster to drop and re-create the table. <p/> <p> <b>WARNING:</b> This is
	 * [obviously] very destructive and is unrecoverable. </p>
	 *
	 * @param dataClass
	 */
	public <T> int clearTable(Class<? extends BusinessObject> dataClass) throws SQLException
	{
		return TableUtils.clearTable(this.connectionSource, dataClass);
	}
}
