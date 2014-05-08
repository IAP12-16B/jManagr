package ch.jmanagr.bl;

import ch.jmanagr.bo.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class TicketsTest
{

	private Tickets tickets;
	private User testUser;

	@Before
	public void setUp() throws Exception
	{
		this.tickets = Tickets.getInstance();
	}

	@Test
	public void testValidate() throws Exception
	{
		fail("Not yet implemented!");
	}

	@Test
	public void testGetAllByUser() throws Exception
	{
		fail("Not yet implemented!");
	}

	@Test
	public void testGetAllByAgent() throws Exception
	{
		fail("Not yet implemented!");
	}

	@Test
	public void testGetAllByResource() throws Exception
	{
		fail("Not yet implemented!");
	}

	@Test
	public void testGetAllByDepartment() throws Exception
	{
		fail("Not yet implemented!");
	}
}
