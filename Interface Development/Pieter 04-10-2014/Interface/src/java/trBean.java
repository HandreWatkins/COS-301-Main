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
public class trBean
{

    private CartesianChartModel model;
    
    public trBean() 
    {				
        model = new CartesianChartModel();
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");			
        boys.set("2004", 120);		
        boys.set("2005", 100);		
        ChartSeries girls = new ChartSeries();	
        girls.setLabel("Girls");			
        girls.set("2004", 52);		
        girls.set("2005", 60);		
        model.addSeries(boys);		
        model.addSeries(girls);	
    }
    
    public CartesianChartModel getModel() 
    {
        return model;
    } 
    public void setModel(CartesianChartModel m) 
    {
        model = m;
    } 
}
   

    

