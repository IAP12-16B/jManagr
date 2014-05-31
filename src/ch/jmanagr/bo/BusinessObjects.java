package ch.jmanagr.bo;

import ch.jmanagr.lib.LOG_LEVEL;
import ch.jmanagr.lib.Logger;

import java.util.HashMap;

/**
 * Multiton This class provides the functionality of a singelton on the per-BusinessObject level. Every BO should only
 * exist once
 */
public class BusinessObjects
{
	private static final
	HashMap<Class<? extends BusinessObject>, BusinessObjectMultiton<? extends BusinessObject>>
			multitonInstances =
			new HashMap<>();

	public static <T extends BusinessObject<T>> T getInstance(Class<T> cls, Integer id)
	{
		synchronized (multitonInstances) {
			if (!multitonInstances.containsKey(cls)) {
				multitonInstances.put(cls, new BusinessObjectMultiton<T>(cls));
			}

			return (T) multitonInstances.get(cls).getInstance(id);
		}
	}

	// Todo getUser(), getTicket(), etc...


	public static class BusinessObjectMultiton<BusinessObjectType extends BusinessObject<BusinessObjectType>>
	{
		private final HashMap<Integer, BusinessObjectType> instances;
		private final Class<BusinessObjectType> businessObjectClass;

		public BusinessObjectMultiton(Class<BusinessObjectType> cls)
		{
			this.businessObjectClass = cls;
			instances = new HashMap<>();
		}

		public BusinessObjectType getInstance(Integer id)
		{
			synchronized (instances) {
				BusinessObjectType boInstance = instances.get(id);

				if (boInstance == null) {
					try {
						boInstance = this.businessObjectClass.newInstance();
						instances.put(id, boInstance);
					} catch (InstantiationException | IllegalAccessException e) {
						Logger.log(LOG_LEVEL.ERROR, e);
					}
				}

				return boInstance;
			}
		}

	}
}
