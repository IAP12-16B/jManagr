package ch.jmanagr.bo;

import ch.jmanagr.lib.TICKET_STATE;

import java.util.Date;

public class Ticket extends AbstractBusinessObject
{
	private String name;
	private String description;
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
		super(id, active, deleted);
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
		super(active, deleted);
		this.setName(name);
		this.setDescription(description);
		this.setStatus(status);
		this.setDate(date);
		this.setResource(resource);
		this.setAgent(agent);
		this.setDepartment(department);
		this.setUser(user);
	}

	public Ticket()
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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
}
