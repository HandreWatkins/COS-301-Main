package pickup;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import HTTPclient.responseCall;
import Util.UrlLinker;

public class GetRequest extends responseCall
{
	@Override
	public UrlLinker clientreqeust(UrlLinker urlproxy) {
		// TODO Auto-generated method stub
		try 
		{
			DefaultHttpClient httpclient = new DefaultHttpClient();
			Long stime = retime();
			Long etime = null;
			
			HttpGet getCall = new HttpGet(urlproxy.getNURL());
			
			HttpResponse response = httpclient.execute(getCall);
			
			if(response.getStatusLine().getStatusCode() != 0)
			{
				etime = System.currentTimeMillis();
			}
			
			Long ftime = null;
			
			if(etime != null)
			{
				ftime= etime - stime;
				urlproxy.settime(ftime);
				urlproxy.setHttp(response);
			}
			else
			{
				urlproxy.settime(ftime);
				urlproxy.setHttp(null);
				
			}
			System.out.println(urlproxy.getNURL());
			return urlproxy;
			
		} catch (Exception e)
		{
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public Long retime() {
		// TODO Auto-generated method stub
		return System.currentTimeMillis();
	}
}
