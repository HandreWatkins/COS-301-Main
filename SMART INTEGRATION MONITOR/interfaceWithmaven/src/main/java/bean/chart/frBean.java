package bean.chart;


import ejb.DistressedresourceEJB;
import ejb.MainactivityEJB;
import entity.Distressedresource;
import entity.Mainactivity;
import java.util.ArrayList;
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
      
      
     private List<smalldistress> dmain = new ArrayList();
     @PostConstruct
    public void init() 
       {	
           try{
               List<Mainactivity> ml = m.getAll();
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
       
       public List<smalldistress> failureMapreduce()
       {
         
      
      List<Distressedresource> dls = d.getAll();
      
       for(Distressedresource ds : dls){ //per mainactivity row
           boolean [] isnotnew = new boolean[dmain.size()];
           for(int g = 0;g<isnotnew.length;g++)
                            isnotnew[g]=false;
           for(int k = 0;k<dmain.size();k++)
                    {  
                        isnotnew[k] = dmain.get(k).uri.equals(ds.getUri());
                }
           boolean chance =true;
           int len = dmain.size();
           for(int l = 0 ;l<len;l++)
               if(isnotnew[l])
                   chance = false;
           if(chance == true)
               dmain.add(new smalldistress(ds.getUri(),0));
       }
       
       for(smalldistress s : dmain){
           if(s.uri.equals(""))
               continue;
           
           for(Distressedresource ds : dls){
               if(ds.getUri().equals(s.uri))
                   s.count++;
           }
       }
       return dmain;
       }
       
       
    public class smalldistress
    {
        private String uri;
        private int count;

        public smalldistress(String uri, int count) {
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

    
    
}
