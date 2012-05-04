package ep1.usp.maps.Overlay;

public class OverlayDto
{

	private int latitude;
	private int longitude;
	private int type;
	private String name;

	public OverlayDto()
	{

	}

	public OverlayDto(int type, int latitude, int longitude, String name)
	{
		this.setType(type);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setName(name);
	}

	public int getLatitude()
	{
		return latitude;
	}

	public int getLongitude()
	{
		return longitude;
	}

	public String getName()
	{
		return name;
	}

	public int getType()
	{
		return type;
	}

	public void setLatitude(int latitude)
	{
		this.latitude = latitude;
	}

	public void setLongitude(int longitude)
	{
		this.longitude = longitude;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setType(int type)
	{
		this.type = type;
	}
}
