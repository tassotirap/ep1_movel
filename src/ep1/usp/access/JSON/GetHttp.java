package ep1.usp.access.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;

public class GetHttp
{

	private String strResponse = null;

	public GetHttp(String URL) throws Exception
	{
		BufferedReader bufferedReader = null;
		try
		{
			HttpClient client = new DefaultHttpClient();
			client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "android");
			HttpGet request = new HttpGet();
			request.setHeader("Content-Type", "text/plain; charset=utf-8");
			request.setURI(new URI(URL));
			HttpResponse response = client.execute(request);
			bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer stringBuffer = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");

			while ((line = bufferedReader.readLine()) != null)
			{
				stringBuffer.append(line + NL);
			}
			bufferedReader.close();

			strResponse = stringBuffer.toString();
		}
		catch (Exception e)
		{
			strResponse = null;
		}
		finally
		{
			if (bufferedReader != null)
			{
				try
				{
					bufferedReader.close();
				}
				catch (IOException e)
				{
				}
			}
		}
	}

	public String getResponse()
	{
		return strResponse;
	}
}
