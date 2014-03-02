package ch.jManagr.lib;


import java.util.ArrayList;
import java.util.Iterator;

public class ShutdownManager extends Thread {
    private static ShutdownManager instance = new ShutdownManager();
    private ArrayList<ShutdownCallback> shutdownCallbacks;

    private ShutdownManager() {
        super();
        shutdownCallbacks = new ArrayList<ShutdownCallback>();
    }

    public static ShutdownManager getInstance() {
        return instance;
    }

    public void run() {
        System.out.println("Shut down");
        ShutdownManager.getInstance().runCallbacks();
        System.out.println("Finished shutdown");
    }

    public void registerCallback(ShutdownCallback sc) {
        System.out.println("Register "+sc.getClass().getName()+" for shutdown");
        this.shutdownCallbacks.add(sc);
    }

    private void runCallbacks() {
        Iterator<ShutdownCallback> it = shutdownCallbacks.iterator();
        while(it.hasNext())
        {
            ShutdownCallback sc = (ShutdownCallback)it;
            System.out.println("call registered shutdownfunction of "+sc.getClass().getName());
            sc.shutdownCallback();
        }
    }

}