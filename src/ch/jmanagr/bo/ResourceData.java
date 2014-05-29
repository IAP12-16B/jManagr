package ch.jmanagr.bo;


public class ResourceData
{
	private Resource resource;
	private String key;
	private String value;

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
}
