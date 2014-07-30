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
        private String url ;
        private String user; 
        private String password ;
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
     public boolean newIpInsert(String ip,String uri,double respondtime) throws SQLException 
     {
         PreparedStatement pst = null;
         String stm = "INSERT INTO mainactivity" +
            		" (ip, uri, \"responseTime\") " +
            		" VALUES(?,?, ?)";
            pst = con.prepareStatement(stm);
            
            pst.setString(1, ip);
            pst.setString(2, uri);
            pst.setDouble(3, respondtime);
            pst.executeUpdate();
            
            //System.out.println("successful inserted");
           // closeDB(con,st,rs);
      return true;   
     }
     public boolean newDistress(String ip,String uri,double respondtime, double expt) throws SQLException 
     {
         PreparedStatement pst = null;
         String stm = "INSERT INTO distressedresources" +
            		" (ip, uri, \"responsetime\",time_expected) " +
            		" VALUES(?,?, ?,?)";
            pst = con.prepareStatement(stm);
            
            pst.setString(1, ip);
            pst.setString(2, uri);
            pst.setDouble(3, respondtime);
            pst.setDouble(4, expt);
            pst.executeUpdate();
         return false;
     }
     public String[] getRules(String uri,double expt)
     {
         String [] temp = null;
         try 
        {
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery("SELECT count(rules_id) as rulescount FROM rules ORDER BY rules_id");
      rs.next();
      int i = rs.getInt(1);
      temp = new String[i];
      rs = st.executeQuery("SELECT * FROM rules ORDER BY rules_id WHERE uri = \""+uri+"\" and expected_time = "+expt);
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
}
