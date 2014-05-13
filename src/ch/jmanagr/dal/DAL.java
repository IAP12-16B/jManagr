package ch.jmanagr.dal;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.lib.STATUS_CODE;

import java.util.List;

public interface DAL<T extends BusinessObject>
{
	/**
	 * Creates a new BusinessObject in the DB
	 *
	 * @param bo the BusinessObject
	 *
	 * @return Whether it was successful or not.
	 */
	public STATUS_CODE create(T bo);

	/**
	 * Fetch all BusinesObjects from DB
	 *
	 * @return a list of all BusinessObjects
	 */
	public List<T> fetch(); // @Pablo: Can return a list of object, which's class implements the
	// BusinessObject-Interface

	public T fetch(int id);

	/**
	 * Deletes a BusinessObject
	 *
	 * @param bo the BusinessObject
	 *
	 * @return Whether it was successful or not.
	 */
	public STATUS_CODE delete(T bo);

	/**
	 * Updates a BusinessObject
	 *
	 * @param bo the BusinessObject
	 *
	 * @return Whether it was successful or not.
	 */
	public STATUS_CODE update(T bo);
}
