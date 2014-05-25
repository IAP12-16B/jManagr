package ch.jmanagr.bo;


public class Settings
{
	private String db_host;
	private String db_database;
	private String db_user;
	private String db_password;
	private int db_port;

	public Settings()
	{

	}

	public Settings(String db_host, String db_database, String db_user, String db_password, int db_port)
	{
		this.db_host = db_host;
		this.db_database = db_database;
		this.db_user = db_user;
		this.db_password = db_password;
		this.db_port = db_port;
	}

	/**
	 * @return String the Host
	 */
	public String getHost()
	{
		return db_host;
	}

	/**
	 * @param db_host
	 */
	public void setHost(String db_host)
	{
		this.db_host = db_host;
	}

	public String getDatabase()
	{
		return db_database;
	}

	public void setDatabase(String db_database)
	{
		this.db_database = db_database;
	}

	public String getUser()
	{
		return db_user;
	}

	public void setUser(String db_user)
	{
		this.db_user = db_user;
	}

	public String getPassword()
	{
		return db_password;
	}

	public void setPassword(String db_password)
	{
		this.db_password = db_password;
	}

	public int getPort()
	{
		return db_port;
	}

	public void setPort(int db_port)
	{
		this.db_port = db_port;
	}

}
