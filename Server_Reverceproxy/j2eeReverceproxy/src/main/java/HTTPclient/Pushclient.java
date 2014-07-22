package HTTPclient;

import javax.servlet.ServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

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
		HttpEntity rEntity = urlLink.getResponse().getEntity();
		
		
		
		
	}
	
	private void setcontext(HttpEntity entity) throws Exception
	{
		if(entity == null)
		{
			throw new Exception();
		}
		
	}
}
