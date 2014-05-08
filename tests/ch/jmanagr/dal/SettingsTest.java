package ch.jmanagr.dal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by kije on 4/1/14.
 */
public class SettingsTest
{
	private Settings settings;
	private ch.jmanagr.bo.Settings testSettings;

	@Before
	public void setUp() throws Exception
	{
		settings = Settings.getInstance();
	}

	@Test
	public void testPutAndGet() throws Exception
	{
		testSettings = settings.get();
		settings.put(testSettings);
		assertTrue(testSettings.equals(settings.get()));
	}
}
