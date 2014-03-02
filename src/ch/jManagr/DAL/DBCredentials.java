package ch.jManagr.DAL;

/* Singelton for Database Credentials  */

public class DBCredentials {
    private static DBCredentials instance = new DBCredentials();

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
    }

    /**
     * Get singelton instance
     *
     * @return DBCredentials    Singelton instance
     */
    public static DBCredentials getInstance() {
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
}
