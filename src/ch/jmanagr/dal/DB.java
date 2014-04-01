package ch.jmanagr.dal;


import org.sql2o.Sql2o;

public class DB {
    private static volatile DB instance;

    private Sql2o sql2o;

    /**
     *
     */
    private DB() {
        sql2o = new Sql2o();
    }


    /**
     *
     * @return DB instance
     */
    public static DB getInstance() {
        if (instance == null) {
            synchronized (DB.class) {
                if (instance == null) {
                    instance = new DB();
                }
            }
        }
        return instance;
    }
}
