/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.foodforall.service;

import com.sg.foodforall.data.IntoleranceRepository;
import com.sg.foodforall.models.Intolerance;
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
public class IntoleranceServiceImplTest {

    private Intolerance intolerance;
    @Mock
    @Autowired
    private IntoleranceRepository repo;
    
    public IntoleranceServiceImplTest() {
        intolerance = new Intolerance();
        intolerance.setName("Fish");
    }
    

    /**
     * Test of save method, of class AllergyServiceImpl.
     */
    @Test
    public void testSaveAndFindAll() {
        repo.save(intolerance);
        List<Intolerance> intoleranceList = repo.findAll();
        assertTrue(intoleranceList.size() > 0);
    }

 
    
}
