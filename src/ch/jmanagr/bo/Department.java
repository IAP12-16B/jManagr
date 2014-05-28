package ch.jmanagr.bo;

import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class Department extends AbstractBusinessObject
{
	private SimpleStringProperty name;
	private List<User> agents;

	public Department(int id, String name, List<User> agents, boolean active, boolean deleted)
	{
		super(id, active, deleted);
		this.name = new SimpleStringProperty();
		this.setName(name);
		this.setAgents(agents);
	}

	public Department(String name, List<User> agents, boolean active, boolean deleted)
	{
		super(active, deleted);
		this.name = new SimpleStringProperty();
		this.setName(name);
		this.setAgents(agents);
	}

	public Department()
	{
		super();
	}

	public String getName()
	{
		return name.getName();
	}

	public void setName(String name)
	{
		this.name.set(name);
	}

	public SimpleStringProperty nameProperty()
	{
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
