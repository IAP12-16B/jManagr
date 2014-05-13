package ch.jmanagr.dal;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.lib.STATUS_CODE;
import org.sql2o.Connection;
import org.sql2o.Query;

import java.lang.reflect.ParameterizedType;
import java.util.*;

public abstract class AbstractDAL<T extends BusinessObject> implements DAL<T>
{
	protected DB db;

	// Temporary list
	protected List<T> dataList;


	protected AbstractDAL()
	{
		db = DB.getInstance();
		dataList = new ArrayList<T>();
	}


	@Override
	public STATUS_CODE delete(T bo)
	{


		/*try {
			String deleteSQL = "DELETE FROM " + bo.getTable() + " WHERE id = :id";
			DB.getSql2o().createQuery(deleteSQL)
					.addParameter("id", bo.getId())
					.executeUpdate();
		} catch (Exception e) { // TODO: better fail handling
			e.printStackTrace();
			return STATUS_CODE.FAIL;
		}*/

		dataList.remove(bo);

		return STATUS_CODE.OK;
	}

	public Class<T> getTClass()
	{
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected List<T> fetch(String columns, String other, HashMap<String, Object> parameters)
	{
		Class<T> t = this.getTClass();
		try (Connection con = DB.getSql2o().open()) {
			Query q = con.createQuery(String.format("SELECT %s FROM %s %s;", columns, t.getSimpleName(), other));

			// adding parameters
			if (!parameters.isEmpty()) {
				Iterator<Map.Entry<String, Object>> it = parameters.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, Object> pairs = it.next();
					q.addParameter(pairs.getKey(), pairs.getValue());
					it.remove(); // avoids a ConcurrentModificationException
				}
			}


			return q.executeAndFetch(t);
		}
	}

	protected List<T> fetch(String columns, String other)
	{
		return this.fetch(columns, other, new HashMap<String, Object>());
	}

	protected List<T> fetch(String columns)
	{
		return this.fetch(columns, "");
	}
}
