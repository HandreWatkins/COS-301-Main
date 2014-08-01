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
	private final Pushclient callClient = new Pushclient();
	private static final ArrayList<Runnable> requestpickup = new ArrayList<Runnable>();
	@SuppressWarnings("unused")
	private final Translate translate = new Translate();
	private ExecutorService executor = null;
	private Boolean singleton = false;
	private final String [] jax_RS = {"GET","POST","PUT"};
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
