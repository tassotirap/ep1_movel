package ep1.usp.gate;

public class GateDto
{
	private int id;
	private String name;
	private int status;
	private int latitude;
	private int longitude;

	public GateDto(int id, String name, int status, int latitude, int longitude)
	{
		this.id = id;
		this.name = name;
		this.status = status;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public GateDto()
	{

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

	public int getLatitude()
	{
		return latitude;
	}

	public void setLatitude(int latitude)
	{
		this.latitude = latitude;
	}

	public int getLongitude()
	{
		return longitude;
	}

	public void setLongitude(int longitude)
	{
		this.longitude = longitude;
	}
}
