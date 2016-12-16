/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.banking.accounts;

import com.google.gson.Gson;
import com.mycompany.banking.http.LoginService;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;

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
@Path("/Account")
public class AccountController {
    LoginService logServ = new LoginService();
    
    @GET
    @Produces("application/json")
    public Response getPayment(@CookieParam("email") Cookie cookie){
        if(logServ.checkLogin(cookie)){
        Gson gson = new Gson();
       AccountService userServ =  new AccountService();
       return Response.status(200).entity(gson.toJson(userServ)).build();
        }else{
            return Response.status(200).entity("{'access': \"denied\"}").build();
        }
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getPayment(@PathParam("id") String id,@CookieParam("email") Cookie cookie){
        if(logServ.checkLogin(cookie)){
       Gson gson = new Gson();
       AccountService userServ =  new AccountService();
       
       return Response.status(200).entity(gson.toJson(userServ.getId(Integer.parseInt(id)))).build();
        }else{
            return Response.status(200).entity("{'access': \"denied\"}").build();
        }
    }
    
    @POST
    @Path("/Add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@Context UriInfo info,@CookieParam("email") Cookie cookie) throws ParseException, SQLException, NoSuchAlgorithmException{
        if(logServ.checkLogin(cookie)){
        Gson gson = new Gson();
        AccountService accServ = new AccountService();
        String user = info.getQueryParameters().getFirst("id");
        String sortcode = info.getQueryParameters().getFirst("sortcode");
        NumberFormat nf = NumberFormat.getInstance();
	double curr_balance = nf.parse(info.getQueryParameters().getFirst("balance")).doubleValue();
         
        String status = "{'user-created':";
        
        try{
            Account account = new Account(Integer.parseInt(user), sortcode, curr_balance);
            accServ.addAccount(account);
            return Response.status(200).entity(gson.toJson(account)).build();
        }catch(NumberFormatException | NoSuchAlgorithmException | SQLException e){
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
    public Response deleteUser(@PathParam("id") String id,@CookieParam("email") Cookie cookie){
        if(logServ.checkLogin(cookie)){
        AccountService accServ = new AccountService();
        String status = "{'delete-status':";
        try{
            accServ.deleteAccount(Integer.parseInt(id));
            status +="\"success\"}";
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
