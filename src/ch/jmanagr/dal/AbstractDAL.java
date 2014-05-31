package ch.jmanagr.dal;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.bo.BusinessObjects;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.STATUS_CODE;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2oException;

import java.sql.SQLException;
import java.util.*;

public abstract class AbstractDAL<BusinessObjectType extends BusinessObject<BusinessObjectType>>
		implements DAL<BusinessObjectType>
{
	protected final String tableName;
	protected final Class<BusinessObjectType> tClass;
	protected DB db;

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

			// todo delete referenced
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

		HashMap<String, String> map = new HashMap<>();
		map.put("id", ((Integer) id).toString());
		u = this.fetch(map, 1).get(0);

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


	/**
	 * Get required fields for saving Hashmap structure: HashMap<DB_COLUMN_NAME, VALUE>
	 *
	 * @return HashMap
	 */
	protected abstract HashMap<String, String> getSaveFields();

	/**
	 * Hook before save. Add additional paramteres to Query or save referenced objects
	 *
	 * @param q
	 * @param bo
	 *
	 * @return
	 */
	protected Query beforeSave(Query q, BusinessObjectType bo)
	{
		return q;
	}

	/**
	 * Method to format a HashMap for use in SQL query
	 *
	 * @param fields
	 * @param keysOrValues
	 *
	 * @return
	 */
	protected String formatFields(HashMap<String, String> fields, boolean keysOrValues)
	{
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

	/**
	 * Hook execute after save.
	 *
	 * @param bo
	 */
	protected void afterSave(BusinessObjectType bo)
	{}

	@Override
	public STATUS_CODE save(BusinessObjectType bo)
	{
		if (bo != null) {
			try (Connection con = DB.getSql2o().beginTransaction()) {
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

				q.getConnection().commit(true);
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

				SQLException sqlE = ((SQLException) e.getCause());
				int error_code = sqlE.getErrorCode();

				return STATUS_CODE.FAIL_WITH_UNKOWN_REASON;
			}
		}
		return STATUS_CODE.FAIL;
	}

	/**
	 * @return
	 *
	 * @see this.getSaveField() Fields for fetch
	 */
	protected abstract HashMap<String, String> getFetchFields();


	/**
	 * Hook executed before fetch. Add parameters to query etc...
	 *
	 * @param q
	 *
	 * @return
	 */
	protected Query beforeFetch(Query q)
	{
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
			List<BusinessObjectType> results = new ArrayList<>();

			for (BusinessObjectType bo : bos) {
				BusinessObjectType resBo = BusinessObjects.getInstance(tClass, bo.getId());
				resBo.copyFromObject(bo);
				this.afterFetch(resBo);
				results.add(resBo);
			}

			return results;
		} catch (Sql2oException e) {
			Logger.log(
					LOG_LEVEL.ERROR,
					"Fetch failed!",
					e
			);
		}
		return null;
	}

	/**
	 * Hook executed after fetch. Here you can fetch e.g. referenced Objects etc...
	 *
	 * @param bo
	 */
	protected void afterFetch(BusinessObjectType bo)
	{}
}
