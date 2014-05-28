package ch.jmanagr.bo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class Department implements BusinessObject
{
	private int id;
	private SimpleStringProperty name = new SimpleStringProperty("");
	private List<User> agents;
	private boolean active;
	private boolean deleted;

	public boolean isActive()
	{
		return active;
	}

	public boolean getActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public boolean isDeleted()
	{
		return deleted;
	}

	public boolean getDeleted()
	{
		return deleted;
	}

	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
	}

	public Department(int id, String name, List<User> agents, boolean active, boolean deleted)
	{
		this.id = id;
		this.name.set(name);
		this.agents = agents;
		this.active = active;
		this.deleted = deleted;

	}

	public Department(String name, List<User> agents, boolean active, boolean deleted)
	{
		this.name.set(name);
		this.agents = agents;
		this.active = active;
		this.deleted = deleted;

	}

	public Department() {}

	public int getId()
	{
		return id;
	}


	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name.getName();
	}

	public void setName(String name)
	{
		this.name.set(name);
	}

    public SimpleStringProperty nameProperty() {
        return this.name;
    }

	public List<User> getAgents()
	{
		return agents;
	}

	public void setAgents(List<User> agents)
	{
		this.agents = agents;
	}
}
