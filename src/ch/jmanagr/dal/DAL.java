package ch.jmanagr.dal;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.lib.STATUS_CODE;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Interface for all DAL classes
 *
 * @param <BusinessObjectType> the BusinessObject for which this Class should be responsible
 */
public interface DAL<BusinessObjectType extends BusinessObject<BusinessObjectType>>
{
	public BusinessObjectType fetchById(Integer id) throws SQLException;

	public void createTable() throws SQLException;

	public void clearTable() throws SQLException;

	public void dropTable() throws SQLException;

	public List<BusinessObjectType> fetchAll() throws SQLException;

	public List<BusinessObjectType> fetch(String fieldName, Object value) throws SQLException;

	public List<BusinessObjectType> fetch(Map<String, Object> params) throws SQLException;

	public List<BusinessObjectType> fetch(Map<String, Object> params, int limit) throws SQLException;

	public List<BusinessObjectType> fetch(String fieldName, Object value, Integer limit) throws SQLException;

	public boolean exists(BusinessObjectType bo) throws SQLException;

	public STATUS_CODE delete(BusinessObjectType bo) throws SQLException;

	public STATUS_CODE delete(Collection<BusinessObjectType> bos) throws SQLException;

	public STATUS_CODE save(BusinessObjectType bo) throws SQLException;
}
