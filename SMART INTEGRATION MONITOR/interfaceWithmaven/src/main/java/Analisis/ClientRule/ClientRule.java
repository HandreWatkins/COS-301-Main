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
        String uri = data[0];
        Long respomce = Long.parseLong(data[2]);
        String [] daStrings = connect.ruleDB(uri);
        
        if(daStrings != null)
        {
            expt = Long.parseLong(daStrings[2]);
            if(expt <= respomce)
                connect.disDB(data[0].substring(0, data[0].indexOf("/")),data[0].substring(data[0].indexOf("/")), data[2], String.valueOf(expt));
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
