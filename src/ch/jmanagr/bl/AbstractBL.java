package ch.jmanagr.bl;

import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.dal.DAL;
import ch.jmanagr.lib.STATUS_CODE;

import java.util.List;

/**
 * @param <BusinessObjectType> The BusinessObject to use
 * @param <DALType>            The DAL class to use
 */
public abstract class AbstractBL<BusinessObjectType extends BusinessObject, DALType extends DAL<BusinessObjectType>>
		implements BL<BusinessObjectType>
{
	protected DALType dal;

	protected AbstractBL()
	{

	}


	@Override
	public List<BusinessObjectType> getAll()
	{
		return dal.fetch();
	}

	@Override
	public STATUS_CODE delete(BusinessObjectType bo)
	{
		return this.dal.delete(bo);
	}

	@Override
	public STATUS_CODE create(BusinessObjectType bo)
	{
		return this.dal.create(bo);
	}

	@Override
	public STATUS_CODE update(BusinessObjectType bo)
	{
		return this.dal.update(bo);
	}
}
