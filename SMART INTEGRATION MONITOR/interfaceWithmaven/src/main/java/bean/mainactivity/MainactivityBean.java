package bean.mainactivity;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ejb.MainactivityEJB;
import entity.Mainactivity;
import java.util.LinkedList;

@ApplicationScoped
@ManagedBean
public class MainactivityBean {

	@Inject
	MainactivityEJB mainactivityEJB;

	public List<Mainactivity> getAllMainactivity(){
		return mainactivityEJB.getAll();
	}
        public List<Mainactivity> getAll15list(){
            List<Mainactivity> m = new LinkedList<>();
            List<Mainactivity> ml = mainactivityEJB.getAll();
            for (int i = 0; i < 15; i++) {
                m.add(ml.get(i));
            }
		return m;
	}
	
}
