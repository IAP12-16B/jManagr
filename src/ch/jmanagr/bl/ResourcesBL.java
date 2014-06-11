package ch.jmanagr.bl;

import ch.jmanagr.bo.Resource;
import ch.jmanagr.dal.ResourcesDAL;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.STATUS_CODE;

import java.sql.SQLException;


public class ResourcesBL extends AbstractBL<Resource, ResourcesDAL>
{
	private static volatile ResourcesBL instance;

	private ResourcesBL()
	{
		super();
		try {
			dal = ResourcesDAL.getInstance();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static ResourcesBL getInstance()
	{
		if (instance == null) {
			synchronized (ResourcesBL.class) {
				if (instance == null) {
					instance = new ResourcesBL();
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