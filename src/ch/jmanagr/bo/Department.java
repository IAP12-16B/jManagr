package ch.jmanagr.bo;

import java.util.ArrayList;

public class Department implements BusinessObject
{
	private int id;
	private String name;
	private ArrayList<Agent> agents;

	public Department(int id, String name, ArrayList<Agent> agents)
	{
		this.id = id;
		this.name = name;
		this.agents = agents;

	}

	public Department(String name, ArrayList<Agent> agents)
	{
		this.name = name;
		this.agents = agents;

	}

	public Department() {}


	@Override
	public boolean equals(Object o)
	{
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Department that = (Department) o;

		if (id != that.id) {
			return false;
		}
		if (agents != null ? !agents.equals(that.agents) : that.agents != null) {
			return false;
		}
		if (name != null ? !name.equals(that.name) : that.name != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (agents != null ? agents.hashCode() : 0);
		return result;
	}

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

	public ArrayList<Agent> getAgents()
	{
		return agents;
	}

	public void setAgents(ArrayList<Agent> agents)
	{
		this.agents = agents;
	}
}
