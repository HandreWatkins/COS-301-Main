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
import javax.persistence.Query;

import entity.Mainactivity;

@LocalBean
@Stateless
public class MainactivityEJB {

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
		Query q = em.createQuery("SELECT m FROM Mainactivity m ORDER BY m.mainactivityId DESC");
		return  q.getResultList();
	}
}

