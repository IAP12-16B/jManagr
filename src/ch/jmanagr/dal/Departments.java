package ch.jmanagr.dal;


import ch.jmanagr.bo.Department;
import ch.jmanagr.lib.STATUS_CODE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Departments extends AbstractDAL<Department>
{

	private static Departments instance;

	private Departments()
	{
		super();
		// Todo: add sample data
	}

	/**
	 * @return Departments instance
	 */
	public static Departments getInstance()
	{
		if (instance == null) {
			synchronized (Departments.class) {
				if (instance == null) {
					instance = new Departments();
				}
			}
		}
		return instance;
	}

	@Override
	public STATUS_CODE create(Department bo)
	{
		dataList.add(bo);
		return STATUS_CODE.OK;
		// TODO: implement
	}

	@Override
	public ObservableList<Department> fetch()
	{
        List<Department> deps = db.createQuery("SELECT id, name FROM departments").executeAndFetch(Department.class); // todo kim pls mach dass das goht i weiss nit was du für komischi sache in DB klass gmacht hesch :P
        ObservableList<Department> depsObsList= FXCollections.observableArrayList(deps); // Now add observability by wrapping it with ObservableList. //changes to list will not fire an event, only changes to observerList

        return depsObsList;
        //return dataList;
		/*List<Department> deps = db.executeAndFetch("SELECT id, name FROM departments", Department.class);
		return deps;*/
		// TODO: implement
	}

	@Override
	public STATUS_CODE update(Department bo)
	{
		for (Department department : dataList) {
			if (department.getId() == bo.getId()) {
				dataList.set(dataList.indexOf(department), bo);
				return STATUS_CODE.OK;
			}
		}

		return STATUS_CODE.FAIL; // TODO: Status for failed update
		// TODO: implement
	}
}
