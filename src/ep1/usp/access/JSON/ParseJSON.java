package ep1.usp.access.JSON;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import ep1.usp.maps.Overlay.OverlayInfo;
import ep1.usp.restaurant.RestaurantInfo;

public class ParseJSON
{

	private String URL = "http://www.tassotirapani.com.br/ep1/Ep1Service.svc/";

	public ArrayList<OverlayInfo> getOverlayInfo()
	{

		ArrayList<OverlayInfo> overlayInfos = new ArrayList<OverlayInfo>();

		try
		{
			GetHttp httpGet = new GetHttp(URL + "GetOverlays");

			if (httpGet.getResponse() != null)
			{
				JSONObject object = (JSONObject) new JSONTokener(httpGet.getResponse()).nextValue();
				JSONArray message = object.getJSONArray("d");
				for (int i = 0; i < message.length(); i++)
				{
					JSONObject lines = (JSONObject) new JSONTokener(message.getString(i)).nextValue();
					OverlayInfo overlayInfo = new OverlayInfo();
					overlayInfo.setType(lines.getInt("Type"));
					overlayInfo.setLatitude(lines.getInt("Latitude"));
					overlayInfo.setLongitude(lines.getInt("Longitude"));
					overlayInfo.setName(lines.getString("Name"));
					overlayInfos.add(overlayInfo);
				}
			}
		}
		catch (Exception e)
		{
		}
		return overlayInfos;

	}

	public boolean setOverlay(int latitude, int longitude, String name, int type)
	{

		try
		{

			String url = URL + "SetOverlay?latitude=" + latitude + "&longitude=" + longitude + "&name=" + name + "&type=" + type;

			new GetHttp(url);
			return true;

		}
		catch (Exception e)
		{
			return false;
		}
	}

	public ArrayList<RestaurantInfo> getRestaurantInfo()
	{
		ArrayList<RestaurantInfo> restaurantInfos = new ArrayList<RestaurantInfo>();

		try
		{
			GetHttp httpGet = new GetHttp(URL + "GetRestaurants");

			if (httpGet.getResponse() != null)
			{
				JSONObject object = (JSONObject) new JSONTokener(httpGet.getResponse()).nextValue();
				JSONArray message = object.getJSONArray("d");
				for (int i = 0; i < message.length(); i++)
				{
					JSONObject lines = (JSONObject) new JSONTokener(message.getString(i)).nextValue();
					RestaurantInfo restaurantInfo = new RestaurantInfo();
					restaurantInfo.setId(lines.getInt("Id"));
					restaurantInfo.setName(lines.getString("Name"));
					restaurantInfo.setStatus(lines.getInt("Status"));
					restaurantInfos.add(restaurantInfo);
				}
			}
		}
		catch (Exception e)
		{
		}
		return restaurantInfos;
	}

	public boolean setRestaurantComment(int restaurantId, String comment, int statusId)
	{

		try
		{

			String url = URL + "SetRestaurantComment?restaurantId=" + restaurantId + "&comment=" + comment + "&status=" + statusId;

			new GetHttp(url);
			return true;

		}
		catch (Exception e)
		{
			return false;
		}
	}
}
