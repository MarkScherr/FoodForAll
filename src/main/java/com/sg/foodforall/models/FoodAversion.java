/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author mrsch
 */
@Data
@Entity
public class FoodAversion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer foodAversionId;

    private String name;    
}
