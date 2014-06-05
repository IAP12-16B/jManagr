package ch.jmanagr.bo;


import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import javafx.beans.property.SimpleStringProperty;

@DatabaseTable(tableName = "Resource")
public class Resource extends AbstractBO<Resource>
{

	@DatabaseField(useGetSet = true, canBeNull = false)
	private SimpleStringProperty name;

	@ForeignCollectionField(eager = true, foreignFieldName = "resource")
	private ForeignCollection<ResourceData> data;

	@ForeignCollectionField(eager = true, foreignFieldName = "resource")
	private ForeignCollection<Ticket> tickets;

	@DatabaseField(useGetSet = true,
	               canBeNull = true,
	               foreign = true,
	               foreignAutoRefresh = true,
	               foreignAutoCreate = true)
	private Resource parent;

	@ForeignCollectionField(eager = true, foreignFieldName = "parent")
	private ForeignCollection<Resource> children;

	public Resource()
	{
		super();
	}

	public Resource(int id)
	{
		super(id);
	}

	protected void initProperties()
	{
		super.initProperties();
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

	public ForeignCollection<ResourceData> getData()
	{
		return data;
	}

	public void setData(ForeignCollection<ResourceData> data)
	{
		this.data = data;
	}

	public ForeignCollection<Ticket> getTickets()
	{
		return tickets;
	}

	public void setTickets(ForeignCollection<Ticket> tickets)
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

	public ForeignCollection<Resource> getChildren()
	{
		return children;
	}

	public void setChildren(ForeignCollection<Resource> children)
	{
		this.children = children;
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

	// todo addData?
}
