package ch.jmanagr.dal;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.lib.STATUS_CODE;

public abstract class AbstractDAL implements DAL
{
	protected DB db;

	public AbstractDAL()
	{
		db = DB.getInstance();
	}

	public STATUS_CODE delete(BusinessObject bo)
	{
		/*try {
			String deleteSQL = "DELETE FROM " + bo.getTable() + " WHERE id = :id";
			DB.getSql2o().createQuery(deleteSQL)
					.addParameter("id", bo.getId())
					.executeUpdate();
		} catch (Exception e) { // TODO: better fail handling
			e.printStackTrace();
			return STATUS_CODE.FAIL;
		}*/

		return STATUS_CODE.OK;
	}
}
