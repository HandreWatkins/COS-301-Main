package ejb;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Users;

@LocalBean
@Stateless
public class UserEJB {

	@PersistenceContext
	EntityManager em;
	public void saveUser(Users user){
		em.merge(user);
	}
	public void deleteUser(Users user){
		em.remove(user);
	}
	//@SuppressWarnings("unchecked")
        public Users find(String username, String password) {            
        return em.createQuery("SELECT u FROM Users u WHERE u.password = :password AND u.username = :username", Users.class)
           .setParameter("username", username)
           .setParameter("password", password)
           .getSingleResult();
        }
}
