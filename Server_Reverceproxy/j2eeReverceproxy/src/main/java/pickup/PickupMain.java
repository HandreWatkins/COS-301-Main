package pickup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import HTTPclient.Pushclient;
import HTTPclient.responseCall;
import Util.Translate;
import Util.UrlLinker;

public class PickupMain 
{
	//=================================================================================
	private Pushclient	callClient = new Pushclient();
	private static ArrayList<UrlLinker> requestpickup = new ArrayList<UrlLinker>();
	private Translate translate = new Translate();
	private String [] jax_RS = {"GET","POST","PUT"};
	//=================================================================================
	
	public PickupMain(Translate trance){}
	
	public void pickupListener(HttpServletRequest requestHTTP, ServletResponse responseHTTP) throws IOException
	{
		UrlLinker requestcontrol = translate.linkurl(requestHTTP,responseHTTP);
		
		synchronized (requestcontrol)
		{
			requestpickup.add(requestcontrol);
		}
		
		while(!requestpickup.isEmpty())
		{
			UrlLinker httpFragment = requestpickup.get(0);
			requestpickup.remove(0);
			
			switch (Arrays.asList(jax_RS).indexOf(httpFragment.getMethod())) 
			{
				case 0:
					responseCall getrequest = new GetRequest();
					httpFragment = getrequest.clientreqeust(httpFragment);
					break;
				case 1:
					responseCall postrequest = new PostRequest();
					httpFragment = postrequest.clientreqeust(httpFragment);
					break;
				case 2:
					responseCall putrequest = new PutRequest();
					httpFragment = putrequest.clientreqeust(httpFragment);
					break;
				default:
					break;
			}
			callClient.responsewriter(httpFragment);
		}
	}
	
	//Test if httpRequest is JAX-RS or JAX-WS
	public Boolean isJAX_RS(HttpServletRequest requestHTTP)
	{
		if(Arrays.asList(jax_RS).contains(requestHTTP.getMethod()))
			return true;
		else
			return false;
	}
}
