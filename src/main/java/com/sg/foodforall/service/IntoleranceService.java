/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.service;

import com.sg.foodforall.models.Intolerance;
import java.util.List;

/**
 *
 * @author mrsch
 */
public interface IntoleranceService {
    
    Result<List<Intolerance>> all();
    
    Result<Intolerance> save(Intolerance intolerance);
    
    Result<Intolerance> validate(Intolerance intolerance);
}
