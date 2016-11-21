/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.users;

import com.google.gson.Gson;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@Path("/Users")
public class UserController {
   
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
    @Path("/Add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@Context UriInfo info) throws ParseException{
        Gson gson = new Gson();
        UserService userServ = new UserService();
        String name = info.getQueryParameters().getFirst("name");
	String occupation = info.getQueryParameters().getFirst("occupation");
        String birthday = info.getQueryParameters().getFirst("birthday");
        
        DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
        Date Birthday = df.parse(birthday);
        String status = "{'user-created':";
        
        try{
            User user = new User(userServ.increment(),name,occupation,Birthday);
            status += "true,\"uri\":\"/api/Users/"+Integer.toString(userServ.increment())+"\"}";
            return Response.status(200).entity(gson.toJson(user)+","+status).build();
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
