package ch.jmanagr.dal;

import ch.jmanagr.dal.db.DB;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.STATUS_CODE;

import java.sql.SQLException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Dal for Settings. This does not use DB connection. Hence, it doesn't need to implement {@link
 * ch.jmanagr.dal.AbstractDAL}
 */
public class SettingsDAL
{
	private static SettingsDAL instance;
	private Preferences preferences;

	private SettingsDAL()
	{
		preferences = Preferences.userRoot().node(this.getClass().getName());
	}

	/**
	 * Get singelton instance
	 *
	 * @return An SettingsDAL instance
	 *
	 * @throws SQLException
	 */
	public static SettingsDAL getInstance()
	{
		if (instance == null) {
			synchronized (SettingsDAL.class) {
				if (instance == null) {
					instance = new SettingsDAL();
				}
			}
		}
		return instance;
	}

	/**
	 * Saves the settings
	 *
	 * @param settings The settings to store
	 *
	 * @return Whether it was successful or not
	 */
	public STATUS_CODE store(ch.jmanagr.bo.Settings settings)
	{
		try {
			preferences.put("DB_HOST", settings.getHost());
			preferences.putInt("DB_PORT", settings.getPort());
			preferences.put("DB_USER", settings.getUser());
			preferences.put("DB_PASSWORD", settings.getPassword());
			preferences.put("DB_DATABASE", settings.getDatabase());
			preferences.sync();
			preferences.flush();

			DB.getInstance().setSettings(settings);

		} catch (IllegalStateException | BackingStoreException | SQLException e) {
			Logger.log(LOG_LEVEL.ERROR, "Fail on store settings", e);
			return STATUS_CODE.FAIL;
		}


		return STATUS_CODE.OK;
	}

	/**
	 * Retrieves the stored Settings
	 *
	 * @return the Settings
	 */
	public ch.jmanagr.bo.Settings retrieve()
	{
		ch.jmanagr.bo.Settings settings = new ch.jmanagr.bo.Settings();
		settings.setHost(preferences.get("DB_HOST", "127.0.0.1"));
		settings.setPort(preferences.getInt("DB_PORT", 3306));
		settings.setUser(preferences.get("DB_USER", "root"));
		settings.setPassword(preferences.get("DB_PASSWORD", ""));
		settings.setDatabase(preferences.get("DB_DATABASE", "jManagr"));

		return settings;
	}
}
