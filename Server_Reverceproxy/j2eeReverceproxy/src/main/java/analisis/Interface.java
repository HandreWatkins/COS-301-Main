package analisis;

import Util.UrlLinker;
import java.util.Queue;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;

@Stateless
public class Interface
{ 
    @Resource(lookup = "jms/queue/myqueue")
    private Queue queue;

    @Inject
    private JMSContext jmsContext;
    
    public void analisHandle(UrlLinker data)
    {
        jmsContext.createProducer().send((Destination) queue, data.getNURL());
    }
}
