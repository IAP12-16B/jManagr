package ch.jmanagr.bo;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.Collection;

@DatabaseTable(tableName = "Department")
public class Department implements BusinessObject<Department>
{
	@DatabaseField(useGetSet = true, generatedId = true, allowGeneratedIdInsert = true)
	private Integer id;

	protected SimpleIntegerProperty idProperty;

	@DatabaseField(defaultValue = "1", useGetSet = true)
	protected boolean active;

	@DatabaseField(defaultValue = "0", useGetSet = true)
	protected boolean deleted;

	@DatabaseField(version = true, useGetSet = true)
	protected Integer version;


	@DatabaseField(useGetSet = true, canBeNull = true, dataType = DataType.STRING)
	private String name; // dummy

	private SimpleStringProperty nameProperty;

	@ForeignCollectionField(eager = true, foreignFieldName = "department")
	private Collection<User> agents;


	public Department()
	{
		this.initProperties();
		this.agents = new ArrayList<>();
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

	public SimpleStringProperty nameProperty()
	{
		return this.nameProperty;
	}

	public Collection<User> getAgents()
	{
		return this.agents;
	}


	public void setAgents(Collection<User> agents)
	{
		this.agents = agents;
	}


	public void addAgent(User agent)
	{
		this.agents.add(agent);
	}


	/**
	 * Copies the values from an other object
	 *
	 * @param bo
	 */
	@Override
	public void copyFromObject(Department bo)
	{
		this.setId(bo.getId());
		this.setActive(bo.getActive());
		this.setDeleted(bo.getDeleted());
		this.setName(bo.getName());
		this.setAgents(bo.getAgents());
	}


	@Override
	public boolean equals(Object o)
	{
		if (this == o) { return true; }
		if (!(o instanceof Department)) { return false; }

		Department that = (Department) o;

		if (active != that.active) { return false; }
		if (deleted != that.deleted) { return false; }
		if (agents != null ? !agents.equals(that.agents) : that.agents != null) { return false; }
		if (idProperty != null ? !idProperty.equals(that.idProperty) : that.idProperty != null) { return false; }
		if (nameProperty != null ? !nameProperty.equals(that.nameProperty) : that.nameProperty != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = idProperty != null ? idProperty.hashCode() : 0;
		result = 31 * result + (active ? 1 : 0);
		result = 31 * result + (deleted ? 1 : 0);
		result = 31 * result + (nameProperty != null ? nameProperty.hashCode() : 0);
		result = 31 * result + (agents != null ? agents.hashCode() : 0);
		return result;
	}

	public String toString()
	{
		return this.nameProperty.getValue();
	}

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
}
