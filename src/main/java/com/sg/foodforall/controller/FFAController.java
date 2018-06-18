/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.sg.foodforall.models.Allergy;
import com.sg.foodforall.models.DinnerGuest;
import com.sg.foodforall.models.FoodAversion;
import com.sg.foodforall.models.Intolerance;
import com.sg.foodforall.service.AllergyServiceImpl;
import com.sg.foodforall.service.DinnerGuestServiceImpl;
import com.sg.foodforall.service.FoodAversionServiceImpl;
import com.sg.foodforall.service.IntoleranceServiceImpl;
import com.sg.foodforall.service.Result;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author mrsch
 */

@RestController
@RequestMapping("/foodforall")
public class FFAController {
 
    
    @Autowired
    private AllergyServiceImpl allergyService;
    
    @Autowired
    private FoodAversionServiceImpl faService;
    
    @Autowired
    private IntoleranceServiceImpl iService;
    
    @Autowired
    private DinnerGuestServiceImpl dgService;
    
    @GetMapping("/allergies")
    public List<Allergy> getAllAllergies() {
        return allergyService.all().getPayload();
    }
    
    @GetMapping("/intolerances")
    public List<Intolerance> getAllIntolerances() {
        return iService.all().getPayload();
    }
    
    @GetMapping("/foodAversions")
    public List<FoodAversion> getAllFoodAversions() {
        return faService.all().getPayload();
    }
    
    @PostMapping("/allergy")
    public ResponseEntity<Allergy> addAllergy(@RequestBody Allergy allergy) {
            Result<Allergy> result = allergyService.save(allergy);
            
            if (result.isSuccess()) {
                return ResponseEntity.ok(allergy);
            } else {
                return ResponseEntity.noContent().build();
            }
    }
    
    @PostMapping("/intolerance")
    public ResponseEntity<Intolerance> addIntolerance(@RequestBody Intolerance intolerance) {
            Result<Intolerance> result = iService.save(intolerance);
            
            if (result.isSuccess()) {
                return ResponseEntity.ok(intolerance);
            } else {
                return ResponseEntity.noContent().build();
            }
    }
    
    @PostMapping("/foodAversion")
    public ResponseEntity<FoodAversion> addFoodAversion(@RequestBody FoodAversion foodAversion) {
            Result<FoodAversion> result = faService.save(foodAversion);
            
            if (result.isSuccess()) {
                return ResponseEntity.ok(foodAversion);
            } else {
                return ResponseEntity.noContent().build();
            }
    }
    
    @PostMapping("/submitGuest")
    public ResponseEntity<DinnerGuest> addGuest(@RequestBody DinnerGuest dg ) {
  
            Result<DinnerGuest> result = dgService.save(dg);
            
            if (result.isSuccess()) {
                return ResponseEntity.ok(dg);
            } else {
                return ResponseEntity.noContent().build();
            }
    }
    
    @GetMapping("/guests")
    public List<DinnerGuest>  getAllGuests() {
        return dgService.getAllGuests().getPayload();
    }
}
