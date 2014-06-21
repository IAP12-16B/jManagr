package ch.jmanagr.bo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest
{

	private User testUser;

	public static final String TESTPASSWORD = "Test123";

	@Before
	public void setUp() throws Exception
	{
		testUser = new User();
	}

	@After
	public void tearDown() throws Exception
	{
		testUser = null;
		System.gc();
	}

	@Test
	public void testPasswordHashing() throws Exception
	{

		testUser.setUnhashedPassword(TESTPASSWORD);
		assertTrue(
				testUser.checkPassword(TESTPASSWORD)
		);

		assertFalse(
				testUser.checkPassword("wrongpassword")
		);

		// Check, if password really gets hashed
		assertNotEquals(testUser.getPassword(), TESTPASSWORD);
	}
}
