package server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class servertest
{
    int PORT = 5254;
    server serv;
    public void main() throws IOException
    {
        serv = new server();
                  new Thread(new Runnable() {
                @Override
                public void run() {
                    serv.start();
                }
            }).start();
        
        System.out.println("test start server");
        
    }
    
    public void stop()
    {
        serv.stop();
    }


    private class server
    {
        ServerSocket serv;
        Boolean start = false;        
        public server() throws IOException 
        {
            serv = new ServerSocket(PORT);
        }
        
        public void start()
        {
            System.out.println("test start server");
            start = true;
            
            new Thread(new Runnable() {

                @Override
                public void run()
                {
                    while(start)
                    {
                        try 
                        {
                            Socket live = serv.accept();
                            System.out.println(live.getInputStream().read());
                            handle(live);
                            
                        } 
                        catch (IOException ex) {
                            Logger.getLogger(servertest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }).start();
        }
        
        private void handle(Socket sockout) throws IOException
        {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sockout.getOutputStream()));
            out.write("true");

        }
        public void stop()
        {
            start = false;
        }
        
    }
    
}
