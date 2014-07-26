package Util;

import java.io.IOException;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class Translate
{
	public Translate()
	{
		
	}
	
	public UrlLinker linkurl(HttpServletRequest request , ServletResponse response) throws IOException
	{
		if(request == null)
		{
			return null;
		}
		
		UrlLinker urlSaver = new UrlLinker();
		urlSaver.setcontext(request.getMethod());
		String url = request.getRequestURL().toString();
		urlSaver.setURL(url);
		String newUrl = /*"http://localhost/" +*/ request.getRequestURL().toString();
		System.out.println(newUrl);
		urlSaver.setNURL(newUrl);
		
		return urlSaver;
	}
	
	public class texttranslate
	{
		public texttranslate() 
		{
			// TODO Auto-generated constructor stub
		}
		
		
		
		
	}
}
