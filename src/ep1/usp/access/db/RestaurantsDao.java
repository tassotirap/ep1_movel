package ep1.usp.access.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ep1.usp.restaurant.RestaurantInfo;

public class RestaurantsDao extends BaseDao<RestaurantInfo>
{
	public RestaurantsDao(Context ctx)
	{
		super(ctx);
		TABLE_NAME = "RESTAURANTS";
		COLUMNS = new String[] { "id", "name", "status", "url", "clear_url" };
	}

	public ArrayList<RestaurantInfo> getAll()
	{
		ArrayList<RestaurantInfo> restaurants = new ArrayList<RestaurantInfo>();

		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(TABLE_NAME, COLUMNS, null, null, null, null, null);

		while (c.moveToNext())
		{
			restaurants.add(new RestaurantInfo(c.getInt(0), c.getString(1), c.getInt(2)));
		}

		c.close();
		db.close();

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
				contentValues.put(COLUMNS[3], restaurant.getUrl());
				contentValues.put(COLUMNS[4], restaurant.getClearUrl());
				SQLiteDatabase db = getWritableDatabase();
				db.insertOrThrow(TABLE_NAME, null, contentValues);
				db.close();
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

		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(TABLE_NAME, new String[] { COLUMNS[1] }, null, null, null, null, null);
		while (c.moveToNext())
		{
			names.add(c.getString(0));
		}
		c.close();
		db.close();
		return names;
	}

	public int getIdByName(String name)
	{
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(TABLE_NAME, new String[] { COLUMNS[0] }, COLUMNS[1] + "=?", new String[] { name }, null, null, null);

		while (c.moveToNext())
		{
			int id = c.getInt(0);
			c.close();
			return id;
		}
		c.close();
		db.close();
		return -1;
	}

	public int getStatusById(int id)
	{
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(TABLE_NAME, new String[] { COLUMNS[2] }, COLUMNS[0] + "=?", new String[] { String.valueOf(id) }, null, null, null);

		while (c.moveToNext())
		{
			int status = c.getInt(0);
			c.close();
			return status;
		}
		c.close();
		db.close();
		return 1;
	}

	@Override
	public void set(RestaurantInfo object)
	{

	}

	public void update(RestaurantInfo restaurantInfo)
	{

		SQLiteDatabase db = getWritableDatabase();

		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMNS[0], restaurantInfo.getId());
		contentValues.put(COLUMNS[1], restaurantInfo.getName());
		contentValues.put(COLUMNS[2], restaurantInfo.getStatus());
		contentValues.put(COLUMNS[3], restaurantInfo.getUrl());
		contentValues.put(COLUMNS[4], restaurantInfo.getClearUrl());

		db.update(TABLE_NAME, contentValues, COLUMNS[0] + "=?", new String[] { String.valueOf(restaurantInfo.getId()) });
		db.close();

	}

	public RestaurantInfo get(int id)
	{
		RestaurantInfo restaurantInfo = new RestaurantInfo();
		SQLiteDatabase db = getWritableDatabase();

		Cursor c = db.query(TABLE_NAME, null, COLUMNS[0] + "=?", new String[] { String.valueOf(id) }, null, null, null);

		while (c.moveToNext())
		{
			restaurantInfo.setId(c.getInt(0));
			restaurantInfo.setName(c.getString(1));
			restaurantInfo.setStatus(c.getInt(2));
			restaurantInfo.setUrl(c.getString(3));
			restaurantInfo.setClearUrl(c.getString(4));
			c.close();
			return restaurantInfo;
		}
		c.close();
		db.close();
		return null;
	}
}
