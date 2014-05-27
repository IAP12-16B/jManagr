package ch.jmanagr.bo;


import java.util.ArrayList;
import java.util.List;

public class Resource extends AbstractBusinessObject
{
	private String name;
	private List<ResourceData> data;
	private ArrayList<Ticket> tickets;
	private Resource parent;
	private ArrayList<Resource> children;

	public Resource(int id, String name, List<ResourceData> data, ArrayList<Ticket> tickets, Resource parent,
	                ArrayList<Resource> children, boolean active, boolean deleted)
	{
		super(id, active, deleted);
		this.setName(name);
		this.setData(data);
		this.setTickets(tickets);
		this.setParent(parent);
		this.setChildren(children);
	}

	public Resource(String name, List<ResourceData> data, ArrayList<Ticket> tickets, Resource parent,
	                ArrayList<Resource> children, boolean active, boolean deleted)
	{
		super(active, deleted);
		this.setName(name);
		this.setData(data);
		this.setTickets(tickets);
		this.setParent(parent);
		this.setChildren(children);
	}

	public Resource()
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
