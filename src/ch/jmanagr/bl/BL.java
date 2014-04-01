package ch.jmanagr.bl;

import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.lib.STATUS_CODE;

import java.util.ArrayList;

public interface BL
{
	public ArrayList<BusinessObject> getAll();

	public STATUS_CODE update(BusinessObject bo);

	public STATUS_CODE validate(BusinessObject bo);

	public STATUS_CODE create(BusinessObject bo);

	public STATUS_CODE delete(BusinessObject bo);

}
