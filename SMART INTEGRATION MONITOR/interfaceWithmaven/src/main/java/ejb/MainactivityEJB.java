/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Mainactivity;
import java.util.ArrayList;
import javax.persistence.NoResultException;

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
}

