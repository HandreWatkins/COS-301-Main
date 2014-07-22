package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.Translate;
import pickup.PickupMain;


public class Proxyfilter implements Filter
{
	private Translate trance = new Translate();
	private PickupMain pickupHandle = new PickupMain(trance);
	
	public boolean accept(Object entry) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("test");
		return false;
	}

	public void destroy()
	{
		
		
	}

	public void doFilter(final ServletRequest requestHTTP, final ServletResponse responseHTTP,FilterChain arg2) throws IOException, ServletException
	{
		if((requestHTTP instanceof HttpServletRequest) && (responseHTTP instanceof HttpServletResponse))
		{
			new Thread(new Runnable()
			{	
				public void run()
				{
					// TODO Auto-generated method stub
					System.out.println(requestHTTP.toString()+ "test");
					pickupHandle.pickupListener(requestHTTP, responseHTTP);
				}
			}).start();
		}
		else
		{
			return;
		}
	}

	public void init(FilterConfig arg0) throws ServletException{}

}
