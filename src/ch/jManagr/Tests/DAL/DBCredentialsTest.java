package ch.jManagr.Tests.DAL;

import ch.jManagr.DAL.DBCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DBCredentialsTest {

    DBCredentials dbc;

    @Before
    public void setUp() throws Exception {
        this.dbc = DBCredentials.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        this.dbc = null;
    }

    @Test
    public void testGetInstance() throws Exception {
        assertTrue(this.dbc.equals(DBCredentials.getInstance()));
    }

    @Test
    public void testGetHost() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testSetHost() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testGetPort() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testSetPort() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testGetDatabase() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testSetDatabase() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testGetUser() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testSetUser() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testGetPassword() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testSetPassword() throws Exception {
        fail("Not yet implemented");
    }
}
