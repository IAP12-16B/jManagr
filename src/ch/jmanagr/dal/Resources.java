package ch.jmanagr.dal;

import ch.jmanagr.bo.Resource;
import ch.jmanagr.lib.STATUS_CODE;
import org.sql2o.Connection;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class Resources extends AbstractDAL<Resource>
{
	private static Resources instance;

	private Resources()
	{
		super();
		// Todo: add sample data
	}

	/**
	 * @return Resources instance
	 */
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
	public STATUS_CODE create(Resource bo)
	{
		try (Connection con = DB.getSql2o().open()) {
			int key = new BigDecimal((Long)
					con.createQuery("INSERT INTO " + Resource.class.getSimpleName() + " (id, name," +
							"parent) VALUES (:id," +
							" :name, :parentId);", true)
							.bind(bo)
							.addParameter("parentId", bo.getParent().getId())
							.executeUpdate()
							.getKey()
			).intValueExact();
			bo.setId(key);

			// todo add children, data and tickets
			return STATUS_CODE.OK;
		}
	}

	@Override
	public List<Resource> fetch()
	{
		return this.fetch("id, name", "");
	}

	@Override
	public Resource fetch(int id)
	{
		HashMap<String, Object> params = new HashMap<>();
		params.put("id", id);
		return this.fetch("id, name", "WEHRE id = :id LIMIT 1;", params).get(0);
	}

	@Override
	public STATUS_CODE update(Resource bo)
	{
		for (Resource resource : dataList) {
			if (resource.getId() == bo.getId()) {
				dataList.set(dataList.indexOf(resource), bo);
				return STATUS_CODE.OK;
			}
		}

		return STATUS_CODE.FAIL; // TODO: Status for failed update
		// TODO: implement
	}
}
