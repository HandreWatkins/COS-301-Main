/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.rule;

import bean.user.LoginBean;
import ejb.RuleEJB;
import ejb.UserEJB;
import entity.Rule;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

/**
 *
 * @author Xciver
 */
@ManagedBean
@SessionScoped
public class RuleBean {
    
        @Inject
	RuleEJB ruleEJB;
        @Inject
        UserEJB userEJB;
        
        
	
        
        private String uri;
        private int expectedTime;

        private HttpServletRequest request = (HttpServletRequest)javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
        private HttpSession session = request.getSession();
        private LoginBean lBean = (LoginBean) session.getAttribute("loginBean");
                
               
    public int getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(int ExpectedTime) {
        this.expectedTime = ExpectedTime;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
        
        
        
        public List<Rule> getAllrule(){
            
		return ruleEJB.getAll();
	}
        
        
        public String uriExists()
        {
            boolean b;
            try{
            b =ruleEJB.uriExist(uri);
            }catch(NullPointerException e)
            {
                b=false;
            }
            if(!b)
            {
                Rule r = new Rule();
                r.setUri(uri);
                r.setUser(userEJB.find(lBean.getUsername()));
                r.setExpectedTime(expectedTime);
                ruleEJB.saveUser(r);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Uri has been created", "Created"));
                
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "The uri already exists", "Error creation"));
            }
            return null;
        }
}
