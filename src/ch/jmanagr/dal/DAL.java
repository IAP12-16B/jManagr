package ch.jmanagr.dal;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.lib.STATUS_CODE;

public interface DAL
{
	public STATUS_CODE create(BusinessObject bo);

	public BusinessObject fetchAll();

	public STATUS_CODE delete(BusinessObject bo);

	public STATUS_CODE update(BusinessObject bo);
}
