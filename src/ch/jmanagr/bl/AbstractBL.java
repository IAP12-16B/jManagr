package ch.jmanagr.bl;

import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.dal.AbstractDAL;
import ch.jmanagr.lib.STATUS_CODE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;

/**
 * @param <BusinessObjectType> The BusinessObject to use
 * @param <DALType>            The DAL class to use
 */
public abstract class AbstractBL<BusinessObjectType extends BusinessObject<BusinessObjectType>,
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
		HashMap<String, String> map = new HashMap<>();
		map.put("deleted", "0");
		ObservableList<BusinessObjectType> depList = FXCollections.observableArrayList(this.dal.fetch(map));
		return depList; // Todo return observable list
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
