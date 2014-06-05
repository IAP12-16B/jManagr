package ch.jmanagr.bo;


import com.j256.ormlite.field.DatabaseField;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class AbstractBO<BusinessObjectType extends BusinessObject<BusinessObjectType>>
		implements BusinessObject<BusinessObjectType>
{
	// todo add versioning

	@DatabaseField(id = true, useGetSet = true, generatedId = true, allowGeneratedIdInsert = true)
	protected SimpleIntegerProperty id;

	@DatabaseField(defaultValue = "1", useGetSet = true)
	protected boolean active;

	@DatabaseField(defaultValue = "0", useGetSet = true)
	protected boolean deleted;

	public AbstractBO()
	{
		super();
		this.initProperties();
	}

	public AbstractBO(int id)
	{
		super();
		this.initProperties();
		this.setId(id);
	}

	public AbstractBO(int id, boolean active, boolean deleted)
	{
		super();
		this.initProperties();
		this.setId(id);
		this.setActive(active);
		this.setDeleted(deleted);
	}

	protected void initProperties()
	{
		this.id = new SimpleIntegerProperty();
	}


	@Override
	public boolean isActive()
	{
		return active;
	}

	@Override
	public Integer getId()

	{
		return id.getValue();
	}

	@Override
	public void setId(int id)
	{
		this.id.set(id);
	}

	public SimpleIntegerProperty idProperty()
	{
		return this.id;
	}

	@Override
	public boolean getActive()
	{
		return active;
	}

	@Override
	public void setActive(boolean active)
	{
		this.active = active;
	}

	@Override
	public boolean getDeleted()
	{
		return deleted;
	}

	@Override
	public boolean isDeleted()
	{
		return deleted;
	}

	@Override
	public void setDeleted(boolean deleted)
	{
		this.deleted = deleted;
	}
}
