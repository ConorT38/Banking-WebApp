/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.banking.transactions;

import com.google.gson.Gson;
import com.mycompany.banking.http.LoginService;
import com.mycompany.banking.users.UserService;
import static java.nio.file.attribute.AclEntryPermission.DELETE;
import java.text.ParseException;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Conor
 */
@Path("/Transaction")
public class TransactionController {
    
    LoginService logServ = new LoginService();
    
    @GET
    @Produces("application/json")
    public Response getTransaction(@CookieParam("email") Cookie cookie){
        if(logServ.checkLogin(cookie)){
        Gson gson = new Gson();
       TransactionService tranServ =  new TransactionService();
       return Response.status(200).entity(gson.toJson(tranServ)).build();
        }else{
            return Response.status(200).entity("{'access': \"denied\"}").build();
        }
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getTransaction(@PathParam("id") String id,@CookieParam("email") Cookie cookie){
        if(logServ.checkLogin(cookie)){
       Gson gson = new Gson();
       TransactionService tranServ =  new TransactionService();
       
       return Response.status(200).entity(gson.toJson(tranServ.getId(Integer.parseInt(id)))).build();
        }else{
            return Response.status(200).entity("{'access': \"denied\"}").build();
        }
    }
    
    @POST
    @Path("/Add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTransaction(@Context UriInfo info,@CookieParam("email") Cookie cookie) throws ParseException{
        if(logServ.checkLogin(cookie)){
        Gson gson = new Gson();
        TransactionService tranServ = new TransactionService();
        Integer account_number = Integer.parseInt(info.getQueryParameters().getFirst("account_number"));
        String card_type = info.getQueryParameters().getFirst("card_type");
	String desc = info.getQueryParameters().getFirst("desc");

        float balance = Float.parseFloat(info.getQueryParameters().getFirst("pin"));
        Date date = new Date();
        String status = "{'user-created':";
        
        try{
            Transaction transaction = new Transaction(account_number, card_type, date, desc, balance);
            status += "true,\"uri\":\"/api/Transaction/"+Integer.toString(tranServ.increment())+"\"}";
            return Response.status(200).entity(gson.toJson(transaction)+","+status).build();
        }catch(Exception e){
            status +="'false', 'error':\""+e+"\"}";
            return Response.status(200).entity(gson.toJson(status)).build();
        }
        }else{
            return Response.status(200).entity("{'access': \"denied\"}").build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTransaction(@PathParam("id") String id,@CookieParam("email") Cookie cookie){
        if(logServ.checkLogin(cookie)){
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
     }else{
            return Response.status(200).entity("{'access': \"denied\"}").build();
        }
    }
    
    
}
