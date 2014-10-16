package ejb;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Users;
import javax.persistence.NoResultException;

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
	@SuppressWarnings("all")
        public Users find(String username) {
             try{
        return em.createQuery("SELECT u FROM Users u WHERE u.username = :username", Users.class)
           .setParameter("username", username)
           .getSingleResult();
        
             
             } catch(NoResultException e) {
        
    }return null;
        }
        public boolean login(String username, String password)
        {
            try{
            Users u = em.createQuery("SELECT u FROM Users u WHERE u.password = :password AND u.username = :username", Users.class)
           .setParameter("username", username)
           .setParameter("password", password)
           .getSingleResult();
            if(!u.getUsername().isEmpty() && !u.getPassword().isEmpty())
            {
                if(u.getUsername().contains(username) && u.getPassword().contains(password)){
                    return true;
                }
            }
             } catch(NoResultException e) {
        
    }
            return false;
}

       
    public boolean register(String username, String password) {
        Users u = find(username);
        if(u!=null)
        {
            return false;
        }
         u = new Users();
        u.setUsername(username);
        u.setPassword(password);
        //Now saving...
        em.getTransaction().begin();
        em.persist(u); //em.merge(u); for updates
        em.getTransaction().commit();
        em.close();

        return true;
    }
}
