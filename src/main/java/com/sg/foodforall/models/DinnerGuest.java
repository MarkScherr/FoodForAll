    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author mrsch
 */
@Data
@Entity
public class DinnerGuest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dinnerGuestId;
    
    @NotNull
    private String firstName;
    
    @NotNull
    private String lastName;
    private String phone;
    private String emailAddress;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "DinnerGuestAllergy",
            joinColumns = @JoinColumn(name = "dinnerGuestId"),
            inverseJoinColumns = @JoinColumn(name = "allergyId")
    )
    
    private List<Allergy> allergies;
        
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "DinnerGuestIntolerance",
            joinColumns = @JoinColumn(name = "dinnerGuestId"),
            inverseJoinColumns = @JoinColumn(name = "intoleranceId")
    )
   
    private List<Intolerance> intolerances;
            
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "DinnerGuestFoodAversion",
            joinColumns = @JoinColumn(name = "dinnerGuestId"),
            inverseJoinColumns = @JoinColumn(name = "foodAversionId")
    )
  
    private List<FoodAversion> foodAversions; 
}
