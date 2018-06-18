/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.data;

import com.sg.foodforall.models.Allergy;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mrsch
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AllergyRepositoryTest {
    @Autowired
    private AllergyRepository repo;
    
    public AllergyRepositoryTest() {
    }
    
    /**
     * Test of findByAllergyId method, of class AllergyRepository.
     */
    @Test
    public void testRepo() {
        List<Allergy> allergies = repo.findAll();
        for (Allergy allergy : allergies) {
            System.out.println(allergy);     
        }
        assertTrue(allergies.size() > 0);
    }

    
}
