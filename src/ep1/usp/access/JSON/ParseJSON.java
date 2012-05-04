package ep1.usp.access.JSON;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import ep1.usp.gate.GateInfo;
import ep1.usp.lib.DateAndTime;
import ep1.usp.maps.Overlay.OverlayInfo;
import ep1.usp.restaurant.MessageInfo;
import ep1.usp.restaurant.RestaurantInfo;

public class ParseJSON
{

	private String URL = "http://www.tassotirapani.com.br/ep1/Ep1Service.svc/";

	public ArrayList<GateInfo> getGates(String min)
	{
		ArrayList<GateInfo> gates = new ArrayList<GateInfo>();

		try
		{
			GetHttp httpGet = new GetHttp(URL + "GetGates?min=" + min);
			if (httpGet.getResponse() != null)
			{
				JSONObject object = (JSONObject) new JSONTokener(httpGet.getResponse()).nextValue();
				JSONArray message = object.getJSONArray("d");
				for (int i = 0; i < message.length(); i++)
				{
					JSONObject lines = (JSONObject) new JSONTokener(message.getString(i)).nextValue();
					GateInfo gate = new GateInfo();
					gate.setId(lines.getInt("Id"));
					gate.setName(lines.getString("Name"));
					gate.setStatus(lines.getInt("Status"));
					gate.setLatitude(lines.getInt("Latitude"));
					gate.setLongitude(lines.getInt("Longitude"));
					gates.add(gate);
				}
			}
			return gates;
		}
		catch (Exception e)
		{
			return null;
		}
	}

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

	public RestaurantInfo getRestaurant(int restaurantId, String min)
	{
		RestaurantInfo restaurantInfo = new RestaurantInfo();
		try
		{
			GetHttp httpGet = new GetHttp(URL + "GetRestaurant?restaurantId=" + restaurantId + "&min=" + min);
			if (httpGet.getResponse() != null)
			{
				JSONObject object = (JSONObject) new JSONTokener(httpGet.getResponse()).nextValue();
				JSONObject message = object.getJSONObject("d");
				restaurantInfo.setId(message.getInt("Id"));
				restaurantInfo.setName(message.getString("Name"));
				restaurantInfo.setStatus(message.getInt("Status"));
				restaurantInfo.setUrl(message.getString("URL"));
				restaurantInfo.setClearUrl(message.getString("ClearURL"));
			}
			return restaurantInfo;

		}
		catch (Exception e)
		{
			return null;
		}
	}

	public ArrayList<MessageInfo> getRestaurantComment(int restaurantId, String qtde)
	{
		ArrayList<MessageInfo> messageInfos = new ArrayList<MessageInfo>();
		try
		{
			GetHttp httpGet = new GetHttp(URL + "GetRestaurantComment?restaurantId=" + restaurantId + "&qtde=" + qtde);
			if (httpGet.getResponse() != null)
			{
				JSONObject object = (JSONObject) new JSONTokener(httpGet.getResponse()).nextValue();
				JSONArray message = object.getJSONArray("d");
				for (int i = 0; i < message.length(); i++)
				{
					JSONObject lines = (JSONObject) new JSONTokener(message.getString(i)).nextValue();
					MessageInfo messageInfo = new MessageInfo();
					messageInfo.setRestaurantId(lines.getInt("RestaurantId"));
					messageInfo.setMessage(lines.getString("Comment"));
					messageInfo.setDate(DateAndTime.ParseToDate(lines.getString("Date")));
					messageInfo.setStaus(lines.getInt("Status"));
					messageInfos.add(messageInfo);
				}
			}
			return messageInfos;

		}
		catch (Exception e)
		{
			return null;
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
					restaurantInfo.setUrl(lines.getString("URL"));
					restaurantInfo.setClearUrl(lines.getString("ClearURL"));
					restaurantInfos.add(restaurantInfo);
				}
			}
		}
		catch (Exception e)
		{
		}
		return restaurantInfos;
	}

	public void setGates(int gateId, double distance)
	{
		try
		{
			new GetHttp(URL + "SetGate?gateId=" + gateId + "&distance=" + distance);
		}
		catch (Exception e)
		{
		}
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

	public boolean setRestaurantComment(int restaurantId, String comment, int statusId)
	{

		try
		{
			comment = URLEncoder.encode(comment, "UTF-8");
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
