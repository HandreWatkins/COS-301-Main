/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;


import entity.Distressedresource;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Xciver
 */
 @LocalBean
@Stateless
public class DistressedresourceEJB {


        public DistressedresourceEJB() {
        }

    
	@PersistenceContext
	EntityManager em;
	public void saveDis(Distressedresource dis){
		em.merge(dis);
	}
	public void deleteDis(Distressedresource dis){
		em.remove(dis);
	}
	@SuppressWarnings("unchecked")
	public List<Distressedresource> getAll(){
            
                List<Distressedresource> results = new ArrayList<Distressedresource>();
		results= em.createQuery("SELECT d FROM Distressedresource d ORDER BY d.distressedresourcesId DESC").getResultList();
		return  results.isEmpty() ? null : results;
            
	}
}   

