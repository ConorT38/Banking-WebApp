/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.banking.accounts;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Conor
 */
public class AccountService {
    
    Map<Integer,Account> accountList = new HashMap<Integer,Account>();
    
    public AccountService(){
        Account conor = new Account(1,"AIBKE",(float)200.21);
        Account stewie = new Account(2,"AIBD",(float)19992.04);
        Account johnny = new Account(3,"AIBTY",(float)32.60);
        
        accountList.put(1,conor);
        accountList.put(2,stewie);
        accountList.put(3,johnny);
    }
    
    public Account getId(int id){
        Account account = accountList.get(id);
        System.out.println(account);
        return account;
    }
    
    public Account addAccount(Account account){
        account.setAccount_number(accountList.size()+1);
        accountList.put(account.getAccount_number(),account);
        return account;
    }
    
    public Account updateAccount(Account account){
        if(account.getAccount_number()<=0){
            return null;
        }
        accountList.put(account.getAccount_number(),account);
        return account;
    }
    
    public Account updateBalance(Account account, double balance){
        if(account.getAccount_number()<=0){
            return null;
        }
        account = new Account(account.getAccount_number(),account.getSortcode(),(float) balance);
        accountList.put(account.getAccount_number(),account);
        return account;
    }
    
    public void deleteAccount(int id){
        accountList.remove(id);
    }
    public int increment(){
        return accountList.size()+1;
    }
}
