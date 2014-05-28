package ch.jmanagr.bo;

import ch.jmanagr.lib.TICKET_STATE;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Ticket implements BusinessObject
{
	protected SimpleIntegerProperty id;
	protected boolean active;
	protected boolean deleted;

	private SimpleStringProperty name;
	private SimpleStringProperty description;
	private TICKET_STATE status;
	private Date date;
	private Resource resource;
	private User agent;
	private Department department;
	private User user;

	public Ticket(int id, String name, String description, TICKET_STATE status, Date date, Resource resource,
	              User agent, Department department, User user, boolean active,
	              boolean deleted)
	{
		this.id = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty();
		this.description = new SimpleStringProperty();
		this.setId(id);
		this.setActive(active);
		this.setDeleted(deleted);
		this.setName(name);
		this.setDescription(description);
		this.setStatus(status);
		this.setDate(date);
		this.setResource(resource);
		this.setAgent(agent);
		this.setDepartment(department);
		this.setUser(user);
	}

	public Ticket(String name, String description, TICKET_STATE status, Date date, Resource resource,
	              User agent, Department department, User user, boolean active,
	              boolean deleted)
	{
		this.id = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty();
		this.description = new SimpleStringProperty();
		this.setActive(active);
		this.setDeleted(deleted);
		this.setName(name);
		this.setDescription(description);
		this.setStatus(status);
		this.setDate(date);
		this.setResource(resource);
		this.setAgent(agent);
		this.setDepartment(department);
		this.setUser(user);
	}

	public Ticket() {
		this.id = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty();
		this.description = new SimpleStringProperty();
	}


	public String getName()
	{
		return name.get();
	}

	public void setName(String name)
	{
		this.name.set(name);
	}

	public String getDescription()
	{
		return description.get();
	}

	public void setDescription(String description)
	{
		this.description.set(description);
	}

	public TICKET_STATE getStatus()
	{
		return status;
	}

	public void setStatus(TICKET_STATE status)
	{
		this.status = status;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public Resource getResource()
	{
		return resource;
	}


	public void setResource(Resource resource)
	{
		this.resource = resource;
	}

	public User getAgent()
	{
		return agent;
	}

	public void setAgent(User agent)
	{
		this.agent = agent;
	}

	public Department getDepartment()
	{
		return department;
	}

	public void setDepartment(Department department)
	{
		this.department = department;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
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
		return id.get();
	}

	public void setId(int id)
	{
		this.id.set(id);
	}
}
