package ch.jmanagr.dal;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.lib.STATUS_CODE;

import java.util.ArrayList;
import java.util.List;

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
}
