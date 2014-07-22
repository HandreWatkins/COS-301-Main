package Util;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class Translate
{
	public Translate()
	{
		
	}
	
	public UrlLinker linkurl(HttpServletRequest request , ServletResponse response)
	{
		if(request == null)
		{
			return null;
		}
		
		UrlLinker urlSaver = new UrlLinker();
		urlSaver.setcontext(request.getMethod());
		urlSaver.setURL(request.getRequestURI());
		String newUrl = "http://localhost/" + request.getRequestURI();
		urlSaver.setNURL(newUrl);
		
		return urlSaver;
	}
}
