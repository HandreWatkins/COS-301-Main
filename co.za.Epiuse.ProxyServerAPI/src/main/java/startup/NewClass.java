package startup;

import connection.Network;
import java.io.IOException;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateful;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@PersistenceContext
@Singleton
@Stateful
@Startup
@Entity
public class NewClass implements Filter
{
    @Id
    private Long webLogic;
    
    public NewClass() throws IOException
    {
        System.out.println("asd");
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long getWebLogic() {
        return webLogic;
    }

    public void setWebLogic(Long webLogic) {
        this.webLogic = webLogic;
    }
    
   
    
}
