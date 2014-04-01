package ch.jmanagr.dal;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created by kije on 4/1/14.
 */
public class DBTest
{
	@Test
	public void testGetSql2o() throws Exception
	{
		assertNotNull(DB.getSql2o());
	}

	@Test
	public void testExecuteAndFetch() throws Exception
	{
		fail("Not yet implemented!");
	}

	@Test
	public void testExecuteAndFetchWithColumnMapping() throws Exception
	{
		fail("Not yet implemented!");
	}
}
