package ch.jmanagr.bo;


import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Collection;
import java.util.Date;

/**
 * Resource BusinessObject
 */
@DatabaseTable(tableName = "Resource")
public class Resource implements BusinessObject<Resource>
{
	@DatabaseField(useGetSet = true, generatedId = true, allowGeneratedIdInsert = true, index = true)
	private Integer id;
	protected SimpleIntegerProperty idProperty;


	@DatabaseField(useGetSet = true, canBeNull = false, dataType = DataType.STRING)
	private String name;
	private SimpleStringProperty nameProperty;
	@ForeignCollectionField(eager = true, foreignFieldName = "resource")
	private Collection<ResourceData> data;
	@ForeignCollectionField(eager = true, foreignFieldName = "resource")
	private Collection<Ticket> tickets;
	@DatabaseField(useGetSet = true,
	               canBeNull = true,
	               foreign = true,
	               foreignAutoRefresh = true,
	               foreignAutoCreate = true)
	private Resource parent;
	@ForeignCollectionField(eager = true, foreignFieldName = "parent", maxEagerLevel = 5)
	private Collection<Resource> children;

	@DatabaseField(useGetSet = true)
	protected boolean active = true;
	@DatabaseField(useGetSet = true, index = true)
	protected boolean deleted = false;
	@DatabaseField(version = true, useGetSet = true, dataType = DataType.DATE_LONG)
	protected Date version;


	/**
	 * Default Constrcutor
	 */
	public Resource()
	{
		this.initProperties();
	}

	/**
	 * Constructor with id
	 *
	 * @param id the ID of the Resource
	 */
	public Resource(Integer id)
	{
		this();
		this.setId(id);
	}

	protected void initProperties()
	{
		this.idProperty = new SimpleIntegerProperty();
		this.nameProperty = new SimpleStringProperty();
	}

	/**
	 * Name getter
	 *
	 * @return the name of the Department
	 */
	public String getName()
	{
		return nameProperty.get();
	}

	/**
	 * Name setter
	 *
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.nameProperty.set(name);
	}

	/**
	 * Gets the data to this Resource
	 *
	 * @return a collection of ResourceData
	 */
	public Collection<ResourceData> getData()
	{
		return data;
	}

	/**
	 * Sets the data of this Resource
	 *
	 * @param data the data to set
	 */
	public void setData(Collection<ResourceData> data)
	{
		this.data = data;
	}

	/**
	 * Gets all Tickets assigned to this Resource
	 *
	 * @return a collection of Tickets
	 */
	public Collection<Ticket> getTickets()
	{
		return tickets;
	}

	/**
	 * Sets all  Tickets assigned to this Resource
	 *
	 * @param tickets the Agents to set
	 */
	public void setTickets(Collection<Ticket> tickets)
	{
		this.tickets = tickets;
	}

	/**
	 * Adds a data entry to this Resource
	 *
	 * @param resourceData The data to add
	 */
	public void addData(ResourceData resourceData)
	{
		data.add(resourceData);
	}

	/**
	 * Remove an entry from this Resource
	 *
	 * @param o The data to remove
	 */
	public void removeData(ResourceData o)
	{
		data.remove(o);
	}

	/**
	 * Adds a child Resource to this Resource
	 *
	 * @param resource the Resource to add
	 */
	public void addChild(Resource resource)
	{
		children.add(resource);
	}

	/**
	 * Remove a child from this Resource
	 *
	 * @param o he Resource to remove
	 */
	public void removeChild(Resource o)
	{
		children.remove(o);
	}

	/**
	 * Gets the parent of this resource
	 *
	 * @return the Parent or null, if this is a top-level root resource
	 */
	public Resource getParent()
	{
		return parent;
	}

	/**
	 * Sets the parent of this Resource
	 *
	 * @param parent the parrent Resource
	 */
	public void setParent(Resource parent)
	{
		this.parent = parent;
	}

	/**
	 * Get all child Resources
	 *
	 * @return a collection of all child Resources
	 */
	public Collection<Resource> getChildren()
	{
		return children;
	}

	/**
	 * Sets all child Resources
	 *
	 * @param children the child resources to set
	 */
	public void setChildren(Collection<Resource> children)
	{
		this.children = children;
	}


	/**
	 * Copies the values from an other Resource
	 *
	 * @param bo the Resource to copy from
	 */
	// todo replaceable with the cloneable interface?
	@Override
	public void copyFromObject(Resource bo)
	{
		this.setId(bo.getId());
		this.setActive(bo.getActive());
		this.setDeleted(bo.getDeleted());
		this.setName(bo.getName());
		this.setData(bo.getData());
		this.setTickets(bo.getTickets());
		this.setParent(bo.getParent());
		this.setChildren(bo.getChildren());
	}


	/**
	 * Name property (required for JavaFX)
	 *
	 * @return the name property
	 */
	public SimpleStringProperty nameProperty()
	{
		return this.nameProperty;
	}

	/**
	 * Method to compare two objects
	 *
	 * @param o the object to compare
	 *
	 * @return whether the passed object is the same as the current instance
	 */
	@Override
	public boolean equals(Object o)
	{
		if (this == o) { return true; }
		if (!(o instanceof Resource)) { return false; }

		Resource resource = (Resource) o;

		if (active != resource.active) { return false; }
		if (deleted != resource.deleted) { return false; }
		if (children != null ? !children.equals(resource.children) : resource.children != null) { return false; }
		if (data != null ? !data.equals(resource.data) : resource.data != null) { return false; }
		if (idProperty != null ? !idProperty.equals(resource.idProperty) : resource.idProperty != null) {
			return false;
		}
		if (nameProperty != null ? !nameProperty.equals(resource.nameProperty) : resource.nameProperty != null) {
			return false;
		}
		if (parent != null ? !parent.equals(resource.parent) : resource.parent != null) { return false; }
		if (tickets != null ? !tickets.equals(resource.tickets) : resource.tickets != null) { return false; }

		return true;
	}

	/**
	 * @return a numerical representation of this object
	 */
	@Override
	public int hashCode()
	{
		int result = idProperty != null ? idProperty.hashCode() : 0;
		result = 31 * result + (nameProperty != null ? nameProperty.hashCode() : 0);
		result = 31 * result + (data != null ? data.hashCode() : 0);
		result = 31 * result + (tickets != null ? tickets.hashCode() : 0);
		result = 31 * result + (parent != null ? parent.hashCode() : 0);
		return result;
	}


	/**
	 * @return the version
	 */
	@Override
	public Date getVersion()
	{
		return version;
	}

	/**
	 * Sets the version of this Department
	 *
	 * @param version the version
	 */
	@Override
	public void setVersion(Date version)
	{
		this.version = version;
	}

	/**
	 * Get the id of the Resource
	 *
	 * @return the id
	 */
	@Override
	public Integer getId()

	{
		return idProperty.getValue();
	}

	/**
	 * Set ID
	 *
	 * @param id the id
	 */
	@Override
	public void setId(Integer id)
	{
		this.idProperty.set(id);
	}

	/**
	 * Id property (used by JavaFX)
	 *
	 * @return the id property
	 */
	public SimpleIntegerProperty idProperty()
	{
		return this.idProperty;
	}

	/**
	 * @return whether the Resource is active
	 */
	@Override
	public boolean getActive()
	{
		return active;
	}

	/**
	 * Sets the active flag of the Resource
	 *
	 * @param active active flag
	 */
	@Override
	public void setActive(boolean active)
	{
		this.active = active;
	}

	/**
	 * @return whether the Resource is deleted
	 */
	@Override
	public boolean getDeleted()
	{
		return deleted;
	}


	/**
	 * Sets the deleted flag of the Resource
	 *
	 * @param deleted deleted flag
	 */
	@Override
	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
	}

	/**
	 * Convert Object to string
	 *
	 * @return the name of the Object
	 */
	@Override
	public String toString()
	{
		return this.nameProperty.get();
	}

	/**
	 * A class to store key/value pair for a {@link ch.jmanagr.bo.Resource}
	 */
	@DatabaseTable(tableName = "ResourceData")
	public static class ResourceData
	{
		@DatabaseField(
				useGetSet = true,
				uniqueCombo = true,
				foreign = true,
				foreignAutoCreate = true,
				foreignAutoRefresh = true)
		private Resource resource;

		@DatabaseField(useGetSet = true, uniqueCombo = true)
		private String key; // todo simple string property
		@DatabaseField(useGetSet = true, canBeNull = true)
		private String value; // todo simple string property

		@DatabaseField(version = true, useGetSet = true)
		protected Integer version;

		/**
		 * @param resource the Resource this date belongs to
		 * @param key      the key for the data
		 * @param value    the data
		 */
		public ResourceData(Resource resource, String key, String value)
		{
			this.setResource(resource);
			this.setKey(key);
			this.setValue(value);
		}

		/**
		 * Default constructor
		 */
		public ResourceData()
		{}

		/**
		 * Get the Resource
		 *
		 * @return the Resource this date belongs to
		 */
		public Resource getResource()
		{
			return resource;
		}

		/**
		 * Set the Resource
		 *
		 * @param resource the Resource this date belongs to
		 */
		public void setResource(Resource resource)
		{
			this.resource = resource;
		}

		/**
		 * Get the key
		 *
		 * @return key
		 */
		public String getKey()
		{
			return key;
		}

		/**
		 * sets the key
		 *
		 * @param key key
		 */
		public void setKey(String key)
		{
			this.key = key;
		}

		/**
		 * gets the value
		 *
		 * @return value
		 */
		public String getValue()
		{
			return value;
		}

		/**
		 * Sets the value
		 *
		 * @param value the value
		 */
		public void setValue(String value)
		{
			this.value = value;
		}

		/**
		 * @return the version
		 */
		public Integer getVersion()
		{
			return version;
		}

		/**
		 * Sets the version of this data
		 *
		 * @param version the version
		 */
		public void setVersion(Integer version)
		{
			this.version = version;
		}

		@Override
		public boolean equals(Object o)
		{
			if (this == o) { return true; }
			if (!(o instanceof ResourceData)) { return false; }

			ResourceData that = (ResourceData) o;

			if (key != null ? !key.equals(that.key) : that.key != null) { return false; }
			if (resource != null ? !resource.equals(that.resource) : that.resource != null) { return false; }
			if (value != null ? !value.equals(that.value) : that.value != null) { return false; }

			return true;
		}

		@Override
		public int hashCode()
		{
			int result = resource != null ? 1 : 0;
			result = 31 * result + (key != null ? key.hashCode() : 0);
			result = 31 * result + (value != null ? value.hashCode() : 0);
			return result;
		}
	}

}
