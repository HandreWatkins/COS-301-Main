package pickup;

import java.util.ArrayList;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;

import HTTPclient.Pushclient;
import HTTPclient.responseCall;
import Util.Translate;

public class PickupMain 
{
	//=================================================================================
	private responseCall callServer = new responseCall();
	private Pushclient	callClient = new Pushclient();
	private static ArrayList<HttpServletRequest> requestpickup = new ArrayList<HttpServletRequest>();
	private static ArrayList<ServletResponse> responsepickup = new ArrayList<ServletResponse>();
	//=================================================================================
	
	public PickupMain(Translate trance)
	{
		// TODO Auto-generated constructor stub
	}
	
	public void pickupListener(HttpServletRequest requestHTTP, ServletResponse responseHTTP)
	{
		synchronized (responseHTTP)
		{
			requestpickup.add(requestHTTP);
			responsepickup.add(responseHTTP);
		}
		
		while(!requestpickup.isEmpty())
		{
			HttpServletRequest request = requestpickup.get(0);
			ServletResponse response = responsepickup.get(0);
			requestpickup.remove(0);
			responsepickup.remove(0);
			
			HttpResponse responseSer = callServer.clientreqeust(request);
			callClient.responsewriter(responseSer,response);
		}
	}
}
