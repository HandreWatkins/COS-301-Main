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


public class Proxyfilter implements Filter
{
	private Translate trance = new Translate();
	private PickupMain pickupHandle = new PickupMain(trance);
	
	public boolean accept(Object entry) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("test");
		return false;
	}

	public void destroy(){}

	public void init(FilterConfig arg0) throws ServletException{}

	public void doFilter(final ServletRequest request, final ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		if((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse))
		{
			final HttpServletRequest requestHTTP = (HttpServletRequest) request;
			
			if(pickupHandle.isJAX_RS(requestHTTP))
			{
				/*new Thread(new Runnable()
				{	
					public void run()
					{*/
						//http://www.developerscrappad.com/1781/java/java-ee/rest-jax-rs/java-ee-7-jax-rs-2-0-cors-on-rest-how-to-make-rest-apis-accessible-from-a-different-domain/
						try {
							String test = requestHTTP.getRequestURI().toString();
							System.out.println(test+ "test");
							pickupHandle.pickupListener(requestHTTP, response);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					/*}
				}).start();*/
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
