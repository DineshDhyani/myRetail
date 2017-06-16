package com.target.myeretail.util;

import java.util.concurrent.Callable;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.myeretail.model.Product;

public class TargetClient implements Callable<Product>{
	
    private static final String HTTPS_API_TARGET_COM_PRODUCTS_V3 = "https://api.target.com/products/v3/";
    private static final String HTTP_TEST_API_TARGET_COM_PRODUCTS_V1 = "http://localhost:9090/myretail/v1/mockurl/products/";
	private Client client;
    private WebTarget target;
    
	public  TargetClient(String id, String fields, String id_type, String key ) {
        client = ClientBuilder.newClient();
        //	https://api.target.com/products/v3/13860428?fields=descriptions;id_type=TCIN 
       // 	&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz
        
        target = client.target(
               // HTTPS_API_TARGET_COM_PRODUCTS_V3+id)
        		HTTP_TEST_API_TARGET_COM_PRODUCTS_V1+id)
        		.queryParam("fields", fields)
                .queryParam("id_type", id_type)
                .queryParam("key", key)
                ;
    }

    public Product getProduct() {
      
    	String string = target
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
    	System.out.println(string);
    	ObjectMapper mapper = new ObjectMapper();
    	
    	JSONObject json;
		try {
			json = new JSONObject(string);
			JSONObject productjson= json.getJSONObject("data");
			Product product = mapper.readValue(productjson.toString(), Product.class);
			 return product;
		} catch (Exception e) {
			e.printStackTrace();
			 return null;
		}
        
    }
    
    /*public static void main(String[] args) {
    	TargetClient t= new TargetClient();
    	t.init("56789", "descriptions", "TCIN", "43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz");
    	

    		
			System.out.println(t.getProduct());
    	
	}*/

	public Product call() throws Exception {

		return getProduct();
	}
}
