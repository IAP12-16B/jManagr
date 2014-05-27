package ch.jmanagr.bo;

import java.util.List;

public class Department extends AbstractBusinessObject
{
	private String name;
	private List<User> agents;

	public Department(int id, String name, List<User> agents, boolean active, boolean deleted)
	{
		super(id, active, deleted);
		this.setName(name);
		this.setAgents(agents);
	}

	public Department(String name, List<User> agents, boolean active, boolean deleted)
	{
		super(active, deleted);
		this.setName(name);
		this.setAgents(agents);
	}

	public Department()
	{
		super();
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
