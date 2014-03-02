package ch.jManagr.DAL;

import java.sql.*;

public class DB {
    private static DB instance = new DB();

    // static code block
    static {
        // load jdbc driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private DB() {
    }

    /**
     * Get singelton instance
     *
     * @return DB    Singelton instance
     */
    public static DB getInstance() {
        return instance;
    }

    /**
     * Get JDBC connection
     *
     * @return Connection   The Database connection
     * @throws SQLException
     */
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(
                String.format(
                        "jdbc:mysql://%s:%s/%s?user=%s&password=%s",
                        DBCredentials.getInstance().getHost(),
                        DBCredentials.getInstance().getPort(),
                        DBCredentials.getInstance().getDatabase(),
                        DBCredentials.getInstance().getPassword()
                )
        );
    }

    public ResultSet select(String q) throws SQLException {
        Statement statement = this.connect().createStatement();
        return statement.executeQuery(q);
    }
}
