package co.za.epiuse.testserverv2.testserverv2.varying;

import co.za.epiuse.testserverv2.testserverv2.echo.Echo;
import co.za.epiuse.testserverv2.testserverv2.failure.Failure;
import co.za.epiuse.testserverv2.testserverv2.slow.GeoName;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Varying 
{
    public Varying() 
    {
        
    }
    
     private void delay() 
     {
        int delay = 0;
        int minute = Calendar.getInstance().get(Calendar.MINUTE);
        if (minute > 0 && minute < 60) {
            int random = new Random().nextInt(20);
            delay += random;
        }
        if (minute > 0 && minute < 25) {
            int random = new Random().nextInt(100);
            delay += random;
        }
        if (minute > 10 && minute < 30) {
            int random = new Random().nextInt(250);
            delay += random;
        }
        if (minute > 20 && minute < 40) {
            int random = new Random().nextInt(500);
            delay += random;
        }
        try {
            Thread.sleep(delay);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
     
    public void varReq(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Random ranval = new Random();
        int rand = ranval.nextInt(2000);
        
        if((rand%3 ==0) || (rand%6 ==0) || (rand%10 ==0))
        {
            Echo eco = new Echo();
            eco.echoReq(request, response);
        }
        else
        {
            if((rand%18 ==0) || (rand%23 ==0) || (rand%105 ==0))
            {
                Failure fail = new Failure();
                fail.fReq(request, response);
            }
            else
            {
                delay();
                response.getWriter().print("this vary");
                response.flushBuffer();
            }
        }
    }
    
}
