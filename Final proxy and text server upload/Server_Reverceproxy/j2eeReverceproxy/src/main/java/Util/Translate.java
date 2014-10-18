package Util;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;

public class Translate
{
	public Translate()
	{
		
	}
	
	public UrlLinker linkurl(HttpServletRequest request) throws IOException
	{
		if(request == null)
		{
			return null;
		}
		
		UrlLinker urlSaver = new UrlLinker();
		urlSaver.setmethod(request.getMethod());
		String url = request.getRequestURL().toString();
		urlSaver.setURL(url);
		String newUrl = getnewUrl(url);
		System.out.println(newUrl);
		urlSaver.setNURL(newUrl);
                urlSaver.setrequest(request);
		
		return urlSaver;
	}
	
	public String getnewUrl(String url)
	{
            int posSlash = url.indexOf("/", url.indexOf("/", 8)+1);
            String nurl = url.replace("j2eeReverceproxy/proxy/", "");//url.substring(0, posSlash+1) + url.substring(url.indexOf("/", posSlash+1)+1) ;
            return nurl; 
            //"http://localhost:8080/servlet";
	}

	public static String traString(HttpEntity entityHttp, String charsetT)
	{
		try 
		{
			return IOUtils.toString(entityHttp.getContent(), charsetT);
		} 
		catch (IOException | IllegalStateException e) {}
		
		return "";
	}
}
