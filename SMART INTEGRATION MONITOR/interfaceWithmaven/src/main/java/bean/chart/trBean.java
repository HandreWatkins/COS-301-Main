package bean.chart;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;


/**
 *
 * @author Pieter
 */
@ManagedBean
@RequestScoped
public class trBean 
{	
    private CartesianChartModel model;
    private CartesianChartModel hModel;
     private CartesianChartModel dModel;
      private CartesianChartModel wModel;
    public trBean() 
    {		
        createModel();
        createhModel();
        createdModel();
        createwModel();   
    }		
    
    private void createModel()
    {
        model = new CartesianChartModel();	
        ChartSeries prTraffic = new ChartSeries();
        ChartSeries archTaffic = new ChartSeries();
        prTraffic.setLabel("Projected Traffic");
        archTaffic.setLabel("Archived Traffic");
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
        model.addSeries(prTraffic);		
        model.addSeries(archTaffic);     
    }
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


   

    

