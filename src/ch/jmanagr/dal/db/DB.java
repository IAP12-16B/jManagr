package ch.jmanagr.dal.db;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.dal.SettingsDAL;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DB
{
	private static volatile DB instance;
	private ch.jmanagr.bo.Settings settings;

	private JdbcPooledConnectionSource connectionSource;


	/**
	 *
	 */
	private DB()
	{
		this.setSettings(SettingsDAL.getInstance().retrieve());
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

	public void setSettings(ch.jmanagr.bo.Settings settings)
	{
		this.settings = settings;
		this.updateConnection();

	}

	protected void updateConnection()
	{
		try {
			connectionSource =
					new JdbcPooledConnectionSource(
							String.format(
									"jdbc:mysql://%s:%d/%s",
									this.settings.getHost(),
									this.settings.getPort(),
									settings.getDatabase()
							),
							this.settings.getUser(),
							this.settings.getPassword()
					);


			// setup connectionsource
			connectionSource.setMaxConnectionAgeMillis(4 * 60 * 1000);
			connectionSource.setCheckConnectionsEveryMillis(60 * 1000);
			connectionSource.setTestBeforeGet(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection()
	{
		try {
			connectionSource.close();
		} catch (SQLException e) {
			e.printStackTrace(); // todo log
		}
	}

	public void shutdown()
	{
		this.closeConnection();
	}

	public ConnectionSource getConnectionSource()
	{
		return this.connectionSource;
	}

	public int createTable(Class<? extends BusinessObject> cls) throws SQLException
	{
		return TableUtils.createTable(this.connectionSource, cls);
	}

	public int createTableIfNotExists(Class cls) throws SQLException
	{
		return TableUtils.createTableIfNotExists(this.connectionSource, cls);
	}

	public int createTable(DatabaseTableConfig conf) throws SQLException
	{
		return TableUtils.createTable(this.connectionSource, conf);
	}

	public int createTableIfNotExists(DatabaseTableConfig conf) throws SQLException
	{
		return TableUtils.createTableIfNotExists(this.connectionSource, conf);
	}

	/**
	 * Clear all data out of the table. For certain database types and with large sized tables, which may take a long
	 * time. In some configurations, it may be faster to drop and re-create the table. <p/> <p> <b>WARNING:</b> This is
	 * [obviously] very destructive and is unrecoverable. </p>
	 *
	 * @param tableConfig
	 */
	public <T> int clearTable(DatabaseTableConfig<T> tableConfig) throws SQLException
	{return TableUtils.clearTable(this.connectionSource, tableConfig);}

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
	{return TableUtils.dropTable(this.connectionSource, dataClass, ignoreErrors);}

	/**
	 * Issue the database statements to drop the table associated with a table configuration. <p/> <p> <b>WARNING:</b>
	 * This is [obviously] very destructive and is unrecoverable. </p>
	 *
	 * @param tableConfig  Hand or spring wired table configuration. If null then the class must have {@link
	 *                     com.j256.ormlite.field.DatabaseField} annotations.
	 * @param ignoreErrors If set to true then try each statement regardless of {@link java.sql.SQLException} thrown
	 *                     previously.
	 *
	 * @return The number of statements executed to do so.
	 */
	public <T, ID> int dropTable(DatabaseTableConfig<T> tableConfig, boolean ignoreErrors) throws SQLException
	{return TableUtils.dropTable(this.connectionSource, tableConfig, ignoreErrors);}

	/**
	 * Clear all data out of the table. For certain database types and with large sized tables, which may take a long
	 * time. In some configurations, it may be faster to drop and re-create the table. <p/> <p> <b>WARNING:</b> This is
	 * [obviously] very destructive and is unrecoverable. </p>
	 *
	 * @param dataClass
	 */
	public <T> int clearTable(Class<? extends BusinessObject> dataClass) throws SQLException
	{return TableUtils.clearTable(this.connectionSource, dataClass);}
}
