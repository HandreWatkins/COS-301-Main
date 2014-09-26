package Analisis;

import java.util.Queue;
import javax.ejb.Asynchronous;

public class AnalisisHandel
{
    private Queue <String> queueData;
    public AnalisisHandel()
    {
        
    }
    
    @Asynchronous
    private void handel()
    {
        while (true)
        {            
            if(!queueData.isEmpty())
            {
                String [] dataSet = dataparch(queueData.remove());
                
            }
        }
    }
    
    public void loadInterface(String value)
    {
        
    }
    
    private String [] dataparch(String val)
    {
        return null;
    }
    
    
}
