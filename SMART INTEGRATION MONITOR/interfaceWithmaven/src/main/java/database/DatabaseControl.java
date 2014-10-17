package database;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    public boolean mainDB(String server, String URI, String restime)
    {
        if(cntrl.insertMainActivity(server,URI,Double.valueOf(restime)))
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
    
    /*@Asynchronous
    private void backlog(int requestnum, String [] requestdata)
    {
        if(!cntrl.testconn())
        {
            backlogDQ.add(requestdata);
            backlogNQ.add(requestnum);
        }
    }
    
    @Asynchronous
    private void pooler()
    {
        while(runPool)
        {
            if(cntrl.testconn())
            {
                while(!backlogDQ.isEmpty())
                {
                    String [] data = backlogDQ.remove();
                    int num = backlogNQ.remove();
                    
                    switch(num)
                    {
                        case 1:
                        { 
                            mainDB(data[0],data[1],data[2]);
                            break;
                        }
                        case 2:
                        {
                            disDB(data[0],data[1],data[2],data[3]);
                            break;
                        }
                    }
                }
            }
        }
    }*/
    
    @Asynchronous
    public String[] AIget(String uri, String date, String time)
    {
        if(cntrl.selectAI(uri,date,time) != null)
            return cntrl.selectAI(uri,date,time); 
        return null;
    }
    
    @Asynchronous
    public void updateAI(String uri, String date, String time, int count, int total)
    {
        cntrl.insertAI(uri,date,time,count,total);       
    }
}
