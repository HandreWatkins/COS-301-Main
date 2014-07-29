package pickup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import HTTPclient.Pushclient;
import Util.Translate;
import Util.UrlLinker;

public class PickupMain
{
	//=================================================================================
	@SuppressWarnings("unused")
	private Pushclient	callClient = new Pushclient();
	private static ArrayList<Runnable> requestpickup = new ArrayList<Runnable>();
	@SuppressWarnings("unused")
	private Translate translate = new Translate();
	private ExecutorService executor = null;
	private Boolean singleton = false;
	private String [] jax_RS = {"GET","POST","PUT"};
	//=================================================================================
	
	public PickupMain(Translate trance) throws IOException
	{	
		executor = Executors.newFixedThreadPool(1000);
		
	}
	
	public void pickupListener(UrlLinker file) throws IOException
	{
		UrlLinker requestcontrol = file;//translate.linkurl(requestHTTP,responseHTTP);
		
		Runnable connect = new PickupHandle(requestcontrol);
		requestpickup.add(connect);
		
		
		
		/*synchronized (requestcontrol)
		{
			requestpickup.add(requestcontrol);
		}*/
		/*while(!requestpickup.isEmpty())
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
		}*/
	}
	
	//Test if httpRequest is JAX-RS or JAX-WS
	public Boolean isJAX_RS(HttpServletRequest requestHTTP)
	{
		if(Arrays.asList(jax_RS).contains(requestHTTP.getMethod()))
			return true;
		else
			return false;
	}
	
	public void start()
	{
		singleton = true;
		
		new Thread(new Runnable() 
		{
			public void run()
			{
				while(singleton)
				{
					if(!requestpickup.isEmpty())
					{
						Runnable connect = requestpickup.remove(0);
						executor.execute(connect);
					}
				}
			}
		}).start();
	}
	
	public boolean hasStart()
	{
		return singleton;
	}
}
