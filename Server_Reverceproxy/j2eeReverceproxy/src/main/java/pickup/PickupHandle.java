package pickup;

import java.util.Arrays;
import HTTPclient.responseCall;
import Util.UrlLinker;

public class PickupHandle
{
	private final String [] jax_RS = {"GET","POST","PUT","DELETE"};
	
	private UrlLinker url = null;
	
	public PickupHandle(UrlLinker urlFile)
	{
            url = urlFile;
	}
	
	public UrlLinker run() 
	{	
            switch (Arrays.asList(jax_RS).indexOf(url.getMethod())) 
            {
                case 0:
                    responseCall getrequest = new GetRequest();
                    url = getrequest.clientreqeust(url);
                    break;
                case 1:
                    responseCall postrequest = new PostRequest();
                    url = postrequest.clientreqeust(url);
                    break;
                case 2:
                    responseCall putrequest = new PutRequest();
                    url = putrequest.clientreqeust(url);
                    break;
                case 3:
                    responseCall deleterequest = new DeleteRequest();
                    url = deleterequest.clientreqeust(url);
                    break;
                default:
                    break;
            }
            return url;
	}
}
