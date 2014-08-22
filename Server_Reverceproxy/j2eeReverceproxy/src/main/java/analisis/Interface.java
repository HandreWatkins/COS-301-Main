package analisis;

import Util.UrlLinker;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Interface
{
    //Var set
    Boolean singleton = false;
    AtomicBoolean start = new AtomicBoolean(false);
    ExecutorService excutor = Executors.newFixedThreadPool(1000);
    ArrayList<Runnable> worker = new ArrayList<>();
    Tool anilTool = new Tool();
    
    public Interface(){}

    public void analisHandle(UrlLinker data) 
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
    
    public void analisListen()
    {
        if(!singleton)
        {
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
        UrlLinker urlData;
        public Generator(UrlLinker link)
        {
            urlData = link;
        }
        
        @Override
        public void run()
        {
            if(anilTool.headercheck(urlData))
            {
                if(anilTool.check_rules(urlData))
                {
                    anilTool.autocheck(urlData);
                }
            }
        }
    }
}
