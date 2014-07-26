package HTTPclient;

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
		if(urlresponse.getResponse() != null)
		{
			HttpEntity rEntity = urlresponse.getResponse().getEntity();
		
			try 
			{
				setcontext(rEntity);
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
		
		
		
	}
}
