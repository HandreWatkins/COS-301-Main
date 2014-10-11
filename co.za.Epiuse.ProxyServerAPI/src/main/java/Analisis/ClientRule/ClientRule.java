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
        String [] daStrings = connect.ruleDB(uri, 0);
        expt = Long.parseLong(daStrings[3]);
        
        return !(daStrings != null && expt <= respomce);
    }
    
    @Override
    public Long getexpected()
    {
        return expt;
    }
}
