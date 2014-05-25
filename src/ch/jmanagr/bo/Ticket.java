package ch.jmanagr.bo;

import ch.jmanagr.lib.TICKET_STATE;

import java.util.Date;

public class Ticket implements BusinessObject
{
	private int id;
	private String name;
	private String description;
	private TICKET_STATE status;
	private Date date;
	private Resource resource;
	private Agent agent;
	private Department department;
	private User user;
	protected boolean active;
	protected boolean deleted;

	public Ticket(int id, String name, String description, TICKET_STATE status, Date date, Resource resource,
	              Agent agent, Department department, User user)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.date = date;
		this.resource = resource;
		this.agent = agent;
		this.department = department;
		this.user = user;
	}

	public Ticket(String name, String description, TICKET_STATE status, Date date, Resource resource,
	              Agent agent, Department department, User user)
	{
		this.name = name;
		this.description = description;
		this.status = status;
		this.date = date;
		this.resource = resource;
		this.agent = agent;
		this.department = department;
		this.user = user;
	}

	public Ticket() {}

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

	public Agent getAgent()
	{
		return agent;
	}

	public void setAgent(Agent agent)
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
