package ch.jmanagr.bl;

import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.dal.AbstractDAL;
import ch.jmanagr.lib.STATUS_CODE;
import javafx.collections.ObservableList;

/**
 * @param <BusinessObjectType> The BusinessObject to use
 * @param <DALType>            The DAL class to use
 */
public abstract class AbstractBL<BusinessObjectType extends BusinessObject,
		DALType extends AbstractDAL<BusinessObjectType>>
		implements BL<BusinessObjectType>
{

	protected DALType dal;

	protected AbstractBL()
	{
	}


	@Override
	public ObservableList<BusinessObjectType> getAll()
	{
		// todo kim was machsch du für komischi sache mach das das goht haha i schrib de satz no gern :P
		//ObservableList<BusinessObjectType> depList = FXCollections.observableArrayList(dal.fetch());
		return null; // Todo
	}

	public BusinessObjectType getById(int id)
	{
		return this.dal.fetch(id);
	}


	@Override
	public STATUS_CODE delete(BusinessObjectType bo)
	{
		return this.dal.delete(bo);
	}

	@Override
	public STATUS_CODE save(BusinessObjectType bo)
	{
		return this.dal.save(bo);
	}
}
