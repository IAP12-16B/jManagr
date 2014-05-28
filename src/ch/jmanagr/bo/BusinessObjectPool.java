package ch.jmanagr.bo;

import java.util.HashMap;

/**
 * Class provides a Business Object pool.
 */
public class BusinessObjectPool
{
	private HashMap<Class<? extends BusinessObject>, HashMap<Integer, BusinessObject>> cache;


	private static BusinessObjectPool instance;

	private BusinessObjectPool()
	{
		cache = new HashMap<>();
	}

	public static BusinessObjectPool getInstance()
	{
		if (instance == null) {
			synchronized (BusinessObjectPool.class) {
				if (instance == null) {
					instance = new BusinessObjectPool();
				}
			}
		}
		return instance;
	}

	public void add(BusinessObject bo)
	{
		// todo implement add
	}
}
