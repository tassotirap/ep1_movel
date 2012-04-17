package ep1.usp.access.db;

import java.util.ArrayList;

import ep1.usp.maps.Overlay.OverlayInfo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class OverlayDao extends BaseDao<OverlayInfo>
{
	public OverlayDao(Context ctx)
	{
		super(ctx);
		TABLE_NAME = "MAP_OVERLAY";
		COLUMNS = new String[] { "type", "latitude", "longitude", "name" };
	}

	public ArrayList<OverlayInfo> getByType(Integer type)
	{
		ArrayList<OverlayInfo> overlays = new ArrayList<OverlayInfo>();

		Cursor c = getWritableDatabase().query(TABLE_NAME, COLUMNS, "type=?", new String[] { type.toString() }, null, null, null);

		while (c.moveToNext())
		{
			overlays.add(new OverlayInfo(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3)));
		}

		c.close();

		return overlays;
	}

	public ArrayList<OverlayInfo> getAll()
	{
		ArrayList<OverlayInfo> overlays = new ArrayList<OverlayInfo>();

		Cursor c = getWritableDatabase().query(TABLE_NAME, COLUMNS, null, null, null, null, null);

		while (c.moveToNext())
		{
			overlays.add(new OverlayInfo(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3)));
		}

		c.close();

		return overlays;
	}

	public void setList(ArrayList<OverlayInfo> lstOverlayInfo)
	{
		for (OverlayInfo overlayinfo : lstOverlayInfo)
		{
			try
			{
				ContentValues contentValues = new ContentValues();
				contentValues.put(COLUMNS[0], overlayinfo.getType());
				contentValues.put(COLUMNS[1], overlayinfo.getLatitude());
				contentValues.put(COLUMNS[2], overlayinfo.getLongitude());
				contentValues.put(COLUMNS[3], overlayinfo.getName());
				getWritableDatabase().insertOrThrow(TABLE_NAME, null, contentValues);
			}
			catch (Exception e)
			{
				e.getMessage();
			}
		}
	}

	public void set(OverlayInfo overlayInfo)
	{
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMNS[0], overlayInfo.getType());
		contentValues.put(COLUMNS[1], overlayInfo.getLatitude());
		contentValues.put(COLUMNS[2], overlayInfo.getLongitude());
		contentValues.put(COLUMNS[3], overlayInfo.getName());
		getWritableDatabase().insertOrThrow(TABLE_NAME, null, contentValues);
	}

	public void deleteByName(String name)
	{
		deleteByColumn(3, name);
	}
}
