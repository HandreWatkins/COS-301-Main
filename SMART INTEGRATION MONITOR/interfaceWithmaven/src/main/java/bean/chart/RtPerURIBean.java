package bean.chart;


 
import ejb.MainactivityEJB;
import entity.Mainactivity;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
 
import org.primefaces.model.chart.DonutChartModel;
 
@RequestScoped
@ManagedBean
public class RtPerURIBean implements Serializable 
{
        @Inject
 private MainactivityEJB mainactivityEjb;
   private DonutChartModel  model;
    
   private List<smallmainactivity> main = new ArrayList();

    public List<smallmainactivity> getMain() {
        return main;
    }

    public void setMain(List<smallmainactivity> main) {
        this.main = main;
    }
   
    @PostConstruct
public void init() {
    try{   
      
       //if(mainactivityEjb != null){
      List<Mainactivity> ml = mainactivityEjb.getAll();
      
       for(Mainactivity m : ml){ //per mainactivity row
           boolean [] isnotnew = new boolean[main.size()];
           for(int g = 0;g<isnotnew.length;g++)
                            isnotnew[g]=false;
           for(int k = 0;k<main.size();k++)
                    {  
                        isnotnew[k] = main.get(k).uri.equals(m.getUri());
                }
           boolean chance =true;
           int len = main.size();
           for(int l = 0 ;l<len;l++)
               if(isnotnew[l])
                   chance = false;
           if(chance == true)
               main.add(new smallmainactivity(m.getUri(),0));
       }
       
       for(smallmainactivity s : main){
           if(s.uri.equals(""))
               continue;
           
           for(Mainactivity m : ml){
               if(m.getUri().equals(s.uri))
                   s.count++;
           }
       }
       model = new DonutChartModel ();		
       Map<String, Number> circle1 = new LinkedHashMap<String, Number>(); 
       for (int j = main.size()-1; j >= 0; j--)  
       {
           if(!main.get(j).uri.equals(""))
           {
              int size = ml.size();
           float i = (float) ((main.get(j).count*1.0/size)*1000);   
           circle1.put(main.get(j).uri, i);
           }
       }
       model.addCircle(circle1);
       
} catch (NullPointerException e)
{
    model = new DonutChartModel ();
    
}
}
               
  
    public class smallmainactivity
    {
        private String uri;
        private int count;

        public smallmainactivity(String uri, int count) {
            this.uri = uri;
            this.count = count;
        }
        

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
        
    }

   public DonutChartModel  getModel() 
   { 
       return model;
   } 
    
}