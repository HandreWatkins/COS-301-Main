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

public class Proxyfilter implements Filter
{
	private PickupMain pickupHandle = new PickupMain();
	/*public boolean accept(Object entry) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}*/

	public void destroy()
	{
		
		
	}

	public void doFilter(ServletRequest requestHTTP, ServletResponse responseHTTP,FilterChain arg2) throws IOException, ServletException
	{
		if((requestHTTP instanceof HttpServletRequest) && (responseHTTP instanceof HttpServletResponse))
		{
			System.out.println(requestHTTP.toString()+ "test");
			pickupHandle.pickupListener(requestHTTP, responseHTTP);
		}
		else
		{
			return;
		}
	}

	public void init(FilterConfig arg0) throws ServletException{}

}