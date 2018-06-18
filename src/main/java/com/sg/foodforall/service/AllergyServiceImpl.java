/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.service;

import com.sg.foodforall.data.AllergyRepository;
import com.sg.foodforall.models.Allergy;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
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
public class AllergyServiceImpl implements AllergyService {
    
    @Autowired
    private AllergyRepository repo;
    
    @Transactional
    public Result<List<Allergy>> all() {
        Result<List<Allergy>> resultList = new Result<>();
        resultList.setPayload(repo.findAll());
        List<Allergy> allergies = resultList.getPayload();
        for (Allergy allergy : allergies) {
            allergy.getIngredients().size();
        }
        return resultList;
    }
    
    @Override
    public Result<Allergy> save(Allergy allergy) {
        Result<Allergy> result = validate(allergy);
       
        if (result.isSuccess()) {
            allergy = repo.save(allergy);
            result.setPayload(allergy);
        }
            return result;
    }
    
    
    public Result<Allergy> validate(Allergy allergy) {
        Result<Allergy> result = new Result<>();
        
        Validator validator =Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Allergy>> errs = validator.validate(allergy);
        
        for (ConstraintViolation<Allergy> err : errs) {
            result.addMessage(err.getMessage());   
        }
        
        return result;
        
    }


    
}
