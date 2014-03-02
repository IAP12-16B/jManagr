package ch.jManagr.DAL;

// NOTE: At the moment, this implementation is not safe against SQL injection.

import java.sql.*;


public class DB {
    private static DB instance = new DB();

    static {
        // load jdbc driver -> http://www.vogella.com/tutorials/MySQLJava/article.html  ?????????
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
     * @return Singelton instance
     */
    public static DB getInstance() {
        return instance;
    }

    /**
     * Get JDBC connection
     *
     * @return The Database connection
     * @throws SQLException
     */
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(
                String.format(
                        "jdbc:mysql://%s:%s/%s?user=%s&password=%s",
                        DBCredentials.getInstance().getHost(),
                        DBCredentials.getInstance().getPort(),
                        DBCredentials.getInstance().getDatabase(),
                        DBCredentials.getInstance().getUser(),
                        DBCredentials.getInstance().getPassword()
                )
        );
    }

    /**
     * Make a raw Query
     *
     * @param q the Query
     * @return returns the Result set
     * @throws SQLException
     */
    private ResultSet rawQuery(String q) throws SQLException {
        Statement statement = this.connect().createStatement();
        return statement.executeQuery(q);
    }

    /**
     * Select query
     *
     * @param query The Query. Note: SELECT will automatically prepended in front of it
     * @return The ResultSet
     * @throws SQLException
     */
    public ResultSet select(String query) throws SQLException {
        return this.rawQuery(String.format("SELECT %s", query));
    }

    /**
     * Select query
     *
     * @param fields The fields to select
     * @param from   The table(s) to select from
     * @return The ResultSet
     * @throws SQLException
     */
    public ResultSet select(String fields, String from) throws SQLException {
        return this.select(String.format("%s FROM %s", fields, from));
    }

    /**
     * Select query
     *
     * @param fields      The fields to select
     * @param from        The table(s) to select from
     * @param whereClause The where clause
     * @return The ResultSet
     * @throws SQLException
     */
    public ResultSet select(String fields, String from, String whereClause) throws SQLException {
        return this.select(String.format("%s FROM %s WHERE %s", fields, from, whereClause));
    }

    /**
     * Select query
     *
     * @param fields The fields to select
     * @param from   The table(s) to select from
     * @param limit  limit of results
     * @return The ResultSet
     * @throws SQLException
     */
    public ResultSet select(String fields, String from, int limit) throws SQLException {
        return this.select(String.format("%s FROM %s LIMIT %d", fields, from, limit));
    }

    /**
     * Select query
     *
     * @param fields The fields to select
     * @param from   The table(s) to select from
     * @param limit  limit of results
     * @param offset The offset the results should start from
     * @return The ResultSet
     * @throws SQLException
     */
    public ResultSet select(String fields, String from, int limit, int offset) throws SQLException {
        return this.select(String.format("%s FROM %s LIMIT %d,%d", fields, from, offset, limit));
    }

    /**
     * Select query
     *
     * @param fields      The fields to select
     * @param from        The table(s) to select from
     * @param whereClause The where clause
     * @param orderBy     The column the results should be ordered by
     * @return The ResultSet
     * @throws SQLException
     */
    public ResultSet select(String fields, String from, String whereClause, String orderBy) throws SQLException {
        return this.select(String.format("%s FROM %s WHERE %s ORDER BY %s", fields, from, whereClause, orderBy));
    }

    /**
     * Select query
     *
     * @param fields      The fields to select
     * @param from        The table(s) to select from
     * @param whereClause The where clause
     * @param limit       limit of results
     * @return The ResultSet
     * @throws SQLException
     */
    public ResultSet select(String fields, String from, String whereClause, int limit) throws SQLException {
        return this.select(String.format("%s FROM %s WHERE %s LIMIT %d", fields, from, whereClause, limit));
    }

    /**
     * Select query
     *
     * @param fields      The fields to select
     * @param from        The table(s) to select from
     * @param whereClause The where clause
     * @param limit       limit of results
     * @param offset      The offset the results should start from
     * @return The ResultSet
     * @throws SQLException
     */
    public ResultSet select(String fields, String from, String whereClause, int limit, int offset) throws SQLException {
        return this.select(String.format("%s FROM %s WHERE %s LIMIT %d,%d", fields, from, whereClause, offset, limit));
    }

    /**
     * Select query
     *
     * @param fields      The fields to select
     * @param from        The table(s) to select from
     * @param whereClause The where clause
     * @param limit       limit of results
     * @param orderBy     The column the results should be ordered by
     * @return The ResultSet
     * @throws SQLException
     */
    public ResultSet select(String fields, String from, String whereClause, int limit, String orderBy) throws SQLException {
        return this.select(String.format("%s FROM %s WHERE %s LIMIT %d ORDER BY %s", fields, from, whereClause, limit, orderBy));
    }

    /**
     * Select query
     *
     * @param fields      The fields to select
     * @param from        The table(s) to select from
     * @param whereClause The where clause
     * @param limit       limit of results
     * @param offset      The offset the results should start from
     * @param orderBy     The column the results should be ordered by
     * @return The ResultSet
     * @throws SQLException
     */
    public ResultSet select(String fields, String from, String whereClause, int limit, int offset, String orderBy) throws SQLException {
        return this.select(String.format("%s FROM %s WHERE %s LIMIT %d,%d ORDER BY %s", fields, from, whereClause, limit, offset, orderBy));
    }
}