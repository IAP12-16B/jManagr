package ch.jmanagr.bo;

import ch.jmanagr.lib.SimpleStringPropertyPersister;
import ch.jmanagr.lib.TICKET_STATE;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

@DatabaseTable(tableName = "User")
public class Ticket extends AbstractBO<Ticket>
{

	@DatabaseField(useGetSet = true, canBeNull = true, persisterClass = SimpleStringPropertyPersister.class)
	private SimpleStringProperty name;

	@DatabaseField(useGetSet = true, canBeNull = true, persisterClass = SimpleStringPropertyPersister.class)
	private SimpleStringProperty description;

	@DatabaseField(useGetSet = true,
	               defaultValue = "0",
	               unknownEnumName = "0",
	               dataType = DataType.ENUM_INTEGER,
	               canBeNull = false)
	private TICKET_STATE status;

	@DatabaseField(useGetSet = true, canBeNull = true)
	private Date date;

	@DatabaseField(useGetSet = true,
	               canBeNull = false,
	               foreign = true,
	               foreignAutoCreate = true,
	               foreignAutoRefresh = true)
	private Resource resource;

	@DatabaseField(useGetSet = true,
	               canBeNull = true,
	               foreign = true,
	               foreignAutoCreate = true,
	               foreignAutoRefresh = true)
	private User agent;

	@DatabaseField(useGetSet = true,
	               canBeNull = true,
	               foreign = true,
	               foreignAutoCreate = true,
	               foreignAutoRefresh = true)
	private Department department;

	@DatabaseField(useGetSet = true,
	               canBeNull = false,
	               foreign = true,
	               foreignAutoCreate = true,
	               foreignAutoRefresh = true)
	private User user;


	public Ticket()
	{
		super();
	}

	public Ticket(int id)
	{
		super(id);
	}

	protected void initProperties()
	{
		super.initProperties();
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

	public SimpleStringProperty nameProperty()
	{
		return this.name;
	}

	public SimpleStringProperty descriptionProperty()
	{
		return this.description;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) { return true; }
		if (!(o instanceof Ticket)) { return false; }

		Ticket ticket = (Ticket) o;

		if (active != ticket.active) { return false; }
		if (deleted != ticket.deleted) { return false; }
		if (agent != null ? !agent.equals(ticket.agent) : ticket.agent != null) { return false; }
		if (date != null ? !date.equals(ticket.date) : ticket.date != null) { return false; }
		if (department != null ? !department.equals(ticket.department) : ticket.department != null) { return false; }
		if (description != null ? !description.equals(ticket.description) : ticket.description != null) {
			return false;
		}
		if (id != null ? !id.equals(ticket.id) : ticket.id != null) { return false; }
		if (name != null ? !name.equals(ticket.name) : ticket.name != null) { return false; }
		if (resource != null ? !resource.equals(ticket.resource) : ticket.resource != null) { return false; }
		if (status != ticket.status) { return false; }
		if (user != null ? !user.equals(ticket.user) : ticket.user != null) { return false; }

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (active ? 1 : 0);
		result = 31 * result + (deleted ? 1 : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (resource != null ? resource.hashCode() : 0);
		result = 31 * result + (agent != null ? agent.hashCode() : 0);
		result = 31 * result + (department != null ? department.hashCode() : 0);
		result = 31 * result + (user != null ? user.hashCode() : 0);
		return result;
	}
}
