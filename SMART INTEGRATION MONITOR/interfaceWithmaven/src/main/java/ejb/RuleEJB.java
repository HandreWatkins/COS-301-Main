/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Rule;
import entity.Users;
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
             } catch(NoResultException e) {
        
    }
            return false;
    }
    
    //@SuppressWarnings("unchecked")
    public List<Rule> getAll(){
            
                List<Rule> results = new ArrayList<>();
		results= em.createQuery("SELECT r FROM Rule r ORDER BY r.rulesId DESC").getResultList();
		return  results.isEmpty() ? null : results;
            
	}
}
