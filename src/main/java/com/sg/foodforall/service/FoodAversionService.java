/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.service;

import com.sg.foodforall.models.FoodAversion;
import java.util.List;

/**
 *
 * @author mrsch
 */
public interface FoodAversionService {
    
    Result<List<FoodAversion>> all();
    
     public Result<FoodAversion> save(FoodAversion foodAversion);
     
     public Result<FoodAversion> validate(FoodAversion foodAversion);
}
