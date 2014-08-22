import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import server.servertest;


public class Unit_test_proxy 
{
    @Test
    public void main() throws IOException
    {
        System.out.println("test 1 start");
        try {
            //Socket client = new Socket("http://localhost:5254", 8081);
            final servertest test = new servertest();
  
            //test.main();
            System.out.println("start");
            //sendGet();
            //test.stop();
            
        } catch (Exception ex) {
            Logger.getLogger(Unit_test_proxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("test 1 end");
    }
    
    	private void sendGet() throws Exception {
 
		String url = "http://localhost:5254/";
                Socket sock = new Socket("localhost", 5254);
                System.out.println(sock.getInputStream().read());
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/12.0");
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print resul
		System.out.println(response.toString());
	}
}
