package bean.rule;

import bean.user.NavigationBean;
import database.DatabaseControl;
import ejb.RuleEJB;
import ejb.UserEJB;
import entity.Rule;
import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean
@RequestScoped
public class RuleBean {
        @Inject
	RuleEJB ruleEJB;
        @Inject
        UserEJB userEJB;

        private String uri;
        private int expectedTime;
        private NavigationBean navigationBean;
     
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

        public String UriExists() throws ClassNotFoundException, IOException, Exception
        {
            DatabaseControl control = new  DatabaseControl();
            try
            {
                control.updaterules(uri, expectedTime, "admin");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rule successfully added", "INFO"));
            }
            catch(Exception e)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Rule for URI already exists", "Error"));
        }
        return null;
}
}
