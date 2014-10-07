package connection;

import Analisis.AnalisisInterface;
import Analisis.ClientRule.ClientRule;
import Analisis.Smart.AIInterface;
import database.DatabaseControl;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.Asynchronous;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup",
            propertyValue = "Queue"),
    @ActivationConfigProperty(propertyName = "destinationType",
            propertyValue = "javax.jms.Queue")
})

public class Network implements MessageListener
{
    volatile Queue <String> inmessage;
    AnalisisInterface testClient = new ClientRule();
    AnalisisInterface testAI = new AIInterface();
    static final Logger logger = Logger.getLogger("SimpleMessageBean");
    DatabaseControl cntrolBD = null;
    
    public Network() throws IOException, SQLException, ClassNotFoundException
    {
       cntrolBD = new DatabaseControl();
    }

    @Override
    public void onMessage(Message message)
    {
       if (message instanceof TextMessage)
       {
           try 
           {
               
               String mess =  message.getBody(String.class);
               logger.log(Level.INFO,"MESSAGE BEAN: Message received: {0}",mess);
               inmessage.add(mess);
               messageLoop();
               
           } catch (JMSException ex) 
           {
               Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }
    
    @Asynchronous
    private void messageLoop()
    {
        while(!inmessage.isEmpty())
        {
            String message = inmessage.remove();
            String [] parced = message.split(",");
            
            if(!testClient.testRequest(parced) && !testAI.testRequest(parced))
            {
                cntrolBD.mainDB(message, message, message);
            }
            else
            {
                cntrolBD.disDB(message, message, message, message);
            }
        }
    }
}
