/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.service;

import com.sg.foodforall.models.DinnerGuest;
import java.util.List;

/**
 *
 * @author mrsch
 */
public interface DinnerGuestService {
    
    Result<DinnerGuest> save(DinnerGuest dg);

    Result<DinnerGuest> validate(DinnerGuest dg);
    
    Result<List<DinnerGuest>> getAllGuests();
    
    Result<List<DinnerGuest>> searchGuest(String firstName, String lastName);
    
    public Result<DinnerGuest> delete(int id);  
}
