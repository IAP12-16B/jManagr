package ch.jmanagr.bo;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Department BusinessObject
 */
@DatabaseTable(tableName = "Department")
public class Department implements BusinessObject<Department>
{
	@DatabaseField(useGetSet = true, generatedId = true, allowGeneratedIdInsert = true, index = true)
	private Integer id;
	protected SimpleIntegerProperty idProperty;

	@DatabaseField(useGetSet = true, canBeNull = true, dataType = DataType.STRING)
	private String name;
	private SimpleStringProperty nameProperty;
	@ForeignCollectionField(eager = true, foreignFieldName = "department")
	private Collection<User> agents;

	@DatabaseField(useGetSet = true)
	protected boolean active = true;
	@DatabaseField(useGetSet = true, index = true)
	protected boolean deleted = false;
	@DatabaseField(version = true, useGetSet = true)
	protected Integer version;


	/**
	 * Default Constrcutor
	 */
	public Department()
	{
		this.initProperties();
		this.agents = new ArrayList<>();
	}

	/**
	 * Constructor with id
	 * @param id the ID of the Department
	 */
	public Department(Integer id)
	{
		this();
		this.setId(id);
	}

	protected void initProperties()
	{
		this.idProperty = new SimpleIntegerProperty();
		this.nameProperty = new SimpleStringProperty();
	}

	/**
	 * Name getter
	 *
	 * @return the name of the Department
	 */
	public String getName()
	{
		return nameProperty.get();
	}

	/**
	 * Name setter
	 *
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.nameProperty.set(name);
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
	 * Gets all Agents in this Department
	 *
	 * @return a collection of Agents
	 */
	public Collection<User> getAgents()
	{
		return this.agents;
	}


	/**
	 * Sets all Agents of this Department
	 *
	 * @param agents the Agents to set
	 */
	public void setAgents(Collection<User> agents)
	{
		this.agents = agents;
	}


	/**
	 * Adds an Agent to this Department
	 *
	 * @param agent the Agent to add
	 */
	public void addAgent(User agent)
	{
		this.agents.add(agent);
	}


	/**
	 * Copies the values from an other Department
	 *
	 * @param bo the Department to copy from
	 */
	@Override
	public void copyFromObject(Department bo)
	{
		this.setId(bo.getId());
		this.setActive(bo.getActive());
		this.setDeleted(bo.getDeleted());
		this.setName(bo.getName());
		this.setAgents(bo.getAgents());
	}


	/**
	 * Method to compare two objects
	 *
	 * @param o the object to compare
	 *
	 * @return whether the passed object is the same as the current instance
	 */
	@Override
	public boolean equals(Object o)
	{
		if (this == o) { return true; }
		if (!(o instanceof Department)) { return false; }

		Department that = (Department) o;

		if (active != that.active) { return false; }
		if (deleted != that.deleted) { return false; }
		if (agents != null ? !agents.equals(that.agents) : that.agents != null) { return false; }
		if (idProperty != null ? !idProperty.equals(that.idProperty) : that.idProperty != null) { return false; }
		if (nameProperty != null ? !nameProperty.equals(that.nameProperty) : that.nameProperty != null) {
			return false;
		}

		return true;
	}

	/**
	 * @return a numerical representation of this object
	 */
	@Override
	public int hashCode()
	{
		int result = idProperty != null ? idProperty.hashCode() : 0;
		result = 31 * result + (active ? 1 : 0);
		result = 31 * result + (deleted ? 1 : 0);
		result = 31 * result + (nameProperty != null ? nameProperty.hashCode() : 0);
		result = 31 * result + (agents != null ? agents.hashCode() : 0);
		return result;
	}

	/**
	 * Convert Object to string
	 *
	 * @return the name of the Object
	 */
	@Override
	public String toString()
	{
		return this.nameProperty.get();
	}

	/**
	 * @return the version
	 */
	@Override
	public Integer getVersion()
	{
		return version;
	}

	/**
	 * Sets the version of this Department
	 *
	 * @param version the version
	 */
	@Override
	public void setVersion(Integer version)
	{
		this.version = version;
	}


	/**
	 * Get the id of the Department
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
	 * @return whether the Department is active
	 */
	@Override
	public boolean getActive()
	{
		return active;
	}

	/**
	 * Sets the active flag of the Department
	 *
	 * @param active active flag
	 */
	@Override
	public void setActive(boolean active)
	{
		this.active = active;
	}

	/**
	 * @return whether the Department is deleted
	 */
	@Override
	public boolean getDeleted()
	{
		return deleted;
	}

	/**
	 * Sets the deleted flag of the Department
	 *
	 * @param deleted deleted flag
	 */
	@Override
	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
	}
}
