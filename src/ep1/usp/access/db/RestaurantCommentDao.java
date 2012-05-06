package ep1.usp.access.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import ep1.usp.lib.DateAndTime;
import ep1.usp.restaurant.MessageDto;

public class RestaurantCommentDao extends BaseDao<MessageDto>
{
	public RestaurantCommentDao(Context ctx)
	{
		super(ctx);
		TABLE_NAME = "RESTAURANT_COMMENTS";
		COLUMNS = new String[] { "idRestaurant", "comment", "date", "status" };
	}

	@Override
	public ArrayList<MessageDto> getAll()
	{
		ArrayList<MessageDto> msg = new ArrayList<MessageDto>();

		Cursor c = getWritableDatabase().query(TABLE_NAME, COLUMNS, null, null, null, null, null);

		while (c.moveToNext())
		{
			try
			{
				msg.add(new MessageDto(c.getInt(0), c.getString(1), DateAndTime.ParseToDate(c.getString(2)), c.getInt(3)));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		c.close();
		close();

		return msg;
	}

	@Override
	public void set(MessageDto msg)
	{
		try
		{
			ContentValues contentValues = new ContentValues();
			contentValues.put(COLUMNS[0], msg.getRestaurantId());
			contentValues.put(COLUMNS[1], msg.getMessage());
			contentValues.put(COLUMNS[2], DateAndTime.ParseToString(msg.getDate()));
			contentValues.put(COLUMNS[3], msg.getStaus());
			getWritableDatabase().insertOrThrow(TABLE_NAME, null, contentValues);
		}
		catch (Exception e)
		{
			e.getMessage();
		}

	}

}
