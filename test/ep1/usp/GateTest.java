package ep1.usp;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.AlertDialog;
import android.location.Location;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowAlertDialog;
import com.xtremelabs.robolectric.shadows.ShadowHandler;

import ep1.usp.gate.Gate;

@RunWith(RobolectricTestRunner.class)
public class GateTest {
	Gate gate;
	
	@Before
	public void setUp() throws Exception {
		gate = new Gate();
		gate.onCreate(null);
	}
	
	@Test
	public void HandlerWith0() {
		ShadowHandler handler = shadowOf(gate.handler);
		handler.sendEmptyMessage(0);
		ShadowHandler.idleMainLooper();
		
		ShadowAlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();
        assertThat(alertDialog.isShowing(), equalTo(true));        
        assertThat(((String) alertDialog.getTitle()), equalTo(gate.getString(R.string.msgErrorTitle)));
        assertThat(((String) alertDialog.getMessage()), equalTo(gate.getString(R.string.msgErrorMsg)));
        assertNotNull(alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL));
	}
	
	@Test
	public void HandlerWith2() {
		ShadowHandler handler = shadowOf(gate.handler);
		handler.sendEmptyMessage(2);
		ShadowHandler.idleMainLooper();
		
		ShadowAlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();
        assertThat(alertDialog.isShowing(), equalTo(true));        
        assertThat(((String) alertDialog.getTitle()), equalTo(gate.getString(R.string.msgSucess)));
        assertThat(((String) alertDialog.getMessage()), equalTo(gate.getString(R.string.msgLocalSend)));
        assertNotNull(alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL));
	}

	@Test
	public void LocationChange()
	{
		Location location = new Location("reverseGeocoded");
		location.setLatitude(23);
		location.setLongitude(46);
		gate.onLocationChanged(location);
	}
	
	@Test
	public void Refresh()
	{
		gate.getBtnRefresh().performClick();		
	}
	
	@Test
	public void SendWithOutUSPLocation()
	{
		Location location = new Location("reverseGeocoded");
		location.setLatitude(23);
		location.setLongitude(46);
		gate.onLocationChanged(location);
		gate.getBtnSend().performClick();		
		ShadowHandler.idleMainLooper();
		
		ShadowAlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();
        assertThat(alertDialog.isShowing(), equalTo(true));        
        assertThat(((String) alertDialog.getTitle()), equalTo(gate.getString(R.string.msgErrorTitle)));
        assertThat(((String) alertDialog.getMessage()), equalTo(gate.getString(R.string.msgOutUSP)));
        assertNotNull(alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL));
	}
	
	@Test
	public void SendWithInUSPLocation()
	{
		Location location = new Location("reverseGeocoded");
		location.setLatitude(-23.575000);
		location.setLongitude(-46.746500);
		gate.onLocationChanged(location);
		gate.getBtnSend().performClick();	
	}
	
	@Test
	public void SendWithOutLocation()
	{
		gate.getBtnSend().performClick();	
		
		ShadowHandler.idleMainLooper();
		
		ShadowAlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();
        assertThat(alertDialog.isShowing(), equalTo(true));        
        assertThat(((String) alertDialog.getTitle()), equalTo(gate.getString(R.string.msgErrorTitle)));
        assertThat(((String) alertDialog.getMessage()), equalTo(gate.getString(R.string.msgLocalErrorMsg)));
        assertNotNull(alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL));
	}
	
}
