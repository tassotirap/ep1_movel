package ep1.usp.access.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class MapSettingsDao extends DatabaseHelper
{

	private String TABLE_NAME = "MAP_CONFIG_VIEW";

	public static Integer BUS_STOP = 1;
	public static Integer UNIVERSITY = 2;
	public static Integer RESTAURANT = 3;

	public MapSettingsDao(Context ctx)
	{
		super(ctx);
	}

	public boolean getBusStopEnable()
	{
		try
		{
			Cursor c = getWritableDatabase().query(TABLE_NAME, new String[] { "enable" }, "type = ?", new String[] { BUS_STOP.toString() }, null, null, null);

			if (c.moveToNext())
			{
				int i = c.getInt(0);
				c.close();
				return i == 1;
			}
			c.close();
			return false;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean getUniversityEnable()
	{
		try
		{
			Cursor c = getWritableDatabase().query(TABLE_NAME, new String[] { "enable" }, "type = ?", new String[] { UNIVERSITY.toString() }, null, null, null);

			if (c.moveToNext())
			{
				int i = c.getInt(0);
				c.close();
				return i == 1;
			}
			c.close();
			return false;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean getRestaurantEnable()
	{
		try
		{
			Cursor c = getWritableDatabase().query(TABLE_NAME, new String[] { "enable" }, "type = ?", new String[] { RESTAURANT.toString() }, null, null, null);

			if (c.moveToNext())
			{
				int i = c.getInt(0);
				c.close();
				return i == 1;
			}
			c.close();
			return false;
		}
		catch (Exception e)
		{
			return false;
		}

	}

	public void setBusStopEnable(boolean value)
	{

		ContentValues contentValues = new ContentValues();

		if (value)
			contentValues.put("enable", 1);
		else
			contentValues.put("enable", 0);

		getWritableDatabase().update(TABLE_NAME, contentValues, "type = ?", new String[] { BUS_STOP.toString() });
	}

	public void setUniversityEnable(boolean value)
	{

		ContentValues contentValues = new ContentValues();

		if (value)
			contentValues.put("enable", 1);
		else
			contentValues.put("enable", 0);

		getWritableDatabase().update(TABLE_NAME, contentValues, "type = ?", new String[] { UNIVERSITY.toString() });
	}

	public void setRestaurantEnable(boolean value)
	{

		ContentValues contentValues = new ContentValues();

		if (value)
			contentValues.put("enable", 1);
		else
			contentValues.put("enable", 0);

		getWritableDatabase().update(TABLE_NAME, contentValues, "type = ?", new String[] { RESTAURANT.toString() });
	}
}
