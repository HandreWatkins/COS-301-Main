package Util;

import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpResponse;

public class UrlLinker
{
	private static String url;
	private static String nUrl;
	private static String method;
	private static String contextType;
	private static Long time;
	private static HttpServletRequest httpRequest;
        private static HttpResponse httpResponce;
	//==============================================================
	
        public void setrequest(HttpServletRequest _httpRequest)
        {
            httpRequest = _httpRequest;
        }
        
        public void setHttp(HttpResponse _httpResponce)
        {
            httpResponce = _httpResponce;
        }
        
	public void setURL(String urlB)
	{
		url = urlB;
	}
	
	public void setNURL(String urlB)
	{
		nUrl = urlB;
	}
	
	public void setmethod(String smethod)
	{
		method = smethod;
	}
	
	public void settime(Long fTime)
	{
		time = fTime;
	}
	
	//===============================================================
        public HttpResponse getResponse()
        {
            return httpResponce;
        }
	
        public HttpServletRequest getRequest()
        {
            return httpRequest;
        }
        
	public String getURL()
	{
		return url;
	}
	
	public String getNURL()
	{
		return nUrl;
	}
	
	public String getContext()
	{
		return contextType;
	}
	
	public String getMethod()
	{
		return method;
	}
	
	public Long getTime()
	{
		return time;
        }
}
