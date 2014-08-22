/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
        

        private Connection con = null;
        private Statement st = null;
        private ResultSet rs = null;
        private final String url ;
        private final String user; 
        private final String password ;
    public DBConnection(String urlT,String userT,String passwordT) throws SQLException, ClassNotFoundException
    {
         
        Class.forName("org.postgresql.Driver");
        this.url = urlT;
        this.user = userT;
        this.password = passwordT;
        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                //System.out.println("Database is connected\n"+rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBConnection.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            closeDB(null,st,rs);
        }
       
        
    }
     public final void closeDB( Connection conT, Statement stT , ResultSet rsT)
        {
            try {
                if (rsT != null) {
                    rsT.close();
                }
                if (stT != null) {
                    stT.close();
                }
                if (conT != null) {
                    conT.close();
                }
                //System.out.println("Connected");

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(DBConnection.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
     
     
     /*MAIN ACTIVITY*/
     public boolean insertMainActivity(String ip,String uri,double respondtime)  
     {
         PreparedStatement pst = null;
         String stm = "INSERT INTO mainactivity" +
            		" (ip, uri, \"responseTime\") " +
            		" VALUES(?,?, ?)";
            try {
                pst = con.prepareStatement(stm);
            
            
                pst.setString(1, ip);
                pst.setString(2, uri);
                pst.setDouble(3, respondtime);
                pst.executeUpdate();
                System.out.println("New ip, uri and respond time inserted");
                //System.out.println("successful inserted");
               // closeDB(con,st,rs);
            return true;   
      } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
     }
     public String[] selectMainActivity() 
     {
         String [] temp = null ;
         //System.out.println("jksdhfkjjsdkhfkjashdskjf");
         try 
        {
           // System.out.println("jksdhfkjjsdkhfkjashdskjf");
             st = con.createStatement();
            //ResultSet rs = st.executeQuery("SELECT count(uri) as rulescount FROM rules ORDER BY rules_id");
            //rs.next();
            //System.out.println("jksdhfkjjsdkhfkjashdskjf");
            ;//rs.getInt(1);
            

            rs = st.executeQuery("SELECT count(*) FROM MainActivity");
            rs.next();
            
            int c = rs.getInt(1);
            //System.out.println(c);
            temp = new String[c];
            int i = 0;
            rs = st.executeQuery("SELECT * FROM MainActivity");
            while ( rs.next() )
            {

              temp[i]  = rs.getString ("mainactivity_id")+",";
              temp[i] += rs.getString ("create_date")+",";
              temp[i] += rs.getString ("ip")+",";
              temp[i] += rs.getString ("uri")+",";
              temp[i] += rs.getString ("responseTime");
              //listOfBlogs.add(blog);
              i++;
            }
            rs.close();
            st.close();
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException creating the list of blogs.");
            System.err.println(se.getMessage());
          }
         return temp;
     }
     
     
     /*DESTRESS RESOURCES*/
     public boolean insertDistress(String ip,String uri,double respondtime, double expt) 
     {
         PreparedStatement pst;
         String stm = "INSERT INTO distressedresources" +
            		" (ip, uri, \"responsetime\",time_expected) " +
            		" VALUES(?,?, ?,?)";
            try {
                pst = con.prepareStatement(stm);
            
            
            pst.setString(1, ip);
            pst.setString(2, uri);
            pst.setDouble(3, respondtime);
            pst.setDouble(4, expt);
            pst.executeUpdate();
            System.out.println("New new distress: ip, uri and respond time inserted");
         return true;
         } catch (SQLException ex) {
             
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
     }
     public String[] selectDistress() 
     {
         String [] temp = null ;
         //System.out.println("jksdhfkjjsdkhfkjashdskjf");
         try 
        {
           // System.out.println("jksdhfkjjsdkhfkjashdskjf");
             st = con.createStatement();
            //ResultSet rs = st.executeQuery("SELECT count(uri) as rulescount FROM rules ORDER BY rules_id");
            //rs.next();
            //System.out.println("jksdhfkjjsdkhfkjashdskjf");
            ;//rs.getInt(1);
            

            rs = st.executeQuery("SELECT count(*) FROM distressedresources");
            rs.next();
            
            int c = rs.getInt(1);
            //System.out.println(c);
            temp = new String[c];
            int i = 0;
            rs = st.executeQuery("SELECT * FROM distressedresources");
            while ( rs.next() )
            {

              temp[i]  = rs.getString ("distressedresources_id")+",";
              temp[i] += rs.getString ("last_update")+",";
              temp[i] += rs.getString ("time_expected")+",";
              temp[i] += rs.getString ("uri")+",";
              temp[i] += rs.getString ("responseTime");
              temp[i] += rs.getString ("ip");
              //listOfBlogs.add(blog);
              i++;
            }
            rs.close();
            st.close();
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException creating the list of blogs.");
            System.err.println(se.getMessage());
          }
         return temp;
     }
     
     
     /*RULES*/
     public String[] selectRules(String uri,double expt) 
     {
         String [] temp = null;
         //System.out.println("jksdhfkjjsdkhfkjashdskjf");
         try 
        {
           // System.out.println("jksdhfkjjsdkhfkjashdskjf");
            Statement st = con.createStatement();
            //ResultSet rs = st.executeQuery("SELECT count(uri) as rulescount FROM rules ORDER BY rules_id");
            //rs.next();
            //System.out.println("jksdhfkjjsdkhfkjashdskjf");
            int i = 1;//rs.getInt(1);
            temp = new String[i];

            rs = st.executeQuery("SELECT * FROM rules WHERE uri = '"+uri+"' AND expected_time = "+expt);
            int c = 0;

            while ( rs.next() )
            {

              temp[c]  = rs.getString ("rules_id")+",";
              temp[c] += rs.getString ("date_time")+",";
              temp[c] += rs.getString ("user_requesting")+",";
              temp[c] += rs.getString ("expected_time")+",";
              temp[c] += rs.getString ("uri");
              //listOfBlogs.add(blog);
              c++;
            }
            rs.close();
            st.close();
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException creating the list of blogs.");
            System.err.println(se.getMessage());
          }
         return temp;
     }
     
     /*USER*/
     public boolean insertUser(String user, String pass)
     {
         PreparedStatement pst;
         String stm = "INSERT INTO users" +
            		" (username, password) " +
            		" VALUES(?,?)";
            try {
                pst = con.prepareStatement(stm);
            
            
            pst.setString(1, user);
            pst.setString(2, pass);
            pst.executeUpdate();
            System.out.println("New user: username and password");
         return true;
         } catch (SQLException ex) {
             
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
     }
     public boolean selectUser(String user, String pass)
     {
         String temp;
         boolean state = false;
         //System.out.println("jksdhfkjjsdkhfkjashdskjf");
         try 
        {
           // System.out.println("jksdhfkjjsdkhfkjashdskjf");
            Statement st = con.createStatement();
            //ResultSet rs = st.executeQuery("SELECT count(uri) as rulescount FROM rules ORDER BY rules_id");
            //rs.next();
            //System.out.println("jksdhfkjjsdkhfkjashdskjf");
            //int i = 1;//rs.getInt(1);
            

            rs = st.executeQuery("SELECT * FROM users WHERE username = '"+user+"' AND password = '"+pass+"'");
            //int c = 0;

            while ( rs.next() )
            {

              temp  = rs.getString ("username")+",";
              temp += rs.getString ("password");
              if(temp.equals(user+","+pass))
                  state = true;
             // else if(temp)
              //listOfBlogs.add(blog);
              //c++;
            }
            rs.close();
            st.close();
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException creating the list of blogs.");
            System.err.println(se.getMessage());
          }
            return state;
     }
     
}
