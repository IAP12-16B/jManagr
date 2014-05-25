package ch.jmanagr.bo;

import java.util.List;

public class Department implements BusinessObject
{
	private int id;
	private String name;
	private List<User> agents;
	private boolean active;
	private boolean deleted;


	public Department(int id, String name, List<User> agents, boolean active, boolean deleted)
	{
		this.id = id;
		this.name = name;
		this.agents = agents;
		this.active = active;
		this.deleted = deleted;

	}

	public Department(String name, List<User> agents, boolean active, boolean deleted)
	{
		this.name = name;
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
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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
