package Util;

import org.apache.http.HttpRequest;

public class Translate
{
	public Translate()
	{
		
	}
	
	public UrlLinker linkurl(HttpRequest request)
	{
		if(request == null)
		{
			return null;
		}
		
		UrlLinker urlSaver = new UrlLinker();
		urlSaver.setcontext(request.getRequestLine().getMethod());
		urlSaver.setURL(request.getRequestLine().getUri());
		String newUrl = "http://localhost/" + request.getRequestLine().getUri();
		urlSaver.setNURL(newUrl);
		
		return urlSaver;
	}
}
