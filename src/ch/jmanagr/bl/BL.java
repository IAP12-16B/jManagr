package ch.jmanagr.bl;

import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.lib.STATUS_CODE;

import java.util.List;

/**
 * @param <BussinessObjectType> The BusinessObject to use
 */
public interface BL<BussinessObjectType extends BusinessObject> // BussinessObjectType is a placeholder for the
// BusinessObject. We need this, to retrieve around return type incompatibility
{
	/**
	 * Get all BusinessObjects
	 *
	 * @return A list of BusinessObjects
	 */
	public List<BussinessObjectType> getAll();

	public STATUS_CODE update(BussinessObjectType bo);

	/**
	 * Validates, if the provided BusinessObject is valid and therefor can be saved in database
	 *
	 * @param bo The BusinessObject to check
	 *
	 * @return the result of the check as STATUS_CODE
	 */
	public STATUS_CODE validate(BussinessObjectType bo);

	/**
	 * Saves a BusinessObject
	 *
	 * @param bo The BusinessObject to save
	 *
	 * @return Whether the action was successful or not
	 */
	public STATUS_CODE create(BussinessObjectType bo);

	/**
	 * Deletes a BusinessObject
	 *
	 * @param bo The BusinessObject to delete
	 *
	 * @return Whether the action was successful or not
	 */
	public STATUS_CODE delete(BussinessObjectType bo);

}
