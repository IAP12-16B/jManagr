package ch.jmanagr.bo;


import ch.jmanagr.lib.USER_ROLE;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by kije on 4/21/14.
 */
public class UserTest
{
	public User testUser;

	@Before
	public void setUp() throws Exception
	{
		this.testUser = new User("Test_User", "Testi", "T.Test", "123", USER_ROLE.USER, true, false);
	}

	@After
	public void tearDown() throws Exception
	{
		this.testUser = null;
	}

	@Test
	public void testHashPassword() throws Exception
	{
		String pw = "123";
		String pwHash = User.hashPassword(pw);

		assertTrue(User.checkPassword(pw, pwHash));
	}
}
