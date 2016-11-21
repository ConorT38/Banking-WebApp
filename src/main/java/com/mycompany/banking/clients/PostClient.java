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

public class PostClient {

    public static void main(String[] args) {

        try {
            Client client = Client.create();
            int port = 8080;
            String url = "http://localhost:" + port + "/api/Users/Add";

            WebResource webResource = client.resource(url);

            String input = "{\"id\":4,\"name\":\"lorrie\",\"occupation\":\"swerv\",\"birthday\":\"Jan 12, 2033 12:02:00 AM\"}";

            ClientResponse response = webResource.type("application/json")
                    .post(ClientResponse.class, input);

            String output = response.getEntity(String.class);
            System.out.println(output);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
