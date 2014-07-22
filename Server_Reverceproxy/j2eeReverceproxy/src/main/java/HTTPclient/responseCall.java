package HTTPclient;

import javax.servlet.ServletRequest;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
public class responseCall
{
	public responseCall() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public HttpResponse clientreqeust(ServletRequest requestHTTP)
	{
		try 
		{
			DefaultHttpClient httpclient = new DefaultHttpClient();
			Long stime = System.currentTimeMillis();
			Long etime = null;
			HttpResponse response = httpclient.execute((HttpUriRequest) requestHTTP);
			
			if(response.getStatusLine().getStatusCode() != 0)
			{
				etime = System.currentTimeMillis();
			}
			
			if(etime != null)
			{
				Long ftime = etime - stime;
			}
			
			return response;
		} catch (Exception e)
		{
			// TODO: handle exception
			return null;
		}
		
		
	}
}
