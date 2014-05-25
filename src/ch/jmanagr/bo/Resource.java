package ch.jmanagr.bo;


import java.util.ArrayList;
import java.util.List;

public class Resource implements BusinessObject
{
	private int id;
	private String name;
	private List<ResourceData> data;
	private ArrayList<Ticket> tickets;
	private Resource parent;
	private ArrayList<Resource> children;

	public Resource(int id, String name, List<ResourceData> data, ArrayList<Ticket> tickets, Resource parent,
	                ArrayList<Resource> children)
	{
		this.id = id;
		this.name = name;
		this.data = data;
		this.tickets = tickets;
		this.parent = parent;
		this.children = children;
	}

	public Resource(String name, List<ResourceData> data, ArrayList<Ticket> tickets, Resource parent,
	                ArrayList<Resource> children)
	{
		this.name = name;
		this.data = data;
		this.tickets = tickets;
		this.parent = parent;
		this.children = children;
	}

	public Resource() {}


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

	public List<ResourceData> getData()
	{
		return data;
	}

	public void setData(List<ResourceData> data)
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
