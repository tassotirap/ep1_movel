package ep1.usp.restaurant;

import java.util.Date;

public class MessageInfo
{
	private int restaurantId;
	
	private String message;
	
	private Date date;

	private int status = 0;

	public MessageInfo(int restaurantId, String message, Date date, int status)
	{
		this.restaurantId = restaurantId;
		this.message = message;
		this.date = date;
		this.status = status;
	}

	public MessageInfo()
	{
	}

	public int getRestaurantId()
	{
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId)
	{
		this.restaurantId = restaurantId;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
	
	public void setStaus(int status)
	{
		this.status = status;
	}

	public int getStaus()
	{
		return status;
	}

}
