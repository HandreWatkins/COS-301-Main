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
public class responseBean
{	
    private CartesianChartModel model;	
    public responseBean() 
    {			
        model = new CartesianChartModel();	
        ChartSeries prTraffic = new ChartSeries();
        ChartSeries archTaffic = new ChartSeries();
        prTraffic.setLabel("Response Time");
      
        prTraffic.set("1", 100);		
        prTraffic.set("2", 225);
         prTraffic.set("3", 303);		
        prTraffic.set("4", 555);
         prTraffic.set("5", 803);		
        prTraffic.set("6", 212);
         prTraffic.set("7", 110);		
        prTraffic.set("8", 21);
        prTraffic.set("9", 15);		
        prTraffic.set("10", 1);	
        prTraffic.set("11", 125);		
        prTraffic.set("12", 20);
         prTraffic.set("13", 403);		
        prTraffic.set("14", 205);
         prTraffic.set("15", 228);		
        prTraffic.set("16", 252);
         prTraffic.set("17", 151);		
        prTraffic.set("18", 551);
        prTraffic.set("19", 955);
          prTraffic.set("20", 100);		
        prTraffic.set("22", 225);
         prTraffic.set("23", 303);		
        prTraffic.set("24", 555);
         prTraffic.set("25", 803);		
        prTraffic.set("26", 212);
         prTraffic.set("27", 110);		
        prTraffic.set("28", 21);
        prTraffic.set("29", 15);		
        prTraffic.set("210", 1);	
        prTraffic.set("211", 125);		
        prTraffic.set("212", 20);
         prTraffic.set("213", 403);		
        prTraffic.set("214", 205);
         prTraffic.set("215", 228);		
        prTraffic.set("216", 252);
         prTraffic.set("217", 151);		
        prTraffic.set("218", 551);
        prTraffic.set("219", 955);
        model.addSeries(prTraffic);
    }		
    
    public CartesianChartModel getModel()
    { return model;
    } 
}


   

    

