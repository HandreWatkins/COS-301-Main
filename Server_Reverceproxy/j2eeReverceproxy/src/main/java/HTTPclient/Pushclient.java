package HTTPclient;

import javax.servlet.ServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class Pushclient 
{
	public Pushclient()
	{
		// TODO Auto-generated constructor stub
	}
	
	public void responsewriter(HttpResponse response, ServletResponse httpResponse)
	{
		HttpEntity rEntity = response.getEntity();
		
		
	}
	
	private void setcontext(HttpEntity entity) throws Exception
	{
		if(entity == null)
		{
			throw new Exception();
		}
		
	}
}
