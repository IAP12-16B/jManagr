package ch.jmanagr.dal;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.lib.STATUS_CODE;

import java.util.List;

public interface DAL
{
	public STATUS_CODE create(BusinessObject bo);

	// Can return a list of object, which's class implements the BusinessObject-Interface
	public List<? extends BusinessObject> fetchAll();

	public STATUS_CODE delete(BusinessObject bo);

	public STATUS_CODE update(BusinessObject bo);
}
