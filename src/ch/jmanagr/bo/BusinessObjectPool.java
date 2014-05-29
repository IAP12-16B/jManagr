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

		if (this.cache.get(cls).containsKey(id)) {
			this.update(bo);
		} else {
			this.cache.get(cls).put(id, bo);
		}
	}

	public <T extends BusinessObject> T get(Class<T> cls, int id) {
		Logger.logln("Get from Pool");
		Logger.logln(cls.getName() + "\t id: " + id);
		if (this.contains(cls, id)) {
			return (T) this.cache.get(cls).get(id);
		}

		return null;
	}

	public void update(BusinessObject bo)
	{
		int id = bo.getId();
		Class<? extends BusinessObject> cls = bo.getClass();
		if (this.cache.get(cls).containsKey(id)) {
			Logger.logln("Update obj in pool");
			Logger.logln(cls.getName() + "\t id: " + id);
			BusinessObject b = this.get(cls, bo.getId());
			b.copyFromObject(bo);
			bo = b; // fixme THIS DOES NOT WORK :////// BECAUSE JAVA IS PASS BY VALUE..... F**k JAVA..........
			// todo find solution
		}
	}

	public <T extends BusinessObject> boolean contains(Class<T> cls, int id) {
		return this.cache.containsKey(cls) && this.cache.get(cls).containsKey(id);
	}

	public <T extends BusinessObject> boolean contains(T bo) {
		return this.contains(bo.getClass(), bo.getId());
	}
}
