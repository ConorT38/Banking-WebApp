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
import com.mycompany.banking.http.LoginService;
import com.mycompany.banking.http.SessionHandler;
import java.net.MalformedURLException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

public class Database {
    
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    Statement st2 = null;
    ResultSet rst = null;

    String url = "jdbc:mysql://localhost:3306/webapi?autoReconnect=true";
    String user = "root";
    String password = "Blizzardofozz1";
    
    SessionHandler session = new SessionHandler();
    
    
    public Database(){
               
    }
    
    
    public Response Login(String email, int pin) throws SQLException, MalformedURLException{
        st = con.createStatement();
        rs = st.executeQuery("SELECT * FROM users WHERE email ="+email+" AND pin ="+pin);
        
        int count = 0;

        while (rs.next()) {
                ++count;
        }

if (count == 1) {
    NewCookie cookie = new NewCookie("name", email);
    return Response.ok("OK").cookie(cookie).build();
}else{
    return Response.serverError().build();
}
        
    }
    
    public void Logout(){
        
    }
    
    public boolean insertUser(String name, String email, int pin)throws SQLException, NoSuchAlgorithmException{
            con = this.Connect();
            st = con.createStatement();
            LoginService logServ = new LoginService();
            rs = st.executeQuery("SELECT MAX(id) as last_id from users");
            email = logServ.hashEmail(email);
            String pin_ = logServ.hashPin(pin);
            if(rs.next()){
            String  lastid = rs.getString("last_id");
            System.out.println("INSERT INTO users VALUES("+(Integer.parseInt(lastid)+1)+",\""+name+"\",\""+email+"\",\""+pin_+"\");");
            int res = st.executeUpdate("INSERT INTO users VALUES("+(Integer.parseInt(lastid)+1)+",\""+name+"\",\""+email+"\",\""+pin_+"\");");  
            return true; 
            }else{
                return false;
            }   
    }
     public boolean insertAccount(int id,String sortcode, double balance)throws SQLException, NoSuchAlgorithmException{
            con = this.Connect();
            st = con.createStatement();
            rs = st.executeQuery("SELECT MAX(id) as last_id from account");   
            if(rs.next()){
            String  lastid = rs.getString("last_id");
            System.out.println("INSERT INTO account VALUES("+(Integer.parseInt(lastid)+1)+","+id+",\""+sortcode+"\","+balance+");");
            int res = st.executeUpdate("INSERT INTO account VALUES("+(Integer.parseInt(lastid)+1)+","+id+",\""+sortcode+"\","+balance+");");  
            return true; 
            }else{
                return false;
            }   
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
    
    public String getAll() throws SQLException{
            con = this.Connect();
            st = con.createStatement();
            st2 = con.createStatement();
            rs = st.executeQuery("SELECT * From users;");
            
            
            String response = "['users':{[";

            while(rs.next()) {
               response+="{'id':"+rs.getString("id")+",'name': '"+rs.getString("name")+"','accounts':{[";
               rst = st2.executeQuery("SELECT * from account where user = "+rs.getString("id"));
               while(rst.next()){
                   response+="{'account_number': "+rst.getString("id")+",'sortcode': '"+rst.getString("sortcode")
                                    +"','balance': "+rst.getString("balance")+"},";
                   
               }
               response = response.substring(0, response.length()-1)+"]}";
            }
               response = response.substring(0, response.length()-1)+"]},";
               
            return response.substring(0, response.length()-1)+"]}]";
            
            
    }
    
    public Connection Connect() throws SQLException{
        return DriverManager.getConnection(url, user, password);    
    }
}
