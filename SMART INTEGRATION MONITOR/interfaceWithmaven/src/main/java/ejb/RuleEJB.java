/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Rule;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Xciver
 */
@LocalBean
@Stateless
public class RuleEJB {
    
    @PersistenceContext
	EntityManager em;
    
    public void saveUser(Rule user){
		em.merge(user);
	}
	public void deleteUser(Rule user){
		em.remove(user);
	}
    public boolean uriExist(String uri)
        {
            try{
            Rule r = em.createQuery("SELECT r FROM Rule r WHERE r.uri = :uri", Rule.class)
           .setParameter("uri", uri)
           .getSingleResult();
            if(!r.getUri().isEmpty())
            {
                if(r.getUri().contains(uri)){
                    return true;
                }
            }
             } catch(NullPointerException e) {
        
    }
            return false;
    }
    public List<Rule> getAllUserR(String user){
            
                List<Rule> results = new ArrayList<Rule>();
		results= em.createQuery("SELECT r FROM Rule r WHERE r.user = :user ORDER BY r.rulesId DESC").setParameter("user", user).getResultList();
		return  results.isEmpty() ? null : results;
            
	}
}
