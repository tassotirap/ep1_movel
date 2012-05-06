package ep1.usp;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.AlertDialog;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import com.xtremelabs.robolectric.shadows.ShadowAlertDialog;
import com.xtremelabs.robolectric.shadows.ShadowHandler;

import ep1.usp.access.db.OverlayDao;
import ep1.usp.maps.Maps;
import ep1.usp.maps.MapsMenu;
import ep1.usp.maps.MapsMenusButtons;
import ep1.usp.maps.MapsSettings;
import ep1.usp.maps.MyLocationListener;
import ep1.usp.maps.Overlay.MyOverlays;


@RunWith(RobolectricTestRunner.class)
public class MapTest {

	private Maps maps;

	@Test
	public void getMapsMenus() throws Exception {
		MapsMenu menu = maps.getMapsMenus();
		assertThat(menu, notNullValue());
	}

	@Test
	public void getMapsMenusButtons() throws Exception {
		MapsMenusButtons menu = maps.getMapsMenusButtons();
		assertThat(menu, notNullValue());
	}

	@Test
	public void getMapsSettings() throws Exception {
		MapsSettings mapSettings = maps.getMapsSettings();
		assertThat(mapSettings, notNullValue());
	}

	@Test
	public void getMyLocation() {
		MyLocationListener location = maps.getMyLocation();
		assertThat(location, notNullValue());
	}

	@Test
	public void getMyOverlays() throws Exception {
		MyOverlays myOverlays = maps.getMyOverlays();
		assertThat(myOverlays, notNullValue());
	}

	@Test
	public void getOverlayDao() throws Exception {
		OverlayDao overlay = maps.getOverlayDao();
		assertThat(overlay, notNullValue());
	}

	@Test
	public void HandlerWith0() {
		ShadowHandler handler = shadowOf(maps.handler);
		handler.sendEmptyMessage(0);
		ShadowHandler.idleMainLooper();
		
		ShadowAlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();
        assertThat(alertDialog.isShowing(), equalTo(true));        
        assertThat(((String) alertDialog.getTitle()), equalTo(maps.getString(R.string.msgErrorTitle)));
        assertThat(((String) alertDialog.getMessage()), equalTo(maps.getString(R.string.msgErrorMsg)));
        assertNotNull(alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL));
	}

	@Test
	public void HandlerWith1() {
		ShadowHandler handler = shadowOf(maps.handler);
		handler.sendEmptyMessage(1);
		ShadowHandler.idleMainLooper();
		
		ShadowAlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();
        assertThat(alertDialog.isShowing(), equalTo(true));        
        assertThat(((String) alertDialog.getTitle()), equalTo(maps.getString(R.string.msgSucess)));
        assertThat(((String) alertDialog.getMessage()), equalTo(maps.getString(R.string.msgSucessAddPoint)));
        assertNotNull(alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL));
	}

	@Before
	public void setUp() throws Exception {
		maps = new Maps();
		maps.onCreate(null);
	}

	@Test
	public void showDialog() {
		maps.showDialog("Teste", "Teste");
		
		ShadowAlertDialog alertDialog = ShadowAlertDialog.getLatestAlertDialog();
        assertThat(alertDialog.isShowing(), equalTo(true));
        assertThat(((String) alertDialog.getTitle()), equalTo("Teste"));
        assertThat(((String) alertDialog.getMessage()), equalTo("Teste"));
        assertNotNull(alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL));
	}

}
