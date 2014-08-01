package Util;

import javax.servlet.ServletResponse;

import org.apache.http.HttpResponse;

public class UrlLinker
{
	private static String url;
	private static String nUrl;
	private static String method;
	private static String contextType;
	private static ServletResponse responsewriter;
	private static HttpResponse response;
	private static Long time;
	
	//==============================================================
	
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
	
	public void setContext(String urlB)
	{
		contextType = urlB;
	}
	
	public void settime(Long fTime)
	{
		time = fTime;
	}
	
	public void setServlet(ServletResponse responseSer)
	{
		responsewriter = responseSer;
	}
	
	public void setHttp(HttpResponse responseHttp)
	{
		response = responseHttp;
	}
	//===============================================================
	
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
	
	public ServletResponse getServlet()
	{
		return responsewriter;
	}
	
	public HttpResponse getResponse()
	{
		return response;
	}
}
