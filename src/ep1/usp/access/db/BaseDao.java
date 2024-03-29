package ep1.usp.access.db;

import java.util.ArrayList;

import android.content.Context;

public abstract class BaseDao<T> extends DatabaseHelper
{
	protected String TABLE_NAME;
	protected String[] COLUMNS;

	public BaseDao(Context ctx)
	{
		super(ctx);
	}

	public void clear()
	{
		getWritableDatabase().delete(TABLE_NAME, null, null);
	}

	public void deleteByColumn(int columnIndex, String value)
	{
		getWritableDatabase().delete(TABLE_NAME, COLUMNS[columnIndex] + ">?", new String[] { value });
	}

	public abstract ArrayList<T> getAll();
	
	public abstract void set(T object);

	public void setList(ArrayList<T> object)
	{
		for (T t : object)
		{
			try
			{
				set(t);
			}
			catch (Exception e)
			{
			}
		}
		close();
	}
}
