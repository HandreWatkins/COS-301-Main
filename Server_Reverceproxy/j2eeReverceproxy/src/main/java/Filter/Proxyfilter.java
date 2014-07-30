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

import pickup.PickupMain;
import Util.Translate;
import Util.UrlLinker;


public class Proxyfilter implements Filter
{
	private Translate trance = new Translate();
	private PickupMain pickupHandle = null;
	private Translate translate = new Translate();
	
	public boolean accept(Object entry) throws IOException
	{
		System.out.println("test");
		return false;
	}

	public void destroy(){}

	public void init(FilterConfig arg0) throws ServletException
	{
		try {
			pickupHandle = new PickupMain(trance);
			
			if(!pickupHandle.hasStart())
				pickupHandle.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doFilter(final ServletRequest request, final ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		if((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse))
		{
			final HttpServletRequest requestHTTP = (HttpServletRequest) request;
			if(!pickupHandle.hasStart())
				pickupHandle.start();
			
			if(pickupHandle.isJAX_RS(requestHTTP))
			{	
				try {
					UrlLinker urlfile = translate.linkurl(requestHTTP,response);
					pickupHandle.pickupListener(urlfile);

				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				chain.doFilter(request, response);
			}
		}
		else
		{
			return;
		}
	}
}



//http://www.developerscrappad.com/1781/java/java-ee/rest-jax-rs/java-ee-7-jax-rs-2-0-cors-on-rest-how-to-make-rest-apis-accessible-from-a-different-domain/
