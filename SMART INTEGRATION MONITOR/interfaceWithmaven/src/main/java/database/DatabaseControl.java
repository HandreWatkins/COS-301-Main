package database;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.nio.file.Path;
import static java.nio.file.Paths.get;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Queue;
import javax.annotation.PreDestroy;
import javax.ejb.Asynchronous;
import javax.inject.Singleton;

@Singleton
public class DatabaseControl 
{
    DBConnection cntrl = null;
    volatile Queue <String []> backlogDQ;
    volatile Queue <Integer> backlogNQ;
    static boolean runPool;
    final String server;
    final String user;
    final String password;
    Properties properties;
    

    public DatabaseControl() throws ClassNotFoundException, IOException, SQLException
    {
        Properties properties = new Properties();
        try
        {
            String propS = "config.properties";
            InputStream inputStream  = this.getClass().getResourceAsStream(propS);
            this.properties = new Properties();

            properties.load(inputStream);
        }
        catch(Exception e)
        {
            properties.setProperty("Server", "jdbc:postgresql://localhost:5432/dbMonitor");
            properties.setProperty("User", "postgres");
            properties.setProperty("Password", "1");
        }
        
        server = properties.getProperty("Server");
        user = properties.getProperty("User");
        password = properties.getProperty("Password");
        
        cntrl = new DBConnection(server, user, password);
    }

    @PreDestroy
    public void closeDB()
    {

    }
    
    @Asynchronous
    public boolean disDB(String ip,String uri,String respondtime, String expt)
    {
        return cntrl.insertDistress(ip,uri,Double.valueOf(respondtime),Double.valueOf(expt));
    }
    
    @Asynchronous
    public boolean mainDB(String serverip, String URI, String restime) throws UnknownHostException
    {
        
        if(cntrl.insertMainActivity(serverip,URI,Double.valueOf(restime)))
        {
            return true;
        }
        else
        {
            String [] dataset = {server,URI,restime};
            //backlog(1,dataset);
            return false;
        }
    }
    
    @Asynchronous
    public String[] ruleDB(String uri)
    {
        return cntrl.selectRules(uri);        
    }
    
    @Asynchronous
    public String[] AIget(String uri, String dateF, String time)
    {
        if(cntrl.selectAI(uri,dateF,time) != null)
        {
            String [] data = cntrl.selectAI(uri,dateF,time);
            return  data.length != 0?data:null;
                        }
        return null;
    }
    
    @Asynchronous
    public void updateAI(String uri, String date, String time, int count, int total)
    {
        cntrl.insertAI(uri,date,time,count,total);       
    }
}
