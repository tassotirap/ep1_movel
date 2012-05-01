package ep1.usp;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.xtremelabs.robolectric.RobolectricTestRunner;

import ep1.usp.access.db.OverlayDao;
import ep1.usp.maps.Maps;
import ep1.usp.maps.MapsMenusButtons;
import ep1.usp.maps.MapsSettings;
import ep1.usp.maps.MyLocationListener;
import ep1.usp.maps.Overlay.MyOverlays;

@RunWith(RobolectricTestRunner.class)
public class MapTest {

	private Maps maps;

	@Before
	public void setUp() throws Exception {
		maps = new Maps();
		maps.onCreate(null);
	}

	@Test
	public void getOverlayDao() throws Exception {
		OverlayDao overlay = maps.getOverlayDao();
		assertThat(overlay, notNullValue());
	}

	@Test
	public void getMapsSettings() throws Exception {
		MapsSettings mapSettings = maps.getMapsSettings();
		assertThat(mapSettings, notNullValue());
	}

	@Test
	public void getMyOverlays() throws Exception {
		MyOverlays myOverlays = maps.getMyOverlays();
		assertThat(myOverlays, notNullValue());
	}
	
	@Test
	public void getMapsMenusButtons() throws Exception {
		MapsMenusButtons menu = maps.getMapsMenusButtons();
		assertThat(menu, notNullValue());
	}
	
	@Test
	public void getMyLocation()
	{
		MyLocationListener location = maps.getMyLocation();
		assertThat(location, notNullValue());
	}
	
	@Test
	public void HandlerWith0()
	{
		maps.handler.sendEmptyMessage(0);	
	}
	
	@Test
	public void HandlerWith1()
	{
		maps.handler.sendEmptyMessage(1);	
	}
	
	@Test
	public void showDialog()
	{
		maps.showDialog("Teste", "Teste");
	}
	
	
}
