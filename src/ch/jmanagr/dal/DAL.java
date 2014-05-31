package ch.jmanagr.dal;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.lib.STATUS_CODE;

import java.util.HashMap;
import java.util.List;

public interface DAL<BusinessObjectType extends BusinessObject>
{
	/**
	 * Saves a BusinessObject to DB
	 *
	 * @param bo
	 *
	 * @return
	 */
	public STATUS_CODE save(BusinessObjectType bo);

	/**
	 * Fetch all BusinessObjects from DB
	 *
	 * @return a list of all BusinessObjects
	 */
	public List<BusinessObjectType> fetch();

	/**
	 * Fetch all matching the criteria provided via parameters (checks for every hash map entry key == value)
	 *
	 * @param parameters
	 * @param limit
	 *
	 * @return List of BusinessObjects
	 */
	public List<BusinessObjectType> fetch(HashMap<String, String> parameters, int limit);

	public List<BusinessObjectType> fetch(HashMap<String, String> parameters);

	/**
	 * Fetch a BusinessObject by id
	 *
	 * @param id the id of the BusinessObject
	 *
	 * @return the BusinessObject
	 */
	public BusinessObjectType fetch(int id);

	/**
	 * Deletes a BusinessObject
	 *
	 * @param bo the BusinessObject
	 *
	 * @return Whether it was successful or not.
	 */
	public STATUS_CODE delete(BusinessObjectType bo);

}
