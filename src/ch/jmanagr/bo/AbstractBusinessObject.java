package ch.jmanagr.bo;


import java.util.Observable;

public class AbstractBusinessObject extends Observable implements BusinessObject
{
	protected int id;
	protected boolean active;
	protected boolean deleted;

	public AbstractBusinessObject(int id, boolean active, boolean deleted)
	{
		super();
		this.setId(id);
		this.setActive(active);
		this.setDeleted(deleted);
	}

	public AbstractBusinessObject(boolean active, boolean deleted)
	{
		super();
		this.setActive(active);
		this.setDeleted(deleted);
	}

	public AbstractBusinessObject()
	{
		super();
	}

	protected void registerInCache()
	{
		BusinessObjectPool.getInstance().add(this);
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
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
		this.changed();
	}

	protected void changed()
	{
		this.setChanged();
		this.notifyObservers(null);
	}
}
