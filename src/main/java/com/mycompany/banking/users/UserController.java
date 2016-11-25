/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.banking.users;

import com.google.gson.Gson;
import com.mycompany.banking.accounts.Account;
import com.mycompany.banking.accounts.AccountService;
import com.mycompany.banking.database.Database;
import java.text.ParseException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
@Path("/Users")
public class UserController {
    
    Database db = new Database();
   
   @GET
    @Produces("application/json")
    public Response getPayment(){
        Gson gson = new Gson();
       UserService userServ =  new UserService();
       return Response.status(200).entity(gson.toJson(userServ)).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getPayment(@PathParam("id") String id){
       Gson gson = new Gson();
       UserService userServ =  new UserService();
       
       return Response.status(200).entity(gson.toJson(userServ.getId(Integer.parseInt(id)))).build();
    }
    
    @POST
    @Path("/Signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@Context UriInfo info) throws ParseException{
        Gson gson = new Gson();
        UserService userServ = new UserService();
        String name = info.getQueryParameters().getFirst("name");
	String email = info.getQueryParameters().getFirst("email");
        String pin_o = info.getQueryParameters().getFirst("pin");
        
        Integer pin = Integer.parseInt(pin_o);
        String status = "{'user-created':";
        
        try{
            User user = new User(userServ.increment(),name,email,pin);
            status += "true,\"uri\":\"/api/Users/"+Integer.toString(userServ.increment())+"\"}";
            return Response.status(200).entity(gson.toJson(user)+","+status).build();
        }catch(Exception e){
            status +="'false', 'error':\""+e+"\"}";
            return Response.status(200).entity(gson.toJson(status)).build();
        }
    }
    
    @POST
    @Path("/Login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(@Context UriInfo info) throws ParseException{            
	String email = info.getQueryParameters().getFirst("email");
        Integer pin = Integer.parseInt(info.getQueryParameters().getFirst("pin"));
        
        try{
            db.Login(email,pin);
            return Response.status(200).entity("{'login':'success'}").build();
        }catch(Exception e){
            return Response.status(200).entity("{'login':'failed'}").build();
        }
    }
    
    @PUT
    @Path("/Add/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAccount(@Context UriInfo info,@PathParam("id") String id) throws ParseException{
        Gson gson = new Gson();
        AccountService accServ = new AccountService();
        String sortcode = info.getQueryParameters().getFirst("name");
	float curr_balance = Float.parseFloat(info.getQueryParameters().getFirst("email"));
         
        String status = "{'user-created':";
        
        try{
            Account account = new Account(Integer.parseInt(id), sortcode, curr_balance);
            accServ.addAccount(account);
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
        UserService userServ = new UserService();
        String status = "{'delete-status':";
        try{
            userServ.deleteUser(Integer.parseInt(id));
            status +="'success'}";
            return Response.status(200).entity(status).build();
        }catch(NumberFormatException e)
        {
            status +="'failed','error':'"+e+"'}";
            return Response.status(200).entity(status).build();
        }
    }
    
}
