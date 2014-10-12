/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.user.registration;

/**
 *
 * @author Xciver
 */
import ejb.UserEJB;
import javax.persistence.NoResultException;
import org.springframework.stereotype.Service;
 
 
/**
 * Registration Business Object implementation.
 * @author itcuties
 *
 */
@Service
public class RegistrationBoImpl implements RegistrationBo {
 
    UserEJB u;
    @Override
    public boolean registerUser(String username, String password) {
        // Output some info
        System.out.println("RegistrationBoImpl:: Registering user " + username + " " + password);
        u = new UserEJB();
        return u.register(username, password);
        
        
        // TODO: Contact your database here
        // ...
    }
   
}
