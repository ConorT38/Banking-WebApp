/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banking.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author x13466188
 */
public class SessionHandler {
    
    public SessionHandler(){
        
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response, String username) {

    HttpSession session = request.getSession();
    session.setAttribute("UserName", username);
}
}
