package pickup;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import HTTPclient.responseCall;
import Util.UrlLinker;

public class PostRequest extends responseCall
{
	@Override
	public UrlLinker clientreqeust(UrlLinker urlproxy) {
		try 
		{
			DefaultHttpClient httpclient = new DefaultHttpClient();
			Long stime = retime();
			Long etime = null;
			
			HttpPost postCall = new HttpPost(urlproxy.getNURL());
			
			HttpResponse response = httpclient.execute(postCall);
			
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
