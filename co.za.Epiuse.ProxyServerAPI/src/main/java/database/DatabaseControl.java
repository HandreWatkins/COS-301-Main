package database;

import java.sql.SQLException;
import javax.annotation.PreDestroy;
import javax.ejb.Asynchronous;
import javax.inject.Singleton;

public class DatabaseControl 
{
    DBConnection cntrl = null;
    
    @Singleton
    public DatabaseControl() throws SQLException, ClassNotFoundException
    {
        cntrl = new DBConnection(null, null, null);
        //selectRules(String uri,double expt)
        //insertDistress(String ip,String uri,double respondtime, double expt)
        //insertMainActivity(String ip,String uri,double respondtime)
    }

    @PreDestroy
    public void closeDB()
    {
        cntrl.closeDB(null, null, null);
    }
    
    @Asynchronous
    public boolean disDB()
    {
        return true;//insertDistress(String ip,String uri,double respondtime, double expt)
    }
    
    @Asynchronous
    public boolean mainDB()
    {
        return true;//insertMainActivity(String ip,String uri,double respondtime)
    }
    
    @Asynchronous
    public String[] ruleDB(String uri, double expt)
    {
        
        return cntrl.selectRules(uri, expt);        
    }
}
