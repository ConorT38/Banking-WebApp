/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.banking.accounts;

/**
 *
 * @author Conor
 */
public class Account {
    
    private Integer account_number;
    private String sortcode;
    private double curr_balance;
    
    public Account(Integer account_number, String sortcode,float curr_balance){
        this.account_number = account_number;
        this.sortcode = sortcode;
        this.curr_balance = curr_balance;
        
    }

    public Integer getAccount_number() {
        return account_number;
    }

    public void setAccount_number(Integer account_number) {
        this.account_number = account_number;
    }

    public String getSortcode() {
        return sortcode;
    }

    public void setSortcode(String sortcode) {
        this.sortcode = sortcode;
    }

    public float getCurr_balance() {
        return (float)curr_balance;
    }

    public void setCurr_balance(float curr_balance) {
        this.curr_balance = curr_balance;
    }
    
    
    
}
