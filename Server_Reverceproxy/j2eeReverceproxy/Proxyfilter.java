package Filter;

import HTTPclient.Pushclient;
import Util.Translate;
import Util.UrlLinker;
import analisis.Interface;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pickup.PickupHandle;
import pickup.PickupMain;


public class Proxyfilter implements Filter
{
    private final Translate trance = new Translate();
    private PickupMain pickupHandle = null;
    private final Translate translate = new Translate();
    private final Pushclient callClient = new Pushclient();
    private Interface inter = new Interface();
    
    PickupHandle pickup = null;
	
    public boolean accept(Object entry) throws IOException
    {
        System.out.println("test");
        return false;
    }

    @Override
    public void destroy(){}

    @Override
    public void init(FilterConfig arg0) throws ServletException
    {
            try
            {
                pickupHandle = new PickupMain(trance);
            }
            catch (IOException e) {}
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        if((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse))
        {
            HttpServletRequest requestHTTP = (HttpServletRequest) request;
            if(pickupHandle.isJAX_RS(requestHTTP))
            {	
                UrlLinker urlfile = translate.linkurl(requestHTTP);
                pickup = new PickupHandle(urlfile);
                
                try 
                {
                    urlfile = pickup.run();
                    //response.getWriter().print(urlfile.getResponse().getEntity());
                    callClient.responsewriter(urlfile,response);
                    inter.analisHandle(urlfile);
                } 
                catch (IOException e){}
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
