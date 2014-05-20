package ch.jmanagr.dal;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.lib.STATUS_CODE;
import org.sql2o.Connection;
import org.sql2o.Query;

import java.lang.reflect.ParameterizedType;
import java.util.*;

public abstract class AbstractDAL<BusinessObjectType extends BusinessObject> implements DAL<BusinessObjectType>
{
	protected DB db;

	// Temporary list
	protected List<BusinessObjectType> dataList;


	protected AbstractDAL()
	{
		db = DB.getInstance();
		dataList = new ArrayList<BusinessObjectType>();
	}


	@Override
	public STATUS_CODE delete(BusinessObjectType bo)
	{

		dataList.remove(bo);

		return STATUS_CODE.OK;
	}

	public Class<BusinessObjectType> getTClass()
	{
		return (Class<BusinessObjectType>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	protected List<BusinessObjectType> fetch(String columns, String other, HashMap<String, Object> parameters)
	{
		Class<BusinessObjectType> t = this.getTClass();
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

	protected List<BusinessObjectType> fetch(String columns, String other)
	{
		return this.fetch(columns, other, new HashMap<String, Object>());
	}

	protected List<BusinessObjectType> fetch(String columns)
	{
		return this.fetch(columns, "");
	}

}
