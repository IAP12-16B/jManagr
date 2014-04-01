package ch.jmanagr.bl;

import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.lib.STATUS_CODE;


public class Resources extends AbstractBL
{
	private static volatile Resources instance;

	private ch.jmanagr.dal.Resources dal;

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
	public STATUS_CODE validate(BusinessObject bo)
	{
		// Todo: implement
		return null;
	}


}