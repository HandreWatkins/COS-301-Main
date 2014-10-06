package analisis;

import Util.UrlLinker;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
public class Interface
{ 
    private JMSContext jmsContext;
    
    @Resource
    InitialContext ctx;
       
    private Queue queue;
    javax.jms.ConnectionFactory cf;
    
    public Interface() {
        try {
            ctx = new InitialContext();
            cf = (ConnectionFactory) ctx.lookup("jms/__defaultConnectionFactory");
            jmsContext = cf.createContext();
            queue = (Queue) ctx.lookup("Queue");
            System.out.println("");
        } catch (NamingException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void analisHandle(UrlLinker data)
    {
        jmsContext.createProducer().send(queue, data.getNURL());
    }
}
