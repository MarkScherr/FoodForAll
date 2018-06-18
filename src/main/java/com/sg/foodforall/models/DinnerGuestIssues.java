/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.models;

/**
 *
 * @author mrsch
 */
public class DinnerGuestIssues {
     private String firstName; 
    
    private String lastName;
            
    private String emailAddress;
    private String phone;
    private String conflictWithRecipe;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConflictWithRecipe() {
        return conflictWithRecipe;
    }

    public void setConflictWithRecipe(String conflictWithRecipe) {
        this.conflictWithRecipe = conflictWithRecipe;
    }


}