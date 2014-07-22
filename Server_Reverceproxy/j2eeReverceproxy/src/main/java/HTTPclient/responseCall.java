package HTTPclient;

import Util.UrlLinker;


public abstract class responseCall
{
	public abstract UrlLinker clientreqeust(UrlLinker urlproxy);
	
	public abstract Long starttime();
	
	public abstract Long stoptime();
}

/*
 * public responseCall() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public HttpResponse clientreqeust(HttpServletRequest requestHTTP)
	{
		try 
		{
			DefaultHttpClient httpclient = new DefaultHttpClient();
			Long stime = System.currentTimeMillis();
			Long etime = null;
			
			HttpResponse response = httpclient.execute((HttpUriRequest) requestHTTP);
			
			if(response.getStatusLine().getStatusCode() != 0)
			{
				etime = System.currentTimeMillis();
			}
			
			if(etime != null)
			{
				Long ftime = etime - stime;
			}
			
			return response;
		} catch (Exception e)
		{
			// TODO: handle exception
			return null;
		}
		
		
	}
 */
