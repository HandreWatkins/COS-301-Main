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
    public trBean() 
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
    
    public CartesianChartModel getModel()
    { return model;
    }
}


   

    

