package ch.jmanagr.dal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kije on 4/1/14.
 */
public class SettingsDALTest
{
	private SettingsDAL settingsDAL;
	private ch.jmanagr.bo.Settings testSettings;

	@Before
	public void setUp() throws Exception
	{
		settingsDAL = SettingsDAL.getInstance();
	}

	@Test
	public void testPutAndGet() throws Exception
	{
		testSettings = settingsDAL.retrieve();
		settingsDAL.store(testSettings);
		assertEquals(settingsDAL.retrieve(), testSettings);
	}
}
