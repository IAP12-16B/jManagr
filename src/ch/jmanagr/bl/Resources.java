package ch.jmanagr.bl;

import ch.jmanagr.bo.Resource;
import ch.jmanagr.lib.STATUS_CODE;


public class Resources extends AbstractBL<Resource, ch.jmanagr.dal.Resources>
{
	private static volatile Resources instance;

	private Resources()
	{
		/* dal =
			DAL als Singelton?
		 */
		// TODO: implement
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
		// Todo: implement
		return null;
	}


}