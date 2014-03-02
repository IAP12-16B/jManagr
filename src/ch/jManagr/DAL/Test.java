package ch.jManagr.DAL;

import org.sql2o.Sql2o;
import java.util.List;

class main {
    public static void main(String[] args) {
        Test t = new Test();
        List<Dada> d = t.getAllTasks();
        for(Dada k : d) {
            System.out.println(k.getId() + k.getDescr());
        }
    }
}

public class Test {

    private Sql2o sql2o;

    public Test() {
        this.sql2o   = new Sql2o("jdbc:mysql://localhost:3306/myDB", "root", "");
    }

    public List<Dada> getAllTasks(){
        String sql = "SELECT id, no, descr FROM dada";
        return this.sql2o.createQuery(sql).executeAndFetch(Dada.class);
    }
}