package ch.jManagr.DAL;
import ch.jManagr.lib.ShutdownCallback;
import ch.jManagr.lib.ShutdownManager;



import java.util.prefs.Preferences;

/* Singelton for Database Credentials  */

public class DBCredentials implements ShutdownCallback {
    private static volatile DBCredentials instance;
    private Preferences prefs;

    /* DB Credentials with default values */
    private String DB_HOST = "localhost";
    private int DB_PORT = 3306;
    private String DB_DATABASE = "jManagr";
    private String DB_USER = "root";
    private String DB_PASSWORD = "";

    /**
     * Default constructor -> private, so only callable within class
     */
    private DBCredentials() {
        prefs = Preferences.userRoot().node(this.getClass().getName());
        ShutdownManager.getInstance().registerCallback(this);

        // load connection data
        DB_HOST = prefs.get("DB_HOST", DB_HOST);
        DB_PORT = prefs.getInt("DB_PORT", DB_PORT);
        DB_DATABASE = prefs.get("DB_DATABASE", DB_DATABASE);
        DB_USER = prefs.get("DB_USER", DB_USER);
        DB_PASSWORD = prefs.get("DB_PASSWORD", DB_PASSWORD);
    }

    /**
     * Get singelton instance
     *
     * @return DBCredentials    Singelton instance
     */
    public static DBCredentials getInstance() {
        if (instance == null) {
            synchronized (DBCredentials.class) {
                if (instance == null)
                    instance = new DBCredentials();
            }
        }
        return instance;
    }

    public String getHost() {
        return this.DB_HOST;
    }

    public void setHost(String host) {
        this.DB_HOST = host;
    }

    public int getPort() {
        return this.DB_PORT;
    }

    public void setPort(int port) {
        this.DB_PORT = port;
    }

    public String getDatabase() {
        return this.DB_DATABASE;
    }

    public void setDatabase(String db) {
        this.DB_DATABASE = db;
    }

    public String getUser() {
        return this.DB_USER;
    }

    public void setUser(String user) {
        this.DB_USER = user;
    }

    public String getPassword() {
        return this.DB_PASSWORD;
    }

    public void setPassword(String password) {
        this.DB_PASSWORD = password;
    }

    /**
     * called on shutdown
     */
    @Override
    public void shutdownCallback() {
        System.out.println("Saving db prefs");
        prefs.put("DB_HOST", DB_HOST);
        prefs.putInt("DB_PORT", DB_PORT);
        prefs.put("DB_DATABASE", DB_DATABASE);
        prefs.put("DB_USER", DB_USER);
        prefs.put("DB_PASSWORD", DB_PASSWORD);
    }
}
