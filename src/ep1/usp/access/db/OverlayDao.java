package ep1.usp.access.db;

import java.util.ArrayList;

import ep1.usp.maps.Overlay.OverlayDto;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class OverlayDao extends BaseDao<OverlayDto>
{
	public OverlayDao(Context ctx)
	{
		super(ctx);
		TABLE_NAME = "MAP_OVERLAY";
		COLUMNS = new String[] { "type", "latitude", "longitude", "name" };
	}

	public void deleteByName(String name)
	{
		deleteByColumn(3, name);
	}

	public ArrayList<OverlayDto> getAll()
	{
		ArrayList<OverlayDto> overlays = new ArrayList<OverlayDto>();

		Cursor c = getWritableDatabase().query(TABLE_NAME, COLUMNS, null, null, null, null, null);

		while (c.moveToNext())
		{
			overlays.add(new OverlayDto(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3)));
		}

		c.close();
		close();

		return overlays;
	}

	public ArrayList<OverlayDto> getByType(Integer type)
	{
		ArrayList<OverlayDto> overlays = new ArrayList<OverlayDto>();

		Cursor c = getWritableDatabase().query(TABLE_NAME, COLUMNS, "type=?", new String[] { type.toString() }, null, null, null);

		while (c.moveToNext())
		{
			overlays.add(new OverlayDto(c.getInt(0), c.getInt(1), c.getInt(2), c.getString(3)));
		}

		c.close();
		close();

		return overlays;
	}

	public void set(OverlayDto overlayInfo)
	{
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMNS[0], overlayInfo.getType());
		contentValues.put(COLUMNS[1], overlayInfo.getLatitude());
		contentValues.put(COLUMNS[2], overlayInfo.getLongitude());
		contentValues.put(COLUMNS[3], overlayInfo.getName());
		getWritableDatabase().insertOrThrow(TABLE_NAME, null, contentValues);
	}
}
