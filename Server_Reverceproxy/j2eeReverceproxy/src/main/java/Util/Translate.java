package Util;

import java.io.IOException;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;

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
		return "http://localhost:8080/servlet";
	}

	public static String traString(HttpEntity entityHttp, String charsetT)
	{
		try 
		{
			return IOUtils.toString(entityHttp.getContent(), charsetT);
		} 
		catch (Exception e) {}
		
		return "";
	}
}
