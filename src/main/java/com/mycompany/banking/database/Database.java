/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking.database;

/**
 *
 * @author x13466188
 */
import com.mycompany.banking.http.SessionHandler;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    String url = "jdbc:mysql://localhost:3306/webapi";
    String user = "root";
    String password = "";
    
    SessionHandler session = new SessionHandler();
    
    
    public Database(){
               
    }
    
    
    public void Login(String email, int pin) throws SQLException, MalformedURLException{
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM users WHERE email ="+email+" AND pin ="+pin);
        
        int count = 0;

        while (rs.next()) {
                ++count;
        }

if (count == 1) {
   //session.doGet(new URL("http:/localhost:8080/api/Users/Login"), response, email);
}
        
    }
    
    public void Logout(){
        
    }
    
    public String getVersion() throws SQLException{
            con = this.Connect();
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) 
               return rs.getString(1);
            else
                return "Something went wrong";
            
    }
    
    public Connection Connect() throws SQLException{
        return DriverManager.getConnection(url, user, password);    
    }
}
