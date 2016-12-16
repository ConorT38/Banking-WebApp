/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.banking.accounts;

import com.mycompany.banking.database.Database;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Conor
 */
public class AccountService {
    
    Map<Integer,Account> accountList = new HashMap<Integer,Account>();
    Database db = new Database();
    
    public AccountService(){
        
    }
    
    public Account getId(int id){
        Account account = accountList.get(id);
        System.out.println(account);
        return account;
    }
    
    public Account addAccount(Account account) throws SQLException, NoSuchAlgorithmException{
        account.setAccount_number(accountList.size()+1);
        accountList.put(account.getAccount_number(),account);
        db.insertAccount(account.getAccount_number(),account.getSortcode(),account.getCurr_balance());
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
        account = new Account(account.getAccount_number(),account.getSortcode(),balance);
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
