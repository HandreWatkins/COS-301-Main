package HTTPclient;

import static org.apache.commons.lang.StringUtils.containsIgnoreCase;
import static org.apache.commons.lang.StringUtils.endsWithIgnoreCase;
import static org.apache.commons.lang.StringUtils.substringBefore;
import static org.apache.commons.lang.StringUtils.trimToEmpty;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import Util.Translate;
import Util.UrlLinker;

public class Pushclient 
{
	UrlLinker urlLink;
	public Pushclient()
	{
		// TODO Auto-generated constructor stub
	}
	
	public void responsewriter(UrlLinker urlresponse) throws IllegalStateException, IOException
	{
		urlLink = urlresponse;
		if(urlresponse != null)
		{
			HttpEntity rEntity = urlresponse.getResponse().getEntity();

			InputStream content = rEntity.getContent();
			try {
				setcontext(rEntity);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("context");
			}
			
			try 
			{
				if(urlLink.getContext() != "binary")
				{
					//String text = Translate.traString(rEntity,"UTF-8");
					Document document = null;
					
					document = Jsoup.parse(content, "UTF-8", urlLink.getURL());
					System.out.println(urlLink.getContext());
					
					if(urlLink.getContext().equals("text"))
					{
					    for (Element element : document.select("[href]")) {
					        element.attr("href", element.attr("href"));
					    }

					    for (Element element : document.select("[src]")) {
					        element.attr("src", element.attr("src"));
					    }
					}
					

					ServletResponse respond =  urlLink.getServlet();
					
					respond.setContentType("text/html;charset=UTF-8");
					respond.getWriter().write(document.html());
					respond.getWriter().flush();
				}
				else
				{
					ServletResponse respond =  urlLink.getServlet();
					//respond.setContentType(url.getMimeType(urlLink.getPathInfo()));
				    OutputStream output = respond.getOutputStream();
				    byte[] buffer = new byte[8192];

				    for (int length = 0; (length = rEntity.getContent().read(buffer)) > 0;) {
				        output.write(buffer, 0, length);
				    }
				}
			}
			catch (Exception e)
			{
				// TODO: handle exception
				System.out.println("line");
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
		context.getContext(urlLink);
	}
	
	private class Contextset
	{
		UrlLinker context = null;
		
		public Contextset() {}
		
		public UrlLinker getContext(UrlLinker urlFrag) throws Exception
		{
			context = urlFrag;
			
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
			 if (containsIgnoreCase(context.getNURL(), ".html"))
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
