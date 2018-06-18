/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.service;

import com.sg.foodforall.data.IntoleranceRepository;
import com.sg.foodforall.models.Intolerance;
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
public class IntoleranceServiceImpl implements IntoleranceService{
    
    @Autowired
    private IntoleranceRepository repo;
    
    @Override
    public Result<List<Intolerance>> all() {
        Result<List<Intolerance>> resultList = new Result<>();
        resultList.setPayload(repo.findAll());
        return resultList;
    } 
    
    @Override
    public Result<Intolerance> save(Intolerance intolerance) {
        Result<Intolerance> result = validate(intolerance);

        if (result.isSuccess()) {
            intolerance = repo.save(intolerance);
            result.setPayload(intolerance);
        }
            return result;
    }
    
    
    @Override
    public Result<Intolerance> validate(Intolerance intolerance) {
        Result<Intolerance> result = new Result<>();
        
        Validator validator =Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Intolerance>> errs = validator.validate(intolerance);
        
        for (ConstraintViolation<Intolerance> err : errs) {
            result.addMessage(err.getMessage());   
        }
        
        return result;
        
    }

    
}
