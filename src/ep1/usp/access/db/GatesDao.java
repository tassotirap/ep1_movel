package ep1.usp.access.db;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import ep1.usp.gate.GateInfo;

public class GatesDao extends BaseDao<GateInfo>
{

	public GatesDao(Context ctx)
	{
		super(ctx);
		TABLE_NAME = "GATES";
		COLUMNS = new String[] { "id", "name", "status", "latitude", "longitude" };
	}

	@Override
	public ArrayList<GateInfo> getAll()
	{
		ArrayList<GateInfo> gates = new ArrayList<GateInfo>();

		SQLiteDatabase db = getWritableDatabase();
		Cursor c = db.query(TABLE_NAME, COLUMNS, null, null, null, null, null);

		while (c.moveToNext())
		{
			gates.add(new GateInfo(c.getInt(0), c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4)));
		}

		c.close();
		db.close();

		return gates;
	}

	@Override
	public void setList(ArrayList<GateInfo> gates)
	{
		ClearAll();
		SQLiteDatabase db = getWritableDatabase();
		try
		{
			for (GateInfo gate : gates)
			{

				ContentValues contentValues = new ContentValues();
				contentValues.put(COLUMNS[0], gate.getId());
				contentValues.put(COLUMNS[1], gate.getName());
				contentValues.put(COLUMNS[2], gate.getStatus());
				contentValues.put(COLUMNS[3], gate.getLatitude());
				contentValues.put(COLUMNS[4], gate.getLongitude());
				db.insertOrThrow(TABLE_NAME, null, contentValues);
			}
		}
		catch (Exception e)
		{
			e.getMessage();
		}
		finally
		{
			db.close();
		}
	}

	@Override
	public void set(GateInfo gate)
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
			db.close();
		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}
	
	public void ClearAll()
	{
		SQLiteDatabase db = getWritableDatabase();
		db.delete(TABLE_NAME, null, null);
	}
}
