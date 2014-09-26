package connection;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup",
            propertyValue = "jms/MyQueue"),
    @ActivationConfigProperty(propertyName = "destinationType",
            propertyValue = "javax.jms.Queue")
})
public class Network implements MessageListener
{
    static final Logger logger = Logger.getLogger("SimpleMessageBean");
    
    public Network() throws IOException
    {
        
    }

    @Override
    public void onMessage(Message message)
    {
       if (message instanceof TextMessage)
       {
           try 
           {
               logger.log(Level.INFO,"MESSAGE BEAN: Message received: {0}", message.getBody(String.class));
               
           } catch (JMSException ex) 
           {
               Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }
}
