package Analisis.ClientRule;

import Analisis.AnalisisInterface;
import database.DatabaseControl;
import javax.inject.Singleton;


public class ClientRule implements AnalisisInterface
{
    DatabaseControl connect = null;
    double expt;
    
    public ClientRule(DatabaseControl _connect)
    {
        connect = _connect;
    }

    @Singleton
    @Override
    public boolean testRequest(String[] data)
    {
        int ix = data[0].indexOf("//")+2;
        int ic = data[0].indexOf("/",ix);
        String serverText = data[0].substring(ix, ic);
        String uriS = data[0].substring(ix+serverText.length());

        int respomce = Integer.parseInt(data[2]);
        
        String [] daStrings = connect.ruleDB(uriS);
        
        if(daStrings != null)
        {
            expt = dataparce(daStrings);
            
            if(expt <= respomce)
                connect.disDB(serverText,uriS, data[2], String.valueOf(expt));
        }
        else
        {
            return true;
        }
        
        return false;
    }
    
    public double  dataparce(String [] arr)
    {
        double rdata = 0;
        String [] nData = arr[0].split(",");
        rdata = Double.valueOf(nData[5]);
        return rdata;
    }
    
    @Override
    public Long getexpected()
    {
        return null;
    }
}
