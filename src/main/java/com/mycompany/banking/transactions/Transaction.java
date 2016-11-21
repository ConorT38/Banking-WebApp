/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.banking.transactions;

import java.util.Date;

/**
 *
 * @author Conor
 */
public class Transaction {
    
    public Integer account_number;
    public String card_type;
    public Date date;
    public String desc;
    public float balance;
    
    public Transaction(Integer account_number,String card_type, Date date, String desc, float balance){
        this.account_number = account_number;
        this.card_type = card_type;
        this.date = date;
        this.desc = desc;
        this.balance = balance;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Integer getAccount_number() {
        return account_number;
    }

    public void setAccount_number(Integer account_number) {
        this.account_number = account_number;
    }
    
    
    
}
