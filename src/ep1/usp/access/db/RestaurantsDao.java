package ep1.usp.access.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import ep1.usp.restaurant.RestaurantInfo;

public class RestaurantsDao extends BaseDao<RestaurantInfo>
{
	public RestaurantsDao(Context ctx)
	{
		super(ctx);
		TABLE_NAME = "RESTAURANTS";
		COLUMNS =  new String[] { "id", "name", "status" }; 
	}
	
	public ArrayList<RestaurantInfo> getAll()
	{
		ArrayList<RestaurantInfo> restaurants = new ArrayList<RestaurantInfo>();
		
		Cursor c = getWritableDatabase().query(TABLE_NAME, COLUMNS, null, null, null, null, null);

		while (c.moveToNext())
		{
			restaurants.add(new RestaurantInfo(c.getInt(0), c.getString(1), c.getInt(2)));
		}

		c.close();

		return restaurants;	
	}
	
	public void setList(ArrayList<RestaurantInfo> restaurants)
	{
		for (RestaurantInfo restaurant : restaurants)
		{
			try
			{
				ContentValues contentValues = new ContentValues();
				contentValues.put(COLUMNS[0], restaurant.getId());
				contentValues.put(COLUMNS[1], restaurant.getName());
				contentValues.put(COLUMNS[2], restaurant.getStatus());
				getWritableDatabase().insertOrThrow(TABLE_NAME, null, contentValues);
			}
			catch (Exception e)
			{
				e.getMessage();
			}
		}
	}
	
	public ArrayList<String> getNames()
	{
		ArrayList<String> names = new ArrayList<String>();
		
		Cursor c = getWritableDatabase().query(TABLE_NAME, new String[]{ COLUMNS[1] }, null, null, null, null, null);

		while (c.moveToNext())
		{
			names.add(c.getString(0));
		}

		c.close();

		return names;			
	}
	
	public int getIdByName(String name)
	{

		Cursor c = getWritableDatabase().query(TABLE_NAME, new String[]{ COLUMNS[0] }, COLUMNS[1] + "=?", new String[]{name}, null, null, null);

		while (c.moveToNext())
		{
			int id = c.getInt(0);
			c.close();
			return id;
		}
		c.close();
		return -1;			
	}

	@Override
	public void set(RestaurantInfo object)
	{
		// TODO Auto-generated method stub
		
	}
}
