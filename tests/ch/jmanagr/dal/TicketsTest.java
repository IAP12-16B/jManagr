package ch.jmanagr.dal;

import ch.jmanagr.TestData;
import ch.jmanagr.bo.Ticket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;


public class TicketsTest
{
	private Tickets tickets;
	private Ticket testTicket;

	@Before
	public void setUp() throws Exception
	{
		tickets = Tickets.getInstance();
		testTicket = TestData.testTicket;
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testCreate() throws Exception
	{

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
