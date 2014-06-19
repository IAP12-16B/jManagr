package ch.jmanagr.bl;

import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.dal.AbstractDAL;
import ch.jmanagr.lib.STATUS_CODE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		List<BusinessObjectType> depList = new ArrayList<>();
		try {
			depList = this.dal.fetch("deleted", "0");
		} catch (SQLException e) {
			e.printStackTrace(); // todo log
		}
		return FXCollections.observableArrayList(depList); // Todo return observable list
	}

	public BusinessObjectType getById(int id)
	{
		try {
			return this.dal.fetchById(id);
		} catch (SQLException e) {
			e.printStackTrace(); // todo log
		}

		return null;
	}


	@Override
	public STATUS_CODE delete(BusinessObjectType bo)
	{
		try {
			return this.dal.softDelete(bo);
			//return this.dal.delete(bo);
		} catch (SQLException e) {
			e.printStackTrace(); // todo log
		}

		return STATUS_CODE.FAIL;
	}

	@Override
	public STATUS_CODE save(BusinessObjectType bo)
	{
		try {
			return this.dal.save(bo);
		} catch (SQLException e) {
			e.printStackTrace();// todo log
		}

		return STATUS_CODE.FAIL;
	}
}
