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

import java.io.Serializable;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import ejb.UserEJB;
 
/**
 * Simple login bean.
 *
 * @author itcuties
 */
@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable {
 
    private static final long serialVersionUID = 7765876811740798583L;
 
    // Simple user database :)
    
    @Inject
	UserEJB service; 
    
    private String username;
    private String password;
     
    private boolean loggedIn;
 
    @ManagedProperty(value="#{navigationBean}")
    private NavigationBean navigationBean;
     
    /**
     * Login operation.
     * @return
     */
    public String doLogin() {
        // Get every user from our sample database :)
        
           
            // Successful login
            if (service.login(username, password))
            {
                loggedIn = true;
                return navigationBean.redirectToWelcome();
            }
            else{
                
        
         
        // Set login ERROR
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect username or password", "Error logging in"));
         
        // To to login page
        return null;
            }
         
    }
     
    /**
     * Logout operation.
     * @return
     */
    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;
         
        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);
         
        return navigationBean.toLogin();
    }
 
    // ------------------------------
    // Getters & Setters
     
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
 
    public boolean isLoggedIn() {
        return loggedIn;
    }
 
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
 
    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }
     
}
