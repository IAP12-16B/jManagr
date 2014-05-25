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
import java.util.Iterator;
import java.util.List;
import java.util.Map;


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
	public STATUS_CODE save(Resource bo)
	{
		try (Connection con = DB.getSql2o().open()) {
			bo.setId(
					this.db.save(
							tableName,
							"id,name,icon,active,deleted",
							":id,:name,:icon,:active,:deleted",
							true
					).bind(bo).executeUpdate().<Integer>getKey(Integer.class)
			);

			for (ResourceData data : bo.getData()) {
				this.saveData(data); // todo what if something went wrong?
			}

			return STATUS_CODE.OK;
		} catch (Sql2oException e) {
			Logger.log(
					LOG_LEVEL.ERROR,
					String.format(
							"Creation of %s with id %d failed!",
							bo.getClass().getName(),
							bo.getId()
					),
					e
			);
		}
		return STATUS_CODE.FAIL;
	}

	public List<ResourceData> fetchData(Resource resource)
	{
		Query dataQuery = this.db.select("key,value", ResourceData.class.getSimpleName(), "Resource = :resource_id");

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
		try (Connection con = DB.getSql2o().open()) {
			this.db.save(
					tableName,
					"resource,key,value",
					":resource_id,:key,:value",
					true
			).bind(data)
			       .addParameter("resource_id", data.getResource().getId())
			       .executeUpdate();
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
	public List<Resource> fetch(HashMap<String, String> parameters, int limit)
	{
		try (Connection con = DB.getSql2o().open()) {
			String where = "";
			Iterator<Map.Entry<String, String>> it = parameters.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> pairs = it.next();
				where += String.format("%s = :%s", pairs.getKey(), pairs.getKey());
				it.remove();
			}

			Query q = this.db.select(
					"id,name,icon,active,deleted",
					this.tableName,
					where,
					limit
			);
			it = parameters.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> pairs = it.next();
				q.addParameter(pairs.getKey(), pairs.getValue());
				it.remove();
			}

			List<Resource> resources = q.executeAndFetch(Resource.class);

			for (Resource resource : resources) {
				resource.setData(this.fetchData(resource));
			}

			return resources;
		} catch (Sql2oException e) {
			Logger.log(
					LOG_LEVEL.ERROR,
					"Fetch failed!",
					e
			);
		}
		return null;
	}

	/**
	 * Fetch all BusinessObjects from DB
	 *
	 * @return a list of all BusinessObjects
	 */
	@Override
	public List<Resource> fetch()
	{
		return this.fetch(new HashMap<String, String>(), -1);
	}

	/**
	 * Fetch a BusinessObject by id
	 *
	 * @param id the id of the BusinessObject
	 *
	 * @return the BusinessObject
	 */
	@Override
	public Resource fetch(int id)
	{
		HashMap<String, String> map = new HashMap<>();
		map.put("id", ((Integer) id).toString());
		return this.fetch(map, 1).get(0);
	}

	/**
	 * Updates a BusinessObject
	 *
	 * @param bo the BusinessObject
	 *
	 * @return Whether it was successful or not.

	@Override
	public STATUS_CODE update(Resource bo)
	{
	try (Connection con = DB.getSql2o().open()) {
	bo.setId(
	this.db.update(
	tableName,
	"name = :name," +
	"icon = :icon," +
	"active = :active," +
	"deleted = :deleted" +
	" WHERE id = :id",
	true
	).bind(bo).executeUpdate().<Integer>getKey(Integer.class)
	);

	// TODO insert data
	return STATUS_CODE.OK;
	} catch (Sql2oException e) {
	Logger.log(
	LOG_LEVEL.ERROR,
	String.format(
	"Update of %s with id %d failed!",
	bo.getClass().getName(),
	bo.getId()
	),
	e
	);
	}

	return STATUS_CODE.FAIL;
	}*/
}
