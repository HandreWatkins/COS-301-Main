package connection;

import Analisis.AnalisisInterface;
import Analisis.ClientRule.ClientRule;
import Analisis.Smart.AIInterface;
import database.DatabaseControl;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
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
    AnalisisInterface testClient = null;
    AnalisisInterface testAI = null;
    static final Logger logger = Logger.getLogger("SimpleMessageBean");
    DatabaseControl cntrolBD = null;
    
    public Network() throws IOException, SQLException, ClassNotFoundException, Exception
    {
       inmessage = new ConcurrentLinkedQueue<>();
       cntrolBD = new DatabaseControl();
       testClient = new ClientRule(cntrolBD);
       testAI = new AIInterface(cntrolBD);
    }

    @Override
    public void onMessage(Message message)
    {
       if (message instanceof TextMessage)
       {
           try 
           {
               String mess =  message.getBody(String.class);
               message.acknowledge();
               inmessage.add(mess);
               messageLoop();
           } catch (JMSException ex) 
           {
               Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
           } catch (UnknownHostException ex) {
               Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }
    
    private void messageLoop() throws UnknownHostException
    {
        while(!inmessage.isEmpty())
        {
            String message = inmessage.remove();
            logger.log(Level.INFO,"MESSAGE BEAN: Message received: {0}",message);
            String [] parced = message.split(",");
            
            int serverTest = Integer.parseInt(parced[1]);
            
            int ix = parced[0].indexOf("//")+2;//, parced[0].indexOf("/")+1);
            int ic = parced[0].indexOf("/",ix);
            char testS = parced[0].charAt(ic);
            String serverText = parced[0].substring(ix, ic);
            String uriS = parced[0].substring(ix+serverText.length());
            
            if(serverTest >= 200 && serverTest < 300 )
            {
                if(testClient.testRequest(parced))
                {
                    if(testAI.testRequest(parced))
                    {
                        cntrolBD.mainDB(serverText,uriS, parced[2]);
                    }
                }
            }
            else
            {
                cntrolBD.disDB(serverText,uriS, parced[1], "99999");
            }
        }
    }
}
