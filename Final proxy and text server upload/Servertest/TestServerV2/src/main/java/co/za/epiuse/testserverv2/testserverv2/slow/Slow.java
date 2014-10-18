package co.za.epiuse.testserverv2.testserverv2.slow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Slow 
{ 
    private static Map<Long, GeoName> data = null;

    public Slow()
    {
        if (data == null) 
        {
            System.err.println("SlowApi loading");
            try {
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("geonames.txt");
                data = load(is);
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            System.err.println("SlowApi loaded: " + data.size());
        }
    }
    
    private static Map<Long, GeoName> load(InputStream is) {
        Map<Long, GeoName> results = new HashMap<Long, GeoName>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            long id = 0;
            while (line != null) {
                //Skip empty lines
                if (line.trim().length() == 0) {
                    continue;
                }

                try {
                    GeoName record = geoname(id, line);
                    if (record != null) {
                        results.put(id, record);
                        id++;
                    }
                }
                catch (Exception e) {
                    // Ignored
                }

                line = br.readLine();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return results;    
    }
    
    private static GeoName geoname(Long id, String line) {
        String[] columns = line.split(Pattern.quote("\t"));
        GeoName a = new GeoName();

        a.setId(id);
        a.setName(columns[1]);
        a.setAlternateNames(columns[3]);
        a.setLatitude(Double.parseDouble(columns[4]));
        a.setLongitude(Double.parseDouble(columns[5]));
        a.setFeatureClass(columns[6]);
        a.setCountryCode(columns[8]);
        a.setElevation(Long.parseLong(columns[15]));
        a.setPopulation(Long.parseLong(columns[16]));
        a.setTimezone(columns[18]);
        a.setModificationDate(Calendar.getInstance());

        return a;
    }
    
    private void delay() 
    {
        int delay = 0;
        int minute = Calendar.getInstance().get(Calendar.MINUTE);
        if (minute > 0 && minute < 60) {
            int random = new Random().nextInt(500);
            delay += random;
        }
        if (minute > new Random().nextInt(20) && minute < new Random().nextInt(40)) {
            int random = new Random().nextInt(1500);
            delay += random;
        }
        try {
            Thread.sleep(delay);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void slowReq(HttpServletRequest request, HttpServletResponse response) throws IOException
    {

        Long ltest = new Long("5881909");
        GeoName test = doRead(ltest);
        
        response.getWriter().print("Slow");
        response.flushBuffer();
    }
    
    protected GeoName doRead(Long id) {
        delay();
        return data.get(id);
    } 
}
