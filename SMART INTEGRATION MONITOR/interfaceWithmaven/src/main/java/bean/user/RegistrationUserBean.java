/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.user;

/**
 *
 * @author Xciver
 */
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
import bean.user.registration.RegistrationBo;
import bean.user.registration.RegistrationBoImpl;
import javax.persistence.NoResultException;
 
/**
 * Registration user JSF bean.
 *
 * @author itcuties
 *
 */
@Component
@ManagedBean
@SessionScoped
public class RegistrationUserBean {
     
    // This is going to be injected by Spring framework
    @Autowired
    RegistrationBo registrationBo;
    NavigationBean n;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     
    /**
     * Method registers user
     * @return 
     */
    public String register() {
        // Output some info
        System.out.println("RegistrationUserBean:: Registering user " + username + " " + password);
         
        // Call the business object to register the user
        registrationBo = new RegistrationBoImpl();
        if(registrationBo.registerUser(username, password)){
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration success", "success"); 
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return n.toLogin();
        }
        return null;
           
    }
     
    // Set the registrationBo attribute used by Spring
    public void setRegistrationBo(RegistrationBo registrationBo) {
        this.registrationBo = registrationBo;
    }
 
        
    
    
     
}
