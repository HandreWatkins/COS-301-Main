package analisis;

import Util.UrlLinker;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class Interface
{
    //Var set
    Boolean singleton = false;
    AtomicBoolean start = new AtomicBoolean(false);
    ExecutorService excutor = Executors.newFixedThreadPool(1000);
    ArrayList<Runnable> worker = new ArrayList<>();
    SSLSocketFactory socketCon;
    
    public Interface()
    {
        socketCon = (SSLSocketFactory) SSLSocketFactory.getDefault();
    }
    
    public void startConnection() throws IOException
    {
        SSLSocket lSocket = null;
        try {
        lSocket = (SSLSocket) socketCon.createSocket("localhost", 8280);   
        } catch (Exception e)
        {
            System.out.println("con");
        }
        //SSLSocket lSocket = (SSLSocket) socketCon.createSocket("localhost", 8280);
        printSocketInfo(lSocket);
        lSocket.startHandshake();
    }
    
    //testCode
       private static void printSocketInfo(SSLSocket s)
       {
            System.out.println("Socket class: "+s.getClass());
            System.out.println("   Remote address = "
               +s.getInetAddress().toString());
            System.out.println("   Remote port = "+s.getPort());
            System.out.println("   Local socket address = "
               +s.getLocalSocketAddress().toString());
            System.out.println("   Local address = "
               +s.getLocalAddress().toString());
            System.out.println("   Local port = "+s.getLocalPort());
            System.out.println("   Need client authentication = "
               +s.getNeedClientAuth());
            SSLSession ss = s.getSession();
            System.out.println("   Cipher suite = "+ss.getCipherSuite());
            System.out.println("   Protocol = "+ss.getProtocol());
    }

    public void analisHandle(UrlLinker data) throws IOException 
    {
        if(!hasStart())
        {
            analisListen();
        } 
        
        if(data != null)
        {
            Runnable newTest = new Generator(data);
            worker.add(newTest);
        }
    }
    
    public void analisListen() throws IOException
    {
        if(!singleton)
        {
            startConnection();
            start.set(true);
            singleton = true;
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while(start.get())
                    {
                        if(!worker.isEmpty())
                        {
                            excutor.execute(worker.remove(0));
                        }
                        else
                        {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }).start();
        }
    }

    private Boolean hasStart()
    {
            return start.get();
    }

    private class Generator implements Runnable
    {
        //UrlLinker urlData;
        public Generator(UrlLinker link)
        {
            //urlData = link;
            
        }
        
        @Override
        public void run()
        {

        }
    }
}