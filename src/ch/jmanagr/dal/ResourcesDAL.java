package ch.jmanagr.dal;


import ch.jmanagr.bo.Resource;

import java.sql.SQLException;

public class ResourcesDAL extends AbstractDAL<Resource>
{
	private static ResourcesDAL instance;

	protected ResourcesDAL() throws SQLException
	{
		super(Resource.class);
	}

	public static ResourcesDAL getInstance() throws SQLException
	{
		if (instance == null) {
			synchronized (ResourcesDAL.class) {
				if (instance == null) {
					instance = new ResourcesDAL();
				}
			}
		}
		return instance;
	}
}
