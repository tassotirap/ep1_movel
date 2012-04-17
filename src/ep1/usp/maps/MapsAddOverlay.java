package ep1.usp.maps;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import ep1.usp.R;
import ep1.usp.access.JSON.ParseJSON;

public class MapsAddOverlay extends Dialog
{
	private Button btnSave;
	private EditText txtName;
	private ParseJSON parseJSON;
	private ArrayAdapter<CharSequence> mAdapterTypes;
	private Spinner spnTypes;
	private int selectedType;
	private int latitude, longitude;
	
	public MapsAddOverlay(Context context)
	{
		super(context);
	}
	
	public void init(int latitude, int longitude)
	{
		parseJSON = new ParseJSON();

		this.latitude = latitude;
		this.longitude = longitude;
		
		getLocations();
		loadBinds();
		fillSpinner();

	}

	private void getLocations()
	{
		((TextView) findViewById(R.id.map_add_overlay_x)).setText("Latitude:" + latitude);
		((TextView) findViewById(R.id.map_add_overlay_y)).setText("Longitude:" + longitude);
	}

	private void loadBinds()
	{
		btnSave = (Button) findViewById(R.id.map_add_overlay_btnSave);
		txtName = (EditText) findViewById(R.id.map_add_overlay_txt_name);

		btnSave.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				addOverlay();
			}
		});

		spnTypes = (Spinner) findViewById(R.id.map_add_overlay_ddlType);
	}

	private void fillSpinner()
	{

		mAdapterTypes = ArrayAdapter.createFromResource(getContext(), R.array.map_addOverlayTypes, android.R.layout.simple_spinner_item);

		mAdapterTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spnTypes.setAdapter(mAdapterTypes);

		spnTypes.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				spinnerChanged();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{

			}
		});

	}

	private void spinnerChanged()
	{
		selectedType = spnTypes.getSelectedItemPosition() + 1;
	}
	
	private void back()
	{
		super.onBackPressed();		
	}

	private void addOverlay()
	{
		try
		{
			if (latitude != 0 && longitude != 0 && selectedType != -1)
			{

				parseJSON.setOverlay(latitude, longitude, txtName.getText().toString(), selectedType);
				AlertDialog alert = new AlertDialog.Builder(getContext()).create();
				alert.setTitle("Sucesso");
				alert.setMessage("Ponto adicionado com sucesso!");
				alert.setButton("OK", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which)
					{
						back();
					}
				});
				alert.show();
				
			}
			else
			{

				AlertDialog alert = new AlertDialog.Builder(getContext()).create();
				alert.setTitle("Erro");
				alert.setMessage("GPS nao encontrado!");
				alert.setButton("OK", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which)
					{
						back();
					}
				});
				alert.show();
			}
		}
		catch (Exception e)
		{
		}
	}
}
