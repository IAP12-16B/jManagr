package ch.jmanagr.bl;


import ch.jmanagr.bo.*;
import ch.jmanagr.lib.STATUS_CODE;
import ch.jmanagr.lib.TICKET_STATE;
import ch.jmanagr.lib.USER_ROLE;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class Tickets extends AbstractBL<Ticket, ch.jmanagr.dal.Tickets>
{
	private static volatile Tickets instance;

	private Tickets()
	{
		super();
		dal = ch.jmanagr.dal.Tickets.getInstance();
	}


	public static Tickets getInstance()
	{
		if (instance == null) {
			synchronized (Tickets.class) {
				if (instance == null) {
					instance = new Tickets();
				}
			}
		}
		return instance;
	}


	@Override
	public STATUS_CODE validate(Ticket bo)
	{
		// Todo: implement
		return STATUS_CODE.OK;
	}

	public List<Ticket> getAllByUser(User user, TICKET_STATE state)
	{
		// Todo: implement with dal

		// test implementation
		List<Ticket> result = new ArrayList<Ticket>();

		ArrayList<Ticket> testTicketList = new ArrayList<Ticket>();
		ArrayList<Agent> testAgentsList = new ArrayList<Agent>();
		Resource testResource = new Resource(
				1,
				"Test-Resource ",
				new HashMap<String, String>(),
				testTicketList,
				null,
				null
		);

		Department testDepartment = new Department(20, "Test Department", testAgentsList);

		Agent testAgent = new Agent(
				2,
				"Test",
				"Agent",
				"agent",
				"pw",
				USER_ROLE.AGENT,
				testDepartment
		);

		testAgentsList.add(testAgent);

		for (int i = 0, num = (int) (Math.random() * 10); i < num; i++) {

			Ticket t = new Ticket(
					i,
					"Test " + i,
					"A test ticket nr. " + i,
					state,
					new Date(),
					testResource,
					testAgent,
					testDepartment,
					user
			);
			testTicketList.add(t);
			result.add(t);
		}

		return result;
	}

	public List<Ticket> getAllByAgent(Agent agent, TICKET_STATE state)
	{
		// Todo: implement with dal

		// test implementation
		List<Ticket> result = new ArrayList<Ticket>();

		ArrayList<Ticket> testTicketList = new ArrayList<Ticket>();
		ArrayList<Agent> testAgentsList = new ArrayList<Agent>();
		Resource testResource = new Resource(
				1,
				"Test-Resource ",
				new HashMap<String, String>(),
				testTicketList,
				null,
				null
		);

		Department testDepartment = new Department(20, "Test Department", testAgentsList);

		User testUser = new User();

		testAgentsList.add(agent);

		for (int i = 0, num = (int) (Math.random() * 10); i < num; i++) {

			Ticket t = new Ticket(
					i,
					"Test " + i,
					"A test ticket nr. " + i,
					state,
					new Date(),
					testResource,
					agent,
					testDepartment,
					testUser
			);
			testTicketList.add(t);
			result.add(t);
		}

		return result;
	}

	public List<Ticket> getAllByResource(Resource resource, TICKET_STATE state)
	{
		// Todo: implement with dal

		// test implementation
		List<Ticket> result = new ArrayList<Ticket>();
		ArrayList<Agent> testAgentsList = new ArrayList<Agent>();


		Department testDepartment = new Department(20, "Test Department", testAgentsList);

		User testUser = new User();

		Agent testAgent = new Agent(
				2,
				"Test",
				"Agent",
				"agent",
				"pw",
				USER_ROLE.AGENT,
				testDepartment
		);

		testAgentsList.add(testAgent);

		for (int i = 0, num = (int) (Math.random() * 10); i < num; i++) {

			Ticket t = new Ticket(
					i,
					"Test " + i,
					"A test ticket nr. " + i,
					state,
					new Date(),
					resource,
					testAgent,
					testDepartment,
					testUser
			);
			result.add(t);
		}

		return result;
	}

	public List<Ticket> getAllByDepartment(Department department, TICKET_STATE state)
	{
		// Todo: implement with dal

		// test implementation
		List<Ticket> result = new ArrayList<Ticket>();

		ArrayList<Ticket> testTicketList = new ArrayList<Ticket>();
		ArrayList<Agent> testAgentsList = new ArrayList<Agent>();
		Resource testResource = new Resource(
				1,
				"Test-Resource ",
				new HashMap<String, String>(),
				testTicketList,
				null,
				null
		);


		User testUser = new User();

		Agent testAgent = new Agent(
				2,
				"Test",
				"Agent",
				"agent",
				"pw",
				USER_ROLE.AGENT,
				department
		);

		testAgentsList.add(testAgent);

		for (int i = 0, num = (int) (Math.random() * 10); i < num; i++) {

			Ticket t = new Ticket(
					i,
					"Test " + i,
					"A test ticket nr. " + i,
					state,
					new Date(),
					testResource,
					testAgent,
					department,
					testUser
			);
			result.add(t);
		}

		return result;
	}

}