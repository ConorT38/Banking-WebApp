/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.users;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Conor
 */
public class UserService {
    
    Map<Integer,User> userList = new HashMap<Integer,User>();
    
    public UserService(){
        User conor = new User(1,"Conor","Student",new Date());
        User stewie = new User(2,"stewie","Student",new Date());
        User johnny = new User(3,"johnny","Student",new Date());
        
        userList.put(1,conor);
        userList.put(2,stewie);
        userList.put(3,johnny);
      
    }
    
    
    public User getId(int id){
        User user = userList.get(id);
        System.out.println(user);
        return user;
    }
    
    public User addUser(User user){
        user.setId(userList.size()+1);
        userList.put(user.getId(),user);
        return user;
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
    
}
