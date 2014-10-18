package Analisis.Smart;

import Analisis.AnalisisInterface;
import database.DatabaseControl;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ejb.Asynchronous;

public class AIInterface implements AnalisisInterface
{
    DatabaseControl connect = null;
    public AIInterface(DatabaseControl _connect)
    {
        connect = _connect;
    }

    @Asynchronous
    @Override
    public boolean testRequest(String[] data)
    {
        int ntime = 0;
        int amount = 0;
        int total = 0;
        double previous = 0;
        
        //split server and uri
        int ix = data[0].indexOf("//")+2;
        int ic = data[0].indexOf("/",ix);
        String serverText = data[0].substring(ix, ic);
        String uriS = data[0].substring(ix+serverText.length());
        
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK); 
        
        Date date = new Date();
        calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        int time = calendar.get(Calendar.HOUR_OF_DAY);
        
        //database
        
        String [] dataAI = connect.AIget(uriS, String.valueOf(day), String.valueOf(time));
        
        if(dataAI != null)
        {
            amount = dataAI.length;
        }
        
        //test
        if(amount < 30)
        {
            connect.updateAI(uriS,String.valueOf(day),String.valueOf(time),amount++,Integer.decode(data[2]));
            return true;
        }
        else
        {
            double [] datasum = dataparce(dataAI);
            double news = standerd(datasum, amount);
            
            if(news < 2.00)
            {
                return true;
            }
            else
            {
                connect.disDB(serverText,uriS, data[2], String.valueOf(news));
            }
            connect.updateAI(uriS,String.valueOf(date),String.valueOf(time),amount,Integer.decode(data[2]));
        }
        return false;
        
    }

    @Override
    public Long getexpected() {
        return null;
        
    } 
    
    private double standerd(double [] sumdata, int countV)
    { 
        double sum = 0;
        double avg = 0;
        
        for(double ave : sumdata)
        {
            sum = sum + ave;
        }
        
        avg = sum/countV;
        sum = 0;
        
        for(double ave : sumdata)
        {
            sum = sum + (Math.pow((ave - avg), 2.0));
        } 
        
        return Math.sqrt(sum/(countV-1));
    }
    
    private double [] dataparce(String [] arr)
    {
        double [] rdata = new double[arr.length];
        
        int i = 0;
        for(String sData : arr)
        {
            String [] nData = sData.split(",");
            rdata[i] = Double.valueOf(nData[5]);
        }
        
        return rdata;
    }
            
}
