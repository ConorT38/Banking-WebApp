/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.banking.transactions;

import com.google.gson.Gson;
import com.mycompany.banking.users.UserService;
import static java.nio.file.attribute.AclEntryPermission.DELETE;
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
@Path("/Transaction")
public class TransactionController {
    
    @GET
    @Produces("application/json")
    public Response getTransaction(){
        Gson gson = new Gson();
       TransactionService tranServ =  new TransactionService();
       return Response.status(200).entity(gson.toJson(tranServ)).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getTransaction(@PathParam("id") String id){
       Gson gson = new Gson();
       TransactionService tranServ =  new TransactionService();
       
       return Response.status(200).entity(gson.toJson(tranServ.getId(Integer.parseInt(id)))).build();
    }
    
    @POST
    @Path("/Add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTransaction(@Context UriInfo info) throws ParseException{
        Gson gson = new Gson();
        TransactionService tranServ = new TransactionService();
        String name = info.getQueryParameters().getFirst("name");
	String email = info.getQueryParameters().getFirst("email");
        String pin_o = info.getQueryParameters().getFirst("pin");
        
        Integer pin = Integer.parseInt(pin_o);
        String status = "{'user-created':";
        
        try{
            Transaction transaction = new Transaction(tranServ.increment(),name,email,pin);
            status += "true,\"uri\":\"/api/Users/"+Integer.toString(tranServ.increment())+"\"}";
            return Response.status(200).entity(gson.toJson(transaction)+","+status).build();
        }catch(Exception e){
            status +="'false', 'error':\""+e+"\"}";
            return Response.status(200).entity(gson.toJson(status)).build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTransaction(@PathParam("id") String id){
        TransactionService tranServ = new TransactionService();
        String status = "{'delete-status':";
        try{
            tranServ.deleteTransaction(Integer.parseInt(id));
            status +="'success'}";
            return Response.status(200).entity(status).build();
        }catch(NumberFormatException e)
        {
            status +="'failed','error':'"+e+"'}";
            return Response.status(200).entity(status).build();
        }
    }
    
    
}
