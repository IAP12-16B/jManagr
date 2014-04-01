package ch.jmanagr.bl;

import ch.jmanagr.bo.User;
import ch.jmanagr.lib.STATUS_CODE;
import ch.jmanagr.lib.USER_ROLE;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class UsersTest
{
	private Users users;

	private User testUser;

	@Before
	public void setUp() throws Exception
	{
		testUser = new User("Test", "User", "test123", "test", USER_ROLE.USER);
		users = Users.getInstance();
		users.create(testUser);
	}

	@After
	public void tearDown() throws Exception
	{
		users.delete(testUser);
	}

	@Test
	public void testValidateUserExists() throws Exception
	{
		User testUser2 = new User(testUser.getFirstname(), testUser.getLastname(), testUser.getUsername(), testUser.getPassword(), testUser.getRole());
		assertEquals(users.validate(testUser2), STATUS_CODE.ALREADY_EXISTS);

		User testUser3 = new User("A", "B", "C", "D", USER_ROLE.ADMIN);
		assertEquals(users.validate(testUser3), STATUS_CODE.OK);

		User testUser4 = new User("A", "B", testUser.getUsername(), "D", USER_ROLE.ADMIN);
		assertEquals(users.validate(testUser4), STATUS_CODE.ALREADY_EXISTS);


	}

	@Test
	public void  testValidateCredentials() throws Exception {
		User testUser2 = new User("|&%~`", "B", "T", "D", USER_ROLE.AGENT);
		assertEquals(users.validate(testUser2), STATUS_CODE.NAME_INVALID);

		User testUser3 = new User("A", "B", "¬&@?`", "D", USER_ROLE.AGENT);
		assertEquals(users.validate(testUser3), STATUS_CODE.USERNAME_INVALID);

		User testUser4 = new User("A", "B", "", "D", USER_ROLE.AGENT);
		assertEquals(users.validate(testUser4), STATUS_CODE.USERNAME_INVALID);

		User testUser5 = new User("A", "B", "C", "", USER_ROLE.AGENT);
		assertEquals(users.validate(testUser5), STATUS_CODE.PASSWORD_INVALID);

		User testUser6 = new User("A", "B", "C", "123", USER_ROLE.AGENT);
		assertEquals(users.validate(testUser6), STATUS_CODE.PASSWORD_INVALID);
	}

	@Test
	public void testLogin() throws Exception
	{
		assertNotNull(users.login(testUser.getUsername(), testUser.getPassword()));
	}

	@Test
	public void testGetCurrentUser() throws Exception
	{
		users.login(testUser.getUsername(), testUser.getPassword());
		assertEquals(users.getCurrentUser(), testUser);
	}
}
