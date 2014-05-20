package ch.jmanagr.bo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Department implements BusinessObject
{
	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleStringProperty name = new SimpleStringProperty("");
	private ArrayList<Agent> agents;

	public Department(int id, String name, ArrayList<Agent> agents)
	{
		this.id.set(id);
		this.name.set(name);
		this.agents = agents;

	}

	public Department(String name, ArrayList<Agent> agents)
	{
		this.name.set(name);
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
    // todo kim was au immer das isch goht nüm will i vo int zu simpleintproperty gwechselt han..
	/*public int hashCode()
	{
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (agents != null ? agents.hashCode() : 0);
		return result;
	}*/

	public int getId()
	{
		return this.id.get();
	}


	public void setId(int id)
	{
		this.id.set(id);
	}

    public SimpleIntegerProperty idProperty()
    {
        return this.id;
    }

	public String getName()
	{
		return this.name.get();
	}

	public void setName(String name)
	{
		this.name.set(name);
	}

    public SimpleStringProperty nameProperty()
    {
        return this.name;
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
