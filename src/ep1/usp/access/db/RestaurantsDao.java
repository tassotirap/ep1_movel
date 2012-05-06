package ep1.usp.access.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ep1.usp.restaurant.RestaurantDto;

public class RestaurantsDao extends BaseDao<RestaurantDto>
{
	public RestaurantsDao(Context ctx)
	{
		super(ctx);
		TABLE_NAME = "RESTAURANTS";
		COLUMNS = new String[] { "id", "name", "status", "url", "clear_url" };
	}

	public RestaurantDto get(int id)
	{
		RestaurantDto restaurantInfo = new RestaurantDto();
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

	public ArrayList<RestaurantDto> getAll()
	{
		ArrayList<RestaurantDto> restaurants = new ArrayList<RestaurantDto>();

		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(TABLE_NAME, COLUMNS, null, null, null, null, null);

		while (c.moveToNext())
		{
			restaurants.add(new RestaurantDto(c.getInt(0), c.getString(1), c.getInt(2)));
		}

		c.close();
		db.close();

		return restaurants;
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
		Cursor c = getWritableDatabase().query(TABLE_NAME, new String[] { COLUMNS[2] }, COLUMNS[0] + "=?", new String[] { String.valueOf(id) }, null, null, null);
		while (c.moveToNext())
		{
			int status = c.getInt(0);
			c.close();
			close();
			return status;
		}
		c.close();
		close();
		return 1;
	}

	@Override
	public void set(RestaurantDto object)
	{
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMNS[0], object.getId());
		contentValues.put(COLUMNS[1], object.getName());
		contentValues.put(COLUMNS[2], object.getStatus());
		contentValues.put(COLUMNS[3], object.getUrl());
		contentValues.put(COLUMNS[4], object.getClearUrl());
		getWritableDatabase().insertOrThrow(TABLE_NAME, null, contentValues);
	}

	public void update(RestaurantDto restaurantInfo)
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
}
