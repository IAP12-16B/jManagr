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
		// TODO: implement
		return null;
	}

	@Override
	public List<Resource> fetch()
	{
		// TODO: implement
		return null;
	}

	@Override
	public STATUS_CODE update(Resource bo)
	{
		// TODO: implement
		return null;
	}
}
