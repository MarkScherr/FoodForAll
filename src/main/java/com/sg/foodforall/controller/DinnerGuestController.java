/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.controller;

import com.sg.foodforall.models.DinnerGuest;
import com.sg.foodforall.models.DinnerGuestSearch;
import com.sg.foodforall.service.DinnerGuestServiceImpl;
import com.sg.foodforall.service.Result;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/dg/foodforall")
public class DinnerGuestController {
    
    @Autowired
    private DinnerGuestServiceImpl dgService;
    
    @PostMapping("/dinnerGuest")
    public ResponseEntity<DinnerGuest> addDinnerGuest(@RequestBody DinnerGuest dg) {
            Result<DinnerGuest> result = dgService.save(dg);
            
            if (result.isSuccess()) {
                return ResponseEntity.ok(dg);
            } else {
                return ResponseEntity.noContent().build();
            }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DinnerGuest> deleteGuest(@PathVariable("id") int id) {
        Result<DinnerGuest> result = dgService.delete(id);
        if (result.isSuccess()) {
                return ResponseEntity.ok(result.getPayload());
            } else {
                return ResponseEntity.noContent().build();
            }
    }
    
    @PostMapping("/searchGuest")
    public @ResponseBody List<DinnerGuest> searchDinnerGuest(@RequestBody DinnerGuestSearch guestSearch ) {
        return dgService.searchGuest(guestSearch.getFirstName(), guestSearch.getLastName()).getPayload();
    }
    
    @GetMapping("/edit/{id}")
    public DinnerGuest editDinnerGuest(@PathVariable("id") int id) {
        return dgService.editDinnerGuest(id);
    }
    
    @PostMapping("/updateGuest")
    public ResponseEntity<DinnerGuest> updateGuest(@RequestBody DinnerGuest dg) {
        Result<DinnerGuest> result = dgService.save(dg);
        if (result.isSuccess()) {
                return ResponseEntity.ok(result.getPayload());
            } else {
                return ResponseEntity.noContent().build();
            }
    }
}
