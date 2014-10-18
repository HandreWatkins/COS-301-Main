package co.za.epiuse.testserverv2.testserverv2;

import co.za.epiuse.testserverv2.testserverv2.echo.Echo;
import co.za.epiuse.testserverv2.testserverv2.failure.Failure;
import co.za.epiuse.testserverv2.testserverv2.slow.Slow;
import co.za.epiuse.testserverv2.testserverv2.varying.Varying;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;

public class ServletTest extends HttpServlet
{
    Echo echotest= new Echo();
    Failure failtest = new Failure();
    Slow slowtest = new Slow();
    Varying varytest = new Varying();
    
    public ServletTest()
    {
        super();
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException
    {
        
    }
    
    @GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        handle(request, response);
    }
    

    @POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        handle(request, response);
    }


    @DELETE
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
       handle(request, response);
    }   
    
    @PUT
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        handle(request, response);
    } 
    
    private void handle(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        if(request.getRequestURI().toString().contains("echo"))
        {
            echotest.echoReq(request, response);
            return;
        }
        
        if(request.getRequestURI().toString().contains("failure"))
        {
            failtest.fReq(request, response);
            return;
        }
        
        if(request.getRequestURI().toString().contains("slow"))
        {
            slowtest.slowReq(request, response);
            return;
        }
        
        if(request.getRequestURI().toString().contains("varying"))
        {
            varytest.varReq(request, response);
            return;
        }
    }
}
