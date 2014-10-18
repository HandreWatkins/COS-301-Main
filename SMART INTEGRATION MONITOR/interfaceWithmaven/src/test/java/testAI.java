
import Analisis.Smart.AIInterface;
import database.DatabaseControl;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.fail;
import org.junit.Test;

public class testAI
{
    @Test
    public void testJDBC() throws ClassNotFoundException
    {
        try {
            DatabaseControl contDB = new DatabaseControl();
            
            if(contDB != null)
                return;
            else
                fail("no database connection");
            
        } catch (Exception ex) {
            Logger.getLogger(testAI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testFunction()
    {
        try {
            DatabaseControl contDB = new DatabaseControl();
            AIInterface aitest = new AIInterface(contDB);
            double [] testdouble = {5,6, 7 ,8};
            if(aitest.standerd(testdouble, 4) == aitest.standerd(testdouble, 4))
            {
                return;
            }
            else
            {
                fail("value inconsistant");
            }
            
        } catch (Exception ex) {
            Logger.getLogger(testAI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testParce()
    {
        try {
            DatabaseControl contDB = new DatabaseControl();
            AIInterface aitest = new AIInterface(contDB);
            String [] testdata = {"1,2,3,6"};
            
            if(aitest.dataparce(testdata)[0] == 6)
            {
                return;
            }
            else
            {
                fail("Parce fail");
            }
        } catch (Exception ex) {
            Logger.getLogger(testAI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
