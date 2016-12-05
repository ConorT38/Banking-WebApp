/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.banking.users;

import com.mycompany.banking.database.Database;
import java.sql.SQLException;

/**
 *
 * @author Conor
 */


class User {

    private  Integer id;
    private  String name;
    private  String email;
    private  Integer pin;
    Database db = new Database();
    
    public User(Integer id, String name, String email, Integer pin) throws SQLException{
         this.id = id;
         this.name = name;
         this.email = email;
         this.pin = pin;
                 
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


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    
    
}
