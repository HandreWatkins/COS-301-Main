/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.component.chart.metergauge.MeterGaugeChart;
import  org.primefaces.model.chart.MeterGaugeChartModel;

/**
 *
 * @author Pieter
 */
@ManagedBean
@RequestScoped
public class rtBean {

    private MeterGaugeChartModel model;
    private int val = 0;
    public rtBean() 
    {
            List<Number> intervals = new ArrayList<Number>()
            {{
                add(200);
                add(300);
                add(400);
                add(500);
            }};
            
            model =  new MeterGaugeChartModel(0, intervals);
            
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
}
