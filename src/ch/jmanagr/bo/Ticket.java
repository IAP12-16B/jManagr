package ch.jmanagr.bo;

import ch.jmanagr.lib.TICKET_STATE;
import com.sun.istack.internal.NotNull;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Ticket implements BusinessObject<Ticket>
{
	protected SimpleIntegerProperty id;
	@NotNull
	protected boolean active;
	@NotNull
	protected boolean deleted;

	private SimpleStringProperty name;
	private SimpleStringProperty description;
	@NotNull
	private TICKET_STATE status;
	private Date date;
	private Resource resource;
	private User agent;
	private Department department;
	private User user;

	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param status
	 * @param date
	 * @param resource
	 * @param agent
	 * @param department
	 * @param user
	 * @param active
	 * @param deleted
	 */
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

	/**
	 * @param name
	 * @param description
	 * @param status
	 * @param date
	 * @param resource
	 * @param agent
	 * @param department
	 * @param user
	 * @param active
	 * @param deleted
	 */
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

	public Ticket()
	{
		this.id = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty();
		this.description = new SimpleStringProperty();
	}


	/**
	 * @return
	 */
	public String getName()
	{
		return name.get();
	}

	/**
	 * @param name
	 */
	public void setName(String name)
	{
		this.name.set(name);
	}

	/**
	 * @return
	 */
	public String getDescription()
	{
		return description.get();
	}

	/**
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description.set(description);
	}

	/**
	 * @return
	 */
	public TICKET_STATE getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(TICKET_STATE status)
	{
		this.status = status;
	}

	/**
	 * @return
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * @param date
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * @return
	 */
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

	/**
	 * Copies the values from an other object
	 *
	 * @param bo
	 */
	@Override
	public void copyFromObject(Ticket bo)
	{
		this.setId(bo.getId());
		this.setActive(bo.getActive());
		this.setDeleted(bo.getDeleted());
		this.setName(bo.getName());
		this.setDescription(bo.getDescription());
		this.setStatus(bo.getStatus());
		this.setDate(bo.getDate());
		this.setResource(bo.getResource());
		this.setAgent(bo.getAgent());
		this.setDepartment(bo.getDepartment());
		this.setUser(bo.getUser());
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
