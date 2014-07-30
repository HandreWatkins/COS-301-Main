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
		urlSaver.setmethod(request.getMethod());
		String url = request.getRequestURL().toString();
		urlSaver.setURL(url);
		urlSaver.setServlet(response);
		String newUrl = getnewUrl(url);
		System.out.println(newUrl);
		urlSaver.setNURL(newUrl);
		
		return urlSaver;
	}
	
	public String getnewUrl(String url)
	{
		//url = url.substring(url.indexOf("/", url.indexOf("//")+1)+1);
		return "http://localhost:8081/servlet"; //url;
	}
	
	public class texttranslate
	{
		public texttranslate() 
		{
			// TODO Auto-generated constructor stub
		}
		
		
		
		
		
		
	}
}
