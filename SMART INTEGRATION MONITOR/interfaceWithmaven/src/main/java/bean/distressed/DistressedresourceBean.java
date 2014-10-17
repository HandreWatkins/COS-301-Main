/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.distressed;

import ejb.DistressedresourceEJB;
import entity.Distressedresource;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

/**
 *
 * @author Xciver
 */


@ApplicationScoped
@ManagedBean
public class DistressedresourceBean {

	@Inject
	DistressedresourceEJB dEJB;

	public List<Distressedresource> getAllDistress(){
		return dEJB.getAll();
	}
        public List<Distressedresource> getAll15list(){
            List<Distressedresource> m = new LinkedList<>();
            List<Distressedresource> ml = dEJB.getAll();
            for (int i = 0; i < 15; i++) {
                m.add(ml.get(i));
            }
		return m;
	}
	
}


