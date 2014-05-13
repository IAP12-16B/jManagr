package ch.jmanagr;

import ch.jmanagr.bo.*;
import ch.jmanagr.dal.Departments;
import ch.jmanagr.dal.Resources;
import ch.jmanagr.dal.Tickets;
import ch.jmanagr.dal.Users;
import ch.jmanagr.lib.TICKET_STATE;
import ch.jmanagr.lib.USER_ROLE;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Class with Test-Data to prevent a) redundancy of creating test objects in each test b) make sure, database relations
 * are valid
 */
public class TestData
{
	public static User testUser;
	public static Ticket testTicket;
	public static Resource testResource;
	public static Department testDepartment;
	public static Agent testAgent;
	public static Admin testAdmin;

	static {
		int randomPostfix = (int) (Math.random() * 1000 + 1);
		testUser = new User("Testuser", "Tester", "test" + randomPostfix, "password", USER_ROLE.USER);

		testAgent = new Agent("Testagent", "MasterTester", "a.test" + randomPostfix, "tst", testDepartment);

		ArrayList<Agent> agentsTestList = new ArrayList<>();
		agentsTestList.add(testAgent);
		testDepartment = new Department("Testdepartment" + randomPostfix, agentsTestList);

		testAdmin = new Admin("DaAdmin", "Root", "r.oot" + randomPostfix, "imsupersecret", testDepartment);

		ArrayList<Ticket> testTicketList = new ArrayList<>();
		testTicketList.add(testTicket);

		HashMap<String, String> testResData = new HashMap<>();
		testResData.put("Size", "500 m2");
		testResource = new Resource("Testres.", testResData, testTicketList, null, new ArrayList<Resource>());

		testTicket = new Ticket("Testticket", "The Testticket", TICKET_STATE.OPEN, new Date(), testResource,
				testAgent, testDepartment, testUser);

		Users.getInstance().create(testUser);

		Departments.getInstance().create(testDepartment);
		Users.getInstance().create(testAgent);
		Users.getInstance().create(testAdmin);

		Resources.getInstance().create(testResource);
		Tickets.getInstance().create(testTicket);
	}

	public TestData() {}

	private static Uninit uniniter = new Uninit();

	// to delete database entries
	private static class Uninit
	{
		public Uninit() {}

		public void finalize()
		{
			/*Users.getInstance().delete(testUser);
			Tickets.getInstance().delete(testTicket);
			Resources.getInstance().delete(testResource);
			Departments.getInstance().delete(testDepartment);
			Users.getInstance().delete(testAgent);
			Users.getInstance().delete(testAdmin);*/
		}
	}
}
