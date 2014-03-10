package ch.jManagr.test.lib;

import ch.jManagr.lib.ShutdownCallback;
import ch.jManagr.lib.ShutdownManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Objects;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class ShutdownManagerTest {

    private ShutdownManager shutdownManager;

    class TestClass implements ShutdownCallback {

        @Override
        public void shutdownCallback() {
            System.out.println("Shut down.");
        }
    }

    @Before
    public void setUp() throws Exception {
        this.shutdownManager = ShutdownManager.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        this.shutdownManager = null;
    }

    @Test
    public void testGetInstance() throws Exception {
        assertSame(this.shutdownManager, ShutdownManager.getInstance());
    }

    @Test
    public void testRegisterCallback() throws Exception {
        TestClass t = new TestClass();
        this.shutdownManager.registerCallback(t);
        assertTrue(this.shutdownManager.getRegisteredCallbacks().contains(t));
    }
}
