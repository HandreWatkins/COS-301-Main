package pickup;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

import HTTPclient.responseCall;
import Util.UrlLinker;

public class PutRequest extends responseCall
{
	@Override
	public UrlLinker clientreqeust(UrlLinker urlproxy) {
		try 
		{
			DefaultHttpClient httpclient = new DefaultHttpClient();
			Long stime = retime();
			Long etime = null;
			
			HttpPut putCall = new HttpPut(urlproxy.getNURL());
			
			HttpResponse response = httpclient.execute(putCall);
			
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
