package bean.chart;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ejb.MainactivityEJB;
import entity.Mainactivity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import org.primefaces.component.chart.metergauge.MeterGaugeChart;
import  org.primefaces.model.chart.MeterGaugeChartModel;

/**
 *
 * @author Pieter
 */
@ManagedBean
@RequestScoped
public class rtBean {
    @Inject
    private MainactivityEJB mainactivityEJB;

    private MeterGaugeChartModel liveModel;
    private MeterGaugeChartModel model;
    private int val = 0;
    public rtBean() 
    {
            List<Number> intervals = new ArrayList<Number>()
            {{
                 add(100);
                add(200);
                add(300);
                add(400);
                add(500);
                add(600);
                add(700);
                add(800);
                add(900);
                add(1000);
            }};
            
            model =  new MeterGaugeChartModel(0, intervals);
            
            liveModel = new MeterGaugeChartModel(100, intervals);
            
            
    }
    public void setModel(MeterGaugeChartModel m)
    {
        model = m;
    }
    
    public MeterGaugeChartModel getModel()
    {
        return model;
    }
    
    public void setVal(int v)
    {
        val = v;
        model.setValue(val);
    }
    
    public int getVal()
    {
        return val;
    }
    @PostActivate
    public MeterGaugeChartModel getLiveModel()
    {
        
    try{
        List<Mainactivity> m = mainactivityEJB.getAll();
        Mainactivity temp = m.get(0);
        liveModel.setValue(temp.getResponseTime());
        
        return liveModel;
    }catch (NullPointerException e) {
        liveModel.setValue(0);
         return liveModel;
    }
       
    }
}
