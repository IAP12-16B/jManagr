package ch.jmanagr.bl;

import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.dal.AbstractDAL;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.STATUS_CODE;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for BL classes. Implements common operations for all BusinessObjects
 *
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
	public List<BusinessObjectType> getAll()
	{
		List<BusinessObjectType> depList = new ArrayList<>();
		try {
			depList = this.dal.fetch("deleted", 0);
		} catch (SQLException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
		}
		return depList;
	}

	public BusinessObjectType getById(int id)
	{
		try {
			return this.dal.fetchById(id);
		} catch (SQLException e) {
			Logger.log(LOG_LEVEL.ERROR, e);
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
			Logger.log(LOG_LEVEL.ERROR, e);

			return this.getStatusCodeFromSQLException(e);
		}
	}

	@Override
	public STATUS_CODE save(BusinessObjectType bo)
	{
		try {
			return this.dal.save(bo);
		} catch (SQLException e) {
			Logger.log(LOG_LEVEL.ERROR, e);

			return this.getStatusCodeFromSQLException(e);
		}
	}


	public STATUS_CODE getStatusCodeFromSQLException(SQLException e)
	{
		switch (e.getErrorCode()) {
			case 1044:
			case 1045: {
				return STATUS_CODE.ACCESS_DENIED;
			}

			case 2002:
			case 2003: {
				return STATUS_CODE.NO_CONNECTION_TO_SERVER;
			}

			case 1129:
			case 1130: {
				return STATUS_CODE.ACCESS_BLOCKED;
			}

			case 1132:
			case 1345:
			case 1454: {
				return STATUS_CODE.NOT_ENOUGH_PRIVILEGES;
			}
		}

		return STATUS_CODE.FAIL;
	}
}
