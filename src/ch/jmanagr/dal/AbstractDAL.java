package ch.jmanagr.dal;

import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.dal.db.DB;
import ch.jmanagr.lib.STATUS_CODE;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class AbstractDAL<BusinessObjectType extends BusinessObject<BusinessObjectType>>
		implements DAL<BusinessObjectType>
{
	protected Dao<BusinessObjectType, Integer> dao;
	protected Class<BusinessObjectType> typeClass;

	protected DB db;

	protected AbstractDAL(Class<BusinessObjectType> typeClass) throws SQLException
	{
		this.typeClass = typeClass;
		this.db = DB.getInstance();

		this.dao = DaoManager.createDao(this.db.getConnectionSource(), this.typeClass);

		if (!this.dao.isTableExists()) {
			this.createTable();
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
	public STATUS_CODE delete(BusinessObjectType bo) throws SQLException
	{
		if (dao.delete(bo) == 1) {
			return STATUS_CODE.OK;
		}

		return STATUS_CODE.FAIL;
	}

	@Override
	public STATUS_CODE delete(Collection<BusinessObjectType> bos) throws SQLException
	{
		if (dao.delete(bos) == bos.size()) {
			return STATUS_CODE.OK;
		}

		return STATUS_CODE.FAIL;
	}

	@Override
	public STATUS_CODE save(BusinessObjectType bo) throws SQLException
	{
		Dao.CreateOrUpdateStatus status = dao.createOrUpdate(bo);
		if (status.isCreated() || status.isUpdated()) {
			return STATUS_CODE.OK;
		}

		return STATUS_CODE.FAIL;
	}
}
