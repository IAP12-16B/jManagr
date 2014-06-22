package ch.jmanagr.bo;

import java.util.Date;

/**
 * Interface for all BusinessObjects
 *
 * @param <T> The BusinessObject class. Is used when somewhere the BusinessObject-Type itself is requires (e.g.
 *            copyFromObject)
 */
public interface BusinessObject<T extends BusinessObject<T>>
{
	/**
	 * Get the id of the BusinessObject
	 *
	 * @return the id
	 */
	public Integer getId();

	/**
	 * Set ID
	 *
	 * @param id the id
	 */
	public void setId(Integer id);


	/**
	 * @return whether the object is active
	 */
	public boolean getActive();

	/**
	 * Sets the active flag of the object
	 *
	 * @param active active flag
	 */
	public void setActive(boolean active);


	/**
	 * @return whether the object is deleted
	 */
	public boolean getDeleted();

	/**
	 * Sets the deleted flag of the object
	 *
	 * @param deleted deleted flag
	 */
	public void setDeleted(boolean deleted);

	/**
	 * Copies the values from an other object
	 *
	 * @param bo the BusinessObject to copy from
	 */
	public void copyFromObject(T bo);

	/**
	 * @return the version
	 */
	public Date getVersion();

	/**
	 * Sets the version of this BusinessObject
	 *
	 * @param version the version
	 */
	public void setVersion(Date version);
}
