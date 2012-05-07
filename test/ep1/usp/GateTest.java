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
import com.xtremelabs.robolectric.shadows.ShadowImageView;

import ep1.usp.gate.Gate;

@RunWith(RobolectricTestRunner.class)
public class GateTest {
	Gate gate;
	Location location;

	@Before
	public void setUp() throws Exception {
		gate = new Gate();
		gate.onCreate(null);
		location = new Location("reverseGeocoded");
		location.setLatitude(23);
		location.setLongitude(46);
	}

	@Test
	public void HandlerWith0() {
		ShadowHandler handler = shadowOf(gate.handler);
		handler.sendEmptyMessage(0);
		ShadowHandler.idleMainLooper();

		ShadowAlertDialog alertDialog = ShadowAlertDialog
				.getLatestAlertDialog();
		assertThat(alertDialog.isShowing(), equalTo(true));
		assertThat(((String) alertDialog.getTitle()),
				equalTo(gate.getString(R.string.msgErrorTitle)));
		assertThat(((String) alertDialog.getMessage()),
				equalTo(gate.getString(R.string.msgErrorMsg)));
		assertNotNull(alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL));
	}

	@Test
	public void HandlerWith2() {
		ShadowHandler handler = shadowOf(gate.handler);
		handler.sendEmptyMessage(2);
		ShadowHandler.idleMainLooper();

		ShadowAlertDialog alertDialog = ShadowAlertDialog
				.getLatestAlertDialog();
		assertThat(alertDialog.isShowing(), equalTo(true));
		assertThat(((String) alertDialog.getTitle()),
				equalTo(gate.getString(R.string.msgSucess)));
		assertThat(((String) alertDialog.getMessage()),
				equalTo(gate.getString(R.string.msgLocalSend)));
		assertNotNull(alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL));
	}

	@Test
	public void LocationChange() {
		gate.onLocationChanged(location);
		assertThat(gate.getLatitude(), equalTo(23000000));
		assertThat(gate.getLongitude(), equalTo(46000000));		
	}

	@Test
	public void refreshGates() {
		gate.getBtnRefresh().performClick();
	}

	@Test
	public void SendWithOutUSPLocation() {
		Location location = new Location("reverseGeocoded");
		location.setLatitude(23);
		location.setLongitude(46);
		gate.onLocationChanged(location);
		gate.getBtnSend().performClick();
		ShadowHandler.idleMainLooper();

		ShadowAlertDialog alertDialog = ShadowAlertDialog
				.getLatestAlertDialog();
		assertThat(alertDialog.isShowing(), equalTo(true));
		assertThat(((String) alertDialog.getTitle()),
				equalTo(gate.getString(R.string.msgErrorTitle)));
		assertThat(((String) alertDialog.getMessage()),
				equalTo(gate.getString(R.string.msgOutUSP)));
		assertNotNull(alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL));
	}

	@Test
	public void SendWithInUSPLocation() {
		Location location = new Location("reverseGeocoded");
		location.setLatitude(-23.575000);
		location.setLongitude(-46.746500);
		gate.onLocationChanged(location);
		gate.getBtnSend().performClick();
	}

	@Test
	public void SendWithOutLocation() {
		gate.getBtnSend().performClick();

		ShadowHandler.idleMainLooper();

		ShadowAlertDialog alertDialog = ShadowAlertDialog
				.getLatestAlertDialog();
		assertThat(alertDialog.isShowing(), equalTo(true));
		assertThat(((String) alertDialog.getTitle()),
				equalTo(gate.getString(R.string.msgErrorTitle)));
		assertThat(((String) alertDialog.getMessage()),
				equalTo(gate.getString(R.string.msgLocalErrorMsg)));
		assertNotNull(alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL));
	}

	@Test
	public void getBtnRefresh() {
		assertNotNull(gate.getBtnRefresh());
	}

	@Test
	public void getBtnSend() {
		assertNotNull(gate.getBtnSend());
	}

	@Test
	public void getGatesDao() {
		assertNotNull(gate.getGatesDao());
	}

	@Test
	public void getImg1() {
		assertNotNull(gate.getImg1());
	}

	@Test
	public void getImg2() {
		assertNotNull(gate.getImg2());
	}

	@Test
	public void getImg3() {
		assertNotNull(gate.getImg3());
	}

	@Test
	public void getTxtDistance1() {
		assertNotNull(gate.getTxtDistance1());
	}

	@Test
	public void getTxtDistance3() {
		assertNotNull(gate.getTxtDistance3());
	}
	
	@Test
	public void getMyManager() {
		assertNotNull(gate.getMyManager());
	}
	
	@Test
	public void refreshImage()
	{
		gate.refreshImage(gate.getImg1(), 3);		
		ShadowImageView image = shadowOf(gate.getImg1());
		assertThat(image.getResourceId(), equalTo(R.drawable.msg3));	
	}
	
	@Test
	public void GPSDisable()
	{
		gate.onLocationChanged(location);
		
		gate.onProviderDisabled(null);
		assertThat(gate.getLatitude(), equalTo(0));
		assertThat(gate.getLongitude(), equalTo(0));	
	}
	
	@Test
	public void showDialog() {
		gate.showDialog("Teste", "Teste");
		
		ShadowAlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();
        assertThat(alertDialog.isShowing(), equalTo(true));
        assertThat(((String) alertDialog.getTitle()), equalTo("Teste"));
        assertThat(((String) alertDialog.getMessage()), equalTo("Teste"));
        assertNotNull(alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL));
	}
}
