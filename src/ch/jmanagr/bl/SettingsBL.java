package ch.jmanagr.bl;


import ch.jmanagr.dal.SettingsDAL;
import ch.jmanagr.lib.STATUS_CODE;

public class SettingsBL
{
	private static SettingsBL instance;
	private SettingsDAL dal;

	private SettingsBL()
	{
		dal = SettingsDAL.getInstance();
	}

	/**
	 * @return a singleton instance
	 */
	public static SettingsBL getInstance()
	{
		if (instance == null) {
			synchronized (SettingsBL.class) {
				if (instance == null) {
					instance = new SettingsBL();
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
