package HTTPclient;

import static org.apache.commons.lang.StringUtils.containsIgnoreCase;
import static org.apache.commons.lang.StringUtils.endsWithIgnoreCase;
import static org.apache.commons.lang.StringUtils.substringBefore;
import static org.apache.commons.lang.StringUtils.trimToEmpty;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

import Util.UrlLinker;

public class Pushclient 
{
	UrlLinker urlLink;
	public Pushclient()
	{
		// TODO Auto-generated constructor stub
	}
	
	public void responsewriter(UrlLinker urlresponse)
	{
		urlLink = urlresponse;
		if(urlresponse != null)
		{
			HttpEntity rEntity = urlresponse.getResponse().getEntity();
		
			try 
			{
				//setcontext(rEntity);
				
				/*InputStream content = rEntity.getContent();
				ServletOutputStream outputStream = urlLink.getServlet().getOutputStream();
				IOUtils.copy(content, outputStream);*/
				
				//while(rEntity.getContent().available() > 0)
					String text = IOUtils.toString(rEntity.getContent(), "UTF-8");
					System.out.println(text);
					
					urlLink.getServlet().setContentType("text/html; charset=ISO-8859-4");
				    try {
				    	urlLink.getServlet().getWriter().print("hi");
				    	urlLink.getServlet().flushBuffer();
				    } catch (IOException e) {

				    }
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
		}
		else
		{
			return;
		}
	}
	
	private void setcontext(HttpEntity entity) throws Exception
	{
		if(entity == null)
		{
			throw new Exception();
		}
		
		//set return context type for return display
		Contextset context = new Contextset();
		context.getContext(entity,urlLink);
		
		
		
	}
	
	private class Contextset
	{
		HttpEntity context = null;
		
		public Contextset() {}
		
		public UrlLinker getContext(HttpEntity entity,UrlLinker urlFrag) throws Exception
		{
			context = entity;
			
			if(isText())
				if(isJava())
					urlFrag.setContext("js");
				else
					urlFrag.setContext("text");
			else
				urlFrag.setContext("binary");
			
			return urlFrag;
		}
				
		private boolean isText()
		{
			 if (containsIgnoreCase(trimToEmpty(((Header) context).getValue().toString()), "image"))
				 return false;
			 else
			    return true;
		}
		
		private boolean isJava()
		{
			return endsWithIgnoreCase(substringBefore(urlLink.getNURL(), "?"), ".js");
		}
	}
}
