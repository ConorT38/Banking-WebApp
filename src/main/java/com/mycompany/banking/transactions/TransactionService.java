/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.banking.transactions;

import com.mycompany.banking.accounts.AccountService;
import com.mycompany.banking.database.Database;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Conor
 */
public class TransactionService {
    
    Map<Integer,Transaction> tranList = new HashMap<Integer,Transaction>();
    Database db = new Database();
    
    public TransactionService(){
        
    }
    
    public Transaction getId(Integer id){
        Transaction tran = tranList.get(id);
        System.out.println(tran);
        return tran;
    }
    public Transaction addTransaction(Transaction transaction) throws SQLException, NoSuchAlgorithmException{
        transaction.setAccount_number(tranList.size()+1);
        tranList.put(transaction.getAccount_number(),transaction);
        db.insertTransaction(transaction.getAccount_number(),transaction.getCard_type(),transaction.getDesc(),transaction.getBalance());
        return transaction;
    }
    
    public Transaction updateTransaction(Transaction transaction){
        if(transaction.getAccount_number()<=0){
            return null;
        }
        tranList.put(transaction.getAccount_number(),transaction);
        return transaction;
    }
    
    public void deleteTransaction(int id){
        tranList.remove(id);
    }
    public int increment(){
        return tranList.size()+1;
    }
    
    
}
