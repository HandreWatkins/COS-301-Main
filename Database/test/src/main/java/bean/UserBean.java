package bean;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import ejb.UserEJB;
import entity.Users;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.persistence.NoResultException;
import javax.transaction.UserTransaction;


@ManagedBean
@SessionScoped
public class UserBean {

	@Inject
	UserEJB service;
        @Resource
        private UserTransaction utx;
        
        private String username = "";
        private String password = "";
        private Users currentUser = null;

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
        
        
        public String login() {
        
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            //System.out.println("asdasdasdfsadf");
            Users user = service.find(username, password);
            
                context.getExternalContext().getSessionMap().put("user", user);
                return "/xhtml/dashboard.xhtml?faces-redirect=true";
            
        }
        catch (NoResultException c) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unknown login, please try again", null));
            return null;
        }
        
    }

    public String logout() {
        currentUser = null;
        username = "";
        password = "";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }
    
    public void checkLoggedIn(ComponentSystemEvent cse) {
    FacesContext context = FacesContext.getCurrentInstance();
    if( currentUser == null){
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "index.xhtml?faces-redirect=true");
    }
}
    @PreDestroy
    public void sessionDestroyed() {
        if(currentUser != null){
             currentUser = null;
             username = "";
             password = "";
        }
    }

    
	
}
