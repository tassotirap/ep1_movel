package ep1.usp.access.db;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ep1.usp.gate.GateDto;

public class GatesDao extends BaseDao<GateDto>
{
	public GatesDao(Context ctx)
	{
		super(ctx);
		TABLE_NAME = "GATES";
		COLUMNS = new String[] { "id", "name", "status", "latitude", "longitude" };
	}

	@Override
	public ArrayList<GateDto> getAll()
	{
		ArrayList<GateDto> gates = new ArrayList<GateDto>();

		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(TABLE_NAME, COLUMNS, null, null, null, null, null);

		while (c.moveToNext())
		{
			gates.add(new GateDto(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4)));
		}

		c.close();
		db.close();

		return gates;
	}

	@Override
	public void set(GateDto gate)
	{
		try
		{
			ContentValues contentValues = new ContentValues();
			contentValues.put(COLUMNS[0], gate.getId());
			contentValues.put(COLUMNS[1], gate.getName());
			contentValues.put(COLUMNS[2], gate.getStatus());
			contentValues.put(COLUMNS[3], gate.getLatitude());
			contentValues.put(COLUMNS[4], gate.getLongitude());
			SQLiteDatabase db = getWritableDatabase();
			db.insertOrThrow(TABLE_NAME, null, contentValues);
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}
}
