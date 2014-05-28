package ch.jmanagr.bo;


import java.util.ArrayList;
import java.util.List;

public class Resource implements BusinessObject
{
	protected int id;
	protected boolean active;
	protected boolean deleted;

	private String name;
	private List<ResourceData> data;
	private ArrayList<Ticket> tickets;
	private Resource parent;
	private ArrayList<Resource> children;

	public Resource(int id, String name, List<ResourceData> data, ArrayList<Ticket> tickets, Resource parent,
	                ArrayList<Resource> children, boolean active, boolean deleted)
	{
		this.setId(id);
		this.setActive(active);
		this.setDeleted(deleted);
		this.setName(name);
		this.setData(data);
		this.setTickets(tickets);
		this.setParent(parent);
		this.setChildren(children);
	}

	public Resource(String name, List<ResourceData> data, ArrayList<Ticket> tickets, Resource parent,
	                ArrayList<Resource> children, boolean active, boolean deleted)
	{
		this.setActive(active);
		this.setDeleted(deleted);
		this.setName(name);
		this.setData(data);
		this.setTickets(tickets);
		this.setParent(parent);
		this.setChildren(children);
	}

	public Resource() {}


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

	public boolean getActive()
	{
		return active;
	}

	public boolean getDeleted()
	{
		return deleted;
	}

	public boolean isDeleted()
	{
		return deleted;
	}

	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public int getId()

	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
