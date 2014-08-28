package startup;

import connection.Network;
import java.io.IOException;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class NewClass
{
    public NewClass() throws IOException
    {
        Network netInterface = new Network();
        //netInterface.
    }
    
    
    
}
