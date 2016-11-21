/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.banking.transactions;

import com.mycompany.banking.accounts.Account;
import com.mycompany.banking.accounts.AccountService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Conor
 */
public class TransactionService {
    
    Map<Integer,Transaction> tranList = new HashMap<Integer,Transaction>();
    
    public TransactionService(){
        AccountService accServ = new AccountService();
        Transaction Conor = new Transaction(1,"Credit",new Date(),"Petrol",(float)-20.00);
        Transaction Jakub = new Transaction(2,"Debit",new Date(),"Food",(float)-10.20);
        Transaction Tranty = new Transaction(3,"Debit",new Date(),"Insurance",(float)-2000.00);
        
        tranList.put(1,Conor);
        accServ.updateBalance(accServ.getId(1), (float)-10.20);
        tranList.put(2,Jakub);
        tranList.put(3,Tranty);
        
    }
    
    public Transaction getId(Integer id){
        Transaction tran = tranList.get(id);
        System.out.println(tran);
        return tran;
    }
    
    
}
