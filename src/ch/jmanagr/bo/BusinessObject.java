package ch.jmanagr.bo;


public interface BusinessObject<T extends BusinessObject>
{
	// TODO Implement fluid interface (bo.setId(1).setName(2)... ect...) on getters and methods without return value

	/**
	 * Get the id of the BusinessObject
	 *
	 * @return the id
	 */
	public Integer getId();

	/**
	 * Set ID
	 *
	 * @param id
	 */
	public void setId(Integer id);

	/**
	 * Return wether the obejct is active
	 *
	 * @return
	 */
	public boolean isActive();

	/**
	 * @return
	 *
	 * @see this.isActive()
	 */
	public boolean getActive();

	/**
	 * Sets the active flag of the object
	 *
	 * @param active
	 */
	public void setActive(boolean active);

	/**
	 * Return wether the obejct is deleted
	 *
	 * @return
	 */
	public boolean isDeleted();

	/**
	 * @return
	 *
	 * @see this.isDeeleted()
	 */
	public boolean getDeleted();

	/**
	 * Sets the deleted flag of the object
	 *
	 * @param deleted
	 */
	public void setDeleted(boolean deleted);

	/**
	 * Copies the values from an other object
	 *
	 * @param bo
	 */
	public void copyFromObject(T bo);

	public Integer getVersion();

	public void setVersion(Integer version);
}
