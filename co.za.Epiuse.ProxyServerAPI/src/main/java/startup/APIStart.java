package startup;

import java.io.IOException;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateful;

@Singleton
@Stateful
@Startup

public class APIStart
{
    public APIStart() throws IOException
    {
        super();
        
    }

}
