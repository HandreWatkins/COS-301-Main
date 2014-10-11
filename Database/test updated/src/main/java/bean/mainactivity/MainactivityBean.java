package bean.mainactivity;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ejb.MainactivityEJB;
import entity.Mainactivity;

@ApplicationScoped
@ManagedBean
public class MainactivityBean {

	@Inject
	MainactivityEJB mainactivityEJB;

	public List<Mainactivity> getAllMainactivity(){
		return mainactivityEJB.getAll();
	}
	
}
