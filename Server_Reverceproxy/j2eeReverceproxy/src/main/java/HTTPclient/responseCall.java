package HTTPclient;

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
	
	public Boolean clientreqeust(HttpRequest request)
	{
		try 
		{
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute((HttpUriRequest) request);
			
			
			
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		
		return false;
	}
}
