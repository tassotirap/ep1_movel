package ep1.usp.access.db;

import java.sql.Date;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import ep1.usp.restaurant.MessageInfo;

public class RestaurantCommentDao extends BaseDao<MessageInfo>
{

	public RestaurantCommentDao(Context ctx)
	{
		super(ctx);
		TABLE_NAME = "RESTAURANT_COMMENTS";
		COLUMNS = new String[] { "idRestaurant", "comment", "date", "status"};
	}

	@Override
	public ArrayList<MessageInfo> getAll()
	{
		ArrayList<MessageInfo> msg = new ArrayList<MessageInfo>();

		Cursor c = getWritableDatabase().query(TABLE_NAME, COLUMNS, null, null, null, null, null);

		while (c.moveToNext())
		{
			msg.add(new MessageInfo(c.getInt(0), c.getString(1), Date.valueOf(c.getString(2)), c.getInt(3)));
		}

		c.close();

		return msg;
	}

	@Override
	public void setList(ArrayList<MessageInfo> object)
	{
		for (MessageInfo msg : object)
		{
			try
			{
				ContentValues contentValues = new ContentValues();
				contentValues.put(COLUMNS[0], msg.getRestaurantId());
				contentValues.put(COLUMNS[1], msg.getMessage());
				contentValues.put(COLUMNS[2], msg.getDate().toString());
				contentValues.put(COLUMNS[3], msg.getStaus());
				getWritableDatabase().insertOrThrow(TABLE_NAME, null, contentValues);
			}
			catch (Exception e)
			{
				e.getMessage();
			}
		}
		
	}

	@Override
	public void set(MessageInfo object)
	{
		// TODO Auto-generated method stub
		
	}

}
