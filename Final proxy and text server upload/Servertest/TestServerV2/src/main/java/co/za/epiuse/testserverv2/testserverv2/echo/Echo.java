package co.za.epiuse.testserverv2.testserverv2.echo;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Echo
{
    public Echo(){}
    
    public void echoReq(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.getWriter().print("work");
        response.flushBuffer();
    }
}
