package ch.jmanagr.lib;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kije on 5/29/14.
 */
public class TICKET_STATETest
{
	@Test
	public void testToString() throws Exception
	{
		assertEquals("0", TICKET_STATE.OPEN.toString());
	}


}
