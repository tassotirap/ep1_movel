package ep1.usp.restaurant;

import java.util.Date;

public class MessageInfo
{
	private int restaurantId;
	
	private String message;
	
	private Date date;

	private int status = 0;

	public MessageInfo(int restaurantId, String message, Date date)
	{
		this.restaurantId = restaurantId;
		this.message = message;
		this.date = date;
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

	public String getStaus()
	{
		switch (status)
		{
			case 1:
				return "Vazio";
			default:
				return "Cheio";
		}
	}

}
