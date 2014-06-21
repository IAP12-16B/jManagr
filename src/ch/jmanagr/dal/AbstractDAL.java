package ch.jmanagr.dal;

import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.dal.db.DB;
import ch.jmanagr.lib.STATUS_CODE;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Abstract class implementing all common operation for BusinessObjects
 *
 * @param <BusinessObjectType> the BusinessObject for which this Class should be responsible
 */
public abstract class AbstractDAL<BusinessObjectType extends BusinessObject<BusinessObjectType>>
		implements DAL<BusinessObjectType>
{
	protected Dao<BusinessObjectType, Integer> dao;
	protected Class<BusinessObjectType> typeClass;

	protected DB db;

	/**
	 * If the application runs the first time
	 */
	protected boolean firstUse = false;

	protected AbstractDAL(Class<BusinessObjectType> typeClass) throws SQLException
	{
		this.typeClass = typeClass;
		this.db = DB.getInstance();

		this.dao = DaoManager.createDao(this.db.getConnectionSource(), this.typeClass);

		this.dao.setObjectCache(true); // enable object cache

		if (!this.dao.isTableExists()) {
			this.createTable();
			this.firstUse = true;
		}
	}

	/**
	 * Create the table(s) necessary for this DAL
	 *
	 * @throws SQLException
	 */
	@Override
	public void createTable() throws SQLException
	{
		this.db.createTableIfNotExists(typeClass);
	}

	/**
	 * Deletes all entries in the table
	 *
	 * @throws SQLException
	 */
	@Override
	public void clearTable() throws SQLException
	{
		this.db.clearTable(typeClass);
	}

	/**
	 * Deletes the Table itself
	 *
	 * @throws SQLException
	 */
	@Override
	public void dropTable() throws SQLException
	{
		this.db.dropTable(typeClass, false);
	}

	/**
	 * Gets the BusinessObject by ID
	 *
	 * @param id the id of the BusinessObject
	 *
	 * @return The BusinessObject with the provided id or null, if not found
	 *
	 * @throws SQLException
	 */
	@Override
	public BusinessObjectType fetchById(Integer id) throws SQLException
	{
		return dao.queryForId(id);
	}

	/**
	 * Gets all BusinessObjects
	 *
	 * @return a list with all BusinessObjects
	 *
	 * @throws SQLException
	 */
	@Override
	public List<BusinessObjectType> fetchAll() throws SQLException
	{
		return dao.queryForAll();
	}

	/**
	 * Parametrized fetch Gets all BusinessObjects matching the parameters
	 *
	 * @param params Map of column = value, used in Where clause
	 * @param limit  the maximum number of returned results. Values below 0 means no limit
	 *
	 * @return a list of all BusinessObjects matching the values provided by params
	 *
	 * @throws SQLException
	 */
	@Override
	public List<BusinessObjectType> fetch(Map<String, Object> params, int limit) throws SQLException
	{
		if (params.containsValue(null) || limit >= 0) {
			QueryBuilder<BusinessObjectType, Integer> queryBuilder = dao.queryBuilder();
			Where<BusinessObjectType, Integer> where = queryBuilder.where();

			Iterator<Map.Entry<String, Object>> entryIterator = params.entrySet().iterator();
			while (entryIterator.hasNext()) {
				Map.Entry<String, Object> entry = entryIterator.next();

				if (entry.getValue() != null) {
					where.eq(entry.getKey(), entry.getValue());
				} else {
					where.isNull(entry.getKey());
				}

				if (entryIterator.hasNext()) {
					where = where.and();
				}
			}

			queryBuilder.setWhere(where);

			if (limit >= 0) {
				queryBuilder.limit((long) limit);
			}

			return dao.query(queryBuilder.prepare());
		}

		return dao.queryForFieldValues(params);
	}

	/**
	 * Parametrized fetch without limit
	 *
	 * @see ch.jmanagr.dal.AbstractDAL#fetch(java.util.Map, int)
	 */
	@Override
	public List<BusinessObjectType> fetch(Map<String, Object> params) throws SQLException
	{
		return this.fetch(params, -1);
	}

	/**
	 * Parametrized fetch for one column = value pair
	 *
	 * @param fieldName the column name
	 * @param value     the value to compare to
	 *
	 * @return a list of all BusinessObjects matching the criteria
	 *
	 * @throws SQLException
	 */
	@Override
	public List<BusinessObjectType> fetch(String fieldName, Object value) throws SQLException
	{
		if (value == null) {
			return dao.query(dao.queryBuilder().where().isNull(fieldName).prepare());
		}

		return dao.queryForEq(fieldName, value);
	}

	/**
	 * Parametrized fetch for one column = value pair and a limit
	 *
	 * @param fieldName the column name
	 * @param value     the value to compare to
	 * @param limit     the maximum number of returned results. Values below 0 means no limit
	 *
	 * @return a list of all BusinessObjects matching the criteria
	 *
	 * @throws SQLException
	 */
	@Override
	public List<BusinessObjectType> fetch(String fieldName, Object value, Integer limit) throws SQLException
	{
		QueryBuilder<BusinessObjectType, Integer> qb = dao.queryBuilder();
		if (limit >= 0) {
			qb.limit(limit.longValue());
		}

		qb.setWhere(qb.where().eq(fieldName, value));

		return dao.query(qb.prepare());
	}

	/**
	 * Check if the provided BusinessObject exists
	 *
	 * @param bo the BusinessObject to check
	 *
	 * @return true if it exists, false if not
	 *
	 * @throws SQLException
	 */
	@Override
	public boolean exists(BusinessObjectType bo) throws SQLException
	{
		return dao.idExists(bo.getId());
	}

	/**
	 * Deletes a BusinessObject
	 *
	 * @param bo the BusinessObject to delete
	 *
	 * @return Status code if it was successful
	 *
	 * @throws SQLException
	 */
	@Override
	public STATUS_CODE delete(final BusinessObjectType bo) throws SQLException
	{
		return TransactionManager.callInTransaction(
				this.db.getConnectionSource(), new Callable<STATUS_CODE>()
		{
			@Override
			public STATUS_CODE call() throws SQLException
			{
				if (dao.delete(bo) == 1) {
					return STATUS_CODE.OK;
				}

				return STATUS_CODE.FAIL;
			}
		}
		);
	}

	/**
	 * Deletes a collection of BusinessObject
	 *
	 * @param bos the BusinessObjects to delete
	 *
	 * @return Status code if it was successful
	 *
	 * @throws SQLException
	 */
	@Override
	public STATUS_CODE delete(final Collection<BusinessObjectType> bos) throws SQLException
	{
		return TransactionManager.callInTransaction(
				this.db.getConnectionSource(),
				new Callable<STATUS_CODE>()
				{
					@Override
					public STATUS_CODE call() throws SQLException
					{
						if (dao.delete(bos) == bos.size()) {
							return STATUS_CODE.OK;
						}

						return STATUS_CODE.FAIL;
					}
				}
		);
	}

	/**
	 * Soft deletes (set deleted to 1) a BusinessObject
	 *
	 * @param bo the BusinessObject to soft-delete
	 *
	 * @return Status code if it was successful
	 *
	 * @throws SQLException
	 */
	public STATUS_CODE softDelete(final BusinessObjectType bo) throws SQLException
	{
		return TransactionManager.callInTransaction(
				this.db.getConnectionSource(),
				new Callable<STATUS_CODE>()
				{
					@Override
					public STATUS_CODE call() throws SQLException
					{
						bo.setDeleted(true);
						if (dao.update(bo) == 1) {
							return STATUS_CODE.OK;
						}

						return STATUS_CODE.FAIL;
					}
				}
		);
	}

	/**
	 * Saves a BusinessObject to DB
	 *
	 * @param bo the BusinessObject to save
	 *
	 * @return Status code if it was successful
	 *
	 * @throws SQLException
	 */
	@Override
	public STATUS_CODE save(final BusinessObjectType bo) throws SQLException
	{
		return TransactionManager.callInTransaction(
				this.db.getConnectionSource(), new Callable<STATUS_CODE>()
		{
			@Override
			public STATUS_CODE call() throws SQLException
			{
				Dao.CreateOrUpdateStatus status = dao.createOrUpdate(bo);
				if (status.isCreated() || status.isUpdated()) {
					return STATUS_CODE.OK;
				}

				return STATUS_CODE.FAIL;
			}
		}
		);


	}
}
