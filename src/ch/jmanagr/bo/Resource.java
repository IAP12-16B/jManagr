package ch.jmanagr.bo;


import java.util.ArrayList;
import java.util.HashMap;

public class Resource implements BusinessObject
{
	private int id;
	private String name;
	private HashMap<String, String> data;
	private ArrayList<Ticket> tickets;
	private Resource parent;
	private ArrayList<Resource> children;

	public Resource(int id, String name, HashMap<String, String> data, ArrayList<Ticket> tickets, Resource parent,
	                ArrayList<Resource> children)
	{
		this.id = id;
		this.name = name;
		this.data = data;
		this.tickets = tickets;
		this.parent = parent;
		this.children = children;
	}

	public int getId()
	{
		return id;
	}


	public void setId(int id)

	{
		this.id = id;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Resource resource = (Resource) o;

		if (id != resource.id) {
			return false;
		}
		if (children != null ? !children.equals(resource.children) : resource.children != null) {
			return false;
		}
		if (data != null ? !data.equals(resource.data) : resource.data != null) {
			return false;
		}
		if (name != null ? !name.equals(resource.name) : resource.name != null) {
			return false;
		}
		if (parent != null ? !parent.equals(resource.parent) : resource.parent != null) {
			return false;
		}
		if (tickets != null ? !tickets.equals(resource.tickets) : resource.tickets != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (data != null ? data.hashCode() : 0);
		result = 31 * result + (tickets != null ? tickets.hashCode() : 0);
		result = 31 * result + (parent != null ? parent.hashCode() : 0);
		result = 31 * result + (children != null ? children.hashCode() : 0);
		return result;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public HashMap<String, String> getData()
	{
		return data;
	}

	public void setData(HashMap<String, String> data)
	{
		this.data = data;
	}

	public ArrayList<Ticket> getTickets()
	{
		return tickets;
	}

	public void setTickets(ArrayList<Ticket> tickets)
	{
		this.tickets = tickets;
	}

	// Todo: addChildren & removeChildren

	public Resource getParent()
	{
		return parent;
	}

	public void setParent(Resource parent)
	{
		this.parent = parent;
	}

	public ArrayList<Resource> getChildren()
	{
		return children;
	}

	public void setChildren(ArrayList<Resource> children)
	{
		this.children = children;
	}
}
