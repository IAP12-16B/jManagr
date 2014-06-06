package ch.jmanagr.dal;


import ch.jmanagr.bo.BusinessObject;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DAL<BusinessObjectType extends BusinessObject>
{
	public BusinessObjectType fetchById(Integer id) throws SQLException;

	public void createTable() throws SQLException;

	public void clearTable() throws SQLException;

	public void dropTable() throws SQLException;

	public List<BusinessObjectType> fetchAll() throws SQLException;

	public List<BusinessObjectType> fetch(String fieldName, Object value) throws SQLException;

	public List<BusinessObjectType> fetch(Map<String, Object> params) throws SQLException;

	public List<BusinessObjectType> fetch(String fieldName, Object value, Integer limit) throws SQLException;
}
