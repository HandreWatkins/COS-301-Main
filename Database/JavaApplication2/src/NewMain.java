/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        
         String url = "jdbc:postgresql://localhost:5432/dbMonitor";
         String user = "postgres";
         String password = "1";
        DBConnection a = new DBConnection(url,user,password);
        //for(int i = 0;i<9999;i++)
            //DBConnection a = new DBConnection(url,user,password);
        //a.newIpInsert("139.156.123.1"+i, "http://www.google.com", 10.512+i);}
        a.newDistress("139.156.123.1", "http://www.google.com", 10.512, 17.254);
    }
}
