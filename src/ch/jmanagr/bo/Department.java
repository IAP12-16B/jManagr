package ch.jmanagr.bo;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import javafx.beans.property.SimpleStringProperty;

@DatabaseTable(tableName = "Department")
public class Department extends AbstractBO<Department>
{
	@DatabaseField(useGetSet = true, canBeNull = true)
	private SimpleStringProperty name;

	@ForeignCollectionField(eager = true, foreignFieldName = "department")
	private ForeignCollection<User> agents;

	public Department(String name, ForeignCollection<User> agents, boolean active, boolean deleted)
	{
		this.initProperties();
		this.setActive(active);
		this.setDeleted(deleted);
		this.setName(name);
		this.setAgents(agents);
	}

	public Department()
	{
		super();
	}

	protected void initProperties()
	{
		super.initProperties();
		this.name = new SimpleStringProperty();
	}

	public String getName()
	{
		return name.get();
	}

	public void setName(String name)
	{
		this.name.set(name);
	}

	public SimpleStringProperty nameProperty()
	{
		return this.name;
	}

	public ForeignCollection<User> getAgents()
	{
		return this.agents;
	}


	public void setAgents(ForeignCollection<User> agents)
	{
		this.agents = agents;
	}

	public boolean getActive()
	{
		return active;
	}

	public boolean getDeleted()
	{
		return deleted;
	}

	public boolean isDeleted()
	{
		return deleted;
	}

	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
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


	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public Integer getId()

	{
		return id.getValue();
	}

	public void setId(int id)
	{
		this.id.setValue(id);
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
		if (id != null ? !id.equals(that.id) : that.id != null) { return false; }
		if (name != null ? !name.equals(that.name) : that.name != null) { return false; }

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (active ? 1 : 0);
		result = 31 * result + (deleted ? 1 : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (agents != null ? agents.hashCode() : 0);
		return result;
	}

	public String toString()
	{
		return this.name.getValue();
	}
}
