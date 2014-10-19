package bean.chart;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ejb.DistressedresourceEJB;
import ejb.MainactivityEJB;
import entity.Distressedresource;
import entity.Mainactivity;
import java.text.DecimalFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Pieter
 */
@ManagedBean
@RequestScoped
public class barBean
{
    @Inject
    private MainactivityEJB mainactivityEjb;
    @Inject
    private DistressedresourceEJB distressEJB;
    
    private CartesianChartModel model;	
    private int req =0,fReq = 0;
    private double persent;
    @PostConstruct
    public void init() 
    {	
        try{
         List<Mainactivity> ml = mainactivityEjb.getAll();
         List<Distressedresource> dl = distressEJB.getAll();
         
             
        model = new CartesianChartModel();	
        ChartSeries overallHealth = new ChartSeries();
        
        overallHealth.setLabel("Requests");
        overallHealth.setLabel("Failed Requests");
        
        int i = 0;
        int j = 0;
        int size = ml.size();
         for(Mainactivity m: ml)
         {
             i++;
         }
         for(Distressedresource d: dl)
         {
             j++;
         }
         req = i+j;
         fReq = j;
        overallHealth.set("Requests", i+j);
        overallHealth.set("Failed Requests", j);
        model.addSeries(overallHealth);
    }catch (NullPointerException e)
    {
        
        model = new CartesianChartModel();	
        ChartSeries prTraffic = new ChartSeries();
        prTraffic.setLabel("Requests");
        prTraffic.setLabel("Failed Requests");
        
        model.addSeries(prTraffic);
    }
    }		
    
    public CartesianChartModel getModel()
    { 
        return model;
    } 
    
    public String getpersent()
    {
        double suc = (double) req -  fReq;
        persent = (suc/req)*100.0;
        return new DecimalFormat("#.##").format(persent) + "%";
    }
}


   

    /**
     * Creates a new instance of barBean
     */
    
