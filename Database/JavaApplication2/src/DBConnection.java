/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
        

        private Connection con = null;
        
        private final String url ;
        private final String user; 
        private final String password ;
    public DBConnection(String urlT,String userT,String passwordT) throws SQLException, ClassNotFoundException
    {
          Statement st = null;
         ResultSet rs = null;
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
                //System.out.println("Connected");

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(DBConnection.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
     /*AI*/
     
     public boolean insertAI(String uri,String date,String time,int count,int total)  
     {
         PreparedStatement pst;
         String stm = "INSERT INTO mainactivity" +
            		" (_date,_time, uri, count,total) " +
            		" VALUES(?,?,?,?,?)";
            try {
                pst = con.prepareStatement(stm);
            
            
                pst.setString(1, date);
                pst.setString(2, time);
                pst.setString(3, uri);
                pst.setInt(4, count);
                pst.setInt(5, total);
                pst.executeUpdate();
                System.out.println("New AI inserted");
                //System.out.println("successful inserted");
               // closeDB(con,st,rs);
            return true;   
      } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
     }
     public String[] selectAI(String uri,String date,String time) 
     {
         String [] temp = null ;
         //System.out.println("jksdhfkjjsdkhfkjashdskjf");
         try 
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(*) FROM ai where uri='"+uri+"' AND _date='"+date+"' AND _time='"+time+"'");
            rs.next();
            
            int c = rs.getInt(1);
            temp = new String[c];
            int i = 0;
            rs = st.executeQuery("SELECT count(*) FROM ai where uri='"+uri+"' AND _date='"+date+"' AND _time='"+time+"'");
            while ( rs.next() )
            {

              temp[i]  = rs.getString ("ai_id")+",";
              temp[i] += rs.getString ("_date")+",";
              temp[i] += rs.getString ("_time")+",";
              temp[i] += rs.getString ("uri")+",";
              temp[i] += rs.getString ("count")+",";
              temp[i] += rs.getString ("total");
              i++;
            }
            rs.close();
            st.close();
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException at select in AI.");
            System.err.println(se.getMessage());
          }
         return temp;
     }
     
     /*MAIN ACTIVITY*/
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
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(*) FROM MainActivity");
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
     public String[] selectDistress() 
     {
         String [] temp = null ;
         try 
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(*) FROM distressedresources");
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
              //listOfBlogs.add(blog);
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
     
     
     /*RULES   CRUD needed*/
     public boolean updateRules(String user_requesting,String uri,double expected_time) 
     {
         //String temp;
         boolean state = false;
         try 
        {
            Statement st = con.createStatement();

            st.executeUpdate("UPDATE rules set uri = '"+uri+"', expected_time="+expected_time+" where user_requesting ='"+user_requesting+"'");
            
            st.close();
            state = true;
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException at update in bookmark.");
            System.err.println(se.getMessage());
          }
            return state;
     }
     public boolean deleteRules(String user_requesting,String uri,double expected_time) 
     {
         //String temp;
         boolean state = false;
         try 
        {
            Statement st = con.createStatement();

            st.executeUpdate("DELETE from rules where user_requesting = '"+user_requesting+"'and expected_time="+expected_time+" and uri = '"+uri+"'");
            //int c = 0;

            
            //con.commit();
            
            st.close();
            state = true;
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException at deleting in rules.");
            System.err.println(se.getMessage());
          }
            return state;
     }
     public boolean insertRules(String user,String uri, double expt) 
     {
         PreparedStatement pst;
         String stm = "INSERT INTO rules" +
            		" (user_requesting, expected_time, uri) " +
            		" VALUES(?,?, ?)";
            try {
                pst = con.prepareStatement(stm);
            
            
            pst.setString(1, user);
            pst.setDouble(2, expt);
            pst.setString(3, uri);
            pst.executeUpdate();
            System.out.println("New rules: user, uri, expected time inserted");
         return true;
         } catch (SQLException ex) {
             
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
     }
     
     public String[] selectRules(String uri,double expt) 
     {
         String [] temp = null;
         try 
        {
            Statement st = con.createStatement();

            int i = 1;
            temp = new String[i];

            ResultSet rs = st.executeQuery("SELECT * FROM rules WHERE uri like '%"+uri+"%' AND expected_time = "+expt);
            int c = 0;

            rs.next(); 


              temp[c]  = rs.getString ("rules_id")+",";
              temp[c] += rs.getString ("date_time")+",";
              temp[c] += rs.getString ("user_requesting")+",";
              temp[c] += rs.getString ("expected_time")+",";
              temp[c] += rs.getString ("uri");
              //listOfBlogs.add(blog);

            rs.close();
            st.close();
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException when retrieving the rules.");
            System.err.println(se.getMessage());
            //return null;
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
         try 
        {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM users WHERE username = '"+user+"' AND password = '"+pass+"'");
            //int c = 0;

            while ( rs.next() )
            {
              //temp  = rs.getString ("user_id")+",";          
              temp = rs.getString ("username")+",";
              temp += rs.getString ("password");
              if(temp.equals(user+","+pass))
                  state = true;
            }
            rs.close();
            st.close();
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException at user identification.");
            System.err.println(se.getMessage());
          }
            return state;
     }
     /* Bookmark   CRUD  */
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
            ResultSet rs = st.executeQuery("SELECT * FROM bookmark WHERE user_requesting = '"+user_requesting+"' and discription = '"+discription+"'");
            rs.next();
         return rs.getString("date_time");
         } catch (SQLException ex) {
             
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
     }
     public boolean updateBookmark(String user_requesting,String discription,String date_time) 
     {
         //String temp;
         boolean state = false;
         try 
        {
            Statement st = con.createStatement();

            st.executeUpdate("UPDATE bookmark set discription = '"+discription+"' where user_requesting ='"+user_requesting+"'");
            //int c = 0;

            
            //con.commit();
            st.close();
            state = true;
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException at update in bookmark.");
            System.err.println(se.getMessage());
          }
            return state;
     }
     public boolean deleteBookmark(String user_requesting,String discription,String date_time) 
     {
         //String temp;
         boolean state = false;
         try 
        {
            Statement st = con.createStatement();

            st.executeUpdate("DELETE from bookmark where user_requesting = '"+user_requesting+"' and date_time='"+date_time+"' and discription = '"+discription+"'");
            //int c = 0;

            
            //con.commit();
            st.close();
            state = true;
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException at deleting in bookmark.");
            System.err.println(se.getMessage());
          }
            return state;
     }
     public String [] selectBookmark(String user_requesting,String discription,String date_time)
     {
         String [] temp = null;
         //boolean state = false;
         try 
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT count(*) FROM bookmark WHERE user_requesting = '"+user_requesting+"' and discription = '"+discription+"' and date_time ='"+date_time+"'");
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
              //if(temp.equals(user+","+pass))
                  
            }
            //state = true;
            rs.close();
            st.close();
          }
          catch (SQLException se) {
            System.err.println("Threw a SQLException at selecting in bookmark.");
            System.err.println(se.getMessage());
          }
            return temp;
     }
     
}
