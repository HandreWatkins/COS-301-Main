package pickup;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import HTTPclient.responseCall;
import Util.UrlLinker;
import java.io.IOException;
import java.util.Enumeration;

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
                    Enumeration header = urlproxy.getRequest().getHeaderNames();
                    while (header.hasMoreElements())
                    {
                        String name = (String) header.nextElement();
                        postCall.addHeader(name, urlproxy.getRequest().getHeader(name));
                    }

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
			
		} catch (IOException e)
		{
			return null;
		}
	}

	@Override
	public Long retime()
	{
		return System.currentTimeMillis();
	}
}
