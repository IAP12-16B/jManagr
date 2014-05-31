package ch.jmanagr.bl;


import ch.jmanagr.bo.Department;
import ch.jmanagr.bo.User;
import ch.jmanagr.lib.STATUS_CODE;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class DepartmentsTest
{
	private Departments departments;

	@Before
	public void setUp() throws Exception
	{
		departments = Departments.getInstance();
	}

	@Test
	public void testValidate() throws Exception
	{
		assertEquals(
				STATUS_CODE.OK, departments.validate(
				new Department(
						20, "Testdepartment",
						new ArrayList<User>(), true, false
				)
		)
		);
		assertEquals(
				STATUS_CODE.NAME_INVALID, departments.validate(
				new Department(
						20,
						"",
						new ArrayList<User>(),
						true,
						false
				)
		)
		);
		assertEquals(STATUS_CODE.FAIL, departments.validate(new Department(0, "", null, true, false)));
	}
}
