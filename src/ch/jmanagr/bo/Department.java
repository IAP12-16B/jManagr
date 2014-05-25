package ch.jmanagr.bo;

import java.util.List;

public class Department implements BusinessObject
{
	private int id;
	private String name;
	private List<Agent> agents;
	protected boolean active;
	protected boolean deleted;

	public Department(int id, String name, List<Agent> agents)
	{
		this.id = id;
		this.name = name;
		this.agents = agents;

	}

	public Department(String name, List<Agent> agents)
	{
		this.name = name;
		this.agents = agents;

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

	public List<Agent> getAgents()
	{
		return agents;
	}

	public void setAgents(List<Agent> agents)
	{
		this.agents = agents;
	}
}
