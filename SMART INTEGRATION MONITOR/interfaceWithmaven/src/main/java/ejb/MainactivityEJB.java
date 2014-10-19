
package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Mainactivity;
import java.sql.Timestamp;
import java.util.ArrayList;

@LocalBean
@Stateless
public class MainactivityEJB {

    public MainactivityEJB() {
    }

    
	@PersistenceContext
	EntityManager em;
	public void saveUser(Mainactivity user){
		em.merge(user);
	}
	public void deleteUser(Mainactivity user){
		em.remove(user);
	}
	@SuppressWarnings("unchecked")
	public List<Mainactivity> getAll(){
            
                List<Mainactivity> results = new ArrayList<Mainactivity>();
		results= em.createQuery("SELECT m FROM Mainactivity m ORDER BY m.mainactivityId DESC").getResultList();
		return  results.isEmpty() ? null : results;
            
	}
        public List<Mainactivity> getTimer(Timestamp tN,Timestamp tT){
            List<Mainactivity> results = new ArrayList<>();
		results= em.createQuery("SELECT m FROM Mainactivity m WHERE m.createDate>:createdateNow AND m.createDate<=:createdateTo ORDER BY m.mainactivityId DESC")
                        .setParameter("createdateNow", tN).setParameter("createdateTo", tT)
                        .getResultList();
		return  results.isEmpty() ? null : results;
        }
}

