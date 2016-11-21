/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.users;

/**
 *
 * @author Conor
 */

import java.util.Date;
import java.util.Map;

class User {

    private  Integer id;
    private  String name;
    private  String occupation;
    private  Date birthday;
    
    public User(Integer id, String name, String occupation, Date birthday){
         this.id = id;
         this.name = name;
         this.occupation = occupation;
         this.birthday = birthday;
    }

    Integer getID() {
        return id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    
}
