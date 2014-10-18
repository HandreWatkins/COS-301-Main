package Display;

import HttpClient.HttpClientCreation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Display_make extends JFrame implements ActionListener
{
    JPanel panel;
    JButton mybutton;
    JButton mybutton2;
    JButton mybutton3;
    JButton mybutton4;
    JButton mybutton5;
    JLabel lable;
    
    public Display_make()
    {
        panel = new JPanel();
        mybutton = new JButton("OK");
        mybutton.addActionListener(this);
        mybutton2 = new JButton("Echo");
        mybutton2.addActionListener(this);
        mybutton3 = new JButton("Fail");
        mybutton3.addActionListener(this);
        mybutton4 = new JButton("Slow");
        mybutton4.addActionListener(this);
        lable = new JLabel();
        mybutton5 = new JButton("Varying");
        mybutton5.addActionListener(this);
        lable = new JLabel();
        
        panel.add(mybutton);
        panel.add(mybutton2);
        panel.add(mybutton3);
        panel.add(mybutton4);
        panel.add(mybutton5);
        panel.add(lable);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        ThreadHandle handel = new ThreadHandle(10);
        if(e.getSource() == mybutton)
        {
            if(lable.getText() != "my Button")
            {
                lable.setText("my Button");
                handel.newThread();
            }
            else
            {
                lable.setText("Still running");
                handel.stopThread();
            }
                
        }
        
        if(e.getSource() == mybutton2)
        {
            try {
                String http = "localhost:8080/j2eeReverceproxy/proxy/TestServerV2/echo";
                HttpClientCreation httpClient = new HttpClientCreation(http);
                httpClient.nReqest();
            } catch (InterruptedException ex) {
                Logger.getLogger(Display_make.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource() == mybutton3)
        {
            try {
                String http = "localhost:8080/j2eeReverceproxy/proxy/TestServerV2/failure";
                HttpClientCreation httpClient = new HttpClientCreation(http);
                httpClient.nReqest();
            } catch (InterruptedException ex) {
                Logger.getLogger(Display_make.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource() == mybutton4)
        {
            try {
                String http = "localhost:8080/j2eeReverceproxy/proxy/TestServerV2/slow";
                HttpClientCreation httpClient = new HttpClientCreation(http);
                httpClient.nReqest();
            } catch (InterruptedException ex) {
                Logger.getLogger(Display_make.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource() == mybutton5)
        {
            try {
                String http = "localhost:8080/j2eeReverceproxy/proxy/TestServerV2/varying";
                HttpClientCreation httpClient = new HttpClientCreation(http);
                httpClient.nReqest();
            } catch (InterruptedException ex) {
                Logger.getLogger(Display_make.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
