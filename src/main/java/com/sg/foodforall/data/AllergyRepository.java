/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.data;

import com.sg.foodforall.models.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mrsch
 */
@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Integer> {
    
}
