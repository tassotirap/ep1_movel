package ep1.usp.access.db;

import java.util.ArrayList;

import com.google.android.maps.GeoPoint;

import android.content.Context;
import android.database.Cursor;

public class SettingsDao extends BaseDao<Object>
{

	public SettingsDao(Context ctx)
	{
		super(ctx);
		TABLE_NAME = "MAP_CONFIG";
		COLUMNS = new String[] { "usp_center_latitude", "usp_center_longitude" }; 
	}

	@Override
	public ArrayList<Object> getAll()
	{
		return null;
	}

	public GeoPoint GetUspCenter()
	{
		GeoPoint geoPoint = null;
		Cursor c = getWritableDatabase().query(TABLE_NAME, COLUMNS, null, null, null, null, null);

		while (c.moveToNext())
		{
			geoPoint = new GeoPoint(c.getInt(0), c.getInt(1));
		}

		c.close();
		close();

		return geoPoint;
	}

	@Override
	public void set(Object object)
	{
		
	}
}
