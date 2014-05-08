package ch.jmanagr.bl;

import ch.jmanagr.bo.Agent;
import ch.jmanagr.bo.Department;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;


public class AgentsTest
{
	private Agents agents;
	private Department testDepartment;

	@Before
	public void setUp()
	{
		agents = Agents.getInstance();
		testDepartment = new Department(10, "Test", new ArrayList<Agent>());
	}

	@Test
	public void testGetAllByDepartment() throws Exception
	{
		for (Agent agent : agents.getAllByDepartment(testDepartment)) {
			assertTrue(testDepartment.equals(agent.getDepartment()));
		}

	}
}
