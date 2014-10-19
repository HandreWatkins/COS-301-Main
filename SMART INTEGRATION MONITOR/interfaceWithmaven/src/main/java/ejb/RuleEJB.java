package ejb;

import entity.Rule;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@LocalBean
@Stateless
public class RuleEJB {
    
    @PersistenceContext
	EntityManager em;
    public void newRule(Rule r){
        Rule rs = r;
        em.persist(rs);
        
    }
    
    public void saveUser(Rule user){
		em.merge(user);
	}
	public void deleteUser(Rule user){
		em.remove(user);
	}
        
    @SuppressWarnings("unchecked")
    public List<Rule> getAll(){
        List<Rule> results = new ArrayList<Rule>();
		results= em.createQuery("SELECT r FROM Rule r ORDER BY r.rulesId DESC").getResultList();
		return  results.isEmpty() ? null : results;
            
    }
    
    
}
