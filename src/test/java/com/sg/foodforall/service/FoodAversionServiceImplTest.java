/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.service;

import com.sg.foodforall.data.FoodAversionRepository;
import com.sg.foodforall.models.FoodAversion;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class FoodAversionServiceImplTest {

    private FoodAversion foodAversion;
    @Mock
    @Autowired
    private FoodAversionRepository repo;
    
    public FoodAversionServiceImplTest() {
        foodAversion = new FoodAversion();
        foodAversion.setName("Fish");
    }
    

    /**
     * Test of save method, of class AllergyServiceImpl.
     */
    @Test
    public void testSaveAndFindAll() {
        repo.save(foodAversion);
        List<FoodAversion> foodAversionList = repo.findAll();
        assertTrue(foodAversionList.size() > 0);
    }

 
    
}
