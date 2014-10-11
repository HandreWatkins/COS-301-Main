package database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.inject.Singleton;

public class DBConnection
{
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private final String url ;
    private final String user; 
    private final String password ;
    
    @Singleton 
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
                System.out.println("Database is connected\n"+rs.getString(1));
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
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBConnection.class.getName());
            lgr.log(Level.WARNING, ex.getMessage(), ex);
        }
    }
    public boolean testconn()
    {
        return con != null;       
    }
     
     
     /*MAIN ACTIVITY*/
    @Asynchronous
     public boolean insertMainActivity(String ip,String uri,double respondtime)  
     {
         PreparedStatement pst;
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
            return true;   
      } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
     }
     
     @Asynchronous
     public String[] selectMainActivity() 
     {
         String [] temp = null ;
         //System.out.println("jksdhfkjjsdkhfkjashdskjf");
         try 
        {
             st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM MainActivity");
            rs.next();
            
            int c = rs.getInt(1);
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
              i++;
            }
            rs.close();
            st.close();
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException at select in Main Activity.");
            System.err.println(se.getMessage());
          }
         return temp;
     }
     
     @Asynchronous
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
            System.out.println("New distress: ip, uri and respond time inserted");
         return true;
         } catch (SQLException ex) {
             
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
     }
     
     @Asynchronous
     public String[] selectDistress() 
     {
        String [] temp = null ;
        try 
        {
            st = con.createStatement();
            rs = st.executeQuery("SELECT count(*) FROM distressedresources");
            rs.next();
            
            int c = rs.getInt(1);
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

              i++;
            }
            rs.close();
            st.close();
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException at selecting in distressed resources.");
            System.err.println(se.getMessage());
          }
         return temp;
     }
     
     @Asynchronous
     /*RULES   CRUD needed*/
     public boolean updateRules(String user_requesting,String uri,double expected_time) 
     {
         //String temp;
         boolean state = false;
         try 
        {
            Statement st = con.createStatement();

            st.executeUpdate("UPDATE rules set uri = '"+uri+"', expected_time="+expected_time+" where user_requesting ='"+user_requesting+"'");
            rs.close();
            st.close();
            state = true;
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException at update in bookmark.");
            System.err.println(se.getMessage());
          }
            return state;
     }
     
     @Asynchronous
     public boolean deleteRules(String user_requesting,String uri,double expected_time) 
     {
         //String temp;
         boolean state = false;
         try 
        {
             try (Statement st = con.createStatement()) {
                 st.executeUpdate("DELETE from rules where user_requesting = '"+user_requesting+"'and expected_time="+expected_time+" and uri = '"+uri+"'");
                 rs.close();
             }
            state = true;
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException at deleting in rules.");
            System.err.println(se.getMessage());
          }
            return state;
     }
     
     @Asynchronous
     public boolean insertRules(String user,String uri, double expt) 
     {
         PreparedStatement pst;
         String stm = "INSERT INTO rules" +
            		" (user_requesting, expected_time, uri) " +
            		" VALUES(?,?, ?)";
        try 
        {
            pst = con.prepareStatement(stm);
            pst.setString(1, user);
            pst.setDouble(2, expt);
            pst.setString(3, uri);
            pst.executeUpdate();
            System.out.println("New rules: user, uri, expected time inserted");
            return true;
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
     }
     
    @Asynchronous
    public String[] selectRules(String uri,double expt) 
    {
        String [] temp = null;
        try 
        {
            try (Statement st = con.createStatement())
            {
                int i = 1;
                temp = new String[i];
                rs = st.executeQuery("SELECT * FROM rules WHERE uri like '%"+uri+"%'");
                int c = 0;
                rs.next();
                
                temp[c]  = rs.getString ("rules_id")+",";
                temp[c] += rs.getString ("date_time")+",";
                temp[c] += rs.getString ("user_requesting")+",";
                temp[c] += rs.getString ("expected_time")+",";
                temp[c] += rs.getString ("uri");

                rs.close();
            }
        }
        catch (SQLException se) {
            System.err.println("Threw a SQLException when retrieving the rules.");
            System.err.println(se.getMessage());
        }
        return temp;
    }
     
     /*USER*/
    @Asynchronous
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
     
    @Asynchronous
     public boolean selectUser(String user, String pass)
     {
        String temp;
        boolean state = false;
        try 
        {
            try (Statement st = con.createStatement()) {
                rs = st.executeQuery("SELECT * FROM users WHERE username = '"+user+"' AND password = '"+pass+"'");
                
                while ( rs.next() )
                {
                    temp = rs.getString ("username")+",";
                    temp += rs.getString ("password");
                    if(temp.equals(user+","+pass))
                        state = true;
                }
                rs.close();
            }
        }
        catch (SQLException se)
        {
            System.err.println("Threw a SQLException at user identification.");
            System.err.println(se.getMessage());
        }
            return state;
     }
     
     
     /* Bookmark   CRUD  */
     @Asynchronous
     public String insertBookmark(String user_requesting,String discription) 
     {
         
         PreparedStatement pst;
         String stm = "INSERT INTO Bookmark" +
            		" (user_requesting, discription) " +
            		" VALUES(?,?)";
            try {
                pst = con.prepareStatement(stm);
            
            
            pst.setString(1, user_requesting);
            pst.setString(2, discription);
            pst.executeUpdate();
            System.out.println("New distress: user and discription inserted");
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM bookmark WHERE user_requesting = '"+user_requesting+"' and discription = '"+discription+"'");
            rs.next();
         return rs.getString("date_time");
         } catch (SQLException ex) {
             
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
     }
     
     @Asynchronous
     public boolean updateBookmark(String user_requesting,String discription,String date_time) 
     {
         boolean state = false;
         try 
        {
             try (Statement st = con.createStatement()) {
                 st.executeUpdate("UPDATE bookmark set discription = '"+discription+"' where user_requesting ='"+user_requesting+"'");
                 rs.close();
             }
            state = true;
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException at update in bookmark.");
            System.err.println(se.getMessage());
          }
            return state;
     }
     
     @Asynchronous
     public boolean deleteBookmark(String user_requesting,String discription,String date_time) 
     {
         //String temp;
         boolean state = false;
         try 
        {
             try (Statement st = con.createStatement()) {
                 st.executeUpdate("DELETE from bookmark where user_requesting = '"+user_requesting+"' and date_time='"+date_time+"' and discription = '"+discription+"'");
                 rs.close();
             }
            state = true;
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException at deleting in bookmark.");
            System.err.println(se.getMessage());
          }
            return state;
     }
     
     @Asynchronous
     public String [] selectBookmark(String user_requesting,String discription,String date_time)
    {
        String [] temp = null;
        try 
        {
            try (Statement st = con.createStatement()) {
                rs = st.executeQuery("SELECT count(*) FROM bookmark WHERE user_requesting = '"+user_requesting+"' and discription = '"+discription+"' and date_time ='"+date_time+"'");
                rs.next();
                int c = rs.getInt(1);
                temp = new String[c];
                int i = 0;
                rs = st.executeQuery("SELECT * FROM bookmark WHERE user_requesting = '"+user_requesting+"' and discription = '"+discription+"' and date_time ='"+date_time+"'");
                
                while ( rs.next() )
                {
                    temp[i]  = rs.getString ("bookmark_id")+",";
                    temp[i]  += rs.getString ("date_time")+",";
                    temp[i] += rs.getString ("user_requesting")+",";
                    temp[i] += rs.getString ("discription");
                }
                
                rs.close();
            }
        }
        catch (SQLException se) {
        System.err.println("Threw a SQLException at selecting in bookmark.");
        System.err.println(se.getMessage());
        }
        return temp;
    }
     
}
