package ch.jmanagr.dal;

import ch.jmanagr.lib.STATUS_CODE;

import java.util.prefs.Preferences;

public class Settings
{
	private Preferences preferences;
	private static volatile Settings instance;

	private Settings()
	{
		preferences = Preferences.userRoot().node(this.getClass().getName());
	}

	public STATUS_CODE put(ch.jmanagr.bo.Settings settings)
	{
		try {
			preferences.put("DB_HOST", settings.getHost());
			preferences.putInt("DB_PORT", settings.getPort());
			preferences.put("DB_USER", settings.getUser());
			preferences.put("DB_PASSWORD", settings.getPassword());
			preferences.put("DB_DATABASE", settings.getDatabase());
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.err.println(e.getLocalizedMessage());
			return STATUS_CODE.FAIL;
		}

		return STATUS_CODE.OK;
	}

	public ch.jmanagr.bo.Settings get()
	{
		ch.jmanagr.bo.Settings settings = new ch.jmanagr.bo.Settings();
		settings.setHost(preferences.get("DB_HOST", "127.0.0.1"));
		settings.setPort(preferences.getInt("DB_PORT", 3306));
		settings.setUser(preferences.get("DB_USER", "root"));
		settings.setPassword(preferences.get("DB_PASSWORD", ""));
		settings.setDatabase(preferences.get("DB_DATABASE", "jManagr"));

		return settings;
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
}
