package pickup;

import HTTPclient.responseCall;
import Util.UrlLinker;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetRequest extends responseCall
{
	@Override
	public UrlLinker clientreqeust(UrlLinker urlproxy)
        {
            try 
            {
                DefaultHttpClient httpclient = new DefaultHttpClient();
                Long stime = retime();
                Long etime = null;
                
                HttpGet getCall = new HttpGet(urlproxy.getNURL());
                Header [] head = urlproxy.getRequest().getHeaders("");
                
                for(Header header : head)
                {
                    getCall.setHeader(header);
                }
                
                getCall.setParams(urlproxy.getRequest().getParams());

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

            } catch (IOException e)
            {
                    return null;
            }
	}

	@Override
	public Long retime() {
		return System.currentTimeMillis();
	}
}
