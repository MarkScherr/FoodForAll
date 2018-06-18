/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.models;

import java.util.List;

/**
 *
 * @author mrsch
 */
public class ApiIngredient {
    private List<String> apiIngredients;

    public  List<String>getApiIngredients() {
        return apiIngredients;
    }

    public void setApiIngredients( List<String> apiIngredients) {
        this.apiIngredients = apiIngredients;
    }
}
