package bean.chart;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Pieter
 */
@ManagedBean
@RequestScoped
public class overallBean {

    /**
     * Creates a new instance of overallBean
     */
       
          private CartesianChartModel model;
        public overallBean()
        {				
            model = new CartesianChartModel();		
            ChartSeries req = new ChartSeries();
            
            req.setLabel("Requests");		
            req.set("Today", 1200);		
            req.set("Overall", 10000);		
            
            ChartSeries fail = new ChartSeries();
            fail.setLabel("Failures");			
            fail.set("Today", 1);		
            fail.set("Overall", 60);		
            model.addSeries(req);		
            model.addSeries(fail);	
        }	
        public CartesianChartModel getModel()
        { 
            return model; 
        } 
    }
