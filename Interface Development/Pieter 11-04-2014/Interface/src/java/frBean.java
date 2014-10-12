
 
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
 
import org.primefaces.model.chart.DonutChartModel;
 
@RequestScoped
@ManagedBean
public class frBean implements Serializable 
{
 
   private DonutChartModel  model;
   public frBean()
   {		
       model = new DonutChartModel ();		
       Map<String, Number> circle1 = new LinkedHashMap<String, Number>();      
       circle1.put("Brand 1", 150);        
       circle1.put("Brand 2", 400);        
       circle1.put("Brand 3", 200);       
       circle1.put("Brand 4", 10);        
       model.addCircle(circle1);        
       Map<String, Number> circle2 = new LinkedHashMap<String, Number>();        
       circle2.put("Brand 1", 540);        	
       circle2.put("Brand 2", 125);        
       circle2.put("Brand 3", 702);        
       circle2.put("Brand 4", 421);        	
       model.addCircle(circle2);         
       Map<String, Number> circle3 = new LinkedHashMap<String, Number>();        
       circle3.put("Brand 1", 40);        
       circle3.put("Brand 2", 325);        	
       circle3.put("Brand 3", 402);        
       circle3.put("Brand 4", 421);        	
       model.addCircle(circle3);	
   }	
   public DonutChartModel  getModel() 
   { 
       return model;
   } 
    
}