/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.banking.clients;

/**
 *
 * @author Conor
 */
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class GetClient {

    public static void main(String[] args) {
        int port = 8080;
        String getUrl = "http://localhost:" + port + "/api/Users/";
        
        Client client = Client.create();
        WebResource target = client.resource(getUrl);

        ClientResponse response = target
                .queryParam("id", "2")
                .get(ClientResponse.class);
        
        System.out.println(response.getEntity(String.class));
    }
}
