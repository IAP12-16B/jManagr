package ch.jManagr.test.DAL;

import ch.jManagr.DAL.DBCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        assertSame(this.dbc, DBCredentials.getInstance());
    }
}
