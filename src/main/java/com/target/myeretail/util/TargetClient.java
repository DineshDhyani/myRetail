package com.target.myeretail.util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.target.myeretail.model.ProductName;

public class TargetClient {
	
    private static final String HTTPS_API_TARGET_COM_PRODUCTS_V3 = "https://api.target.com/products/v3/";
	private Client client;
    private WebTarget target;
    
	protected void init(String id, String fields, String id_type, String key ) {
        client = ClientBuilder.newClient();
        //	https://api.target.com/products/v3/13860428?fields=descriptions;id_type=TCIN 
       // 	&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz
        
        target = client.target(
                HTTPS_API_TARGET_COM_PRODUCTS_V3+id)
        		.queryParam("fields", fields)
                .queryParam("id_type", id_type)
                .queryParam("key", key)
                ;
    }

    public ProductName getProduct(String place) {
        return target
                .request(MediaType.APPLICATION_JSON)
                .get(ProductName.class);
    }
    
    public static void main(String[] args) {
		new TargetClient().init("123", "desc", "TCIN", "68767826876hsxjshxj8798");
	}
}
