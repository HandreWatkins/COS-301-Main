package HttpClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import javax.net.ssl.HttpsURLConnection;

public class HttpClientCreation
{
    private final String [] req = {"GET","PUT","DELETE"};
    private final String USER_AGENT = "Mozilla/5.0";
    String url;
    public HttpClientCreation(String reqString) 
    {
        url = reqString;
    }
    
    public void nReqest() throws InterruptedException, IOException
    {
        deleteReq();
        /*Random ranval = new Random();
        int rVal = ranval.nextInt(3);

        try
        {
            switch(req[rVal])
            {
                case "GET":
                    getReq();
                    break;

                case "PUT":
                    putReq();
                    break;

                case "DELETE":
                    deleteReq();
                    break;
            }
        }
        catch(Exception e)
        {

        }
        
        Thread.sleep(500);*/
    }
    
    private void getReq() throws MalformedURLException, IOException
    {
        url = "http://"+url;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        

        int responseCode = con.getResponseCode();
        System.out.println("GET method on URL: " + url);
        System.out.println("Server responce: " + responseCode);
    }
    
    private void postReq() throws MalformedURLException, IOException
    {
        url = "https://"+url;
        URL obj =  new URL(null,url,new sun.net.www.protocol.https.Handler());
        
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        
        System.setProperty("javax.net.ssl.keyStore", "Drive:/FullPath/keystorefile.store");
        System.setProperty("javax.net.ssl.keyStorePassword", "Password");
        
        int responseCode = con.getResponseCode();
        
        System.out.println("Post method on URL: " + url);
        System.out.println("Server responce: " + responseCode);
    }
    
    private void deleteReq() throws MalformedURLException, IOException
    {
        url = "http://"+url;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("DELETE");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("DELETE method on URL: " + url);
        System.out.println("Server responce: " + responseCode);
    }
    
    private void putReq() throws MalformedURLException, IOException
    {
        url = "http://"+url;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("PUT");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("PUT method on URL: " + url);
        System.out.println("Server responce: " + responseCode);
    }
}
