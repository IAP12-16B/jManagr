package ch.jmanagr.bl;

import ch.jmanagr.bo.Resource;
import ch.jmanagr.dal.ResourcesDAL;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.STATUS_CODE;

import java.sql.SQLException;


public class Resources extends AbstractBL<Resource, ResourcesDAL>
{
	private static volatile Resources instance;

	private Resources()
	{
		super();
		try {
			dal = ResourcesDAL.getInstance();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static Resources getInstance()
	{
		if (instance == null) {
			synchronized (Resources.class) {
				if (instance == null) {
					instance = new Resources();
				}
			}
		}
		return instance;
	}


	@Override
	public STATUS_CODE validate(Resource bo)
	{
		try {
			if (!this.dal.exists(bo)) {
				return STATUS_CODE.OK;
			}
		} catch (SQLException e) {
			Logger.log(LOG_LEVEL.WARNING, e);

			return STATUS_CODE.FAIL;
		}
		return STATUS_CODE.ALREADY_EXISTS;
	}


}