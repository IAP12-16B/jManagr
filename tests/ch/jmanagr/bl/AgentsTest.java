package ch.jmanagr.bl;

import ch.jmanagr.bo.Agent;
import ch.jmanagr.bo.Department;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.fail;


public class AgentsTest
{
	private Agents agents;
	private Department testDepartment;

	@Before
	public void setUp()
	{
		agents = Agents.getInstance();
	}

	@Test
	public void testGetAllByDepartment() throws Exception
	{
		testDepartment = new Department(10, "Test", new ArrayList<Agent>());
		// Todo: implement test
		fail("Not yet implemented!");

	}
}
