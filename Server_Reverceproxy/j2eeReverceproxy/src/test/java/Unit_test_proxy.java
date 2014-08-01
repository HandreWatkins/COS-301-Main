import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Unit_test_proxy 
{
    public static void main(String[] args) throws IOException
    {
        System.out.println("test 1 start");
        try {
            //Socket client = new Socket("http://localhost:8081/Ty", 8081);
            sendGet();
        } catch (Exception ex) {
            Logger.getLogger(Unit_test_proxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("test 1 end");
    }
    
    	private static void sendGet() throws Exception {
 
		String url = "http://localhost:8081/Ty";
 
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
 
		//print result
		System.out.println(response.toString());
 
	}
}
