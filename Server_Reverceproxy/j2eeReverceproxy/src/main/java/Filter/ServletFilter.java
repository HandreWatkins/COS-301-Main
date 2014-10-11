package Filter;

import HTTPclient.Pushclient;
import HTTPclient.responseCall;
import Util.Translate;
import Util.UrlLinker;
import analisis.Interface;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import pickup.DeleteRequest;
import pickup.GetRequest;
import pickup.PickupHandle;
import pickup.PickupMain;
import pickup.PostRequest;
import pickup.PutRequest;

@WebFilter(filterName="proxyfilter", urlPatterns="/proxFil/*")
@Asynchronous
public class ServletFilter extends HttpServlet implements Filter
{
    private final Translate trance = new Translate();
    private PickupMain pickupHandle = null;
    private final Translate translate = new Translate();
    private final Pushclient callClient = new Pushclient();
    private final Interface inter = new Interface();
    private PickupHandle pickup = null;

    public ServletFilter()
    {
        super();
    }

    @Override
    public void init(FilterConfig fc) throws ServletException 
    {
        try
        {
            pickupHandle = new PickupMain(trance);
        }
        catch (IOException e) {}
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException
{
        try
        {
            pickupHandle = new PickupMain(trance);
        }
        catch (IOException e) {}
    }

    @GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpServletRequest requestHTTP = (HttpServletRequest) request;
        if((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse))
        {
            if(pickupHandle.isJAX_RS(request))
            {
                UrlLinker urlfile = translate.linkurl(requestHTTP);
                pickup = new PickupHandle(urlfile);
                UrlLinker urltrans = trance.linkurl(request);
                responseCall getrequest = new GetRequest();
                UrlLinker url = getrequest.clientreqeust(urltrans);
                callback(response, url);
            }
        }
    }

    @POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpServletRequest requestHTTP = (HttpServletRequest) request;
        if((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse))
        {
            if(pickupHandle.isJAX_RS(request))
            {
                UrlLinker urlfile = translate.linkurl(requestHTTP);
                pickup = new PickupHandle(urlfile);
                UrlLinker urltrans = trance.linkurl(request);
                responseCall postrequest = new PostRequest();
                UrlLinker url = postrequest.clientreqeust(urltrans);
                callback(response, url);
            }
        }
    }

    @PUT
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpServletRequest requestHTTP = (HttpServletRequest) request;
        if((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse))
        {
            if(pickupHandle.isJAX_RS(request))
            {
                UrlLinker urlfile = translate.linkurl(requestHTTP);
                pickup = new PickupHandle(urlfile);
                UrlLinker urltrans = trance.linkurl(request);
                responseCall putRequest = new PutRequest();
                UrlLinker url = putRequest.clientreqeust(urltrans);
                callback(response, url);
            }
        }
    }

    @DELETE
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpServletRequest requestHTTP = (HttpServletRequest) request;
        if((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse))
        {
            if(pickupHandle.isJAX_RS(request))
            {
                UrlLinker urlfile = translate.linkurl(requestHTTP);
                pickup = new PickupHandle(urlfile);
                UrlLinker urltrans = trance.linkurl(request);
                responseCall detrequest = new DeleteRequest();
                UrlLinker url = detrequest.clientreqeust(urltrans);
                callback(response, url);
            }
        }        
    }
    
    protected void callback(HttpServletResponse response,UrlLinker url)
    {
        try {
            callClient.responsewriter(url,response);
            inter.analisHandle(url);
        } catch (IOException ex) {
            Logger.getLogger(ServletFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServletFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET @POST @PUT @DELETE
    @Override
    @Asynchronous
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
                    callClient.responsewriter(urlfile,response);
                    inter.analisHandle(urlfile);
                } 
                catch (IOException e){} catch (Exception ex) {
                    Logger.getLogger(ServletFilter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                chain.doFilter(request, response);
            }
        }
    }

}
