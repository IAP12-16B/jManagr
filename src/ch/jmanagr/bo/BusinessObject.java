package ch.jmanagr.bo;


public interface BusinessObject
{
	/**
	 * Get the id of the BusinessObject
	 *
	 * @return the id
	 */
	public int getId();

	public void setId(int id);

	public boolean isActive();

	public boolean getActive();

	public void setActive(boolean active);

	public boolean isDeleted();

	public boolean getDeleted();


	public void setDeleted(boolean deleted);
}
