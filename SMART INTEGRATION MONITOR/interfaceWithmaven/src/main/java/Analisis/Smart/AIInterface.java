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
        String uri = data[0];
        int total = 0;
        double previous = 0;
        
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK); 
        
        Date date = new Date();
        calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        int time = calendar.get(Calendar.HOUR_OF_DAY);
        
        //database
        
        String [] dataAI = connect.AIget(uri, String.valueOf(date), String.valueOf(time));
        
        if(dataAI != null)
        {
            amount = Integer.parseInt(dataAI[4]);
            previous = Integer.parseInt(dataAI[5]);
            total = (int)previous + Integer.parseInt(data[2]);
        }
        else
        {
            total = Integer.parseInt(data[2]);
            previous = 0;
            
        }
        //test
        if(amount < 10)
        {
            double stdNew = standerd(amount, total, previous, ntime);
            //update db
            connect.updateAI(uri,String.valueOf(date),String.valueOf(time),amount,total);
            return true;
        }
        else
        {
            double stdNew = standerd(amount, total, previous, ntime);
            double news = Math.sqrt(stdNew/amount);
            
            if(news < 2.00)
            {
                return true;
            }
            else
            {
                connect.disDB(data[0].substring(0, data[0].indexOf("/")),data[0].substring(data[0].indexOf("/")), data[2], String.valueOf(stdNew));
            }
            connect.updateAI(uri,String.valueOf(date),String.valueOf(time),amount,total);
        }
        return false;
        
    }

    @Override
    public Long getexpected() {
        return null;
        
    } 
    
    private double standerd(int count, double total, double prevous, double time)
    { 
        return prevous+(time-(total/count))*(time-((total+time)/count++));
    }
}
