package ch.jmanagr.dal;


import ch.jmanagr.bo.Resource;
import ch.jmanagr.lib.STATUS_CODE;
import com.j256.ormlite.misc.TransactionManager;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

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

	public List<Resource> fetchOnlyRootResources() throws SQLException
	{
		HashMap<String, Object> map = new HashMap<>();
		map.put("parent_id", null);
		map.put("deleted", 0);

		return this.fetch(map);
	}

	@Override
	public void createTable() throws SQLException
	{
		super.createTable();
		this.db.createTableIfNotExists(Resource.ResourceData.class);
	}

	@Override
	public STATUS_CODE softDelete(final Resource bo) throws SQLException
	{
		return TransactionManager.callInTransaction(
				this.db.getConnectionSource(),
				new Callable<STATUS_CODE>()
				{
					@Override
					public STATUS_CODE call() throws SQLException
					{
						bo.setDeleted(true);
						if (dao.update(bo) == 1) {
							List<Resource> resources = dao.queryForEq("parent_id", bo.getId());
							for (Resource resource : resources) {
								ResourcesDAL.this.softDelete(resource);
							}
						}

						return STATUS_CODE.FAIL;
					}
				}
		);

	}

	public static class ResourceDataDAL extends AbstractDAL<Resource.ResourceData>
	{
		private static ResourceDataDAL instance;

		protected ResourceDataDAL() throws SQLException
		{
			super(Resource.ResourceData.class);

		}

		public static ResourceDataDAL getInstance() throws SQLException
		{
			if (instance == null) {
				synchronized (ResourceDataDAL.class) {
					if (instance == null) {
						instance = new ResourceDataDAL();
					}
				}
			}
			return instance;
		}
	}
}
