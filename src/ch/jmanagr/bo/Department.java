package ch.jmanagr.bo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class Department implements BusinessObject
{
	protected SimpleIntegerProperty id;
	protected boolean active;
	protected boolean deleted;

	private SimpleStringProperty name;
	private List<User> agents;

	public Department(int id, String name, List<User> agents, boolean active, boolean deleted)
	{
		this.id = new SimpleIntegerProperty();
		this.setId(id);
		this.setActive(active);
		this.setDeleted(deleted);
		this.name = new SimpleStringProperty();
		this.setName(name);
		this.setAgents(agents);
	}

	public Department(String name, List<User> agents, boolean active, boolean deleted)
	{
		this.id = new SimpleIntegerProperty();
		this.setActive(active);
		this.setDeleted(deleted);
		this.name = new SimpleStringProperty();
		this.setName(name);
		this.setAgents(agents);
	}

	public Department() {
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

	public SimpleStringProperty nameProperty() // FIXME @mnewmedia Question: do we need this property?
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
