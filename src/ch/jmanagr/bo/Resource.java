package ch.jmanagr.bo;


import com.sun.istack.internal.NotNull;
import com.sun.javafx.beans.IDProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

@IDProperty("id")
public class Resource implements BusinessObject<Resource>
{
	// Todo: ordering, parent/child, etc...
	protected SimpleIntegerProperty id;
	@NotNull
	protected boolean active;
	@NotNull
	protected boolean deleted;

	@NotNull
	private SimpleStringProperty name;
	private List<ResourceData> data;
	private ArrayList<Ticket> tickets;
	private Resource parent;
	private ArrayList<Resource> children;

	public Resource(int id, String name, List<ResourceData> data, ArrayList<Ticket> tickets, Resource parent,
	                ArrayList<Resource> children, boolean active, boolean deleted)
	{
		this.id = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty();
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
		this.id = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty();
		this.setActive(active);
		this.setDeleted(deleted);
		this.setName(name);
		this.setData(data);
		this.setTickets(tickets);
		this.setParent(parent);
		this.setChildren(children);
	}

	public Resource()
	{
		this.id = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty();
	}


	public String getName()
	{
		return name.get();
	}

	public void setName(String name)
	{
		this.name.set(name);
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

	/**
	 * Copies the values from an other object
	 *
	 * @param bo
	 */
	@Override
	public void copyFromObject(Resource bo)
	{
		this.setId(bo.getId());
		this.setActive(bo.getActive());
		this.setDeleted(bo.getDeleted());
		this.setName(bo.getName());
		this.setData(bo.getData());
		this.setTickets(bo.getTickets());
		this.setParent(bo.getParent());
		this.setChildren(bo.getChildren());
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public Integer getId()

	{
		return id.getValue();
	}

	public void setId(int id)
	{
		this.id.setValue(id);
	}

	public SimpleIntegerProperty idProperty()
	{
		return this.id;
	}

	public SimpleStringProperty nameProperty()
	{
		return this.name;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) { return true; }
		if (!(o instanceof Resource)) { return false; }

		Resource resource = (Resource) o;

		if (active != resource.active) { return false; }
		if (deleted != resource.deleted) { return false; }
		if (children != null ? !children.equals(resource.children) : resource.children != null) { return false; }
		if (data != null ? !data.equals(resource.data) : resource.data != null) { return false; }
		if (id != null ? !id.equals(resource.id) : resource.id != null) { return false; }
		if (name != null ? !name.equals(resource.name) : resource.name != null) { return false; }
		if (parent != null ? !parent.equals(resource.parent) : resource.parent != null) { return false; }
		if (tickets != null ? !tickets.equals(resource.tickets) : resource.tickets != null) { return false; }

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (active ? 1 : 0);
		result = 31 * result + (deleted ? 1 : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (data != null ? data.hashCode() : 0);
		result = 31 * result + (tickets != null ? tickets.hashCode() : 0);
		result = 31 * result + (parent != null ? parent.hashCode() : 0);
		result = 31 * result + (children != null ? children.hashCode() : 0);
		return result;
	}
}
