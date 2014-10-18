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
        @ManagedProperty(value="#{loginBean}")
	private LoginBean lBean;
        
        private String uri;
        private int expectedTime;

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
        
        
        
        public List<Rule> getAllMainactivity(){
		return ruleEJB.getAllUserR(lBean.getUsername());
	}
        
        
        public void UriExists()
        {
            
            if(!ruleEJB.uriExist(uri))
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
        }
}
