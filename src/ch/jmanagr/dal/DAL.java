package ch.jmanagr.dal;


import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.lib.STATUS_CODE;

import java.util.List;

public interface DAL<T extends BusinessObject>
{
	public STATUS_CODE create(T bo);

	// Can return a list of object, which's class implements the BusinessObject-Interface
	public List<T> fetch();

	public STATUS_CODE delete(T bo);

	public STATUS_CODE update(T bo);
}
