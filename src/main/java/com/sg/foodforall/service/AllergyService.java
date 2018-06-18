/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.service;

import com.sg.foodforall.models.Allergy;

/**
 *
 * @author mrsch
 */
public interface AllergyService {
    
    Result<Allergy> save(Allergy allergy);
    
}
