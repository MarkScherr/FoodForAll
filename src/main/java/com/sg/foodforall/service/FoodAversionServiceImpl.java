/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.service;

import com.sg.foodforall.data.FoodAversionRepository;
import com.sg.foodforall.models.FoodAversion;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mrsch
 */
@Service
public class FoodAversionServiceImpl implements FoodAversionService {
    
    @Autowired
    private FoodAversionRepository repo;
    
    @Override
    public Result<List<FoodAversion>> all() {
        Result<List<FoodAversion>> resultList = new Result<>();
        resultList.setPayload(repo.findAll());
        return resultList;
    }
    
    @Override
    public Result<FoodAversion> save(FoodAversion foodAversion) {
        Result<FoodAversion> result = validate(foodAversion);

        if (result.isSuccess()) {
            foodAversion = repo.save(foodAversion);
            result.setPayload(foodAversion);
        }
            return result;
    }
    
    
    @Override
    public Result<FoodAversion> validate(FoodAversion foodAversion) {
        Result<FoodAversion> result = new Result<>();
        
        Validator validator =Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<FoodAversion>> errs = validator.validate(foodAversion);
        
        for (ConstraintViolation<FoodAversion> err : errs) {
            result.addMessage(err.getMessage());   
        }
        
        return result;
        
    }
}
