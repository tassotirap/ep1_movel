package ep1.usp.restaurant;

import java.util.List;

public class MenuInfo
{
	private int restaurantId;
	private List<String> itens;
	private List<String> desserts;
	private List<String> drinks;

	public int getRestaurantId()
	{
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId)
	{
		this.restaurantId = restaurantId;
	}

	public List<String> getItens()
	{
		return itens;
	}

	public void setItens(List<String> itens)
	{
		this.itens = itens;
	}

	public List<String> getDesserts()
	{
		return desserts;
	}

	public void setDesserts(List<String> desserts)
	{
		this.desserts = desserts;
	}

	public List<String> getDrinks()
	{
		return drinks;
	}

	public void setDrinks(List<String> drinks)
	{
		this.drinks = drinks;
	}

}
