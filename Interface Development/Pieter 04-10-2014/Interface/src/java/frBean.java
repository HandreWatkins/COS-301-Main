/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.OhlcChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Pieter
 */
@ManagedBean
@RequestScoped
public class frBean {

    /**
     * Creates a new instance of frBean
     */
    
         private OhlcChartModel model; 
         public frBean() 
         {	
   
         }
    
    
}
