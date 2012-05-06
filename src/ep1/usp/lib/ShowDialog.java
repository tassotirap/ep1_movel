package ep1.usp.lib;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

public class ShowDialog
{
	public static void show(String title, String message, Activity activity)
	{
		Builder alert = new AlertDialog.Builder(activity);
		alert.setTitle(title);
		alert.setMessage(message);
		alert.setNeutralButton("OK", null);
		alert.show();
	}

}
