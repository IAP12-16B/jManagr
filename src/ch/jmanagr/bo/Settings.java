package ch.jmanagr.bo;

/**
 * Class to store Settings
 */
public class Settings
{
	private String db_host;
	private String db_database;
	private String db_user;
	private String db_password;
	private int db_port;

	/**
	 * Default constructor
	 */
	public Settings()
	{}

	/**
	 * @param db_host     The host of the DB server
	 * @param db_database The database name
	 * @param db_user     The DB user name
	 * @param db_password the Password of the DB user
	 * @param db_port     the Port
	 */
	public Settings(String db_host, String db_database, String db_user, String db_password, int db_port)
	{
		this.setHost(db_host);
		this.setDatabase(db_database);
		this.setUser(db_user);
		this.setPassword(db_password);
		this.setPort(db_port);
	}

	/**
	 * @return String the Host
	 */
	public String getHost()
	{
		return db_host;
	}

	/**
	 * @param db_host The host of the DB server
	 */
	public void setHost(String db_host)
	{
		this.db_host = db_host;
	}

	/**
	 * @return the Database name
	 */
	public String getDatabase()
	{
		return db_database;
	}

	/**
	 * @param db_database the Database name
	 */
	public void setDatabase(String db_database)
	{
		this.db_database = db_database;
	}

	/**
	 * @return The DB user name
	 */
	public String getUser()
	{
		return db_user;
	}

	/**
	 * @param db_user The DB user name
	 */
	public void setUser(String db_user)
	{
		this.db_user = db_user;
	}

	/**
	 * @return the Password of the DB user
	 */
	public String getPassword()
	{
		return db_password;
	}

	/**
	 * @param db_password the Password of the DB user
	 */
	public void setPassword(String db_password)
	{
		this.db_password = db_password;
	}

	/**
	 * @return the Port
	 */
	public int getPort()
	{
		return db_port;
	}

	/**
	 * @param db_port the Port
	 */
	public void setPort(int db_port)
	{
		this.db_port = db_port;
	}


	@Override
	public boolean equals(Object o)
	{
		if (this == o) { return true; }
		if (!(o instanceof Settings)) { return false; }

		Settings settings = (Settings) o;

		if (db_port != settings.db_port) { return false; }
		if (db_database != null ? !db_database.equals(settings.db_database) : settings.db_database != null) {
			return false;
		}
		if (db_host != null ? !db_host.equals(settings.db_host) : settings.db_host != null) { return false; }
		if (db_password != null ? !db_password.equals(settings.db_password) : settings.db_password != null) {
			return false;
		}
		if (db_user != null ? !db_user.equals(settings.db_user) : settings.db_user != null) { return false; }

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = db_host != null ? db_host.hashCode() : 0;
		result = 31 * result + (db_database != null ? db_database.hashCode() : 0);
		result = 31 * result + (db_user != null ? db_user.hashCode() : 0);
		result = 31 * result + (db_password != null ? db_password.hashCode() : 0);
		result = 31 * result + db_port;
		return result;
	}
}
