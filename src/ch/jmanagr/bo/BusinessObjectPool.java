package ch.jmanagr.bo;

import ch.jmanagr.lib.Logger;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Class provides a Business Object pool.
 */
public abstract class BusinessObjectPool implements Observer
{/*
	private HashMap<Class<? extends AbstractBusinessObject>, HashMap<Integer, AbstractBusinessObject>> cache;


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

	public void add(AbstractBusinessObject bo)
	{
		Class<? extends AbstractBusinessObject> cls = bo.getClass();
		if (!this.cache.containsKey(cls)) {
			this.cache.put(cls, new HashMap<Integer, AbstractBusinessObject>());
		}
		bo.addObserver(this);
		this.cache.get(cls).put(bo.getId(), bo);
	}*/
}
