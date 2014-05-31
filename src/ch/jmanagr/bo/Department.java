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

	public int getId()

	{
		return id.get();
	}

	public void setId(int id)
	{
		this.id.set(id);
	}
}
