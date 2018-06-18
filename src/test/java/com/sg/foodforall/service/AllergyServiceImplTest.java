/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.service;

import com.sg.foodforall.data.AllergyRepository;
import com.sg.foodforall.models.Allergy;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mrsch
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class AllergyServiceImplTest {
    private Allergy allergy;
    @Mock
    @Autowired
    private AllergyRepository repo;
    
    public AllergyServiceImplTest() {
        allergy = new Allergy();
        allergy.setName("Fish");
    }
    

    /**
     * Test of save method, of class AllergyServiceImpl.
     */
    @Test
    public void testSaveAndFindAll() {
        repo.save(allergy);
        List<Allergy> allergyList = repo.findAll();
        assertTrue(allergyList.size() > 0);
    }

 
    
}
