package pickup;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import HTTPclient.Pushclient;
import Util.Translate;
import Util.UrlLinker;

public class PickupMain
{
	//=================================================================================
	@SuppressWarnings("unused")
	private final Pushclient callClient = new Pushclient();
	@SuppressWarnings("unused")
	private final Translate translate = new Translate();
	private final String [] jax_RS = {"GET","POST","PUT","DELETE"};
	//=================================================================================
	
	public PickupMain(Translate trance) throws IOException{}
	
	public void pickupListener(UrlLinker file) throws IOException
	{
            //UrlLinker requestcontrol = file;//translate.linkurl(requestHTTP,responseHTTP);
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
