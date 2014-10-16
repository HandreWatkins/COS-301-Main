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
        System.out.println();
        a.insertRules("admin", "http://www.google.com", 10.512);
        String[] jk = a.selectRules("http://www.google.com", 10.512);
        System.out.println(a.updateRules("admin", "http://www.google.com", 11));
        System.out.println(a.deleteRules("admin", "http://www.google.com", 11));
        /*for (String jk1 : jk) {
            System.out.print(jk1);
        }*/
        System.out.println(jk[0]);
        
         System.out.println(a.selectUser("admin", "admin"));
         String [] jk2 = a.selectMainActivity();
        // a.insertUser("boy", "boy");
     /* for (int i = 0; i < 1000; i++) {
                System.out.println(jk2[i]);
          }*/

         for (int i = 0; i < 100; i++) {
                a.insertMainActivity("111.101.11.1", "http://www.cs.up.ac.za", 20+2*i);
          }
        
      /*  String jb =a.insertBookmark("admin",  "my special");
        String[] jk1 = new String[12];
         jk1=a.selectBookmark("admin","my special", jb);
        System.out.println(jk1[0]);*/
        /*System.out.println(a.updateBookmark("admin", "http://www.google.com", 11));
        System.out.println(a.deleteBookmark("admin", "http://www.google.com", 11));
        */
        //DBConnection b = new DBConnection(url,user,password);
        //for(int i = 0;i<9999;i++)
            //DBConnection a = new DBConnection(url,user,password);
        //a.insertMainActivity("111.101.11.1", "http://www.cs.up.ac.za", 55.313);
       // a.insertDistress("139.156.123.1", "http://www.google.com", 555.545, 456.254);
       // b.newDistress("139.156.555.1", "http://www.google.com", 11.342, 17.254);
        
        
    }
}
