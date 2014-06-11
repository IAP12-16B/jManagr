package ch.jmanagr.bo;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ResourceData")
public class ResourceData
{
	@DatabaseField(
			useGetSet = true,
	               uniqueCombo = true,
	               foreign = true,
	               foreignAutoCreate = true,
	               foreignAutoRefresh = true)
	private Resource resource;

	@DatabaseField(useGetSet = true, uniqueCombo = true)
	private String key; // todo simple string property

	@DatabaseField(useGetSet = true, canBeNull = true)
	private String value; // todo simple string property

	@DatabaseField(version = true, useGetSet = true)
	protected Integer version;

	public ResourceData(Resource resource, String key, String value)
	{
		this.setResource(resource);
		this.setKey(key);
		this.setValue(value);
	}

	public ResourceData() {}

	public Resource getResource()
	{
		return resource;
	}

	public void setResource(Resource resource)
	{
		this.resource = resource;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public Integer getVersion()
	{
		return version;
	}

	public void setVersion(Integer version)
	{
		this.version = version;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) { return true; }
		if (!(o instanceof ResourceData)) { return false; }

		ResourceData that = (ResourceData) o;

		if (key != null ? !key.equals(that.key) : that.key != null) { return false; }
		if (resource != null ? !resource.equals(that.resource) : that.resource != null) { return false; }
		if (value != null ? !value.equals(that.value) : that.value != null) { return false; }

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = resource != null ? resource.hashCode() : 0;
		result = 31 * result + (key != null ? key.hashCode() : 0);
		result = 31 * result + (value != null ? value.hashCode() : 0);
		return result;
	}
}
