package ch.jmanagr.bl;


import ch.jmanagr.lib.STATUS_CODE;

public class Settings
{
	private static volatile Settings instance;
	private ch.jmanagr.dal.Settings dal;

	private Settings()
	{
		dal = ch.jmanagr.dal.Settings.getInstance();
	}

	public static Settings getInstance()
	{
		if (instance == null) {
			synchronized (Settings.class) {
				if (instance == null) {
					instance = new Settings();
				}
			}
		}
		return instance;
	}

	public STATUS_CODE store(ch.jmanagr.bo.Settings settings)
	{
		return dal.store(settings);
	}

	public ch.jmanagr.bo.Settings retrieve()
	{
		return dal.retrieve();
	}
}
