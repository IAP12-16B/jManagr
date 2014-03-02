package ch.jManagr.Tests.DAL;

import ch.jManagr.DAL.DB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DBTest {

    public DB db;

    @Before
    public void setUp() throws Exception {
        this.db = DB.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        this.db = null;
    }

    @Test
    public void testGetInstance() throws Exception {
        assertTrue(this.db.equals(DB.getInstance()));
    }

    @Test
    public void testSelect() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testSelect1() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testSelect2() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testSelect3() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testSelect4() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testSelect5() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testSelect6() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testSelect7() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testSelect8() throws Exception {
        fail("Not yet implemented");
    }

    @Test
    public void testSelect9() throws Exception {
        fail("Not yet implemented");
    }
}
