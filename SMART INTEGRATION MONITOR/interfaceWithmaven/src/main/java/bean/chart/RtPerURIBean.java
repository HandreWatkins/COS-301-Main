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
       for(smallmainactivity s : main)
       {
           if(!s.uri.equals(""))
           {
              int size = ml.size();
           float i = (float) ((s.count*1.0/size)*1000);   
           circle1.put(s.uri, i);
           }
       }
       //circle1.put("www.google.com", 150);        
//       circle1.put("www.facebook.com", 400);        
//       circle1.put("www.twitter.com", 200);       
//       circle1.put("www.gmail.com", 10);        
//       circle1.put("ww.cs.up.ac.za", 1);        	
//       circle1.put("www.office.com", 125);        
//       circle1.put("www.blah.com", 702);        
//       circle1.put("www.blah.com", 421);    
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

  /* public RtPerURIBean()
   {
     */
     //  List<smallmainactivity> main = new ArrayList();
   //    mainactivityEjb = null;
       //if(mainactivityEjb != null){
 //     List<Mainactivity> ml = mainactivityEjb.getAll();*/
      /* for(Mainactivity m : ml){
           if(!main.isEmpty())
                if(!main.contains(new smallmainactivity(m.getUri(),0)))
                {
                    main.add(new smallmainactivity(m.getUri(),0));
                }
           else
                {
                    main.add(new smallmainactivity(m.getUri(),0));
                }
       }
       
       int i =0;
       for(smallmainactivity s : main){
           int scount=0;
           for(Mainactivity m : ml){
               if(m.getUri().equals(s.uri))
                   scount++;
           }
           for (int j = 0; j < main.size(); j++) {
                   if(main.contains(new smallmainactivity(s.uri, s.count))){
                       main.get(j).count = scount;
               }
           }
           //s.setCount(scount);
           i++;
       } */
       
    /*  model = new DonutChartModel ();		
       Map<String, Number> circle1 = new LinkedHashMap<String, Number>(); */
      /* for(smallmainactivity s : main)
           circle1.put(s.uri, (s.count/i*100));*/
       /*circle1.put("www.google.com", 150);        
//       circle1.put("www.facebook.com", 400);        
//       circle1.put("www.twitter.com", 200);       
//       circle1.put("www.gmail.com", 10);        
//       circle1.put("ww.cs.up.ac.za", 1);        	
//       circle1.put("www.office.com", 125);        
//       circle1.put("www.blah.com", 702);        
//       circle1.put("www.blah.com", 421);    
       model.addCircle(circle1);        
       
   }	*/
   public DonutChartModel  getModel() 
   { 
       return model;
   } 
    
}