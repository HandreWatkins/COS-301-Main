package HTTPclient;

import static org.apache.commons.lang.StringUtils.containsIgnoreCase;
import static org.apache.commons.lang.StringUtils.endsWithIgnoreCase;
import static org.apache.commons.lang.StringUtils.substringBefore;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletResponse;
import org.apache.http.HttpEntity;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import Util.UrlLinker;

public class Pushclient 
{
    UrlLinker urlLink;
    Contextset context = new Contextset();
    public Pushclient(){}

    public void responsewriter(UrlLinker urlresponse , ServletResponse resonsewrite) throws IllegalStateException, IOException, Exception
    {
        urlLink = urlresponse;
        if(urlresponse != null)
        {
            HttpEntity rEntity = urlresponse.getResponse().getEntity();

            InputStream content = rEntity.getContent();
            /*try {
                setcontext(rEntity);
            } catch (Exception e1) {
                System.out.println("context");
            }*/

            try 
            {
                if(!"binary".equals(context.getContext(urlLink)))
                {
                    Document document = null;
                    document = Jsoup.parse(content, "UTF-8", urlLink.getURL());
                    System.out.println(urlLink.getContext());

                    if(context.getContext(urlLink).equals("text"))
                    {
                        for (Element element : document.select("[href]")) {
                            element.attr("href", element.attr("href"));
                        }

                        for (Element element : document.select("[src]")) {
                            element.attr("src", element.attr("src"));
                        }
                    }

                    ServletResponse respond = resonsewrite;
                    respond.setContentType("text/html;charset=UTF-8");
                    String test = respond.getCharacterEncoding();
                    System.out.println(test);
                    respond.getWriter().write(document.html());
                    respond.getWriter().flush();
                }
                else
                {
                    ServletResponse respond =  resonsewrite;
                    //respond.setContentType(url.getMimeType(urlLink.getPathInfo()));
                    OutputStream output = respond.getOutputStream();
                    byte[] buffer = new byte[8192];

                    for (int length = 0; (length = rEntity.getContent().read(buffer)) > 0;) {
                        output.write(buffer, 0, length);
                    }
                }
            }
            catch (IOException | IllegalStateException e)
            {
                System.out.println("line");
            }
        }
        else
        {
            return;
        }
    }
    
    private class Contextset
    {
        UrlLinker context = null;

        public Contextset() {}

        public String getContext(UrlLinker urlFrag) throws Exception
        {
            context = urlFrag;

            if(isText())
                if(isJava())
                    return "js";
                else
                    return "text";
            else
                return "binary";
        }

        private boolean isText()
        {			
            return !containsIgnoreCase(context.getNURL(), ".html");
        }

        private boolean isJava()
        {
                return endsWithIgnoreCase(substringBefore(urlLink.getNURL(), "?"), ".js");
        }
    }
}
