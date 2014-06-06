package ch.jmanagr.bo;

import com.sun.istack.internal.NotNull;
import com.sun.javafx.beans.IDProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

@IDProperty("id")
public class Department implements BusinessObject<Department>
{
	protected SimpleIntegerProperty id;
	@NotNull
	protected boolean active;
	@NotNull
	protected boolean deleted;

	@NotNull
	private SimpleStringProperty name;
	@NotNull
	private List<User> agents;

	public Department(int id, String name, List<User> agents, boolean active, boolean deleted)
	{
		this.initProperties();
		this.setId(id);
		this.setActive(active);
		this.setDeleted(deleted);
		this.setName(name);
		this.setAgents(agents);
	}

	public Department(String name, List<User> agents, boolean active, boolean deleted)
	{
		this.initProperties();
		this.setActive(active);
		this.setDeleted(deleted);
		this.setName(name);
		this.setAgents(agents);
	}

	public Department()
	{
		this.initProperties();
	}

	private void initProperties()
	{
		this.id = new SimpleIntegerProperty();
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

	public SimpleIntegerProperty idProperty()
	{
		return this.id;
	}

	public List<User> getAgents()
	{
		return agents;
	}

	public void setAgents(List<User> agents)
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
}
