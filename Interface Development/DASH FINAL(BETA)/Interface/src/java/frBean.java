
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Pieter
 */
@ManagedBean
@RequestScoped
public class frBean {

      private PieChartModel model;
    public frBean()
       {		
           model = new PieChartModel();	
           model.set("Failure", 75);		
           model.set("None", 25);	
       }	
       public PieChartModel getModel() 
       {		
           return model;	
       }
    }
