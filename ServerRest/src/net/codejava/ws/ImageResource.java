package net.codejava.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
@Path("/")
public class ImageResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public String recognize(ImageUrl imageUrl) {
    	String url = imageUrl.getUrl();
    	
    	//List of all objects detected in the image
		List<String> resultList = Clarifai2.recognize(url);
		
		StringBuilder itemsDetected = new StringBuilder();
		for(String item : resultList) {
			itemsDetected.append(item);
		}

    	return "<html><title>Hello</title><body>"+itemsDetected+"<body></html>";
    }
}
