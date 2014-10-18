package Analisis.ClientRule;

import Analisis.AnalisisInterface;
import database.DatabaseControl;
import javax.inject.Singleton;


public class ClientRule implements AnalisisInterface
{
    DatabaseControl connect = null;
    Long expt;
    
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

        Long respomce = Long.parseLong(data[2]);
        
        String [] daStrings = connect.ruleDB(uriS);
        
        if(daStrings != null)
        {
            expt = Long.parseLong(daStrings[2]);
            if(expt <= respomce)
                connect.disDB(serverText,uriS, data[2], String.valueOf(expt));
        }
        else
        {
            return true;
        }
        
        return false;
    }
    
    @Override
    public Long getexpected()
    {
        return expt;
    }
}
