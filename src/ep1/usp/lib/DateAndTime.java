package ep1.usp.lib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTime
{
	public static String ParseToString(Date date)
	{
		SimpleDateFormat formatBra = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatBra.format(date);
	}

	public static Date ParseToDate(String date)
	{
		SimpleDateFormat formatBra = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			return formatBra.parse(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static String ParseToStringPrint(Date date)
	{
		SimpleDateFormat formatBra = new SimpleDateFormat("dd/MM/yyyy\nHH:mm:ss");
		return formatBra.format(date);
	}
}
