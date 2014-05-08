package ch.jmanagr.dal;

import ch.jmanagr.bo.Resource;
import ch.jmanagr.lib.STATUS_CODE;

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
		dataList.add(bo);
		return STATUS_CODE.OK;
	}

	@Override
	public List<Resource> fetch()
	{
		// TODO: implement
		return dataList;
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
