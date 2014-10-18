package pickup;

import HTTPclient.responseCall;
import Util.UrlLinker;
import com.sun.javafx.fxml.builder.URLBuilder;
import java.io.IOException;
import java.util.Enumeration;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

public class DeleteRequest extends responseCall
{
    @Override
    public UrlLinker clientreqeust(UrlLinker urlproxy) {
            try 
            {
                    DefaultHttpClient httpclient = new DefaultHttpClient();
                    Long stime = retime();
                    Long etime = null;
                    
                    /*Enumeration parms = urlproxy.getRequest().getParameterNames();

                    URIBuilder builder = new URIBuilder();
                    builder.setScheme("HTTP").setHost("localhost").setPath(urlproxy.getNURL());
                    while (parms.hasMoreElements())
                    {
                        String name = (String) parms.nextElement();
                        builder.addParameter(name, urlproxy.getRequest().getParameter(name));
                    }*/
                    
                    HttpDelete deleteCall = new HttpDelete(urlproxy.getNURL());
                    
                    Enumeration header = urlproxy.getRequest().getHeaderNames();
                    while (header.hasMoreElements())
                    {
                        String name = (String) header.nextElement();
                        deleteCall.addHeader(name, urlproxy.getRequest().getHeader(name));
                    }
                    
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
