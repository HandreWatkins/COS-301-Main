package Util;

public class UrlLinker
{
	private static String url;
	private static String nUrl;
	private static String contextType;
	private static Long time;
	
	public void setURL(String urlB)
	{
		url = urlB;
	}
	
	public void setNURL(String urlB)
	{
		nUrl = urlB;
	}
	
	public void setcontext(String urlB)
	{
		url = urlB;
	}
	
	public void settime(Long fTime)
	{
		time = fTime;
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
	
	public Long getTime()
	{
		return time;
	}
}
