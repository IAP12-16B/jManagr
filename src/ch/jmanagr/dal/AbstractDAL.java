package ch.jmanagr.dal;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.bo.BusinessObjectPool;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.STATUS_CODE;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2oException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

	/**
	 * Fetch a BusinessObject by id
	 *
	 * @param id the id of the BusinessObject
	 *
	 * @return the BusinessObject
	 */
	@Override
	public BusinessObjectType fetch(int id)
	{
		BusinessObjectType u = null;
		if (!BusinessObjectPool.getInstance().contains(tClass, id)) {
			HashMap<String, String> map = new HashMap<>();
			map.put("id", ((Integer) id).toString());
			u = this.fetch(map, 1).get(0);
			if (u != null) {
				BusinessObjectPool.getInstance().add(u);
			}
		} else {
			u = BusinessObjectPool.getInstance().get(tClass, id);
		}
		return u;
	}

	/**
	 * Fetch all BusinessObjects from DB
	 *
	 * @return a list of all BusinessObjects
	 */
	@Override
	public List<BusinessObjectType> fetch()
	{
		return this.fetch(new HashMap<String, String>(), -1);
	}

	// todo abstract as good as possible


	/* EXPERIMENTAL */

	protected abstract HashMap<String, String> getSaveFields();

	protected Query beforeSave(Query q, BusinessObjectType bo) {
		return q;
	}

	protected String formatFields(HashMap<String, String> fields, boolean keysOrValues) {
		String res = "";

		Iterator<Map.Entry<String, String>> it = fields.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = it.next();
			if (keysOrValues) {
				res += String.format("`%s`", pairs.getKey());
			} else {
				res += String.format(":%s", pairs.getValue());
			}

			if (it.hasNext()) {
				res += ", ";
			}
			it.remove();
		}

		return res;
	}

	protected void afterSave(BusinessObjectType bo){}

	@Override
	public STATUS_CODE save(BusinessObjectType bo)
	{
		try (Connection con = DB.getSql2o().open()) {
			Query q = this.db.save(
					tableName,
					this.formatFields(this.getSaveFields(), true),
					this.formatFields(this.getSaveFields(), false),
					true
			);
			q.bind(bo);
			this.beforeSave(q, bo);

			bo.setId(
					q.executeUpdate().<Integer>getKey(Integer.class)
			);

			this.afterSave(bo);

			if (!BusinessObjectPool.getInstance().contains(bo)) {
				BusinessObjectPool.getInstance().add(bo);
			}
			return STATUS_CODE.OK;
		} catch (Sql2oException e) {
			Logger.log(
					LOG_LEVEL.ERROR,
					String.format(
							"Save of %s with id %d failed!",
							bo.getClass().getName(),
							bo.getId()
					),
					e
			);
		}
		return STATUS_CODE.FAIL;
	}


	protected abstract HashMap<String, String> getFetchFields();


	protected Query beforeFetch(Query q) {
		return q;
	}

	@Override
	public List<BusinessObjectType> fetch(HashMap<String, String> parameters, int limit)
	{
		try (Connection con = DB.getSql2o().open()) {
			String where = "";
			Iterator<Map.Entry<String, String>> it = parameters.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> pairs = it.next();
				where += String.format("`%s` = :%s", pairs.getKey(), pairs.getKey());
			}

			Query q = this.db.select(
					this.formatFields(this.getFetchFields(), true),
					this.tableName,
					where,
					limit
			);
			it = parameters.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> pairs = it.next();
				q.addParameter(pairs.getKey(), pairs.getValue());
			}

			List<BusinessObjectType> bos = this.beforeFetch(q).executeAndFetch(tClass);

			for (BusinessObjectType bo : bos) {
				if (!BusinessObjectPool.getInstance().contains(bo)) {
					BusinessObjectPool.getInstance().add(bo);
				}

				this.afterFetch(bo);
			}

			return bos;
		} catch (Sql2oException e) {
			Logger.log(
					LOG_LEVEL.ERROR,
					"Fetch failed!",
					e
			);
		}
		return null;
	}

	protected void afterFetch(BusinessObjectType bo){}
}
