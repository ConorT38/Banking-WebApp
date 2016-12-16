/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.banking.users;

import com.mycompany.banking.database.Database;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;

/**
 *
 * @author Conor
 */
public class UserService {
    
    Map<Integer,User> userList = new HashMap<Integer,User>();
    Database db = new Database();
    
    public UserService() throws SQLException{
        User conor = new User(1,"Conor","datboi@gmail.com",3234);
        User stewie = new User(2,"stewie","stew@ei.com",5554);
        User johnny = new User(3,"johnny","John.s@gmail.com",8463);
        
        userList.put(1,conor);
        userList.put(2,stewie);
        userList.put(3,johnny);
             
    }
    
    
    public User getId(int id){
        User user = userList.get(id);
        System.out.println(user);
        return user;
    }
    
    public User addUser(User user) throws SQLException, NoSuchAlgorithmException{
        user.setId(userList.size()+1);
        userList.put(user.getId(),user);
        db.insertUser(user.getName(),user.getEmail(),user.getPin());
        return user;
    }
    
    public String getAll() throws SQLException{
        return db.getAll();
        
    }
    
    public User updateUser(User user){
        if(user.getId()<=0){
            return null;
        }
        userList.put(user.getId(),user);
        return user;
    }
    
    public void deleteUser(int id){
        userList.remove(id);
    }
    public int increment(){
        return userList.size()+1;
    }
    @GET
    @Produces("application/json")
    public Boolean checkLogin(@CookieParam("name") Cookie cookie){
       if(cookie==null)
            return null;
       else
            return true;
    }
    
}
