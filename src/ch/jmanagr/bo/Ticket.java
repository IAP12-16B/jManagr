package ch.jmanagr.bo;

import ch.jmanagr.lib.TICKET_STATE;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

/**
 * Ticket BusinessObject
 */
@DatabaseTable(tableName = "Ticket")
public class Ticket implements BusinessObject<Ticket>
{
	// id
	@DatabaseField(useGetSet = true, generatedId = true, allowGeneratedIdInsert = true, index = true)
	private Integer id;
	protected SimpleIntegerProperty idProperty;

	// datafield
	@DatabaseField(useGetSet = true, canBeNull = true, dataType = DataType.STRING)
	private String name;
	private SimpleStringProperty nameProperty;
	@DatabaseField(useGetSet = true, canBeNull = true, dataType = DataType.STRING)
	private String description;
	private SimpleStringProperty descriptionProperty;
	@DatabaseField(useGetSet = true,
	               defaultValue = "OPEN",
	               unknownEnumName = "OPEN",
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

	// flags
	@DatabaseField(useGetSet = true)
	protected boolean active = true;
	@DatabaseField(useGetSet = true, index = true)
	protected boolean deleted = false;
	@DatabaseField(version = true, useGetSet = true)
	protected Integer version;


	public Ticket()
	{
		this.initProperties();
	}

	/**
	 * @param id th id of the Ticket
	 */
	public Ticket(int id)
	{
		this.initProperties();
		this.setId(id);
	}

	protected void initProperties()
	{
		this.idProperty = new SimpleIntegerProperty();
		this.nameProperty = new SimpleStringProperty();
		this.descriptionProperty = new SimpleStringProperty();
	}


	/**
	 * @return name
	 */
	public String getName()
	{
		return nameProperty.get();
	}

	/**
	 * @param name the name
	 */
	public void setName(String name)
	{
		this.nameProperty.set(name);
	}

	/**
	 * @return description
	 */
	public String getDescription()
	{
		return descriptionProperty.get();
	}

	/**
	 * @param description description
	 */
	public void setDescription(String description)
	{
		this.descriptionProperty.set(description);
	}

	/**
	 * @return status
	 */
	public TICKET_STATE getStatus()
	{
		return status;
	}

	/**
	 * @param status status
	 */
	public void setStatus(TICKET_STATE status)
	{
		this.status = status;
	}

	/**
	 * @return Date
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * @param date Date
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * @return the assigned Resource (if any)
	 */
	public Resource getResource()
	{
		return resource;
	}


	/**
	 * Set the Resource
	 *
	 * @param resource resource
	 */
	public void setResource(Resource resource)
	{
		this.resource = resource;
	}

	/**
	 * Get the assigned Agent
	 *
	 * @return agent
	 */
	public User getAgent()
	{
		return agent;
	}

	/**
	 * Assign an agent
	 *
	 * @param agent agent
	 */
	public void setAgent(User agent)
	{
		this.agent = agent;
	}

	/**
	 * Get the assigned Department
	 *
	 * @return a Department
	 */
	public Department getDepartment()
	{
		return department;
	}

	/**
	 * Assign a Department
	 *
	 * @param department a Department
	 */
	public void setDepartment(Department department)
	{
		this.department = department;
	}

	/**
	 * Get the User who has created this Ticket
	 *
	 * @return User
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * Set the creator of this Ticket
	 *
	 * @param user The User who has created this Ticket
	 */
	public void setUser(User user)
	{
		this.user = user;
	}


	/**
	 * Copies the values from an other object
	 *
	 * @param bo The Ticket to copy from
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

	/**
	 * Name property (required for JavaFX)
	 *
	 * @return the name property
	 */
	public SimpleStringProperty nameProperty()
	{
		return this.nameProperty;
	}

	/**
	 * Description property (required for JavaFX)
	 *
	 * @return the description property
	 */
	public SimpleStringProperty descriptionProperty()
	{
		return this.descriptionProperty;
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
		if (descriptionProperty != null ?
		    !descriptionProperty.equals(ticket.descriptionProperty) :
		    ticket.descriptionProperty != null) {
			return false;
		}
		if (idProperty != null ? !idProperty.equals(ticket.idProperty) : ticket.idProperty != null) { return false; }
		if (nameProperty != null ? !nameProperty.equals(ticket.nameProperty) : ticket.nameProperty != null) {
			return false;
		}
		if (resource != null ? !resource.equals(ticket.resource) : ticket.resource != null) { return false; }
		if (status != ticket.status) { return false; }
		if (user != null ? !user.equals(ticket.user) : ticket.user != null) { return false; }

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = idProperty != null ? idProperty.hashCode() : 0;
		result = 31 * result + (active ? 1 : 0);
		result = 31 * result + (deleted ? 1 : 0);
		result = 31 * result + (nameProperty != null ? nameProperty.hashCode() : 0);
		result = 31 * result + (descriptionProperty != null ? descriptionProperty.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		return result;
	}

	public Integer getVersion()
	{
		return version;
	}

	public void setVersion(Integer version)
	{
		this.version = version;
	}


	/**
	 * Get the id of the Ticket
	 *
	 * @return the id
	 */
	@Override
	public Integer getId()

	{
		return idProperty.getValue();
	}

	/**
	 * Set ID
	 *
	 * @param id the id
	 */
	@Override
	public void setId(Integer id)
	{
		this.idProperty.set(id);
	}

	/**
	 * Id property (used by JavaFX)
	 *
	 * @return the id property
	 */
	public SimpleIntegerProperty idProperty()
	{
		return this.idProperty;
	}

	/**
	 * @return whether the object is active
	 */
	@Override
	public boolean getActive()
	{
		return active;
	}

	/**
	 * Sets the active flag of the object
	 *
	 * @param active active flag
	 */
	@Override
	public void setActive(boolean active)
	{
		this.active = active;
	}

	/**
	 * @return whether the object is deleted
	 */
	@Override
	public boolean getDeleted()
	{
		return deleted;
	}

	/**
	 * Sets the deleted flag of the object
	 *
	 * @param deleted deleted flag
	 */
	@Override
	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
	}
}
