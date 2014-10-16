package bean.chart;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ejb.MainactivityEJB;
import entity.Mainactivity;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;


/**
 *
 * @author Pieter
 */
@ManagedBean
@RequestScoped
public class responseBean
{	
    @Inject
    private MainactivityEJB mainactivityEjb;
    private CartesianChartModel model;	
    @PostConstruct
    public void init() 
    {	
        try{
         List<Mainactivity> ml = mainactivityEjb.getAll();
         
             
        model = new CartesianChartModel();	
        ChartSeries prTraffic = new ChartSeries();
        prTraffic.setLabel("Response Time");
        int i = 1;
        int size = ml.size();
         for(Mainactivity m: ml)
         {
             if(i<15 && i<=size)
             {
             prTraffic.set(m.getMainactivityId().toString() ,  m.getResponseTime());
             i++;
             }
         }/*
        
        prTraffic.set("1", (int)(Math.random()*1000 ));		
        prTraffic.set("2", (int)(Math.random()*1000 ));
         prTraffic.set("3", (int)(Math.random()*1000 ));		
        prTraffic.set("4", (int)(Math.random()*1000 ));
         prTraffic.set("5", (int)(Math.random()*1000 ));		
        prTraffic.set("6", (int)(Math.random()*1000 ));
         prTraffic.set("7", (int)(Math.random()*1000 ));		
        prTraffic.set("8", (int)(Math.random()*1000 ));
        prTraffic.set("9", (int)(Math.random()*1000 ));		
        prTraffic.set("10",(int)(Math.random()*1000 ));	
        prTraffic.set("11", (int)(Math.random()*1000 ));		
        prTraffic.set("12", (int)(Math.random()*1000 ));
         prTraffic.set("13", (int)(Math.random()*1000 ));		
        prTraffic.set("14", (int)(Math.random()*1000 ));
         prTraffic.set("15", (int)(Math.random()*1000 ));		
        prTraffic.set("16", (int)(Math.random()*1000 ));
         prTraffic.set("17", (int)(Math.random()*1000 ));		
        prTraffic.set("18", (int)(Math.random()*1000 ));
        prTraffic.set("19", (int)(Math.random()*1000 ));
          prTraffic.set("20", (int)(Math.random()*1000 ));		
        prTraffic.set("22", (int)(Math.random()*1000 ));
         prTraffic.set("23", (int)(Math.random()*1000 ));		
        prTraffic.set("24", (int)(Math.random()*1000 ));
         prTraffic.set("25", (int)(Math.random()*1000 ));		
        prTraffic.set("26", (int)(Math.random()*1000 ));
         prTraffic.set("27", (int)(Math.random()*1000 ));		
        prTraffic.set("28", (int)(Math.random()*1000 ));
        prTraffic.set("29", (int)(Math.random()*1000 ));		
        prTraffic.set("210", (int)(Math.random()*1000 ));	
        prTraffic.set("211", (int)(Math.random()*1000 ));		
        prTraffic.set("212", (int)(Math.random()*1000 ));
         prTraffic.set("213", (int)(Math.random()*1000 ));		
        prTraffic.set("214", (int)(Math.random()*1000 ));
         prTraffic.set("215", (int)(Math.random()*1000 ));		
        prTraffic.set("216", (int)(Math.random()*1000 ));
         prTraffic.set("217", (int)(Math.random()*1000 ));		
        prTraffic.set("218", (int)(Math.random()*1000 ));
        prTraffic.set("219", (int)(Math.random()*1000 ));*/
        model.addSeries(prTraffic);
    }catch (NullPointerException e)
    {
        
        model = new CartesianChartModel();	
        ChartSeries prTraffic = new ChartSeries();
        prTraffic.setLabel("Response Time");
        
        model.addSeries(prTraffic);
    }
    }		
    
    public CartesianChartModel getModel()
    { return model;
    } 
}


   

    

