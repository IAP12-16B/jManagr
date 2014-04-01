package ch.jmanagr.bl;

import ch.jmanagr.bo.BusinessObject;
import ch.jmanagr.lib.STATUS_CODE;


public class Departments extends AbstractBL
{
	private static volatile Departments instance;

	protected ch.jmanagr.dal.Departments dal;

	private Departments()
	{
		/* dal =
			DAL als Singelton?
		 */
		// TODO: implement
	}


	public static Departments getInstance()
	{
		if (instance == null) {
			synchronized (Departments.class) {
				if (instance == null) {
					instance = new Departments();
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