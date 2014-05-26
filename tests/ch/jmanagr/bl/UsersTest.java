package ch.jmanagr.bl;

public class UsersTest
{
	/*private Users users;

	private User testUser;

	@Before
	public void setUp() throws Exception
	{
		testUser = TestData.testUser;
		users = Users.getInstance();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testValidateUserExists() throws Exception
	{
		User testUser2 = new User(testUser.getFirstname(), testUser.getLastname(), testUser.getUsername(),
				testUser.getPassword(), testUser.getRole());
		assertEquals(users.validate(testUser2), STATUS_CODE.ALREADY_EXISTS);

		User testUser3 = new User("A", "B", "C", "D", USER_ROLE.ADMIN);
		assertEquals(users.validate(testUser3), STATUS_CODE.OK);

		User testUser4 = new User("A", "B", testUser.getUsername(), "D", USER_ROLE.ADMIN);
		assertEquals(users.validate(testUser4), STATUS_CODE.ALREADY_EXISTS);


	}

	@Test
	public void testValidateCredentials() throws Exception
	{
		User testUser2 = new User("|&%~`", "B", "T", "D", USER_ROLE.AGENT);
		assertEquals(STATUS_CODE.NAME_INVALID, users.validate(testUser2));

		User testUser3 = new User("A", "B", "¬&@?`", "D", USER_ROLE.AGENT);
		assertEquals(STATUS_CODE.USERNAME_INVALID, users.validate(testUser3));

		User testUser4 = new User("A", "B", "", "D", USER_ROLE.AGENT);
		assertEquals(STATUS_CODE.USERNAME_INVALID, users.validate(testUser4));

		User testUser5 = new User("A", "B", "C", "", USER_ROLE.AGENT);
		assertEquals(STATUS_CODE.PASSWORD_INVALID, users.validate(testUser5));

		User testUser6 = new User("A", "B", "C", "123", USER_ROLE.AGENT);
		assertEquals(STATUS_CODE.PASSWORD_INVALID, users.validate(testUser6));

		User testUser7 = new User("A", "B", "C", "Hello123imagoodpassword", USER_ROLE.AGENT);
		assertEquals(STATUS_CODE.OK, users.validate(testUser7));
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
	}*/
}
