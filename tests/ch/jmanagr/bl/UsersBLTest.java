package ch.jmanagr.bl;

import ch.jmanagr.bo.Settings;
import ch.jmanagr.bo.User;
import ch.jmanagr.dal.SettingsDAL;
import ch.jmanagr.dal.db.DB;
import org.junit.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assume.assumeNotNull;


public class UsersBLTest
{
	private UsersBL bl;

	private User testUser;

	private final String testPw = "testpw";

	@Before
	public void setUp() throws Exception
	{
		bl = UsersBL.getInstance();

		testUser = new User();
		testUser.setUsername("testUser");
		testUser.setUnhashedPassword(testPw);
		bl.save(testUser);
	}

	@After
	public void tearDown() throws Exception
	{
		bl.delete(testUser);
		bl = null;
		System.gc();
	}

	@BeforeClass
	public static void beforeClass() throws Exception
	{
		DB.getInstance().setSettings(
				new Settings(
						SettingsDAL.getInstance().retrieve().getHost(),
						"jManagr_test",
						SettingsDAL.getInstance().retrieve().getUser(),
						SettingsDAL.getInstance().retrieve().getPassword(),
						SettingsDAL.getInstance().retrieve().getPort()
				)
		);
	}

	@AfterClass
	public static void afterClass() throws Exception
	{
		UsersBL.getInstance().dal.clearTable();
	}


	@Test
	public void testLogin() throws Exception
	{
		assumeNotNull(DB.getInstance());
		assumeNotNull(bl);

		assertNotNull(bl.login(testUser.getUsername(), testPw));
		assertNull(bl.login("a", "b"));
	}
}
