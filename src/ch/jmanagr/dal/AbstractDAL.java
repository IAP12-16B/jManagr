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
		// TODO: Implement delete
		return STATUS_CODE.OK;
	}
}
