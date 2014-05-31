package ch.jmanagr.dal;

import ch.jmanagr.bo.Resource;
import ch.jmanagr.bo.ResourceData;
import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;
import ch.jmanagr.lib.STATUS_CODE;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2oException;

import java.util.HashMap;
import java.util.List;


public class Resources extends AbstractDAL<Resource>
{
	private static Resources instance;

	private Resources()
	{
		super(Resource.class);
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
	protected HashMap<String, String> getSaveFields()
	{
		HashMap<String, String> fields = new HashMap<>();
		fields.put("id", "id");
		fields.put("name", "name");
		//fields.put("icon", "icon");
		fields.put("active", "active");
		fields.put("deleted", "deleted");
		return fields;
	}

	@Override
	protected void afterSave(Resource bo)
	{
		for (ResourceData data : bo.getData()) {
			this.saveData(data); // todo what if something went wrong?
		}
	}

	public List<ResourceData> fetchData(Resource resource)
	{
		Query dataQuery = this.db.select(
				"`key`,`value`",
				ResourceData.class.getSimpleName(),
				"`Resource` = :resource_id"
		);

		List<ResourceData> data = dataQuery.addParameter("resource_id", resource.getId()).executeAndFetch(
				ResourceData.class
		);

		for (ResourceData resourceData : data) {
			resourceData.setResource(
					resource
			);
		}

		return data;
	}


	public STATUS_CODE saveData(ResourceData data)
	{
		try (Connection con = DB.getSql2o().beginTransaction()) {
			this.db.save(
					tableName,
					"`resource`,`key`,`value`",
					":resource_id,:key,:value",
					true
			).bind(data)
			       .addParameter("resource_id", data.getResource().getId())
			       .executeUpdate().commit(true);
			return STATUS_CODE.OK;
		} catch (Sql2oException e) {
			Logger.log(
					LOG_LEVEL.ERROR,
					String.format(
							"Creation of %s for resource id %d failed!",
							data.getClass().getName(),
							data.getResource().getId()
					),
					e
			);
		}
		return STATUS_CODE.FAIL;
	}

	@Override
	protected HashMap<String, String> getFetchFields()
	{
		return this.getSaveFields();
	}

	@Override
	protected void afterFetch(Resource resource)
	{
		super.afterFetch(resource);
		resource.setData(this.fetchData(resource));
	}
}
