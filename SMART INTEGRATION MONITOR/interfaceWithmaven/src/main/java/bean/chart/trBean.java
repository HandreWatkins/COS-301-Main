package bean.chart;

import ejb.DistressedresourceEJB;
import ejb.MainactivityEJB;
import entity.Mainactivity;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;


@ManagedBean
@RequestScoped
public class trBean 
{	
    @Inject
    private MainactivityEJB mainactivityEJB;
    @Inject
    private DistressedresourceEJB distressEJB;
    
    private CartesianChartModel model;
    private CartesianChartModel hModel;
     private CartesianChartModel dModel;
      private CartesianChartModel wModel;
      @PostConstruct
    public void init()
    {
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
    	java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        Timestamp min = new Timestamp(0);
        Long ij = (long) currentTimestamp.getTime() -30000;
        min.setTime(ij);
        try{
            List<Mainactivity> ml = mainactivityEJB.getTimer(min,currentTimestamp);

        model = new CartesianChartModel();	
        ChartSeries prTraffic = new ChartSeries();
        prTraffic.setLabel("Projected Traffic");

        List<Integer> persecavg = new LinkedList<>();
        int k = 0;
       
        for (int i = 0; i < 30 ; i++) {
                persecavg.add(0);
            }
        Timestamp t = new java.sql.Timestamp(min.getTime());//30000
        
        for (int i = 0; i < 30; i++) {
                
            k += 1000;
    	java.sql.Timestamp sec = new java.sql.Timestamp(t.getTime()+k);

        float avg = 0;
        int size = 0;
        for(int y=0;y<ml.size();y++)
        {
            if(ml.get(y).getCreateDate().compareTo(sec)<=0&& ml.get(y).getCreateDate().compareTo(t)>=0)
            {
                avg += ml.get(y).getResponseTime();
                size++;
            }
        }
        t.setTime(t.getTime()+k);
        persecavg.set(i, (int) (avg/size));    
        
    }
        int i;
        for(i=1; i<=30;i++)
        {
            prTraffic.set(i, persecavg.get(i-1));
            //archTaffic.set(i, 0);
        }

         model.addSeries(prTraffic);
         //model.addSeries(archTaffic);
    }catch (NullPointerException e)
    {
        
         model = new CartesianChartModel();	
        ChartSeries prTraffic = new ChartSeries();
        //ChartSeries archTaffic = new ChartSeries();
         prTraffic.setLabel("Projected Traffic");
        //archTaffic.setLabel("Archived Traffic");
        for (int i = 0; i < 30; i++) {
            prTraffic.set(i, 0);
        }

        model.addSeries(prTraffic);
        }
    }
    public trBean() 
    {		
        
        createhModel();
        createdModel();
        createwModel();   
    }		
    
    /*private void createModel()
    {
        model = new CartesianChartModel();	
        ChartSeries prTraffic = new ChartSeries();
        ChartSeries archTaffic = new ChartSeries();
        
        prTraffic.set("1", 1);		
        prTraffic.set("2", 2);
         prTraffic.set("3", 3);		
        prTraffic.set("4", 5);
         prTraffic.set("5", 8);		
        prTraffic.set("6", 2);
         prTraffic.set("7", 1);		
        prTraffic.set("8", 1);
        prTraffic.set("9", 5);		
        prTraffic.set("10", 1);
         archTaffic.set("1", 8);		
        archTaffic.set("2", 1);
         archTaffic.set("3", 4);		
        archTaffic.set("4", 3);
         archTaffic.set("5", 9);		
        archTaffic.set("6", 2);
         archTaffic.set("7", 1);		
        archTaffic.set("8", 1);
        archTaffic.set("9", 5);		
        archTaffic.set("10", 1);		
        //model.addSeries(prTraffic);		
        
    }*/
    private void createhModel()
    {
        hModel = new CartesianChartModel();	
        ChartSeries hTraffic = new ChartSeries();
        hTraffic.setLabel("Average Request Per Hour");
        
        hTraffic.set("1", 1); 
        hTraffic.set("2", 14); 
        hTraffic.set("3", 19); 
        hTraffic.set("4", 104); 
        hTraffic.set("5", 110); 
        hTraffic.set("6", 1); 
        hTraffic.set("7", 7); 
        hTraffic.set("", 13); 
        hTraffic.set("8", 15); 
        hTraffic.set("9", 19); 
        hTraffic.set("10", 122); 
        hTraffic.set("11", 502); 
        hTraffic.set("12", 609); 
        hTraffic.set("13", 701); 
        hTraffic.set("14", 201); 
        hModel.addSeries(hTraffic);
    }
     private void createdModel()
    {
        dModel = new CartesianChartModel();	
        ChartSeries hTraffic = new ChartSeries();
        hTraffic.setLabel("Average Request Per Day");
        
        hTraffic.set("1", 1); 
        hTraffic.set("2", 14); 
        hTraffic.set("3", 19); 
        hTraffic.set("4", 104); 
        hTraffic.set("5", 110); 
        hTraffic.set("6", 1); 
        hTraffic.set("7", 7); 
        hTraffic.set("", 13); 
        hTraffic.set("8", 15); 
        hTraffic.set("9", 19); 
        hTraffic.set("10", 122); 
        hTraffic.set("11", 502); 
        hTraffic.set("12", 609); 
        hTraffic.set("13", 701); 
        hTraffic.set("14", 201);
        hTraffic.set("15", 1); 
        hTraffic.set("16", 14); 
        hTraffic.set("17", 19); 
        hTraffic.set("18", 104); 
        hTraffic.set("19", 110); 
        hTraffic.set("20", 1); 
        hTraffic.set("21", 7); 
        hTraffic.set("22", 13); 
        hTraffic.set("23", 15); 
        hTraffic.set("24", 19); 
      
        dModel.addSeries(hTraffic);
      
    }
      private void createwModel()
    {
        wModel = new CartesianChartModel();	
        ChartSeries hTraffic = new ChartSeries();
        hTraffic.setLabel("Average Request Per Week");
        
        hTraffic.set("1", 50000); 
        hTraffic.set("2", 90000); 
        hTraffic.set("3", 40000); 
        hTraffic.set("4", 1000); 
        hTraffic.set("5", 100); 
        hTraffic.set("6", 80000); 
        hTraffic.set("7", 70000);
        wModel.addSeries(hTraffic);
    }
    
    public CartesianChartModel getModel()
    { 
        return model;
    }
     public CartesianChartModel gethModel()
    { 
        return hModel;
    }
      public CartesianChartModel getdModel()
    { 
        return dModel;
    }
       public CartesianChartModel getwModel()
    { 
        return wModel;
    }
}


   

    

