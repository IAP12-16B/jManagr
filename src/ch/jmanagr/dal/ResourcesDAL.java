package ch.jmanagr.dal;


import ch.jmanagr.bo.Resource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

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
	protected void afterSoftDelete(Resource bo) throws SQLException
	{
		super.beforeSoftDelete(bo);
		List<Resource> resources = this.fetch("parent_id", bo.getId());
		for (Resource resource : resources) {
			this.softDelete(resource);
		}

		for (Resource.ResourceData data : bo.getData()) {
			ResourceDataDAL.getInstance().softDelete(data);
		}
	}

	@Override
	protected void afterSave(Resource bo) throws SQLException
	{
		super.afterSave(bo);
		for (Resource.ResourceData data : bo.getData()) {
			ResourceDataDAL.getInstance().save(data);
		}
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
