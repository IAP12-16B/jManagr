package ch.jmanagr.bo;

import ch.jmanagr.lib.Logger;

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
		int id = bo.getId();
		Class<? extends BusinessObject> cls = bo.getClass();


		if (!this.cache.containsKey(cls)) {
			this.cache.put(cls, new HashMap<Integer, BusinessObject>());
		}

		this.cache.get(cls).put(id, bo);
	}

	public <T extends BusinessObject> T get(Class<T> cls, int id) {
		Logger.log("Get from cache");
		Logger.log(cls.getName() + "\t id: "+id);
		if (this.contains(cls, id)) {
			return (T) this.cache.get(cls).get(id);
		}

		return null;
	}

	public <T extends BusinessObject> boolean contains(Class<T> cls, int id) {
		return this.cache.containsKey(cls) && this.cache.get(cls).containsKey(id);
	}

	public <T extends BusinessObject> boolean contains(T bo) {
		return this.contains(bo.getClass(), bo.getId());
	}
}
