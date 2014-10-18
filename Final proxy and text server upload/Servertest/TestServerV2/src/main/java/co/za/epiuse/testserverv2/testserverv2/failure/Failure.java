package co.za.epiuse.testserverv2.testserverv2.failure;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Failure
{

    public Failure() 
    {
    }
    
    public void fReq(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setStatus(404);
        response.setHeader("HTTP 1.1 200", "HTTP 1.1 404");
        response.getWriter().print("Fail");
        response.flushBuffer();
    }
}
