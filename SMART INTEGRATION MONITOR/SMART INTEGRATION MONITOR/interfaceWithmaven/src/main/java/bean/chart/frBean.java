package bean.chart;


import ejb.DistressedresourceEJB;
import ejb.MainactivityEJB;
import entity.Distressedresource;
import entity.Mainactivity;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Pieter
 */
@ManagedBean
@RequestScoped
public class frBean {

    @Inject
    DistressedresourceEJB d;
    @Inject
    MainactivityEJB m;
      private PieChartModel model;
      @PostConstruct
    public void init() 
       {	
           try{List<Mainactivity> ml = m.getAll();
           List<Distressedresource> dl = d.getAll();
           
           int i =0;
           for(Mainactivity ms : ml){
               i++;
           }
           int j = 0;
           for(Distressedresource ds : dl){
               j++;
           }
           
           int total = i+j;
           double pval = (double) i*1.0/total*100;
           double fval = (double) j*1.0/total*100;
           
           model = new PieChartModel();	
           model.set("Pass", pval);		
           model.set("Failure", fval);	
       }catch(NullPointerException e)
       {
           model = new PieChartModel();
           model.set("Pass", 0);		
           model.set("Failure", 0);
       }
       }
       public PieChartModel getModel() 
       {		
           return model;	
       }
    }
