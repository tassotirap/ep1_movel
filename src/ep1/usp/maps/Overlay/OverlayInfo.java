package ep1.usp.maps.Overlay;

public class OverlayInfo {
	
	private int latitude;
	private int longitude;
	private int type;
	private String name;
	
	public OverlayInfo()
	{
		
	}
		
	public OverlayInfo(int type, int latitude, int longitude, String name)
	{
		this.setType(type);
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setName(name);	
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
