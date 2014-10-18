/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Filter;

import Util.UrlLinker;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;
import javax.servlet.FilterChain;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.annotations.BeforeMethod;


/**
 *
 * @author Kurai Okami
 */
public class ServletFilterTest {
    
    public ServletFilterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of init method, of class ServletFilter.
     */
    @Test
    public void testInit_ServletConfig() throws Exception {
        System.out.println("init");
        ServletConfig config = null;
        ServletFilter instance = new ServletFilter();
        instance.init(config);
        // TODO review the generated test code and remove the default call to fail.
        if(instance.getServletConfig() == null)
            System.out.println("test success");
        else
            fail("The test case is a prototype.");
    }

    /**
     * Test of doGet method, of class ServletFilter.
     */
    @Test
    public void testDoGet() throws Exception {
       /* System.out.println("doGet");
        
        ServletRunner sr = new ServletRunner();
        sr.registerServlet( "proxy", Servlet.class.getName() );
        
        ServletUnitClient client = sr.newClient();
        WebRequest request2   = new GetMethodWebRequest( "http://localhost/proxy/test" );
        
        //HttpServletRequest request = (HttpServletRequest) request2;
        HttpServletResponse response = null;
        ServletFilter instance = new ServletFilter();
        instance.doGet(request, response);
        // TODO review the generated test code and remove the default call to fail.

        fail("The test case is a prototype.");*/
    }

    /**
     * Test of doPost method, of class ServletFilter.
     */
    @Test
    public void testDoPost() throws Exception {
       /* System.out.println("doPost");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ServletFilter instance = new ServletFilter();
        instance.doPost(request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of doPut method, of class ServletFilter.
     */
    @Test
    public void testDoPut() throws Exception {
        /*System.out.println("doPut");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ServletFilter instance = new ServletFilter();
        instance.doPut(request, response);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of doDelete method, of class ServletFilter.
     */
    @Test
    public void testDoDelete() throws Exception {
        System.out.println("doDelete");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        ServletFilter instance = new ServletFilter();
        instance.doDelete(request, response);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of callback method, of class ServletFilter.
     */
    @Test
    public void testCallback() throws Exception {
        System.out.println("callback");
        HttpServletResponse response = null;
        UrlLinker url = null;
        ServletFilter instance = new ServletFilter();
        instance.callback(response, url);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of doFilter method, of class ServletFilter.
     * @throws java.lang.Exception
     */

    /*@BeforeMethod
  public void setUp() throws Exception {
    request = context.mock(HttpServletRequest.class);
    response = context.mock(HttpServletResponse.class);
  }*/
    @Test
    public void testDoFilter() throws Exception {
        System.out.println("doFilter");
        ServletRequest request = null;
        ServletResponse response = null;
        FilterChain chain = null;
        ServletFilter instance = new ServletFilter();
        instance.doFilter(request, response, chain);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
