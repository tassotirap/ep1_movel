package ep1.usp.gate;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import ep1.usp.R;
import ep1.usp.access.db.GatesDao;

public class Gate extends Activity implements LocationListener
{
	private Button btnRefresh = null;
	private Button btnSend = null;
	private GatesDao gatesDao = null;
	private LocationManager myManager;

	private ImageView img1 = null;
	private ImageView img2 = null;
	private ImageView img3 = null;

	private TextView txtDistance1 = null;
	private TextView txtDistance2 = null;
	private TextView txtDistance3 = null;

	private int latitude;
	private int longitude;

	private double dist1 = 0, dist2 = 0, dist3 = 0;

	public Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);

			switch (msg.what)
			{
				case 0:
					showDialog(getString(R.string.msgErrorTitle), getString(R.string.msgErrorMsg));
					break;
				case 1:
					refreshGates();
					break;
				case 2:
					showDialog(getString(R.string.msgSucess), getString(R.string.msgLocalSend));
					break;
				default:
					break;
			}

		}
	};

	private void bindListeners()
	{
		getBtnRefresh().setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				loadGates();
			}
		});

		getBtnSend().setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				sendPost();
			}
		});
	}

	private void loadGates()
	{
		LoadingGetGate loadingGetGates = new LoadingGetGate(this);
		loadingGetGates.Show();
	}

	private void refreshDistance(Location location)
	{
		ArrayList<GateDto> gates = getGatesDao().getAll();
		if (gates.size() > 0)
		{
			dist1 = refreshDistanceGate(location, gates.get(0));
			dist2 = refreshDistanceGate(location, gates.get(1));
			dist3 = refreshDistanceGate(location, gates.get(2));

			writeDistance(getTxtDistance1(), dist1);
			writeDistance(getTxtDistance2(), dist2);
			writeDistance(getTxtDistance3(), dist3);
		}
	}

	private double refreshDistanceGate(Location location, GateDto gate)
	{
		Location locationGate1 = new Location("reverseGeocoded");
		locationGate1.setLatitude(gate.getLatitude() / 1e6);
		locationGate1.setLongitude(gate.getLongitude() / 1e6);
		return location.distanceTo(locationGate1);
	}

	private void sendPost()
	{
		if (latitude != 0 && longitude != 0)
		{
			if (-23575000 <= latitude && latitude <= -23550900 && -46746500 <= longitude && longitude <= -46711400)
			{
				int gateId = 0;
				double dist = 0;
				if (dist1 < dist2 && dist1 < dist3)
				{
					gateId = 1;
					dist = dist1;
				}
				else if (dist2 < dist3)
				{
					gateId = 2;
					dist = dist2;
				}
				else
				{
					gateId = 3;
					dist = dist3;
				}
				LoadingSendGate sendGate = new LoadingSendGate(this, gateId, dist);
				sendGate.Show();
			}
			else
			{
				showDialog(getString(R.string.msgErrorTitle), getString(R.string.msgOutUSP));
			}
		}
		else
			showDialog(getString(R.string.msgErrorTitle), getString(R.string.msgLocalErrorMsg));
			
	}

	private void startListening()
	{
		myManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	}

	private void stopListening()
	{
		if (myManager != null)
			myManager.removeUpdates(this);
	}

	private void writeDistance(TextView text, double dist)
	{
		DecimalFormat df = new DecimalFormat("#,##0.00");
		String sDist;
		if (dist > 10000)
			sDist = df.format(dist / 1000) + "km";
		else if (dist > 1)
			sDist = df.format(dist) + "m";
		else
			sDist = df.format(dist * 100) + "cm";

		sDist = sDist.replace('.', '#');
		sDist = sDist.replace(',', '.');
		sDist = sDist.replace('#', ',');
		text.setText(sDist);

	}

	@Override
	protected void onDestroy()
	{
		stopListening();
		super.onDestroy();
	}

	@Override
	protected void onPause()
	{
		stopListening();
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		startListening();
		super.onResume();
	}

	public Button getBtnRefresh()
	{
		if (btnRefresh == null)
			btnRefresh = (Button) findViewById(R.id.gate_btnRefresh);
		return btnRefresh;
	}

	public Button getBtnSend()
	{
		if (btnSend == null)
			btnSend = (Button) findViewById(R.id.gate_btnSend);
		return btnSend;
	}

	public GatesDao getGatesDao()
	{
		if (gatesDao == null)
			gatesDao = new GatesDao(getApplicationContext());
		return gatesDao;
	}

	public ImageView getImg1()
	{
		if (img1 == null)
			img1 = (ImageView) findViewById(R.id.gate_imgGate1);
		return img1;
	}

	public ImageView getImg2()
	{

		if (img2 == null)
			img2 = (ImageView) findViewById(R.id.gate_imgGate2);
		return img2;
	}

	public ImageView getImg3()
	{
		if (img3 == null)
			img3 = (ImageView) findViewById(R.id.gate_imgGate3);
		return img3;
	}

	public TextView getTxtDistance1()
	{
		if (txtDistance1 == null)
			txtDistance1 = (TextView) findViewById(R.id.gate_txtDistance1);
		return txtDistance1;
	}

	public TextView getTxtDistance2()
	{
		if (txtDistance2 == null)
			txtDistance2 = (TextView) findViewById(R.id.gate_txtDistance2);
		return txtDistance2;
	}

	public TextView getTxtDistance3()
	{
		if (txtDistance3 == null)
			txtDistance3 = (TextView) findViewById(R.id.gate_txtDistance3);
		return txtDistance3;
	}

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gate);
		myManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		bindListeners();
		refreshGates();
	}

	@Override
	public void onLocationChanged(Location location)
	{
		latitude = (int) (location.getLatitude() * 1e6);
		longitude = (int) (location.getLongitude() * 1e6);
		refreshDistance(location);
	}

	@Override
	public void onProviderDisabled(String arg0)
	{
		latitude = 0;
		longitude = 0;
	}

	@Override
	public void onProviderEnabled(String arg0)
	{
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2)
	{
	}

	public void refreshGates()
	{
		ArrayList<GateDto> gates = getGatesDao().getAll();
		if (gates.size() > 0)
		{
			refreshImage(getImg1(), gates.get(0).getStatus());
			refreshImage(getImg2(), gates.get(1).getStatus());
			refreshImage(getImg3(), gates.get(2).getStatus());
		}
	}

	public void refreshImage(ImageView img, int status)
	{
		switch (status)
		{
			case 1:
				img.setImageResource(R.drawable.msg1);
				break;
			case 2:
				img.setImageResource(R.drawable.msg2);
				break;
			case 3:
				img.setImageResource(R.drawable.msg3);
				break;
			case 4:
				img.setImageResource(R.drawable.msg4);
				break;
			case 5:
				img.setImageResource(R.drawable.msg5);
				break;
		}

	}

	public void showDialog(String title, String message)
	{
		Builder alert = new AlertDialog.Builder(this);
		alert.setTitle(title);
		alert.setMessage(message);
		alert.setNeutralButton("OK", null);
		alert.show();
	}

}
