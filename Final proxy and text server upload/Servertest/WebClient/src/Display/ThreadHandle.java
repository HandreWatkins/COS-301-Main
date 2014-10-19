package Display;

import HttpClient.HttpClientCreation;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadHandle
{
    static Boolean runner = false;
    final String [] ext = {"/echo","/failures","/slow","/varying"};
    ExecutorService executorService = null;
    
    public ThreadHandle(int val)
    {
        executorService = Executors.newFixedThreadPool(val);
    }
    
    public void newThread()
    {
        runner = true;
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(runner)
                {
                    executorService.execute(new threadStarer());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ThreadHandle.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
        
    }
    
    public void stopThread()
    {
        runner = false;
        executorService.shutdown();
    }
    
    private class threadStarer implements Runnable
    {
        @Override
        public void run() 
        {
            try {
                String http = "localhost:8080/j2eeReverceproxy/proxy/TestServerV2"+pickOption();
                HttpClientCreation httpClient = new HttpClientCreation(http);
                httpClient.nReqest();
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadHandle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ThreadHandle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        private String pickOption()
        {
             Random randomval = new Random();
             int random = randomval.nextInt(4);
             return ext[random];
        }
    }
}
