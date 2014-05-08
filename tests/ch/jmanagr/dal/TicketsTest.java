package ch.jmanagr.dal;

import ch.jmanagr.bo.Ticket;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;


public class TicketsTest
{
	private Tickets tickets;

	@Before
	public void setUp() throws Exception
	{
		tickets = Tickets.getInstance();
	}

	@Test
	public void testCreate() throws Exception
	{
		// Todo: implement test
		fail("Not yet implemented!");
	}

	@Test
	public void testFetchAll() throws Exception
	{
		for (Ticket ticket : tickets.fetch()) {
			System.out.println(ticket.toString());
		}


		fail("Not yet implemented!");
	}

	@Test
	public void testUpdate() throws Exception
	{
		// Todo: implement test
		fail("Not yet implemented!");
	}

}
