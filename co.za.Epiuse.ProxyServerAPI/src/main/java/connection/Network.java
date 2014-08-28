package connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.ejb.Asynchronous;
import javax.net.ssl.SSLServerSocketFactory;

public class Network 
{
    SSLServerSocketFactory  slServ;
    ServerSocket ss;
    boolean server = false;
    
    public Network() throws IOException
    {
        slServ = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        ss = slServ.createServerSocket(8280);
        serverin();
    }
    
    @Asynchronous
    private void serverin() throws IOException
    {
        while(server)
        {
            Socket s = ss.accept();
        }
    }
    
    
    
}
