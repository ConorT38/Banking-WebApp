/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.banking.accounts;

import com.google.gson.Gson;
import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Conor
 */
@Path("/Account")
public class AccountController {
    
    @GET
    @Produces("application/json")
    public Response getPayment(){
        Gson gson = new Gson();
       AccountService userServ =  new AccountService();
       return Response.status(200).entity(gson.toJson(userServ)).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getPayment(@PathParam("id") String id){
       Gson gson = new Gson();
       AccountService userServ =  new AccountService();
       
       return Response.status(200).entity(gson.toJson(userServ.getId(Integer.parseInt(id)))).build();
    }
    
    @POST
    @Path("/Add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@Context UriInfo info) throws ParseException{
        Gson gson = new Gson();
        AccountService accServ = new AccountService();
        String sortcode = info.getQueryParameters().getFirst("sortcode");
	float curr_balance = Float.parseFloat(info.getQueryParameters().getFirst("balance"));
         
        String status = "{'user-created':";
        
        try{
            Account account = new Account(accServ.increment(), sortcode, curr_balance);
            status += "true,\"uri\":\"/api/Users/"+Integer.toString(accServ.increment())+"\"}";
            return Response.status(200).entity(gson.toJson(account)+","+status).build();
        }catch(Exception e){
            status +="'false', 'error':\""+e+"\"}";
            return Response.status(200).entity(gson.toJson(status)).build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") String id){
        AccountService accServ = new AccountService();
        String status = "{'delete-status':";
        try{
            accServ.deleteAccount(Integer.parseInt(id));
            status +="'success'}";
            return Response.status(200).entity(status).build();
        }catch(NumberFormatException e)
        {
            status +="'failed','error':'"+e+"'}";
            return Response.status(200).entity(status).build();
        }
    }
}
