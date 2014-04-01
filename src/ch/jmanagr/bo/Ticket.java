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
	private Resource ressource;
	private Agent agent;
	private Department department;
	private User user;

	public Ticket(int id, String name, String description, TICKET_STATE status, Date date, Resource ressource,
	              Agent agent, Department department, User user)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.date = date;
		this.ressource = ressource;
		this.agent = agent;
		this.department = department;
		this.user = user;
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

		Ticket ticket = (Ticket) o;

		if (id != ticket.id) {
			return false;
		}
		if (agent != null ? !agent.equals(ticket.agent) : ticket.agent != null) {
			return false;
		}
		if (date != null ? !date.equals(ticket.date) : ticket.date != null) {
			return false;
		}
		if (department != null ? !department.equals(ticket.department) : ticket.department != null) {
			return false;
		}
		if (description != null ? !description.equals(ticket.description) : ticket.description != null) {
			return false;
		}
		if (!name.equals(ticket.name)) {
			return false;
		}
		if (ressource != null ? !ressource.equals(ticket.ressource) : ticket.ressource != null) {
			return false;
		}
		if (status != ticket.status) {
			return false;
		}
		if (user != null ? !user.equals(ticket.user) : ticket.user != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = id;
		result = 31 * result + name.hashCode();
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (ressource != null ? ressource.hashCode() : 0);
		result = 31 * result + (agent != null ? agent.hashCode() : 0);
		result = 31 * result + (department != null ? department.hashCode() : 0);
		result = 31 * result + (user != null ? user.hashCode() : 0);
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

	public Resource getRessource()
	{
		return ressource;
	}

	public void setRessource(Resource ressource)
	{
		this.ressource = ressource;
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
