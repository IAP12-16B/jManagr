package ch.jmanagr.dal;

import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.dal.db.DB;
import ch.jmanagr.lib.STATUS_CODE;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public abstract class AbstractDAL<BusinessObjectType extends BusinessObject<BusinessObjectType>>
		implements DAL<BusinessObjectType>
{
	protected Dao<BusinessObjectType, Integer> dao;
	protected Class<BusinessObjectType> typeClass;

	protected DB db;

	// If this class is instantiated for the first time
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

	@Override
	public void createTable() throws SQLException
	{
		this.db.createTableIfNotExists(typeClass);
	}

	@Override
	public void clearTable() throws SQLException
	{
		this.db.clearTable(typeClass);
	}

	@Override
	public void dropTable() throws SQLException
	{
		this.db.dropTable(typeClass, false);
	}

	@Override
	public BusinessObjectType fetchById(Integer id) throws SQLException
	{
		return dao.queryForId(id);
	}

	@Override
	public List<BusinessObjectType> fetchAll() throws SQLException
	{
		return dao.queryForAll();
	}

	@Override
	public List<BusinessObjectType> fetch(Map<String, Object> params) throws SQLException
	{
		return dao.queryForFieldValues(params);
	}

	@Override
	public List<BusinessObjectType> fetch(String fieldName, Object value) throws SQLException
	{
		if (value == null) {
			return dao.query(dao.queryBuilder().where().isNull(fieldName).prepare());
		}

		return dao.queryForEq(fieldName, value);
	}

	@Override
	public List<BusinessObjectType> fetch(String fieldName, Object value, Integer limit) throws SQLException
	{
		QueryBuilder<BusinessObjectType, Integer> qb = dao.queryBuilder();
		qb.limit(limit.longValue());
		qb.setWhere(qb.where().eq(fieldName, value));

		return dao.query(qb.prepare());
	}

	@Override
	public boolean exists(BusinessObjectType bo) throws SQLException
	{
		return dao.idExists(bo.getId());
	}

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

	@Override
	public STATUS_CODE delete(final Collection<BusinessObjectType> bos) throws SQLException
	{
		return TransactionManager.callInTransaction(
				this.db.getConnectionSource(), new Callable<STATUS_CODE>()
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
