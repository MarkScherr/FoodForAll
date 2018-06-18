/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.controller;

import com.sg.foodforall.models.ApiIngredient;
import com.sg.foodforall.models.DinnerGuestIssues;
import com.sg.foodforall.service.DinnerGuestServiceImpl;
import com.sg.foodforall.service.Result;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mrsch
 */
@RestController
@RequestMapping("/verify")
public class RecipeVerifyController {

    @Autowired
    DinnerGuestServiceImpl dgService;
    
    @PostMapping("/verifyAPI")
    public  @ResponseBody List<DinnerGuestIssues> validateRecipe(@RequestBody ApiIngredient ingredients) {
        Result<List<DinnerGuestIssues>> result = dgService.checkForIssues(ingredients.getApiIngredients());
           if(result.isSuccess()) {
               return result.getPayload();
           }
           else {
               return null;
           }
    }
}