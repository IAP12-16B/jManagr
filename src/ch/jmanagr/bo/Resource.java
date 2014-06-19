package ch.jmanagr.bo;


import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Collection;

@DatabaseTable(tableName = "Resource")
public class Resource implements BusinessObject<Resource>
{
	@DatabaseField(useGetSet = true, generatedId = true, allowGeneratedIdInsert = true)
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
	@DatabaseField(defaultValue = "1", useGetSet = true)
	protected boolean active;
	@DatabaseField(defaultValue = "0", useGetSet = true)
	protected boolean deleted;
	@DatabaseField(version = true, useGetSet = true)
	protected Integer version;


	public Resource()
	{
		this.initProperties();
	}

	public Resource(int id)
	{
		this.initProperties();
		this.setId(id);
	}

	protected void initProperties()
	{
		this.idProperty = new SimpleIntegerProperty();
		this.nameProperty = new SimpleStringProperty();
	}


	public String getName()
	{
		return nameProperty.get();
	}

	public void setName(String name)
	{
		this.nameProperty.set(name);
	}

	public Collection<ResourceData> getData()
	{
		return data;
	}

	public void setData(Collection<ResourceData> data)
	{
		this.data = data;
	}

	public Collection<Ticket> getTickets()
	{
		return tickets;
	}

	public void setTickets(Collection<Ticket> tickets)
	{
		this.tickets = tickets;
	}

	// Todo: addChildren & removeChildren

	public Resource getParent()
	{
		return parent;
	}

	public void setParent(Resource parent)
	{
		this.parent = parent;
	}

	public Collection<Resource> getChildren()
	{
		return children;
	}

	public void setChildren(Collection<Resource> children)
	{
		this.children = children;
	}


	/**
	 * Copies the values from an other object
	 *
	 * @param bo
	 */
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


	public SimpleStringProperty nameProperty()
	{
		return this.nameProperty;
	}

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

	// todo addData?

	public Integer getVersion()
	{
		return version;
	}

	public void setVersion(Integer version)
	{
		this.version = version;
	}

	@Override
	public boolean isActive()
	{
		return active;
	}

	@Override
	public Integer getId()

	{
		return idProperty.getValue();
	}

	@Override
	public void setId(Integer id)
	{
		this.idProperty.set(id);
	}

	public SimpleIntegerProperty idProperty()
	{
		return this.idProperty;
	}

	@Override
	public boolean getActive()
	{
		return active;
	}

	@Override
	public void setActive(boolean active)
	{
		this.active = active;
	}

	@Override
	public boolean getDeleted()
	{
		return deleted;
	}

	@Override
	public boolean isDeleted()
	{
		return deleted;
	}

	@Override
	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
	}

	@Override
	public String toString()
	{
		return this.nameProperty.get();
	}
}
