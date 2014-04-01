package ch.jmanagr.bl;

import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.dal.DAL;
import ch.jmanagr.lib.STATUS_CODE;

import java.util.ArrayList;

public abstract class AbstractBL implements BL
{
	protected DAL dal;

	@Override
	public ArrayList<BusinessObject> getAll()
	{
		return dal.fetchAll();
	}

	@Override
	public STATUS_CODE delete(BusinessObject bo)
	{
		return dal.delete(bo);
	}

	@Override
	public STATUS_CODE create(BusinessObject bo)
	{
		return dal.create(bo);
	}

	@Override
	public STATUS_CODE update(BusinessObject bo)
	{
		return dal.update(bo);
	}
}
