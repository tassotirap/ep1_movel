package ep1.usp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.xtremelabs.robolectric.RobolectricTestRunner;

import ep1.usp.maps.Maps;
import ep1.usp.maps.MapsMenu;
import ep1.usp.maps.Overlay.OverlayIcon;
import ep1.usp.maps.Overlay.RouteOverlay;

@RunWith(RobolectricTestRunner.class)
public class MapsMenuTest {

	private Maps maps;
	private MapsMenu mapsMenu;

	@Before
	public void setUp() throws Exception {
		maps = new Maps();
		maps.onCreate(null);
		mapsMenu = new MapsMenu(maps);
		mapsMenu.init();
	}

	@Test
	public void onRefreshOverlayClick() throws Exception {
		maps.getMapsMenusButtons().getBtnRefresh().performClick();			
	}
	
	@Test
	public void onBusStopClick() throws Exception {	
		
		OverlayIcon busStop = maps.getMyOverlays().getBusStopOverlay();	
		Boolean status = maps.getMapsMenusButtons().getShowBusStop();
		maps.getMapsMenusButtons().getBtnBusStop().performClick();		
		
		if(status)
		{
			assertThat(maps.getMapsMenusButtons().getShowBusStop(), equalTo(false));	
			assertThat(maps.getMapsSettings().getMap().getOverlays().contains(busStop), equalTo(false));
		}
		else			
		{
			assertThat(maps.getMapsMenusButtons().getShowBusStop(), equalTo(true));	
			if(busStop.size() > 0)
				assertThat(maps.getMapsSettings().getMap().getOverlays().contains(busStop), equalTo(true));
			else
				assertThat(maps.getMapsSettings().getMap().getOverlays().contains(busStop), equalTo(false));
		}	
	}
	
	@Test
	public void onRestaurantClick() throws Exception {	
		
		OverlayIcon restaurant = maps.getMyOverlays().getRestaurantOverlay();	
		Boolean status = maps.getMapsMenusButtons().getShowRestaurant();
		maps.getMapsMenusButtons().getBtnRestaurant().performClick();		
		
		if(status)
		{
			assertThat(maps.getMapsMenusButtons().getShowRestaurant(), equalTo(false));	
			assertThat(maps.getMapsSettings().getMap().getOverlays().contains(restaurant), equalTo(false));
		}
		else			
		{
			assertThat(maps.getMapsMenusButtons().getShowRestaurant(), equalTo(true));	
			if(restaurant.size() > 0)
				assertThat(maps.getMapsSettings().getMap().getOverlays().contains(restaurant), equalTo(true));
			else
				assertThat(maps.getMapsSettings().getMap().getOverlays().contains(restaurant), equalTo(false));
		}	
	}
	
	@Test
	public void onUniversityClick() throws Exception {	
		
		OverlayIcon university = maps.getMyOverlays().getUniversityOverlay();	
		Boolean status = maps.getMapsMenusButtons().getShowUniversity();
		maps.getMapsMenusButtons().getBtnUniversity().performClick();		
	
		if(status)
		{
			assertThat(maps.getMapsMenusButtons().getShowUniversity(), equalTo(false));	
			assertThat(maps.getMapsSettings().getMap().getOverlays().contains(university), equalTo(false));
		}
		else			
		{
			assertThat(maps.getMapsMenusButtons().getShowUniversity(), equalTo(true));
			if(university.size() > 0)
				assertThat(maps.getMapsSettings().getMap().getOverlays().contains(university), equalTo(true));
			else
				assertThat(maps.getMapsSettings().getMap().getOverlays().contains(university), equalTo(false));
		}
		
	}
	
	@Test
	public void onRoute1Click() throws Exception {	
		
		RouteOverlay route1 =  maps.getMyOverlays().getRoute1();
		Boolean status = maps.getMapsMenusButtons().getShowRoute1();
		maps.getMapsMenusButtons().getBtnRoute1().performClick();	
		
		if(status)
		{
			assertThat(maps.getMapsMenusButtons().getShowRoute1(), equalTo(false));	
			assertThat(maps.getMapsSettings().getMap().getOverlays().contains(route1), equalTo(false));
		}
		else			
		{
			assertThat(maps.getMapsMenusButtons().getShowRoute1(), equalTo(true));	
			assertThat(maps.getMapsSettings().getMap().getOverlays().contains(route1), equalTo(true));
		}
	}
	
	@Test
	public void onRoute2Click() throws Exception {	
		
		RouteOverlay route2 =  maps.getMyOverlays().getRoute2();
		Boolean status = maps.getMapsMenusButtons().getShowRoute2();
		maps.getMapsMenusButtons().getBtnRoute2().performClick();	
		
		if(status)
		{
			assertThat(maps.getMapsMenusButtons().getShowRoute2(), equalTo(false));	
			assertThat(maps.getMapsSettings().getMap().getOverlays().contains(route2), equalTo(false));
		}
		else			
		{
			assertThat(maps.getMapsMenusButtons().getShowRoute2(), equalTo(true));	
			assertThat(maps.getMapsSettings().getMap().getOverlays().contains(route2), equalTo(true));
		}
	}
}
