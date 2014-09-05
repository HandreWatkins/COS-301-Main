package pickup;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;

import HTTPclient.responseCall;
import Util.UrlLinker;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpDelete;

public class DeleteRequest extends responseCall
{
    @Override
    public UrlLinker clientreqeust(UrlLinker urlproxy) {
            try 
            {
                    DefaultHttpClient httpclient = new DefaultHttpClient();
                    Long stime = retime();
                    Long etime = null;

                    HttpDelete deleteCall = new HttpDelete(urlproxy.getNURL());
                    Header [] head = urlproxy.getRequest().getHeaders("");

                    for(Header header : head)
                    {
                        deleteCall.setHeader(header);
                    }

                    deleteCall.setParams(urlproxy.getRequest().getParams());

                    HttpResponse response = httpclient.execute(deleteCall);

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
