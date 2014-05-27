package ch.jmanagr.dal;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.STATUS_CODE;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.HashMap;
import java.util.List;

public abstract class AbstractDAL<BusinessObjectType extends BusinessObject> implements DAL<BusinessObjectType>
{
	protected DB db;

	protected final String tableName;
	protected final Class<BusinessObjectType> tClass;

	protected AbstractDAL(Class<BusinessObjectType> cls)
	{
		db = DB.getInstance();
		this.tClass = cls;
		this.tableName = this.tClass.getSimpleName();
	}


	public STATUS_CODE delete(BusinessObjectType bo)
	{
		try (Connection con = DB.getSql2o().open()) {
			this.db.softDelete(tableName, "`id` = :id").addParameter("id", bo.getId()).executeUpdate();
			bo.setDeleted(true);
			return STATUS_CODE.OK;
		} catch (Sql2oException e) {
			Logger.log(
					LOG_LEVEL.ERROR,
					String.format(
							"Delete of %s with id %d failed!",
							bo.getClass().getName(),
							bo.getId()
					),
					e
			);
		}
		return STATUS_CODE.FAIL;
	}

	/**
	 * Fetch all matching the criteria provided via parameters (checks for every hash map entry key == value)
	 *
	 * @param parameters
	 *
	 * @return List of BusinessObjects
	 */
	@Override
	public List<BusinessObjectType> fetch(HashMap<String, String> parameters)
	{
		return this.fetch(parameters, -1);
	}
}
