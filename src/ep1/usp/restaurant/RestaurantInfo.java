package ep1.usp.restaurant;

public class RestaurantInfo
{
	private int id;
	private String name;
	private int status;
	private String url;
	private String clearUrl;

	public RestaurantInfo()
	{

	}

	public RestaurantInfo(int id, String name, int status)
	{
		this.id = id;
		this.name = name;
		this.status = status;
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

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getClearUrl()
	{
		return clearUrl;
	}

	public void setClearUrl(String clearUrl)
	{
		this.clearUrl = clearUrl;
	}

}
