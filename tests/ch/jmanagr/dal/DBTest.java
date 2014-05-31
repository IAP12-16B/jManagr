package ch.jmanagr.dal;


import org.junit.Test;

import static org.junit.Assert.assertNotNull;

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

	// TODO do more tests?
}
